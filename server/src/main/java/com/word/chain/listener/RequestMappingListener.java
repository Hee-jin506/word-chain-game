package com.word.chain.listener;

import java.util.List;
import java.util.Map;
import com.word.chain.command.AddMemberCommand;
import com.word.chain.command.DeleteMemberCommand;
import com.word.chain.command.DetailMemberCommand;
import com.word.chain.command.ListMemberCommand;
import com.word.chain.command.LogInCommand;
import com.word.chain.command.LogOutCommand;
import com.word.chain.command.PlayMultiGameCommand;
import com.word.chain.command.PlaySingleGameCommand;
import com.word.chain.command.SettingsCommand;
import com.word.chain.domain.Member;
import com.word.chain.domain.WordList;
import com.word.context.ApplicationContextListener;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    List<WordList> levelList = (List<WordList>) context.get("levelList");
    List<Member> memberList = (List<Member>) context.get("memberList");

    
    ListMemberCommand memberListCommand = new ListMemberCommand(memberList);
    context.put("/member/add", new AddMemberCommand(memberList));
    context.put("/member/login", new LogInCommand(memberList));
    context.put("/member/logout", new LogOutCommand(memberList));
    context.put("/member/delete", new DeleteMemberCommand(memberList));
    context.put("/member/detail", new DetailMemberCommand(memberList));
    context.put("/member/list", new ListMemberCommand(memberList));
    context.put("/play/multi", new PlayMultiGameCommand(memberList, levelList));
    context.put("/play/single", new PlaySingleGameCommand(memberList, levelList));
    context.put("/member/settings", new SettingsCommand(memberList));
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    
  }
}
