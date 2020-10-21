package com.word.chain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class WordList extends HashMap<Character, ArrayList<String>> {

  private static final long serialVersionUID = 1L;

  public WordList() {
    for (int i = 97; i < 123; i++)
      put((char)i, new ArrayList<String>());
  }

  public void add(String word) {
    get(word.charAt(0)).add(word);
  }

  public void list() {
    Set<Character> keys = keySet();
    for (char key : keys) {
      System.out.print(key + " : ");
      for (String word : get(key))
        System.out.print(word + " ");
      System.out.println();
    }
  }

  public String attack(String word) {
    return random(word.charAt(0));
  }

  public String firstAttack() {
    while (true) {
      String word = random((char)(Math.random() * 26 + 97));
      if (word != null) {
        return word;
      }
    }
  }

  private String random(char firstChar) {
    ArrayList<String> words = get(firstChar);
    if (words.size() != 0) {
      return words.get((int)(Math.random() * words.size()));
    } else {
      return null;
    }
  }
}
