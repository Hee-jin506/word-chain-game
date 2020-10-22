package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.util.Prompt;

public class UpdateMemberCommand implements Command {

  List<Member> memberList;
  Member member;

  public UpdateMemberCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[회원 정보 수정]");
      String password = Prompt.inputString("암호: ", out,in);

      int index = findByPassword(password);

      if (index == -1) {
        out.println("암호가 일치하지 않습니다.");
        return;
      }

      String name = Prompt.inputString(
          String.format("이름(%s): ", member.getName()));
      String updatePassword = Prompt.inputString(
          String.format("암호(%s): ", member.getPassword()));
      String computer = Prompt.inputString(
          String.format("상대 이름(%s): ", member.getComputer()));

      String response = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
      if(!response.equalsIgnoreCase("y")) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }

      member.setName(name);
      member.setPassword(updatePassword);
      member.setComputer(computer);

      System.out.println("회원 정보를 변경하였습니다.");
    } catch (Exception e) {
      out.printf("잘못된 접근입니다. - %s\n", e.getMessage());
      }
  }
  private int findByPassword(String password) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if(member.getPassword() == password) {
        return i;
      }
    }
    return -1;
  }
}
