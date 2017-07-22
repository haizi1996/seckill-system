package seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import seckill.dao.SeckillDao;
import seckill.dao.SuccessKilledDao;
import seckill.dao.cache.RedisDao;
import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.enums.SeckillStateEnum;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;
import seckill.exception.SeckillException;
import seckill.pojo.Seckill;
import seckill.pojo.SuccessKilled;
import seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

	@Autowired
	private SeckillDao seckillDao;
	 @Autowired
	 private RedisDao redisDao;
	@Autowired
	private SuccessKilledDao successKilledDao;

	// 用于混淆MD5
	private final String slat = "skdfjksjdf7787%^%^%^FSKJFK*(&&%^%&^8DF8^%^^*7hFJDHFJ";

	@Override
	public List<Seckill> getSeckillList() {

		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill selectSeckillDetailById(long seckillId) {
		return seckillDao.queryBySeckillId(seckillId);
	}

	@SuppressWarnings("unused")
	@Override
	public Exposer exportSeckillUrl(Long seckillId) {
		
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill == null) {
			seckill = seckillDao.queryBySeckillId(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			} else {
				redisDao.putSeckill(seckill);
			}
		}
		if(seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		
		if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}else {
			return new Exposer(true, getMD5(seckillId), seckillId);
		}
	}

	public String getMD5(Long seckillId) {
		//TODO
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	@Transactional
	@Override
	public SeckillExecution executeSeckill(Long seckillId, Long phone, String md5) {
		try {

			if (md5 == null || !md5.equals(getMD5(seckillId))) {
				throw new RuntimeException("seckill data rewrite");
			}
			// 执行秒杀逻辑==>减库存，记录秒杀明细
			int rows = seckillDao.reduceNumber(seckillId, new Date());
			if (rows <= 0) {
				// 没有更新记录，可能是秒杀商品售罄或者秒杀已结束，统一抛出一个异常
				throw new SeckillCloseException("秒杀以结束");
			} else {
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, phone);
				if (insertCount <= 0) {
					// 重复秒杀
					throw new RepeatKillException("重复秒杀");
				} else {
					SuccessKilled sucessKilled = successKilledDao.queryByIdWithSeckill(seckillId, phone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, sucessKilled);
				}
			}
		}catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			// 所有编译期异常转化为运行期异常
			throw new SeckillException("seckilll inner error :" + e.getMessage());
		}
	}

	@Override
	public SeckillExecution executeSeckillProcedure(Long seckillId, Long phone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new RuntimeException("seckill data rewrite");
		}
		Date killTime = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seckillId", seckillId);
		map.put("phone", phone);
		map.put("killTime", killTime);
		map.put("result", null);
		try {
			seckillDao.killByProcedure(map);
			int result = MapUtils.getInteger(map, "result", -2);
			if (result == 1) {
				SuccessKilled sk = successKilledDao.queryByIdWithSeckill(seckillId, phone);
				return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, sk);
			}else {
				return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
		}
		
	}

}
