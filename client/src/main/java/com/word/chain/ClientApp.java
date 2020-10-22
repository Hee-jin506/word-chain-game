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

        out.println(id);
        out.flush();
        
        receiveResponse(out, in);

        String input = Prompt.inputString("숫자를 입력하세요.");
        
        if (input.equalsIgnoreCase("quit"))
          break;
        
        out.println(input);
        out.flush();
        
        receiveResponse(out, in);

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
      } else if (response.equals("!{}!")) {
        // 사용자로부터 값을 입력을 받아서 서버에 보낸다.
        out.println(Prompt.inputString(""));
        out.flush(); // 주의! 출력하면 버퍼에 쌓인다. 서버로 보내고 싶다면 flush()를 호출하라!
      } else if (response.endsWith("!{id}")) {
        id = response.split("!")[0];
        System.out.println(id);
      } else {
        System.out.println(response);
      }
    }
  }
}
