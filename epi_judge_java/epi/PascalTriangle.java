package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")
  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    // TODO - you fill in here.
    List<List<Integer>> result = new ArrayList<>();
    if(numRows < 1) return result;

    result.add(List.of(1));

    for (int i = 1; i < numRows; i++) {
      List<Integer> preList = result.get(result.size() - 1);
      List<Integer> curList = new ArrayList<>();

      for (int j = 0; j <= i; j++) {
        int upVal = j < preList.size() ? preList.get(j) : 0;
        int preVal = (0 <= j-1 && j-1 < preList.size()) ? preList.get(j-1) : 0;
        curList.add(upVal + preVal);
      }
      result.add(curList);
    }
    return result;
  }

  @Test
  @DisplayName("Default Test")
  void test(){
    Assertions.assertEquals(List.of(List.of(1)), generatePascalTriangle(1));
    Assertions.assertEquals(List.of(List.of(1),List.of(1,1)), generatePascalTriangle(2));
    Assertions.assertEquals(List.of(List.of(1),List.of(1,1),List.of(1,2,1)), generatePascalTriangle(3));
    Assertions.assertEquals(List.of(List.of(1),List.of(1,1),List.of(1,2,1),List.of(1,3,3,1)), generatePascalTriangle(4));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
