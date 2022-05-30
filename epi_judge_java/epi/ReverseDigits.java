package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseDigits {
  //
  // 42 -> 24
  // -314 -> -413

  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    // TODO - you fill in here.
    int remaining = Math.abs(x);
    int result = 0;

    while(0 < remaining){
      result = result * 10 + (remaining % 10); //가장 우측 값 반환
      remaining = remaining/10;
    }

    return x < 0 ? -result : result ;
  }

  @Test
  public void test(){
    long result = reverse(123);
    Assertions.assertEquals(321, result);

    result = reverse(-123);
    Assertions.assertEquals(-321, result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
