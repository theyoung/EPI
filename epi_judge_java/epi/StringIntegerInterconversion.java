package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    StringBuilder sb = new StringBuilder();
    boolean sign = true;
    if (x < 0) {
      sign = false;
      x *= -1;
    }

    while(0 != x){
      char remainder = (char) (Math.abs(x % 10) + '0');
      sb.append(remainder);
      x = x/10;
    }
    if(sign == false) sb.append('-');
    sb.reverse();
    return sb.length() == 0 ? "0" : sb.toString();
  }
  public static int stringToInt(String s) {
    // TODO - you fill in here.
    if(s.length() == 0) return 0;

    int result = 0;
    int left = 0;
    boolean sign = true;
    if(s.charAt(0) == '+' || s.charAt(0) == '-') {
      sign = s.charAt(0) == '+';
      left++;
    }

    while(left < s.length()){
      result = (result*10) + (s.charAt(left) - '0');
      left++;
    }

    return sign == false? -result : result;
  }

  @Test
  void testI2S(){
    Assertions.assertEquals("1", intToString(1));
    Assertions.assertEquals("123", intToString(123));
    Assertions.assertEquals("145632132", intToString(145632132));
    Assertions.assertEquals("-4176473", intToString(-4176473));
    Assertions.assertEquals("-2203", intToString(-2203));
    Assertions.assertEquals("0", intToString(0));
    Assertions.assertEquals("-2147483648", intToString(-2147483648));
  }

  @Test
  void testS2I(){
    Assertions.assertEquals(1, stringToInt("1"));
    Assertions.assertEquals(123, stringToInt("123"));
    Assertions.assertEquals(145632132, stringToInt("145632132"));
    Assertions.assertEquals(1456321320, stringToInt("1456321320"));
    Assertions.assertEquals(1456321320, stringToInt("+1456321320"));
    Assertions.assertEquals(-1456321320, stringToInt("-1456321320"));
    Assertions.assertEquals(0, stringToInt(""));
    Assertions.assertEquals(-4176473, stringToInt("-4176473"));
    Assertions.assertEquals(Integer.parseInt(intToString(-4176473)), stringToInt("-4176473"));
    Assertions.assertEquals(Integer.parseInt(intToString(-2203)), stringToInt("-2203"));
    Assertions.assertEquals(0, stringToInt("0"));
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
