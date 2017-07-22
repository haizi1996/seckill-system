package seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import seckill.pojo.Seckill;

public interface SeckillDao {

	/**
	 * 根据开始索引查询秒杀商品列表
	 * 
	 * @param index
	 * @param limit
	 * @return
	 */
	public List<Seckill> queryAll(@Param("index") int index,@Param("limit") int limit);
	/**
	 * 根据seckillId查找记录的详情
	 * @param seckillId
	 * @return
	 */
	public Seckill queryBySeckillId(@Param("seckillId")long seckillId); 
	
	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return 如果影响行数等于>1，表示更新的记录行数
	 */
	public int reduceNumber(@Param("seckillId")long seckillId ,@Param("killTime") Date killTime);
	/**
	 * 调用存储过程
	 * @param paramMap
	 */
	
	void killByProcedure(Map<String,Object>paramMap);
}
