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
     loadData(memberList, memberFile, Member[].class);
     loadData(levelList, levelFile, WordList[].class);

//     basicData(levelList);
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
      e.printStackTrace();

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
  
  public static void basicData(ArrayList<WordList> levelList) {
    WordList words = new WordList();
    words.add("banana");
    words.add("draw");
    words.add("fall");
    words.add("get");
    words.add("issue");
    words.add("keep");
    words.add("place");
    words.add("quality");
    words.add("take");
    words.add("work");
    words.add("xray");
    words.add("yet");
    words.add("zone");
    levelList.add(words);
    
    words = new WordList();
    words.add("advance");
    words.add("bad");
    words.add("drive");
    words.add("exercise");
    words.add("good");
    words.add("indicate");
    words.add("just");
    words.add("know");
    words.add("leave");
    words.add("notice");
    words.add("present");
    words.add("quiet");
    words.add("strike");
    words.add("then");
    words.add("urge");
    words.add("wear");
    words.add("xmas");
    words.add("yield");
    words.add("zero");
    levelList.add(words);
    
    words = new WordList();
    words.add("ask");
    words.add("bed");
    words.add("direct");
    words.add("even");
    words.add("find");
    words.add("hit");
    words.add("jealous");
    words.add("keen");
    words.add("mind");
    words.add("operate");
    words.add("quote");
    words.add("right");
    words.add("treat");
    words.add("view");
    words.add("wonder");
    words.add("xenophobia");
    words.add("year");
    words.add("zoo");
    levelList.add(words);
    
    words = new WordList();
    words.add("adopt");
    words.add("box");
    words.add("carry");
    words.add("extend");
    words.add("fancy");
    words.add("grow");
    words.add("hard");
    words.add("join");
    words.add("kind");
    words.add("measure");
    words.add("name");
    words.add("opposite");
    words.add("positive");
    words.add("question");
    words.add("spot");
    words.add("there");
    words.add("unless");
    words.add("whatever");
    words.add("xenon");
    words.add("yawn");
    words.add("zoom");
    levelList.add(words);
    
    words = new WordList();
    words.add("appeal");
    words.add("bear");
    words.add("credit");
    words.add("executive");
    words.add("figure");
    words.add("general");
    words.add("hang");
    words.add("impose");
    words.add("journey");
    words.add("key");
    words.add("matter");
    words.add("need");
    words.add("observe");
    words.add("please");
    words.add("range");
    words.add("travel");
    words.add("unique");
    words.add("vision");
    words.add("withdraw");
    words.add("xylem");
    words.add("youth");
    words.add("zip");
    levelList.add(words);
    
    words = new WordList();
    words.add("accept");
    words.add("bother");
    words.add("condition");
    words.add("develop");
    words.add("experience");
    words.add("feature");
    words.add("give");
    words.add("head");
    words.add("instruction");
    words.add("knit");
    words.add("like");
    words.add("mark");
    words.add("nation");
    words.add("occation");
    words.add("prompt");
    words.add("report");
    words.add("service");
    words.add("twelve");
    words.add("use");
    words.add("vehicle");
    words.add("well");
    words.add("xml");
    words.add("yellow");
    words.add("zodiac");
    levelList.add(words);
    
    words = new WordList();
    words.add("address");
    words.add("beer");
    words.add("cost");
    words.add("due");
    words.add("establish");
    words.add("fiction");
    words.add("gather");
    words.add("hear");
    words.add("junior");
    words.add("kid");
    words.add("look");
    words.add("mess");
    words.add("negative");
    words.add("order");
    words.add("practice");
    words.add("queen");
    words.add("release");
    words.add("track");
    words.add("unit");
    words.add("virtue");
    words.add("water");
    words.add("xfactor");
    words.add("yard");
    words.add("zealous");
    levelList.add(words);

    words = new WordList();
    words.add("against");
    words.add("become");
    words.add("discipline");
    words.add("exhibit");
    words.add("funtion");
    words.add("genius");
    words.add("happen");
    words.add("increase");
    words.add("joy");
    words.add("knot");
    words.add("load");
    words.add("miss");
    words.add("nature");
    words.add("play");
    words.add("reason");
    words.add("subject");
    words.add("talk");
    words.add("usual");
    words.add("very");
    words.add("word");
    words.add("xenodochium");
    words.add("yes");
    words.add("zest");
    levelList.add(words);
    
    words = new WordList();
    words.add("account");
    words.add("board");
    words.add("claer");
    words.add("drop");
    words.add("ever");
    words.add("fit");
    words.add("garage");
    words.add("indeed");
    words.add("jump");
    words.add("kick");
    words.add("level");
    words.add("master");
    words.add("nerveous");
    words.add("promise");
    words.add("query");
    words.add("result");
    words.add("sense");
    words.add("urgent");
    words.add("vote");
    words.add("worth");
    words.add("xylogen");
    words.add("yell");
    words.add("zenith");
    levelList.add(words);

    words = new WordList();
    words.add("approach");
    words.add("buy");
    words.add("contack");
    words.add("decide");
    words.add("ego");
    words.add("foot");
    words.add("ground");
    words.add("host");
    words.add("improve");
    words.add("jam");
    words.add("kingdom");
    words.add("lack");
    words.add("maintain");
    words.add("never");
    words.add("object");
    words.add("presence");
    words.add("queue");
    words.add("receive");
    words.add("stuff");
    words.add("though");
    words.add("unlike");
    words.add("volume");
    words.add("write");
    words.add("xanthein");
    words.add("yarn");
    words.add("zinc");
    levelList.add(words);
  }
}
