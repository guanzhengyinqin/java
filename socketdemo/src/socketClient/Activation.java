package socketClient;


import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import java.text.ParseException;

public class Activation {
    private final static String DES = "DES";
    private static String key = "123456654321";

    
    public static void main(String[] args) throws Exception {
        System.err.println("xxxx:"+createKey("lGOYyYxjUXxv78Hcf27ckg=="));
    }

    /**
     * 校验机器码的格式是否正确
     *
     * @param jiqima
     * @return false为格式错误
     */
    public static boolean formatCheckout(String jiqima) {
        if (jiqima == null
                || jiqima.length() != 24) return false;
        else return true;
    }

    /**
     * 传入机器码返回激活码
     *
     * @param jiqima 机器码
     * @return 激活码
     * @throws Exception
     * @throws IOException
     */
    public static String createKey(String jiqima) throws IOException, Exception {
        if (!formatCheckout(jiqima)) return null;

        String key = null;
        try {
            String GG = getFetchSynchronizationTime();
//        	String GG = "2017-08-08 11:02:15";
            System.err.println("xxxx:"+GG);
            
            String dd = getTime(GG);
            System.err.println("xxxx:"+dd);
            int ds = Integer.parseInt(dd);

            String num31 = Integer.toString(ds, 32);
            System.err.println("xxxx:"+num31);
            
            System.err.println("xxxx:"+jiqima);
            String DD = decrypt(jiqima);
            
            
            
            key = encrypt(DD + num31);
            System.out.println("DD:"+DD);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    
    //加密
    public static String encrypt(String datay) throws Exception{
        return encrypt(datay, key);
    }
    //解密
    public static String decrypt(String data) throws IOException,
            Exception {
        return decrypt(data, key);
    }
    
    

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
    
    
    /**
     * 获取同步时间
     *
     * @return
     */
    public static String getFetchSynchronizationTime() {

        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String FetchSynchronizationTime = dff.format(new Date());

        return FetchSynchronizationTime;
    }

    //将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;

    }
}
