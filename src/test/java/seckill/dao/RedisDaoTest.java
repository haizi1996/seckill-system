package seckill.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import seckill.BaseTest;
import seckill.dao.cache.RedisDao;
import seckill.pojo.Seckill;


public class RedisDaoTest extends BaseTest{
	
	private long id = 1001;

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;
	@Test
	public void testGetSeckill() throws Exception {
		
		Seckill seckill = redisDao.getSeckill(id);
		if(seckill == null) {
			seckill = seckillDao.queryBySeckillId(id);
			System.out.println(redisDao.putSeckill(seckill));
			seckill = redisDao.getSeckill(id);
			System.out.println(seckill);
		}
		
		
		
		
	}

}
