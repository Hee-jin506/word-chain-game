package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.word.chain.domain.WordList;
import com.word.util.Prompt;

public class PlaySingleGameCommand implements Command {
  
  ArrayList<WordList> levelWords = new ArrayList<>();
  
  {
    for (int i = 0; i < 10; i++) {
      WordList words = new WordList();
      words.add("admin");
      words.add("bus");
      words.add("car");
      words.add("dirt");
      words.add("enum");
      words.add("flower");
      words.add("gun");
      words.add("humble");
      words.add("image");
      words.add("juice");
      words.add("king");
      words.add("large");
      
      levelWords.add(words);
    }
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
        
        if (response.equals("1")) {
          from1To3(1, out, in);
        } else if (response.equals("2")) {
          
        } else if (response.equals("3")) {
          return;
        } else {
          out.println("유효하지 않은 명령입니다.");
        }
      }
      
    } catch (Exception e) {
      out.println("싱글게임 중 문제 발생");
    }
  }
  
  public void from1To3(int level, PrintWriter out, BufferedReader in) throws Exception {
    WordList words = levelWords.get(level-1);
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
        out.println("타임아웃! 게임오버!");
        break;
      } else if (defense.charAt(0) == attack.charAt(attack.length() - 1)) {
        out.println("ok!");
      } else {
        out.println("틀렸습니다. 게임오버!");
        break;
      }
      
      attack = words.attack(defense);
      out.println("컴퓨터 : " + attack);
    }
  }
}
