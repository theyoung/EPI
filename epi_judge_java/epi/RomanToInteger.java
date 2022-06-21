package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

enum DIGITE {I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10),L(50),C(100),D(500),M(1000);
  private int num;
  DIGITE(int i) {
    this.num = i;
  }
  public int getValue(){
    return this.num;
  }
  public static DIGITE valueOf(char ch){
    String str = String.valueOf(ch);
    return DIGITE.valueOf(str);
  }
  public static DIGITE findByValue(int value){
    for(DIGITE v : values()){
      if(v.getValue() == value) return v;
    }
    return null;
  }
}
public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    // TODO - you fill in here.
    int result = 0;
    int right = 0;
    while(right < s.length()){
      char cur = s.charAt(right);
      if(right+1 < s.length() && cur == 'I' && (s.charAt(right+1) == 'V' || s.charAt(right+1) == 'X')){
        result += DIGITE.valueOf(s.charAt(right+1)).getValue() - 1;
        right++;
      } else if(right+1 < s.length() && cur == 'X' && (s.charAt(right+1) == 'L' || s.charAt(right+1) == 'C')){
        result += DIGITE.valueOf(s.charAt(right+1)).getValue() - 10;
        right++;
      } else if(right+1 < s.length() && cur == 'C' && (s.charAt(right+1) == 'D' || s.charAt(right+1) == 'M')){
        result += DIGITE.valueOf(s.charAt(right+1)).getValue() - 100;
        right++;
      } else {
        result += DIGITE.valueOf(s.charAt(right)).getValue();
      }
      right++;
    }

    return result;
  }

  public static int romanToIntegerReverse(String s) {
    // TODO - you fill in here.
    int result = DIGITE.valueOf(s.charAt(s.length() - 1)).getValue();

    for (int i = s.length() - 2; 0 <= i; i--) {
      int cur = DIGITE.valueOf(s.charAt(i)).getValue();
      int pre = DIGITE.valueOf(s.charAt(i+1)).getValue();

      if(cur < pre){
        result -= cur;
      } else {
        result += cur;
      }
    }

    return result;
  }

  @Test
  void test(){
    Assertions.assertEquals(DIGITE.I, DIGITE.findByValue(1));
    Assertions.assertEquals(DIGITE.X, DIGITE.findByValue(10));
    Assertions.assertEquals(DIGITE.X, DIGITE.valueOf("X"));
    Assertions.assertEquals(1, romanToInteger("I"));
    Assertions.assertEquals(10, romanToInteger("X"));
    Assertions.assertEquals(20, romanToInteger("XX"));
    Assertions.assertEquals(30, romanToInteger("XXX"));
    Assertions.assertEquals(59, romanToInteger("XXXXXIIIIIIIII"));
    Assertions.assertEquals(59, romanToInteger("LIIIIIIIII"));
    Assertions.assertEquals(59, romanToInteger("LVIIII"));
    Assertions.assertEquals(59, romanToInteger("LIX"));
    Assertions.assertEquals(9, romanToInteger("IX"));
    Assertions.assertEquals(4, romanToInteger("IV"));
    Assertions.assertEquals(40, romanToInteger("XL"));
  }

  @Test
  void testReverse(){
    Assertions.assertEquals(1, romanToIntegerReverse("I"));
    Assertions.assertEquals(10, romanToIntegerReverse("X"));
    Assertions.assertEquals(20, romanToIntegerReverse("XX"));
    Assertions.assertEquals(30, romanToIntegerReverse("XXX"));
    Assertions.assertEquals(59, romanToIntegerReverse("XXXXXIIIIIIIII"));
    Assertions.assertEquals(59, romanToIntegerReverse("LIIIIIIIII"));
    Assertions.assertEquals(59, romanToIntegerReverse("LVIIII"));
    Assertions.assertEquals(59, romanToIntegerReverse("LIX"));
    Assertions.assertEquals(9, romanToIntegerReverse("IX"));
    Assertions.assertEquals(4, romanToIntegerReverse("IV"));
    Assertions.assertEquals(40, romanToIntegerReverse("XL"));
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
