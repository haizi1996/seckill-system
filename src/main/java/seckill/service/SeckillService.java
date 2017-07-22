package seckill.service;

import java.util.List;

import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;
import seckill.exception.SeckillException;
import seckill.pojo.Seckill;

public interface SeckillService {
	/**
	 * 查询所有秒杀记录
	 * 
	 * @return
	 */

	List<Seckill> getSeckillList();
	/**
	 * 根据seckillId查找秒杀记录的详情
	 * @param seckillId
	 * @return
	 */
	Seckill selectSeckillDetailById(long seckillId);
	
	/**
	 * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
	 * @param seckillId
	 * @return
	 */
	Exposer exportSeckillUrl(Long seckillId);
	/**
	 * 执行秒杀操作,需要传过来一个md5加密的字符串，和系统生成的字符串是否一致。若不一致，则url修改了
	 * @param seckillId
	 * @param phone
	 * @param md5
	 * @return
	 * @throws SeckillException
	 * @throws RepeatKillException
	 * @throws SeckillCloseException
	 */
	SeckillExecution executeSeckill(Long seckillId, Long phone, String md5)throws SeckillException, RepeatKillException, SeckillCloseException;
	
	/**
	 * 通过调用存储过程执行秒杀操作
	 * @param seckillId
	 * @param phone
	 * @param md5
	 * @return
	 * @throws SeckillException
	 * @throws RepeatKillException
	 * @throws SeckillCloseException
	 */
	SeckillExecution executeSeckillProcedure(Long seckillId, Long phone, String md5)throws SeckillException, RepeatKillException, SeckillCloseException;
		
}
