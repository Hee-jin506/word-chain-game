package com.word.chain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.word.util.Prompt;

public class ClientApp {
  
  static boolean stop = false;
  
  static String id = "";
  
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("프로그램 사용법:");
      System.out.println("  java -cp ... ClientApp 서버주소 포트번호");
      System.exit(0);
    }

    while (true) {
    try (Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

        if (stop) {
          break;
        }
        out.println(id);
        out.flush();
        
        receiveResponse(out, in);
        
        String input = Prompt.inputString("> ");

        
        out.println(input);
        out.flush();
        
        if (input.equalsIgnoreCase("quit"))
          break;
        receiveResponse(out, in);
        if (input.equalsIgnoreCase("stop"))
          stop = true;
        
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  }

  private static void receiveResponse(PrintWriter out, BufferedReader in) throws Exception {
    while (true) {
      String response = in.readLine();
      
      if (response.equals("!{ends}")) {
        break;
      }
        else if (response.endsWith("!{id}")) {
          id = response.split("!")[0];
      
      } else if (response.endsWith("!{}")) {
        out.println(Prompt.inputString(response.split("!")[0]));
        out.flush();
      }  else if (response.endsWith("!{time}")) {
        System.out.print(response.split("!")[0]);
        out.println(timer(Integer.parseInt(response.split("!")[1])));
        out.flush();
        
      }  else if (response.equals("!{noid}")) {
        id = "";
      } else {
        System.out.println(response);
      }
    }
  }
  
  public static String timer(int time) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = "";
    
    try {
      int count = 0;
      while (!br.ready()) {
        Thread.sleep(100);
        count += 100;
        if (count == time * 1000)
          break;
      }
      
      if (br.ready())
        input = br.readLine();
      else {
        input = "timeout";
      }
    } catch (Exception e) {
      System.out.println("입력 중 오류 발생!");
    }
    return input;
  
  }
}
 