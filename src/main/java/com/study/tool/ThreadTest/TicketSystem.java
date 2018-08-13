package com.study.tool.ThreadTest;

public class TicketSystem{
   public static void main(String[] args){
      SellThread st=new SellThread();
      
      new Thread(st).start();
      new Thread(st).start();
      new Thread(st).start();
   }
}
