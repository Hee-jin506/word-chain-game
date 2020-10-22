package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.word.chain.domain.Member;

public class ListMemberCommand extends LoggedOutCommand {

  List<Member> memberList;

  public ListMemberCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("[회원 목록]");

    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member member = iterator.next();
      out.printf("아이디: %s, 이름: %s, 레벨: %s\n",
          member.getId(),
          member.getName(),
          member.getMaxLevel());
      out.println("(사용한 단어 목록)");
      member.getUsedWords().list(out, in);
    }
  }
}
