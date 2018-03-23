/** 
 * 不调用api,自编写算法生成随机数 
 * 找到了两个算法, 第一个很简单, 但可惜不是随机的, 第二个是典型的伪随机数算法,  
 * 可惜要用到2的几百万次方这样巨大的整数, 真痛苦 要是有UNIX上计算密码的源代码就好了' 
 * 第一种做法: 
 * f(k) = (k*F(N-1)) mod F(N)  
 * 其中k是一个序列号, 就是要取的那个数的顺序号 
 * F(N)是这样一个序列 F(0) = 0, F(1) = 1, F(N+2) = F(N+1)+F(N) (for N>=0) 
 * 第二种做法 
 * V = ( ( V * 2 ) + B .xor. B ... )(Mod 2^n) 
 * V是要取的随机数, B是个种子, n是随机数的最大个数 
 * 效果肯定是不错啦, 因为用不到很大的表 
 * 至于应用是这样的, 比如, 你要给每个用户在注册的时候一个ID但有不希望用户在看到自己的ID的时候能知道其他用户的ID,  
 * 如果用SEQUENCE来生成ID的话, 一个用户只要把自己的ID减1就能得到其它用户的ID了. 所以要用随机数来做ID, 这样用户很难猜到其他用户的ID了. 
 *  
 * 当然主要的问题是, 随机数可能重复. 因此希望使用一个随机数做种子用它来确定一组"无规律"的自然数序列,  
 * 并且在这个序列中不会出现重复的自然数. 在这里使用的方法生成的序列并不是没有规律的, 只不过这个轨律很难被发现就是了.  
 * Xn+1 = (aXn + b) mod c (其中, abc通常是质数)是一种被广泛使用的最简单的随机数发生算法, 有研究表表明这个算法生成的随机数基本上符合统计规律, 
 *  JAVA, BORLAND C等用的都是这个方法, 一般只要保证第一个种子是真正的随机数就行了,  
 *  下面来说一下重复的问题, 
 *  上述方法会有可能出现重复, 因为当(aXn + b)有可能是同样的数或者说余数相同的数, 因此要想不重复就得变形 
 *  偶想到的方法是 
 *  Xn=(a*n + b) mod c n是一个在1到c之间的整数, a*n + b就是一个线性公式了, 且若n不同则a*n + b也不同, 它们除上质数c得到的余数也肯定不同,  
 *  因为 若不考虑a和b而只有n的时候, 每次的结果都是n,而线性公式, 只不过移动了这条直线的位置和斜率而已, 每个结果仍然不会相同的,  
 *  为了增加不可预计性, 偶又为上面那个公式设计了, 随机数种子, 于是就变成了这个样子F(N)=(随机数*(N+随机数))MOD 一个质数  
 *  这样就能够产生 1到选定质数之间的一个"无规律"的自然数序列了, 只要改变随机数就能改变序列的次序 
 *  在应用的时候, 要把随机数种子和最后用到的序列号保存到一个表里, 每此使用的时候取出来算好, 再把序列号更新一下就可以了 
 *  具体地说, 就是可以建一个表来保存每个序列的随机数种子, 然后再为这个序列建一个SEQUENCE就行了 
 *  然后就 
 *  SELECT MOD(序列控制表.随机数*(SEQ.NEXTVAL+序列控制表.随机数)),序列控制表.质数) 
 *  FROM 序列控制表 
 *  WHERE 序列控制表.序列ID=XX 
 *  就OK了 
 *  注意 序列控制表.质数 决定了序列的范围 
 *  还有一种类型的，就是“真随机”， 
 *  大概是根据随机按键的键位和鼠标在CRT上任意的“随机”位置以及当时的系统时间（或相对间隔，一般至少毫秒级） 
 *  来适应某种较复杂的算法来产生的。...其实，可以产生随机效应的自变量的确是很多的，就看你怎么用好 
 *  相关书籍：系统论和混沌学：高等数学中的分形理论和物理热学中的耗散结构理论 
 *   
 *  我在这里用java写第二变型的线性算法，首先取一个基数base = 256.0,以及两个常数a=17.0和b=139.0， 
 *  这里基数base一般取2的整数倍，记住是2的整数倍，并且常数a,b可以根据经验随意来取，可以按照如下的递推算法逐个得到[0,1] 
 *  之间的随机数 
 *  ri = mod(a*ri-1 + b,base) 
 *  pi = ri/base 
 *  其中i =1,2,...而pi便是递推得到的第i个随机数。 
 *  下面写的算法关键亮点： 
 *  1）首先这里取模运算时针对浮点数的，而c语言中的取模运算不能应用于浮点数据的操作。这样需要自己写取模程序 
 *  2）ri是随着递推而每次更新的，因此我们的参数的按地址传值的 
 *  @author Özil ισνΞ 
 */  
package random;  
  
import java.util.Random;  
  
public class SeftRandom {  
      
    /** 
     *  
     * @param r 将变量的地址传进函数中，以便每次调用后更新随机种子的值，否则将得到完全一样的数据从而失去随机性 
     * @return 
     */  
    static double seftRandom(double[] r){
        double base,u,v,p,temp1,temp2,temp3;  
        //基数  
        base = 256.0;  
        //两个常数 uv;  
        u = 17.0;  
        v = 139.0;  
        //计算总值  
        temp1 = u*(r[0])+v;  
        //计算商  
        temp2 = (int)(temp1/base);  
        //计算余数,1到base的余数  
        temp3 = temp1 - temp2*base;  
        //更新随机种子，为下一次使用  
        r[0] = temp3;  
        //随机数赋值 ，获取[0,1]的随机数  
        p = r[0]/base;  
        return p;  
    }  
      
    public static void main(String[] args){  
        /**基本版本**/  
        int i;  
        double[] r = {5.0};  
        System.out.printf("产生10个[0,1]之间的随机数：\n");  
        //循环调用  
        for(i = 0;i<10;i++){  
            System.out.printf("%10.5f\n",seftRandom(r));  
        }  
        System.out.println();  
        /**改良版本**/  
        //比如我们可以m+(n-m)*seftRandom(r)获取[m,n]之间随机浮点数  
        double m,n;  
        m = 10.0;  
        n = 20.0;  
        System.out.println("产生10个[10.0,20,0]之间的浮点随机数：");  
        for(i = 0;i<10;i++){  
            System.out.printf("%10.5f\n",m+(n-m)*seftRandom(r));  
        }  
          
        /**[m,n]之间均匀分布的随机整数算法**/  
        int cout = 0;  
        int m1,n1;  
        m1 = 100;  
        n1 = 200;  
        System.out.println("产生10个[100,200]之间的随机整数");  
        for(i = 0;i<10;i++){  
            System.out.printf("%5d",m1+(int)((n1-m1)*seftRandom(r)));  
            cout++;  
            if(cout%5 ==0){  
                System.out.println();  
            }  
        }  
        System.out.println();  
          
        /**随机生成ab,随机数更逼真,每次都不同有木有，哈哈**/  
        // 获得系统时间，作为生成随机数的种子  
         Long seed = System.currentTimeMillis();  
         Random r1 = new Random(seed);  
         float i2;  
         float i3;  
         System.out.println("产生10个[100,200]之间的随机整数");  
            for(i = 0;i<10;i++){  
                //调用Random随机产生[0-99)的浮点数赋予数组array  
                i2 = r1.nextFloat()*100;  
                //Math.random调用随机产生[0.0-100)的浮点数赋予数组array  
                i3 = (float)Math.random()*100;  
                System.out.printf("%5d",m1+(int)((n1-m1)*seftRandom(r,i2,i3)));  
                cout++;
                if(cout%5 ==0){
                    System.out.println();  
                }  
            }  
    }  
      
    /** 
     * 重写seftRandom方法，使随机数更逼真 
     * @param r 将变量的地址传进函数中，以便每次调用后更新随机种子的值，否则将得到完全一样的数据从而失去随机性 
     * @param u 随机数 
     * @param v 随机数 
     * @return  返回逼真的随机数 
     */  
    static double seftRandom(double[] r,double u,double v){
        double base,p,temp1,temp2,temp3;
        //基数
        base = 256.0;
        //计算总值
        temp1 = u*(r[0])+v;
        //计算商
        temp2 = (int)(temp1/base);
        //计算余数,1到base的余数
        temp3 = temp1 - temp2*base;
        //更新随机种子，为下一次使用
        r[0] = temp3;
        //随机数赋值 ，获取[0,1]的随机数
        p = r[0]/base;
        return p;
    }  
}  