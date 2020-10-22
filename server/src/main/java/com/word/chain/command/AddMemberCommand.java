package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.util.Prompt;

public class AddMemberCommand extends LoggedOutCommand {

  List<Member> memberList;

  public AddMemberCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[회원 가입]");

      Member member = new Member();
      member.setId(Prompt.inputString("아이디: ", out, in));
      member.setName(Prompt.inputString("이름: ", out, in));
      member.setPassword(Prompt.inputString("암호: ", out, in));
      member.setComputer(Prompt.inputString("상대 이름: ", out, in));

      memberList.add(member);
      out.println("회원가입이 완료되었습니다!");
      out.println();

    } catch (Exception e) {
      out.printf("잘못된 접근입니다. - %s\n", e.getMessage());
    }
  }
}

