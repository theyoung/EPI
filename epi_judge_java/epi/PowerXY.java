package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // TODO - you fill in here.
    double result = 1.0;
    long power = y;
  //101
    while(0 < power){
      if((power & 1) != 0){
        result *= x;//4*2
      }
      x *= x;//4
      power >>>= 1;
    }

    return result;
  }

  @Test
  public void test(){
    double result = PowerXY.power(2, 4);
    Assertions.assertEquals(16., result, 0.01);
    result = PowerXY.power(2, 1);
    Assertions.assertEquals(2., result, 0.01);
    result = PowerXY.power(2, 2);
    Assertions.assertEquals(4., result, 0.01);
    result = PowerXY.power(2, 3);
    Assertions.assertEquals(8., result, 0.01);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
