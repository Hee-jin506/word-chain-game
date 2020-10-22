package com.word.chain.domain;

public class Member {
  private String id;
  private String name;
  private String password;
  private String computer;
  private WordList usedWords = new WordList();
  private int maxLevel;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getComputer() {
    return computer;
  }
  public void setComputer(String computer) {
    this.computer = computer;
  }
  public WordList getUsedWords() {
    return usedWords;
  }
  public void setUsedWords(WordList usedWords) {
    this.usedWords = usedWords;
  }
  public int getMaxLevel() {
    return maxLevel;
  }
  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }
}
