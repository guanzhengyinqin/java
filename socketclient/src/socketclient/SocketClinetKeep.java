package socketclient;

//import com.utils.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketClinetKeep {
    private static final long REPTITION_CONNECT_TIME = 10000;
    private static final int CONNECT_OUT_TIME = 15000;
    private String ip;
    private int port;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ExecutorService executorService;
    private OnSocketReadListener onSocketReadListener;
    private OnSocketConnectListener onSocketConnectListener;
    private ConnectRunnable connectRunnable;

    public SocketClinetKeep() {
        executorService = Executors.newFixedThreadPool(4);
    }

    public void setOnSocketReadListener(OnSocketReadListener onSocketReadListener) {
        this.onSocketReadListener = onSocketReadListener;
    }


    public void startClinet(){
        startClinet(ip, port);
    }

    public void startClinet(String ip, int port, OnSocketConnectListener onSocketConnectListener){
        this.onSocketConnectListener = onSocketConnectListener;
        startClinet(ip, port);
    }

    public void startClinet(String ip, int port){
        this.ip = ip;
        this.port = port;

        connectRunnable = new ConnectRunnable();
        executorService.execute(connectRunnable);
    }

    /**
     * 连接任务
     */
    private class ConnectRunnable implements Runnable{

        private boolean connectLock = true;
        @Override
        public void run() {
            try {
                //Util.log("开始连接网络");
            	System.out.println("开始连接网络");

                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), CONNECT_OUT_TIME);
                //标记是否要连接
                if(!connectLock){
                    //Util.log("不需要连接1");
                    System.out.println("不需要连接1");
                    return ;
                }
                SocketClinetKeep.this.socket = socket;

                printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //开启心跳 判断什么时候断开连接
                startHeartbeat();

                //开启读取信息任务
                executorService.execute(readRunnable);


                //Util.log("连接上网络了");
                System.out.println("连接上网络了");
                if(onSocketConnectListener != null){
                    onSocketConnectListener.onConnectSu();
                }

            } catch (IOException e) {
                e.printStackTrace();
                if(!connectLock){
                    //Util.log("不需要连接2");
                    System.out.println("不需要连接2");
                    return ;
                }
                //Util.log("连接失败,重新连接");
                System.out.println("连接失败,重新连接");
                sleep(REPTITION_CONNECT_TIME);
                run();
            }
        }
        public void stop(){
            connectLock = false;
        }
    }

    /**
     * 停止连接
     */
    public void stopClinet(){

        //停止心跳
        synchronized (SocketClinetKeep.class){
            heartbeatRunnable.stop();
        }

        //断开socket连接
        freeSocket();

        if(connectRunnable != null){
            connectRunnable.stop();
        }

        //Util.log("断开socket连接成功！");
        System.out.println("断开socket连接成功！");

    }

    /**
     * 读取信息任务
     */
    private Runnable readRunnable = new Runnable() {
        @Override
        public void run() {
            String content = null;
            while ((content = readFromClient()) != null) {
                //Util.log("收到服务端发来数据:"+content);
                System.out.println("收到服务端发来数据:"+content);
                if (onSocketReadListener != null) {
                    onSocketReadListener.onSocketRead(content);
                }
            }
        }

        private String readFromClient() {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public void sendData(String data){
        if(printWriter != null){
            printWriter.print(data);
            printWriter.flush();
        }
    }

    /**
     * 开启心跳 判断什么时候断开连接
     */
    private void startHeartbeat(){
        executorService.execute(heartbeatRunnable);
    }

    HeartbeatRunnable heartbeatRunnable = new HeartbeatRunnable();

    class HeartbeatRunnable implements Runnable{
        private Thread thread;
        private boolean heartbeatLock = false;
        @Override
        public void run() {
            thread = Thread.currentThread();
            heartbeatLock = true;
            while(heartbeatLock){
                sleep(REPTITION_CONNECT_TIME);
                synchronized (SocketClinetKeep.class){
                    if(!heartbeatLock) {
                        //Util.log("心跳--被程序手动停止");
                        System.out.println("心跳--被程序手动停止");
                        return ; //被程序手动停止
                    }

                    boolean bool = isConnect();
                    //Util.log("是否连接："+bool);
                    System.out.println("是否连接："+bool);
//                    if(!bool){
//                        //已经断开连接了 尝试重新连接
//                        Util.log("已经断开连接，正在尝试连接");
//                        heartbeatLock = false;
//                        freeSocket();
//                        startClinet();
//                    }
                }
            }
        }

        public void stop(){
            heartbeatLock = false;
            if(thread != null){
                thread.interrupt();
            }
        }
    }

    public void freeSocket(){
        if (printWriter != null) {
            printWriter.close();
            printWriter = null;
        }

        if(bufferedReader != null){
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bufferedReader = null;

        }

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = null;
        }

    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 判断是否保持连接
     * @return
     */
    public boolean isConnect() {
        if(socket != null){
            try{
                socket.sendUrgentData(0xFF);
                return true;
            }catch(Exception e){
                return false;
            }
        }else return false;
    }

    public interface OnSocketReadListener{
        void onSocketRead(String data);
    }

    public interface OnSocketConnectListener{
        void onConnectSu();
    }
}
