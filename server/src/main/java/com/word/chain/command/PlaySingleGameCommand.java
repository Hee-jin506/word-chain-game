package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.chain.domain.WordList;
import com.word.util.Prompt;

public class PlaySingleGameCommand extends LoggedInCommand {

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

        loop:
          switch (response) {
            case "1":
              playSingle(1, out, in);
              return;
            case "2":
              out.printf("당신은 %d레벨까지 클리어했습니다.\n", super.loggedInMember.getMaxLevel());
              int level = 0;
              while (true) {
                String str = Prompt.inputString(String.format("몇 레벨을 플레이하시겠습니까?(~%d, 뒤로가기-q) :", super.loggedInMember.getMaxLevel()), out, in);
                if (str.equalsIgnoreCase("q"))
                  break loop;
                level = Integer.parseInt(str);
                if (level > super.loggedInMember.getMaxLevel()) {
                  out.printf("%d 이하의 레벨만 선택가능합니다.\n", super.loggedInMember.getMaxLevel());
                } else if (level < 1) {
                  out.println("1 이상의 레벨을 선택해주세요.");
                } else {
                  break;
                }
              }
              playSingle(level, out, in);
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

  public void playSingle(int level, PrintWriter out, BufferedReader in) throws Exception {
    String computer = super.loggedInMember.getComputer();
    List<String> usedWords = new ArrayList<>();
    boolean win = false;
    WordList words = new WordList();
    
    for (String word : levelList.get(level-1))
      words.add(word);
    
    if (loggedInMember.isTeachingComputer()) {
      for (String word : super.loggedInMember.getUsedWords())
        words.add(word);
    }

    out.println("곧 끝말잇기 게임이 시작됩니다...!");
    out.flush();

    for (int i = 5; i >= 1; i--) {
      Thread.sleep(500);
      out.print(i + " ");
      out.flush();
    }

    out.printf("\n[level%d 시작!]\n", level);
    out.flush();

    String attack = words.firstAttack();
    usedWords.add(attack);
    out.printf("%s : %s\n", computer, attack);

    while (true) {
      String defense = Prompt.inputString("당신 : ", out, in);
      if (defense.equals("")) {
        out.println("시간 초과!");
        break;
      } else if (usedWords.contains(defense)) {
        out.println("이미 사용된 단어입니다.");
        break;
      } else if (defense.charAt(0) == attack.charAt(attack.length() - 1)) {
        out.println("ok!");
        usedWords.add(defense);
        super.loggedInMember.getUsedWords().add(defense);
      } else {
        out.println("틀렸습니다.");
        break;
      }

      int i = 0;
      for (; i < 3; i++) {
        attack = words.attack(defense);
        if (attack != null && !usedWords.contains(attack)) {
          usedWords.add(attack);
          out.printf("%s : %s\n", computer, attack);
          break;
        }
      }

      if (i == 3) {
        out.printf("%s : %c ", computer, defense.charAt(defense.length() - 1));
        out.flush();
        for (int j = 0; j < 4; j++) {
          Thread.sleep(500);
          out.print(". ");
          out.flush();
        }
        out.printf("\n%s는 더이상 단어가 떠오르지 않습니다.\n", computer);
        win = true;
        break;
      }
    }

    if (win) {
      out.printf("[level%d 클리어]\n", level);
      String nextLevel = Prompt.inputString("다음 레벨을 이어 하시겠습니까?(Y/n) : ", out, in);
      if (nextLevel.equalsIgnoreCase("n")) {
        out.println("메인 메뉴로 갑니다.");
        if (super.loggedInMember.getMaxLevel() < level) 
          super.loggedInMember.setMaxLevel(level);
        return;

      } else {
        playSingle(level + 1, out, in);
      }
    } else {
      out.println("[게임오버]");
      String retry  = Prompt.inputString("다시 시도하시겠습니까?(Y/n) : ", out, in);
      if (retry.equalsIgnoreCase("n")) {
        out.println("메인 메뉴로 갑니다.");
      } else {
        playSingle(level, out, in);
      }
    }
  }
}

