package com.word.chain.command;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import com.word.chain.domain.Member;
import com.word.chain.domain.WordList;

public class PlayMultiGameCommand extends LoggedInCommand {

  List<WordList> levelList;

  public PlayMultiGameCommand(List<Member> memberList, List<WordList> levelList) {
    this.memberList = memberList;
    this.levelList = levelList;
  }

  @Override
  public void execute(PrintWriter out, BufferedReader in) {
    out.println("                         ______              ");
    out.println(" _________        .---\"\"\"      \"\"\"---.       ");
    out.println(":______.-':      :  .--------------.  :      ");
    out.println("| ______  |      | :                : |      ");
    out.println("|:______B:|      | |  Little Error: | |      ");
    out.println("|:______B:|      | |                | |      ");
    out.println("|:______B:|      | |  Power not     | |      ");
    out.println("|         |      | |  found.        | |      ");
    out.println("|:_____:  |      | |                | |      ");
    out.println("|    ==   |      | :                : |        ");
    out.println("|       O |      :  '--------------'  :        ");
    out.println("|       o |      :'---...______...---'             ");
    out.println("|       o |-._.-i___/'             \\._              ");
    out.println("|'-.____o_|   '-.   '-...______...-'  `-._          ");
    out.println(":_________:      `.____________________   `-.___.-. ");
    out.println("                 .'.eeeeeeeeeeeeeeeeee.'.      :___:");
    out.println("    fsc        .'.eeeeeeeeeeeeeeeeeeeeee.'.         ");
    out.println("              :____________________________:");
    
    out.println();
    out.println("+---------------------------------------------------+");
    out.println("|죄송합니다. 멀티 게임은 현재 준비중인 서비스입니다.|");
    out.println("+---------------------------------------------------+");
    
    out.println("게임 메뉴로 다시 돌아갑니다.");
    out.flush();
    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      
    }
  }
}

