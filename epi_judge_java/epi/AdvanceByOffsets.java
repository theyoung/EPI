package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
public class AdvanceByOffsets {
  // <3,3,1,0,2,0,1>
  // -> tur or false
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    // TODO - you fill in here.
    int max = 0;

    for (int i = 0; i < maxAdvanceSteps.size(); i++) {
      if(max < i) return false;
      max = Math.max(maxAdvanceSteps.get(i)+i, max); //index 3
    }

    return true;
  }

  @Test
  void test(){
    List<Integer> input = List.of(3,3,1,0,2,0,1);

    Assertions.assertTrue(canReachEnd(input));

    input = List.of(3,2,0,0,2,0,1);

    Assertions.assertFalse(canReachEnd(input));

    input = List.of(1,0,2,0,2,0,1);

    Assertions.assertFalse(canReachEnd(input));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
