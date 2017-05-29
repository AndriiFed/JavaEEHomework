package model;

public class Person {
  private String login;
  private int age;

  public Person() {
  }

  public Person(String login, int age) {
    this.login = login;
    this.age = age;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
            "login='" + login + '\'' +
            ", age=" + age +
            '}';
  }

}
