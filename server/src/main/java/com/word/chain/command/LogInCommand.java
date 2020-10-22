package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.util.Prompt;

public class LogInCommand implements Command {

  List<Member> memberList;

  public LogInCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
    out.println("[로그인]");
    String id = Prompt.inputString("아이디: ", out, in);
    String password = Prompt.inputString("암호: ", out, in);

    int index = indexOf(id);
    int index2 = indexOf2(id, password);

    if (index == -1) {
      out.println("해당 아이디의 회원이 없습니다.");
      return;
    }

    if (index2 == -1) {
      out.println("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
      return;
    }

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }




  private int indexOf(String id) { // 아이디값 일치여부 검증
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId() == id) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf2(String id, String password) { // 아이디, 비밀번호값 일치여부 검증
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if(member.getId() == id && member.getPassword() == password) {
        return i;
      }
    }
    return -1;
  }


}
