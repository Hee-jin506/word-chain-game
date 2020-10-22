package com.word.chain.command;

import java.util.List;
import com.word.chain.domain.Member;

public abstract class LoggedInCommand implements Command {
  
  Member loggedInMember;
  
  List<Member> memberList = null;
  
  public void registerMember(String id) {
    this.loggedInMember = findById(id);
  }
  
  private Member findById(String id) {
    for (Member member : memberList) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
}
