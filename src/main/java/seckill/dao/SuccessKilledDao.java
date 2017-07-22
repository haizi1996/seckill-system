package seckill.dao;

import org.apache.ibatis.annotations.Param;

import seckill.pojo.SuccessKilled;


public interface SuccessKilledDao {
	
	
	/**
	 * 插入成功购买的明细
	 * @param seckillId
	 * @param usePhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId")long seckillId , @Param("userPhone") long userPhone );

	/**
	 * 根据id查询SuccessKilled并携带秒杀产品对象实体
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
