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
        
        String input = Prompt.inputString("숫자를 입력하세요.");

        
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
      
      if (response.length() == 0) {
        break;
      }
        else if (response.endsWith("!{id}")) {
          System.out.println(response);
          id = response.split("!")[0];
       
      
      } else if (response.equals("!{}!")) {
        out.println(Prompt.inputString(""));
        out.flush();
        
      }  else {
        System.out.println(response);
      }
    }
  }
}
