package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;

public class LogInCommand implements Command {
  List<Member> memberList;
  
  public LogInCommand(List<Member> list) {
    memberList = list;
  }
  
  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    
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
