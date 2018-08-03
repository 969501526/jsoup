package jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//设置ip和接口
		Jedis jedis = new Jedis("172.16.28.96",6379);
		//保存数据
		jedis.del("name");
		//获取数据
		String name = jedis.get("name");
		System.out.println(name);
		//关闭资源
		jedis.close();
	}

}
