package jedis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

class Jediis {

	@Test
	public void test() {
		//设置ip和接口
		Jedis jedis = new Jedis("172.16.28.96",6379);
		//保存数据
		jedis.set("name", "ddd");
		//获取数据
		String name = jedis.get("name");
		System.out.println(name);
		//关闭资源
		jedis.close();
	}

	
	@Test
	public void demo() {
		//设置连接池获取对象
		JedisPoolConfig config = new JedisPoolConfig();
		//设置最大连接数
		config.setMaxTotal(30);
		//设置最大空闲连接数
		config.setMaxIdle(10);
		//获得连接池
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			//通过连接池获得连接
			jedis=jedisPool.getResource();
			jedis.set("name", "张三");
			String name = jedis.get("name");
			System.out.println(name);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
		}
	}
	
	@Test
	public void demo1() {
		//设置连接池获取对象
		JedisPoolConfig config = new JedisPoolConfig();
		//设置连接池最大连接数
		config.setMaxTotal(2);
		//设置连接池最大空闲连接数
		config.setMaxIdle(1);
		//设置连接池
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		Jedis jedis2 = null;
		Jedis jedis3 = null;
		try {
			//从连接池获取连接
			jedis = jedisPool.getResource();
			jedis.set("name", "zhangsan");
			String name=jedis.get("name");
			System.out.println(name);
			jedis2 = jedisPool.getResource();
			jedis2.set("age","12");
			String age = jedis2.get("age");
			System.out.println(age);
			jedis3 = jedisPool.getResource();
			jedis3.set("sex","2");
			String sex = jedis2.get("sex");
			System.out.println(sex);
		} catch (Exception e) {
			// TODO: handle excep1tion
			System.out.println("error,错误原因:"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
			if(jedis2!=null) {
				jedis2.close();
			}
			if(jedis3!=null) {
				jedis3.close();
			}

		}
	}
	
	@Test
	public void demo3() {
		//设置连接池获取对象
		JedisPoolConfig config = new JedisPoolConfig();
		//设置最大连接数
		config.setMaxTotal(30);
		//设置最大空闲连接数
		config.setMaxIdle(10);
		//获得连接池
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			//通过连接池获得连接
			jedis=jedisPool.getResource();
			Long number = jedis.append("name", "123");
			System.out.println(number);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
		}
	}
	
	@Test
	public void demo4() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("username", "admin");
			map.put("password", "admin");
			map.put("age", "21");
			map.put("sex", "nan");
			map.put("name", "zhangsan");
			jedis = jedisPool.getResource();
			jedis.hmset("myha", map);
			Long i=jedis.del("myhash");
			if(i>0) {
				System.out.println("delete");
			}
			} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
		}
		
	}
	
	@Test
	public void demo5() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.linsert("mylist",LIST_POSITION.AFTER, "b", "123");
			jedis.del("mylist");
			} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
		}
		
	}
	
	@Test
	public void demo6() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String[] str = {"a","2","3"};
			//jedis.sadd("myset2", str);
//			jedis.spop("myset");随机删除一个元素
			//jedis.sdiff("myset","myset2");
			jedis.sunion("myset","myset2");
			} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
		}
	}

	@Test
	public void demo7() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(config,"172.16.28.96",6379);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Map<String, Double> map = new HashMap<>();
			map.put("name", 1d);
			map.put("age", 20d);
//			jedis.zadd("myzset",map);
			jedis.del("myzset");
			} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error,"+e.getMessage());
		}finally {
			if(jedisPool!=null) {
				jedisPool.close();
			}
			if(jedis!=null) {
				jedis.close();
			}
		}
	}
}
