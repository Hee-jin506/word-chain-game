package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.util.Prompt;

public class SettingsCommand extends LoggedInCommand {

  public SettingsCommand(List<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    while (true) {
      try {
        out.println("[설정]");
        out.printf("(1) 당신의 이름 변경 [%s]\n", loggedInMember.getName());
        out.printf("(2) 컴퓨터의 이름 변경 [%s]\n", loggedInMember.getComputer());

        String computerLearning = (loggedInMember.isTeachingComputer()) ? "[on]" : "[off]";
        out.println("(3) 컴퓨터 데이터 학습 여부 설정 변경 " + computerLearning);
        out.println("(4) 메인 메뉴로 돌아가기");

        switch (Prompt.inputString("숫자를 입력하세요 : ", out, in)) {
          case "1":
            loggedInMember.setName(Prompt.inputString(
                String.format("이름(%s): ", loggedInMember.getName()), out, in));
            break;
          case "2":
            loggedInMember.setComputer(Prompt.inputString(
                String.format("컴퓨터의 이름(%s): ", loggedInMember.getComputer()), out, in));
            break;
          case "3":
            loggedInMember.setTeachingComputer(
                (Prompt.inputString("컴퓨터 데이터 학습 여부 설정(Y/f): ", out, in).equalsIgnoreCase("n")) ? false : true);
            break;
          case "4":
            return;
          default:
            out.println("유효하지 않은 명령입니다.");
        }

      } catch (Exception e) {
        out.println("설정 조정 중 오류 발생 : " + e.getMessage());
      }
    }
  }
}
