package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    // TODO - you fill in here.
    StringBuilder sb = new StringBuilder();
    sb.append("1");

    for(int i = 2; i <= n; i++){
      String cur = sb.toString();
      sb.setLength(0);

      int left = 0;
      int right = 1;
      for (; right < cur.length(); right++) {
        if(cur.charAt(left) != cur.charAt(right)){
          sb.append(right-left);
          sb.append(cur.charAt(left));
          left = right;
        }
      }

      sb.append(right-left);
      sb.append(cur.charAt(left));
    }

    return sb.toString();
  }

  @Test
  void test(){
    Executable ONE = () -> Assertions.assertEquals("1", lookAndSay(1));
    Executable TWO = () -> Assertions.assertEquals("11", lookAndSay(2));
    Executable THREE = () -> Assertions.assertEquals("21", lookAndSay(3));
    Executable FOUR = () -> Assertions.assertEquals("1211", lookAndSay(4));
    Executable FIVE = () -> Assertions.assertEquals("111221", lookAndSay(5));

    Assertions.assertAll(ONE,TWO,THREE,FOUR,FIVE);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
