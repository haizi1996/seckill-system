package seckill.dao.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import seckill.pojo.Seckill;
import seckill.util.JsonUtil;
import seckill.util.ProtostuffUtil;

@SuppressWarnings("unused")
public class RedisDao {

	private final JedisPool jedisPool;

	public RedisDao(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}

	public Seckill getSeckill(Long seckillId) {
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckillId;
			byte[] bytes = jedis.get(key.getBytes());
			if (bytes != null) {
				//Seckill seckill = JsonUtil.fromBytes(bytes, Seckill.class);
				Seckill seckill = ProtostuffUtil.deserialize(bytes, Seckill.class);
				return seckill;
			}
		} finally {
			jedis.close();
		}
		return null;
	}

	public String putSeckill(Seckill seckill) {
		// set Object(Seckill) -> 序列号 -> byte[]
		Jedis jedis = jedisPool.getResource();
		try {
			String key = "seckill:" + seckill.getSeckillId();
		//	byte[] value = JsonUtil.toBytes(seckill);
			byte[] value = ProtostuffUtil.serialize(seckill);	 
			// 缓存超时 60*60
			int timeOut = 60 * 60;
			String res = jedis.setex(key.getBytes(), timeOut, value);
			return res;
		} finally {
			jedis.close();
		}
	}

}
