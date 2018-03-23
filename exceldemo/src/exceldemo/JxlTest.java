package exceldemo;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class JxlTest{
	/**
	 * jxl 创xcel
	 */
	public static void writerJXL() {
	    try {
	        long dir= (long) Math.round(Math.floor(Math.random() * 10000000));
	        String dirPath ="c://11//".concat(String.valueOf(dir)) ;
	        File file = new File(dirPath);
	        if(file.exists()) {
	            System.out.println(file.getName()+"存xl");
	            if(file.delete()) {
	                file.mkdir();
	            }
	 
	        }else {
	            file.mkdir();
	        }
	        //      打   
	        long l = (long) Math.round(Math.floor(Math.random() * 1000000000));
	        String name = "jxl_".concat(String.valueOf(l)).concat(".xls");
	        File excel = new  File(dirPath.concat("//").concat(name));
	        if(!excel.getParentFile().exists()){
	        	file.mkdirs();
	        }
	        WritableWorkbook book = Workbook.createWorkbook(excel);
	        int j = 0 ;
	        while(j < 4){ //控heet创建
	            WritableSheet sheet  =  book.createSheet("test"+j ,j);
	            Label label1 = null ;
	            Label label2 = null ;
	            for(int i = 0 ; i < 3 ;i ++){
	                label1  =   new  Label(0,i,"A数xl"+i ); //第一列第一行
	                label2  =   new  Label(1,i,"A数xl"+i ); //第二列第一行
	                sheet.addCell(label1);    //  将    
	                sheet.addCell(label2);               
	            }
	            j++ ;
	        }        
	        book.write();
	        book.close();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (RowsExceededException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (WriteException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }    
	}
	
	public static void main(String[] args) {
		writerJXL();
	}
}