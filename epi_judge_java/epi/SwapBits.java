package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  // x input
  // i left
  // j right
  public static long swapBits(long x, int i, int j) {
    // TODO - you fill in here.
    if( (((x >>> i)&1 ) ^ ((x >>> j)&1)) == 1){
      long bitMask = (1L << i) | (1L << j);
      x = x ^ bitMask;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
