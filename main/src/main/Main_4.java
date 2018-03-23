package main;

import java.util.Scanner;

import main.Observed.OnHaveNumListener;

public class Main_4{
    public static void main(String[] args){
        //Observed observed = new Observed();
        //注册监听
        Observed.setOnHaveNumListener(new OnHaveNumListener() {
            @Override
            public void num(int num) {
                //当数字发生改变就会调用这个方法
            	System.out.println(num);
            }
        });
        
        @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
        while(true){
        	int a = input.nextInt();
        	Observed.doNum(a);
        }
        
        
        
        
    }
}

//被观察者
class Observed{
  static OnHaveNumListener onHaveNumListener;

  public static void setOnHaveNumListener(OnHaveNumListener onHaveNumListenerp) {
      onHaveNumListener = onHaveNumListenerp;
     
  }
  
  
  
  public static void doNum(int num){
      if(onHaveNumListener != null){
          onHaveNumListener.num(num);
      }
  }

  interface OnHaveNumListener{
      void num(int num);
  }
  
}
