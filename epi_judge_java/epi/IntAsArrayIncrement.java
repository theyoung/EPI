package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayIncrement {
  //<1,2,9> + 1
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // TODO - you fill in here.
    Collections.reverse(A);
    int carryin = 1;

    for (int i = 0; i < A.size(); i++) {
      int cur = A.get(i) + carryin;
      carryin = 1 <= cur/10? 1 : 0;
      A.set(i,cur%10);
    }
    if(0 < carryin) A.add(1);

    Collections.reverse(A);
    return A;
  }

  @Test
  void test(){
    List<Integer> list = new ArrayList<Integer>(List.of(1,2,9));
    List<Integer> result = List.of(1, 3, 0);

    list = plusOne(list);

    Assertions.assertEquals(result, list);

    list = new ArrayList<Integer>(List.of(9,9,9));
    result = List.of(1, 0, 0, 0);

    list = plusOne(list);
    Assertions.assertEquals(result, list);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
