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
        out.println("                    _         _         _                              ");
        out.println(" _ _ _  ___  ___  _| |   ___ | |_  ___ |_| ___    ___  ___  _____  ___ ");
        out.println("| | | || . ||  _|| . |  |  _||   || .'|| ||   |  | . || .'||     || -_|");
        out.println("|_____||___||_|  |___|  |___||_|_||__,||_||_|_|  |_  ||__,||_|_|_||___|");
        out.println("                                                 |___|                 ");
        out.println("+----------------------------------------------------------------------+");
        out.println("/                     끝말잇기 게임을 시작해볼까요?!                   /");
        out.println("+----------------------------------------------------------------------+");
        out.println("\\                              < MENU >                                \\");
        out.println("+----------------------------------------------------------------------+");
        out.println("/                                                                      /");
        out.println("\\                            1. 처음부터                               \\");
        out.println("/                                                                      /");
        out.println("\\                            2. 불러오기                               \\");
        out.println("/                                                                      /");
        out.println("\\                            3. 메인 메뉴                              \\");
        out.println("/                                                                      /");
        out.println("+----------------------------------------------------------------------+");
        out.println("\\ 명령어의 번호를 입력해주세요!                                        \\");
        out.println("+----------------------------------------------------------------------+");
        String response = Prompt.inputString("> ", out, in);

        loop:
          switch (response) {
            case "1":
              playFrom1To3(1, out, in);
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
              
              if (level >= 1 && level <= 3)
                playFrom1To3(level, out, in);
              else if (level >= 4 && level <= 7)
                playFrom4To7(level, out, in);
              else if (level >= 8 && level <= 10)
                playFrom8To10(level, out, in);
              
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
  
  public void playFrom1To3(int level, PrintWriter out, BufferedReader in) throws Exception {
    playSingle(level, out, in, 8);
  }
  
  public void playFrom4To7(int level, PrintWriter out, BufferedReader in) throws Exception {
    playSingle(level, out, in, 5);
  }
  
  public void playFrom8To10(int level, PrintWriter out, BufferedReader in) throws Exception {
    playSingle(level, out, in, 3);
  }
  

  public void playSingle(int level, PrintWriter out, BufferedReader in, int time) throws Exception {
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

    out.println("+-----------------------------+");
    out.printf("| %d초 안에 단어를 입력하세요. |\n", time);
    out.println("+-----------------------------+");
    out.println();
    out.println("곧 끝말잇기 게임이 시작됩니다...!");
    out.flush();

    for (int i = 5; i >= 1; i--) {
      Thread.sleep(500);
      out.println(i + " ");
      out.println();
      out.flush();
    }
    
    out.println("+---------------+");
    out.printf("| level%2d 시작! |\n", level);
    out.println("+---------------+");
    out.flush();

    String attack = words.firstAttack();
    usedWords.add(attack);
    out.printf("%s : %s\n", computer, attack);

    while (true) {
      out.printf("당신 : !%d!{time}\n", time);
      out.flush();
      String defense = in.readLine();
      
      if (defense.equals("timeout")) {
        out.println("시간 초과!");
        break;
      } else if (usedWords.contains(defense)) {
        out.println("이미 사용된 단어입니다.");
        break;
      } else if (defense.charAt(0) == attack.charAt(attack.length() - 1)) {
        out.println("OK!");
        usedWords.add(defense);
        super.loggedInMember.getUsedWords().add(defense);
      } else {
        out.println("틀렸습니다.");
        break;
      }

      out.println();
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
    
    out.println();

    if (win) {
      out.println("+-----------------+");
      out.printf ("| level%2d 클리어 |\n", level);
      out.println("+-----------------+");
      out.println();
      String nextLevel = Prompt.inputString("다음 레벨을 이어 하시겠습니까?(Y/n) : ", out, in);
      if (nextLevel.equalsIgnoreCase("n")) {
        out.println("게임 메뉴로 다시 돌아갑니다.");
        out.flush();
        try {
          Thread.sleep(2000);
        } catch (Exception e) {
          
        }
        if (super.loggedInMember.getMaxLevel() < level) 
          super.loggedInMember.setMaxLevel(level);
        return;

      } else {
        if (level + 1 >= 1 && level + 1<= 3)
          playFrom1To3(level + 1, out, in);
        else if (level + 1 >= 4 && level + 1 <= 7)
          playFrom4To7(level + 1, out, in);
        else if (level + 1 >= 8 && level + 1 <= 10)
          playFrom8To10(level + 1, out, in);
      }
    } else {
      out.println("+------------+");
      out.printf ("| 게임 오버! |\n", level);
      out.println("+------------+");
      out.println();
      String retry  = Prompt.inputString("다시 시도하시겠습니까?(Y/n) : ", out, in);
      if (retry.equalsIgnoreCase("n")) {
        out.println("게임 메뉴로 다시 돌아갑니다.");
        out.flush();
        if (super.loggedInMember.getMaxLevel() < level - 1) 
          super.loggedInMember.setMaxLevel(level - 1);
        try {
          Thread.sleep(2000);
        } catch (Exception e) {
          
        }
      } else {
        if (level >= 1 && level <= 3)
          playFrom1To3(level, out, in);
        else if (level >= 4 && level <= 7)
          playFrom4To7(level, out, in);
        else if (level >= 8 && level <= 10)
          playFrom8To10(level, out, in);
      }
    }
  }
}

