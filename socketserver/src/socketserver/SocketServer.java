package socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketServer{

    public static final String TAG = "xx";

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private List<Socket> socketList;

    private int mPort;
    private boolean mLockServerThread = false;
    private OnSocketReadListener onSocketReadListener;

    public SocketServer() {
        executorService = Executors.newFixedThreadPool(4);
        socketList = Collections.synchronizedList(new ArrayList<Socket>());
    }

    public SocketServer(int nThreads) {
        executorService = Executors.newFixedThreadPool(nThreads);
        socketList = Collections.synchronizedList(new ArrayList<Socket>());
    }

    public List<Socket> getSocketList() {
        return socketList;
    }

    public void setOnSocketReadListener(OnSocketReadListener onSocketReadListener) {
        this.onSocketReadListener = onSocketReadListener;
    }

    /**
     * 开启
     * @param port
     */
    public void startServer(int port) {

        if (port < 1024 || port > 65535) return;
        if(mPort == port) return ;


        if(mLockServerThread){
            //说明已经开启过需要先关闭
            closeSocket();
        }
        mLockServerThread = true;
        mPort = port;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(mPort);
                    System.out.println("run: 11111:"+mLockServerThread+", port:"+mPort);
                    //Log.d(TAG, "run: 11111:"+mLockServerThread+", port:"+mPort);
                    while (mLockServerThread) {
                        try {

                            Socket socket = serverSocket.accept();
                            checkSocket(socket);
                            socketList.add(socket);
                            System.out.println("有主机连接:"+socketList.size() +", 连接端为："+socket.getInetAddress().getHostAddress());
                            //Log.d(TAG, "有主机连接:"+socketList.size() +", 连接端为："+socket.getInetAddress().getHostAddress());
                            executorService.execute(new TaskRunnable(socket));
                            //executorService.e
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socketList.clear();
            }
        });
    }

    private void checkSocket(Socket socket) {
        for (Socket socketTemp :
                socketList) {
            if(socketTemp.getInetAddress().getHostAddress().equals(socket.getInetAddress().getHostAddress())){
                try {
                    socketTemp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socketList.remove(socketTemp);
                return ;
            }
        }
    }

    class TaskRunnable implements Runnable {

        private Socket socket;
        private BufferedReader bufferedReader;

        public TaskRunnable(Socket socket) {
            this.socket = socket;
            try {

                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String content = null;

            while ((content = readFromClient()) != null) {
            	System.out.println("11111111读到数据: " + content);
                //Log.d(TAG, "11111111读到数据: " + content);
                if (onSocketReadListener != null) {
                    onSocketReadListener.onSocketRead(socket.getInetAddress().getHostAddress(), socket.getPort(), content);
                }
            }

            System.out.println("循环停止关闭连接");
            //Log.d(TAG, "循环停止关闭连接");
            socketList.remove(socket);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
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

    }

    /**
     * 关闭连接
     */
    public void closeSocket(){
    	System.out.println("closeSocket: 关闭连接");
        //Log.d(TAG, "closeSocket: 关闭连接");
        if(!mLockServerThread) return ;
        mLockServerThread = false;
        mPort = 0;
        for (int i = 0; i < socketList.size(); i++) {
            try {
                socketList.get(i).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socketList.clear();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverSocket = null;
    }

    public void sendData(String data){
        if(socketList != null){
            for (Socket socket :
                    socketList) {
            	System.out.println("写入："+data);
                //Log.log("写入："+data);
                try {
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    printWriter.print(data);
                    printWriter.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendData(String ip, int port, String data){
        if(socketList != null){
            for (Socket socket :
                    socketList) {

                String ipTemp = socket.getInetAddress().getHostAddress();
                int portTemp = socket.getPort();

                if(ipTemp.equals(ip) && portTemp == port){
                	System.out.println("写入："+data);
                    //Log.log("写入："+data);
                    try {
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        printWriter.print(data);
                        printWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void sendData(Socket socket, String data){
    	System.out.println("写入："+data);
        //Log.log("写入："+data);
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.print(data);
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public interface OnSocketReadListener{
        void onSocketRead(String ip, int port, String data);
    }
}
