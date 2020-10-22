package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.chain.domain.WordList;

public class PlayMultiGameCommand extends LoggedInCommand {
  
  List<Member> memberList;
  List<WordList> levelList;

  public PlayMultiGameCommand(List<Member> memberList, List<WordList> levelList) {
    this.memberList = memberList;
    this.levelList = levelList;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    // TODO Auto-generated method stub
    
  }

}
