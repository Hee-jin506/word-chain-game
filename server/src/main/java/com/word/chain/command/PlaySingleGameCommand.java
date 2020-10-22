package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.chain.domain.WordList;
import com.word.util.Prompt;

public class PlaySingleGameCommand implements Command {

  List<Member> memberList;
  List<WordList> levelList;

  public PlaySingleGameCommand(List<Member> memberList, List<WordList> levelList) {
    this.memberList = memberList;
    this.levelList = levelList;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    try {
      while (true) {
        out.println("[싱글게임]");
        out.println("(1) 처음부터");
        out.println("(2) 불러오기");
        out.println("(3) 메인메뉴");
        String response = Prompt.inputString("숫자를 입력하세요. : ", out, in);

        switch (response) {
          case "1":
            from1To3(1, out, in);
            return;
          case "2":
            break;
          case "3":
            return;
          default:
            out.println("유효하지 않은 명령입니다.");
        }
      }

    } catch (Exception e) {
      out.println("싱글게임 중 문제 발생");
    }
  }

  public void from1To3(int level, PrintWriter out, BufferedReader in) throws Exception {
    boolean win = false;
    WordList words = levelList.get(level-1);
    out.println("10초 안에 단어를 입력하세요!");

    for (int i = 5; i >= 1; i--) {
      out.print(i);
      Thread.sleep(800);
    }

    out.println("[level1 시작!]");

    String attack = words.firstAttack();
    out.println("컴퓨터 : " + attack);


    while (true) {
      String defense = Prompt.inputString("당신 : "/*, out, in*/);
      if (defense.equals("")) {
        out.println("시간 초과!");
        break;
      } else if (defense.charAt(0) == attack.charAt(attack.length() - 1)) {
        out.println("ok!");
      } else {
        out.println("틀렸습니다.");
        break;
      }

      attack = words.attack(defense);
      if (attack == null) {
        out.println("컴퓨터 : " + defense.charAt(defense.length() - 1));
        win = true;
        break;
      }
    }
    
    if (win) {
      out.printf("[level%d 클리어]", level);
      String nextLevel = Prompt.inputString("다음 레벨을 이어 하시겠습니까?(Y/n) : ");
      if (nextLevel.equalsIgnoreCase("n")) {
        out.println("메인 메뉴로 갑니다.");
        return ;
      } else {
        from1To3(level + 1, out, in);
      }
    } else {
      out.println("[게임오버]");
      String retry  = Prompt.inputString("다시 시도하시겠습니까?(Y/n) : ");
      if (retry.equalsIgnoreCase("n")) {
        out.println("메인 메뉴로 갑니다.");
      } else {
        from1To3(level, out, in);
      }
    }
  }
}
