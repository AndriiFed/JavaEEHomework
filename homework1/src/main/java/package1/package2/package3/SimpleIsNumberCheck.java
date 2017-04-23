package package1.package2.package3;

import org.apache.commons.lang.math.NumberUtils;

public class SimpleIsNumberCheck {
  public static void myIsNumber(String number) {
    if (NumberUtils.isNumber(number)) {
      System.out.println(number + " is number!");
    } else {
      System.out.println(number + " is not number!");
    }
  }
  public static void main(String[] args) {
    System.out.println("Homework1. External libraries.\r\nPlease input your number as command line argument");

    if (args.length > 0 && args[0] != null) {
      myIsNumber(args[0]);
    }
  }

}
