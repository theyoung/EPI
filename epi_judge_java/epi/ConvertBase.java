package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ConvertBase {

  //numAssString의 base는 b1
  //base b2로 변경해라
  @EpiTest(testDataFile = "convert_base.tsv")
  public static String convertBase(String numAsString, int b1, int b2) {
    // TODO - you fill in here.
    int original = 0;
    int left = 0;
    boolean sign = true;
    if(numAsString.charAt(0) == '-' || numAsString.charAt(0) == '+' ) {
      sign = numAsString.charAt(0) == '+';
      left++;
    }

    while (left < numAsString.length()) {
      char ch = numAsString.charAt(left);
      int cur;
      if(Character.isDigit(ch)){
        cur = ch -'0';
      } else {
        cur = ch - 'A' + 10;
      }
      original = (original * b1) + cur;

      left++;
    }
    if(original == 0) return "0";

    StringBuilder sb = new StringBuilder();

    while(original != 0){
      int remainder = original%b2;
      if(9 < remainder){
        sb.append((char) ('A' + (remainder - 10)));
      } else {
        sb.append(remainder);
      }
      original = original/b2;
    }
    if(!sign) sb.append('-');
    sb.reverse();
    return sb.toString();
  }

  @Test
  void Test(){
    Assertions.assertAll("Simple Test",
      ()-> Assertions.assertEquals("10", convertBase("A", 16, 10)),
      ()-> Assertions.assertEquals("11", convertBase("B", 16, 10)),
      ()-> Assertions.assertEquals("12", convertBase("C", 16, 10)),
      ()-> Assertions.assertEquals("13", convertBase("D", 16, 10)),
      ()-> Assertions.assertEquals("15", convertBase("F", 16, 10)),
      ()-> Assertions.assertEquals("2", convertBase("10", 2, 10)),
      ()-> Assertions.assertEquals("0", convertBase("0", 10, 16))
    );

    Executable a = () -> Assertions.assertEquals("A", convertBase("10", 10, 16));
    Executable b = () -> Assertions.assertEquals("1A7", convertBase("615", 7, 13));

    Assertions.assertAll("Failure Testcase", a,b);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
