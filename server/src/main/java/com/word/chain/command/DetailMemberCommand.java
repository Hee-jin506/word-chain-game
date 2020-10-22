package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.util.Prompt;

public class DetailMemberCommand implements Command {

  List<Member> memberList;

  public DetailMemberCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      out.println("[회원 정보]");
      String id = Prompt.inputString("아이디: ", out, in);
      Member member = findById(id);

      if (member == null) {
        out.println("검색되는 회원이 없습니다.");
        return;
      }

      out.printf("이름: %s\n", member.getName());
      out.printf("최대 달성 레벨: %s\n", member.getMaxLevel());
      out.println("(내 단어)");
      member.getUsedWords().list();

    } catch (Exception e) {
      out.printf("잘못된 접근입니다. - %s\n", e.getMessage());
    }
  }

  private Member findById(String id) {
    for (int i = 0; i < memberList.size(); i++) {
      Member member = memberList.get(i);
      if (member.getId() == id) {
        return member;
      }
    }
    return null;
  }
}
