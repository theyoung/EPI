package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // TODO - you fill in here.
    if(x < 0) return false;
    //171
    int len = (int)Math.floor(Math.log10(x)); //3

    while(0 < len){
      int leftNumber = (int) (x / Math.pow(10, len));
      int rightNumber = x % 10;

      if(leftNumber != rightNumber) return false;

      x %= Math.pow(10, len);
      x = (int)x / 10;
      len-=2;
    }

    return true;
  }

  @Test
  public void test(){
    Assertions.assertTrue(isPalindromeNumber(171));
    Assertions.assertTrue(isPalindromeNumber(21712));
    Assertions.assertFalse(isPalindromeNumber(21732));
    Assertions.assertFalse(isPalindromeNumber(-21732));
    Assertions.assertFalse(isPalindromeNumber(-21732));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
