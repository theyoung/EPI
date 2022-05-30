package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    long result = 0;
    int power = 32;
    long yPower = y << power;
    // 1111 = 15
    // 1000 -> 111
    //  100 -> 1
    //   10
    // 10 =  2 -> 7 = 111

    while(y <= x){
      while(yPower > x){
        yPower >>>= 1;
        --power;
      }
      result += 1 << power;
      x -= yPower;
    }
    return (int)result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
