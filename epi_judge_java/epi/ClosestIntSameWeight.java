package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  //다시 풀어야함
  public static long closestIntSameBitCount(long x) {
    // TODO - you fill in here.
    for (int i = 0; i < 63 - 1; i++) {
      if(((x>>>i) & 1) != ((x>>>(i+1)) & 1) ){
        x = x ^ ((1L << i) | (1L << (i + 1)));
        return x;
      }
    }

    throw new IllegalArgumentException("All bits are 0 or 1");
  }

  @Test
  void test(){
    long result = closestIntSameBitCount(0b0111L);
    Assertions.assertEquals(0b1011L,result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
