package com.word.chain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.word.util.Prompt;

// Stateful 통신
// => 서버와 연결하여 간단한 메시지 주고 받기
// => 사용자가 입력한 명령을 서버에 전송하기
// => 사용자가 quit 명령을 입력할 때까지 반복한다.
// => 서버가 응답의 끝을 알리는 빈 줄을 보낼 때까지 클라이언트는 계속 읽는다.
// => 애플리케이션 아규먼트를 통해 서버의 주소와 포트번호를 입력 받는다.
//
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
