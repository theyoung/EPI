package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // TODO - you fill in here.
    x = x ^ (x>>>32);
    x = x ^ (x>>>16);
    x = x ^ (x>>>8);
    x = x ^ (x>>>4);
    x = x ^ (x>>>2);
    x = x ^ (x>>>1);

    return (short)(x & 1);
  }

//  public static void main(String[] args) {
//
//    System.out.println(parity(0b1111111111111111111111111111111111111111111111111111111111111110l));
//  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
