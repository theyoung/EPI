package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {

  // <1,9,3,7,0,7,7,2,1>
  // <-7,6,1,8,3,8,2,5,7,2,8,7>
  // <-1,4,7,5,7,3,9,5,2,5,8,9,6,7,6,4,1,2,9,2,7>
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // TODO - you fill in here.
    boolean minus = (num1.get(0) < 0 ? -1 : 1) != (num2.get(0) < 0 ? -1 : 1);
    if(num1.get(0) < 0) num1.set(0, num1.get(0) * -1);
    if(num2.get(0) < 0) num2.set(0, num2.get(0) * -1);

    Collections.reverse(num1);
    Collections.reverse(num2);
    List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size()+num2.size(),0));

    int startIndex = 0;
    for (int i = 0; i < num1.size(); i++) {
      int baseNum = num1.get(i);

      for(int j = 0; j < num2.size(); j++){
        int targetNum = num2.get(j) * baseNum;
        int quotient = targetNum/10;
        int remainder = targetNum%10;

        result.set(startIndex+j, result.get(startIndex+j)+remainder);
        result.set(startIndex+j+1, result.get(startIndex+j+1)+quotient);
      }
      startIndex++;//increase 10
    }

    int quotient = 0;
    for (int i = 0; i < result.size(); i++) {
      int targetNum = result.get(i) + quotient;
      quotient = targetNum/10;
      int remainder = targetNum%10;
      result.set(i, remainder);
    }

    Collections.reverse(result);
    int s = 0;
    for(int i = 0; i < result.size(); s++,i++){
      if(0 < result.get(i)) break;
    }

    List<Integer> real = result.subList(s,result.size());
    if(real.size()==0) return List.of(0);
    if(minus) real.set(0, real.get(0) * -1);
    return real;
  }

  @Test
  void test(){
    List<Integer> num1 = new ArrayList<Integer>(List.of(1,9,3,7,0,7,7,2,1));
    List<Integer> num2 = new ArrayList<Integer>(List.of(-7,6,1,8,3,8,2,5,7,2,8,7));
    List<Integer> expect = List.of(-1,4,7,5,7,3,9,5,2,5,8,9,6,7,6,4,1,2,9,2,7);

    List<Integer> result = multiply(num1, num2);
    Assertions.assertEquals(expect, result);

    num1 = new ArrayList<Integer>(List.of(9));
    num2 = new ArrayList<Integer>(List.of(9));
    expect = List.of(8,1);

    result = multiply(num1, num2);
    Assertions.assertEquals(expect, result);

    num1 = new ArrayList<Integer>(List.of(1,9));
    num2 = new ArrayList<Integer>(List.of(1));
    expect = List.of(1,9);

    result = multiply(num1, num2);
    Assertions.assertEquals(expect, result);

    num1 = new ArrayList<Integer>(List.of(1,9));
    num2 = new ArrayList<Integer>(List.of(0));
    expect = List.of(0);

    result = multiply(num1, num2);
    Assertions.assertEquals(expect, result);

    num1 = new ArrayList<Integer>(List.of(1, 3, 1, 4, 1, 2));
    num2 = new ArrayList<Integer>(List.of(-1, 3, 1, 3, 3, 3, 2));
    expect = List.of(-1, 7, 2, 5, 8, 7, 5, 8, 4, 7, 8, 4);

    result = multiply(num1, num2);
    Assertions.assertEquals(expect, result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
