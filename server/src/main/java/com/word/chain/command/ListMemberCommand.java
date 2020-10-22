package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.word.chain.domain.Member;

public class ListMemberCommand implements Command {

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
      out.printf("%d, %s, %s, %s\n",
          member.getId(),
          member.getName(),
          member.getMaxLevel(),
          member.getUsedWords());
    }
  }
}
