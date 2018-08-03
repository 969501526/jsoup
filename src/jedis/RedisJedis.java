package jedis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

class RedisJedis {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	public static Jedis getjedis() {
		//设置连接池的获取对象
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		//创建连接池
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			//从连接池中获取连接
			jedis = jedisPool.getResource();
			return jedis;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,报错原因："+e.getMessage());
			return null;
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
			if(jedisPool!=null) {
				jedisPool.close();
			}
		}
	}
	
	@Test
	public void demo() {
		Jedis jedis = getjedis();
		if(jedis==null) {
			System.out.println("...");
		}
		jedis.set("name", "ccc");
		String name = jedis.get("name");
		System.out.println(name);
	}
}
