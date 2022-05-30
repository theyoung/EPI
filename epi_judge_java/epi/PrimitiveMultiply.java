package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // TODO - you fill in here.
    long sum = 0;
    while(x != 0){
      if((x & 1) != 0){
        sum = add(sum, y);
      }
      x >>>= 1; //1일경우 한번 더 해줌
      y <<= 1; //한번 곱할 때 마다 자리수는 하나씩 늘려줘야 한다.
    }
    return sum;
  }

  public static long add(long a, long b){
    long sum = 0, carryin = 0, k = 1, tempA = a, tempB = b;

    while(tempA != 0 || tempB != 0){
      long ak = a & k;
      long bk = b & k;
      long carryout = (ak & bk) | (ak & carryin) | (bk & carryin);

      sum |= (ak ^ bk ^ carryin);
      carryin = carryout << 1;
      k = k << 1;
      tempA >>>= 1;
      tempB >>>= 1;
    }

    return sum | carryin;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
