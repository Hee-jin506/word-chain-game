package com.word.chain.domain;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WordList extends ArrayList<String> {

  private static final long serialVersionUID = 1L;

  @Override
  public boolean add(String word) {
    if (word.length() != 0)
      super.add(word);
      return true;
  }

  public void list(PrintWriter out, BufferedReader in) {
    for (String word : this)
      out.println(word + " ");
  }

  public String attack(String word) {
    ArrayList<String> candidates = new ArrayList<>();
    char first = word.charAt(word.length() - 1);
    for (String candidate : this)
      if (candidate.charAt(0) == first)
        candidates.add(candidate);
    return candidates.get((int)(Math.random() * candidates.size()));
  }

  public String firstAttack() {
    return this.get((int)(Math.random() * this.size()));
  }
}
