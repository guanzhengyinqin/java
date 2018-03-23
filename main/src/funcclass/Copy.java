package funcclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class Copy {
	
	/**
     * 复制文件
     *
     * @param f1 源文件
     * @param f2 新文件地址
     * @return
     * @throws Exception
     */
    public static long copyFile(File f1, File f2) throws Exception {
        long time = new Date().getTime();
        int length = 2097152;
        FileInputStream in = new FileInputStream(f1);
        FileOutputStream out = new FileOutputStream(f2);
        byte[] buffer = new byte[length];
        while (true) {
            int ins = in.read(buffer);
            if (ins == -1) {
                in.close();
                out.flush();
                out.close();
                return new Date().getTime() - time;
            } else
                out.write(buffer, 0, ins);
        }
    }

}
