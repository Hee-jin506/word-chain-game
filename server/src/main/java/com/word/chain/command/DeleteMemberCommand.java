package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.util.Prompt;

public class DeleteMemberCommand implements Command {

  List<Member> memberList;

  public DeleteMemberCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[회원 탈퇴]");
      String id = Prompt.inputString("아이디: ", out, in);
      String password = Prompt.inputString("암호: ", out, in);

      int index = indexOf(id);
      int index2 = indexOf2(id, password);

      if (index == -1) {
        out.println("해당 아이디의 회원이 없습니다.");
        return;
      }

      if (index2 == -1) {
        out.println("암호가 일치하지 않습니다.");
        return;
      }

      String response = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ", out, in);
      if (!response.equalsIgnoreCase("y")) {
        out.println("회원 탈퇴를 취소하였습니다.");
        return;
      }

      memberList.remove(index2);
      out.println("회원을 삭제하였습니다.");

    } catch (Exception e) {
      out.printf("작업 처리 중 오류 발생! - %s\n", e.getMessage());
    }
  }

  private int indexOf(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId() == id) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf2(String id, String password) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if(member.getId() == id && member.getPassword() == password) {
        return i;
      }
    }
    return -1;
  }

}
