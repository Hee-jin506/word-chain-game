package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;

public class LogOutCommand extends LoggedInCommand {
 List<Member> memberList;
  
  public LogOutCommand(List<Member> list) {
    memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    // TODO Auto-generated method stub
    
  }
  

}
