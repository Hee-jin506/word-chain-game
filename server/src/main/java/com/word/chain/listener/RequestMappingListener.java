package com.word.chain.listener;

import java.util.List;
import java.util.Map;
import com.word.chain.command.BoardAddCommand;
import com.word.chain.command.BoardDeleteCommand;
import com.word.chain.command.BoardDetailCommand;
import com.word.chain.command.BoardListCommand;
import com.word.chain.command.BoardUpdateCommand;
import com.word.chain.command.CalculatorCommand;
import com.word.chain.command.HelloCommand;
import com.word.chain.command.AddMemberCommand;
import com.word.chain.command.DeleteMemberCommand;
import com.word.chain.command.DetailMemberCommand;
import com.word.chain.command.ListMemberCommand;
import com.word.chain.command.MemberUpdateCommand;
import com.word.chain.command.ProjectAddCommand;
import com.word.chain.command.ProjectDeleteCommand;
import com.word.chain.command.ProjectDetailCommand;
import com.word.chain.command.ProjectListCommand;
import com.word.chain.command.ProjectUpdateCommand;
import com.word.chain.command.TaskAddCommand;
import com.word.chain.command.TaskDeleteCommand;
import com.word.chain.command.TaskDetailCommand;
import com.word.chain.command.TaskListCommand;
import com.word.chain.command.TaskUpdateCommand;
import com.word.chain.domain.Board;
import com.word.chain.domain.Member;
import com.word.chain.domain.Project;
import com.word.chain.domain.Task;
import com.word.context.ApplicationContextListener;

// 클라이언트 요청을 처리할 커맨드 객체를 준비한다.
public class RequestMappingListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String,Object> context) {
    List<Wor>
    List<Member> members = (List<Member>) context.get("memberList");

    context.put("/board/add", new BoardAddCommand(boardList));
    context.put("/board/list", new BoardListCommand(boardList));
    context.put("/board/detail", new BoardDetailCommand(boardList));
    context.put("/board/update", new BoardUpdateCommand(boardList));
    context.put("/board/delete", new BoardDeleteCommand(boardList));

    ListMemberCommand memberListCommand = new ListMemberCommand(memberList);
    context.put("/member/add", new AddMemberCommand(memberList));
    context.put("/member/list", memberListCommand);
    context.put("/member/detail", new DetailMemberCommand(memberList));
    context.put("/member/update", new MemberUpdateCommand(memberList));
    context.put("/member/delete", new DeleteMemberCommand(memberList));

    context.put("/project/add", new ProjectAddCommand(projectList, memberListCommand));
    context.put("/project/list", new ProjectListCommand(projectList));
    context.put("/project/detail", new ProjectDetailCommand(projectList));
    context.put("/project/update", new ProjectUpdateCommand(projectList, memberListCommand));
    context.put("/project/delete", new ProjectDeleteCommand(projectList));

    context.put("/task/add", new TaskAddCommand(taskList, memberListCommand));
    context.put("/task/list", new TaskListCommand(taskList));
    context.put("/task/detail", new TaskDetailCommand(taskList));
    context.put("/task/update", new TaskUpdateCommand(taskList, memberListCommand));
    context.put("/task/delete", new TaskDeleteCommand(taskList));

    context.put("/hello", new HelloCommand());

    context.put("/calc", new CalculatorCommand());
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
  }
}
