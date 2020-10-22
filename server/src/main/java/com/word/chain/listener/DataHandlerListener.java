package com.word.chain.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.word.chain.domain.Member;
import com.word.chain.domain.WordList;
import com.word.context.ApplicationContextListener;

// 게시물, 회원, 프로젝트, 작업 데이터를 파일에서 로딩하고 파일로 저장하는 일을 한다.
public class DataHandlerListener implements ApplicationContextListener {

  List<Member> memberList = new LinkedList<>();
  File memberFile = new File("./members.json"); // 회원을 저장할 파일 정보

  ArrayList<WordList> levelList = new ArrayList<>();
  File levelFile = new File("./levelWords.json");

  @Override
  public void contextInitialized(Map<String,Object> context) {
    // 애플리케이션의 서비스가 시작되면 먼저 파일에서 데이터를 로딩한다.
    // 파일에서 데이터 로딩
    loadData(memberList, memberFile, Member[].class);
    loadData(levelList, levelFile, WordList[].class);

    // 옵저버가 파일에서 데이터(게시글,회원,프로젝트,작업)를 읽어
    // List 컬렉션에 저장한 다음,
    // 발행자(App 객체)가 사용할 수 있도록 맵 객체에 담아서 공유한다.
    context.put("memberList", memberList);
    context.put("levelList", levelList);
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    // 애플리케이션 서비스가 종료되면 컬렉션에 보관된 객체를 파일에 저장한다.
    // 데이터를 파일에 저장
    saveData(memberList, memberFile);
    saveData(levelList, levelFile);
  }

  private <T> void loadData(
      Collection<T> list, // 객체를 담을 컬렉션
      File file, // JSON 문자열이 저장된 파일
      Class<T[]> clazz // JSON 문자열을 어떤 타입의 배열로 만들 것인지 알려주는 클래스 정보
      ) {
    BufferedReader in = null;

    try {
      in = new BufferedReader(new FileReader(file));
      list.addAll(Arrays.asList(new Gson().fromJson(in, clazz)));
      System.out.printf("'%s' 파일에서 총 %d 개의 객체를 로딩했습니다.\n",
          file.getName(), list.size());

    } catch (Exception e) {
      System.out.printf("'%s' 파일 읽기 중 오류 발생! - %s\n",
          file.getName(), e.getMessage());

    } finally {
      try {
        in.close();
      } catch (Exception e) {
      }
    }
  }

  private void saveData(Collection<?> list, File file) {
    BufferedWriter out = null;

    try {
      out = new BufferedWriter(new FileWriter(file));

      Gson gson = new Gson();
      String jsonStr = gson.toJson(list);
      out.write(jsonStr);

      out.flush();

      System.out.printf("총 %d 개의 객체를 '%s' 파일에 저장했습니다.\n",
          list.size(), file.getName());

    } catch (IOException e) {
      System.out.printf("객체를 '%s' 파일에  쓰는 중 오류 발생! - %s\n",
          file.getName(), e.getMessage());

    } finally {
      try {
        out.close();
      } catch (IOException e) {
      }
    }
  }
}
