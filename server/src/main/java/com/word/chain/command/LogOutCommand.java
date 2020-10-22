package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;

public class LogOutCommand extends LoggedInCommand {

  public LogOutCommand(List<Member> list) {
    super.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("!{noid}");
  }
}
