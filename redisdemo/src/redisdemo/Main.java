package redisdemo;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import redis.clients.jedis.Jedis;

public class Main {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//连接本地的 Redis 服务
        Jedis jedis = RedisUtil.getJedis();
        if(jedis.isConnected()){
        	System.out.println("连接成功");
        }else{
        	System.out.println("链接失败");
        }
       
       //jedis.set("str1", "么么哒");
       System.out.println(jedis.get("str1"));
       
       
       /*
       Set set = jedis.keys("*-3".getBytes());
       System.out.println(set.size());
       Iterator<Object> it = set.iterator();
       Object o = null;
       while(it.hasNext()){
    	   byte[] data = (byte[]) it.next();
    	   o = jedis.get(data);
    	   for(byte b:data){
    		   System.out.print(b+"\t");
    	   }
    	   String a = (String) SerializeUtil.unserialize(data);
    	   //jedis.del(data);
    	   System.out.println(a);
       }
       
       System.out.println(o.toString());
       Class clazz = o.getClass();
       Method[] meArr = clazz.getDeclaredMethods();
       System.out.println(meArr.length);
       for(Method m:meArr){
    	   System.out.println(m.getName());
       }
       System.out.println(clazz.getDeclaredFields().length);
       System.out.println(clazz.getSimpleName());
       
       byte[] data2 = (byte[]) o;
       
       for(byte b:data2){
    	   System.out.print(b+"\t");
       }
       */
       
       //jedis.
       //long a = jedis.del("*-3".getBytes());
       //System.out.println(a);
       
       
        //设置 redis 字符串数据
       // jedis.set("runoobkey", "www.runoob2.com");
        // 获取存储的数据并输出
       // System.out.println("key'runoobkey'redis 存储的字符串为: "+ jedis.get("runoobkey"));
       // System.out.println(jedis.dbSize());
        
		//byte[] data = SerializeUtil.serialize("2106837620:2458455660:com.baxcall.visitor.mapper.IntervieweeMapper.getAllInterviewees:0:2147483647:SELECT id,account,intervieweeNo,password,name,phone,departmentId,organizationId,site,jurisdiction,del FROM tb_interviewee WHERE del = 1 LIMIT ?,?:0:6    value=[com.baxcall.visitor.bean.Interviewee@32aaa169, com.baxcall.visitor.bean.Interviewee@604b0e7b]".toString());
		//System.out.println(data);
		//byte[] data = jedis.get(SerializeUtil.serialize("2106837620:2458455660:com.baxcall.visitor.mapper.IntervieweeMapper.getAllInterviewees:0:2147483647:SELECT id,account,intervieweeNo,password,name,phone,departmentId,organizationId,site,jurisdiction,del FROM tb_interviewee WHERE del = 1 LIMIT ?,?:0:6"));
		
        //System.out.println(jedis.flushDB());
        //System.out.println(jedis.del(SerializeUtil.serialize("131028247:2093129637:com.baxcall.visitor.mapper.IntervieweeMapper.login:0:2147483647:SELECT id,account,intervieweeNo,password,name,phone,departmentId,organizationId,site,jurisdiction,del FROM tb_interviewee WHERE account = ? AND password = ? AND del = 1:18938671143:123456")));
		/*
		Object obj = SerializeUtil.unserialize(data);
		System.out.println(obj);
		*/
		/*
		String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
        uuid = uuid.replace("-", "");               //因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可  
        System.out.println(uuid);
        System.out.println(uuid.length());
        */
	}
	
	

}
