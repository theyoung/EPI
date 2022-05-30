package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SortedArrayRemoveDups {
  //<2,3,5,5,7,11,11,11,13>
  //<2,3,5,7,11,13,...>
  // Returns the number of valid entries after deletion.
  public static int deleteDuplicates(List<Integer> A) {
    // TODO - you fill in here.
    if(A.size() == 0) return 0;

    int index = 0;

    //<2,3,5,5,7,11,11,11,13>
    // 2 3 5 7,11,13,11,11,5
    for(int i = 1; i < A.size(); i++){
        if(!A.get(index).equals(A.get(i))){
          Collections.swap(A,index+1, i);
          index++;
        }
    }

    return index+1;
  }

  @Test
  public void test(){
    List<Integer> input = new ArrayList<>(List.of(2, 3, 5, 5, 7, 11, 11, 11, 13));
    List<Integer> expect = List.of(2, 3, 5, 7, 11, 13, 11, 11, 13);

    Assertions.assertEquals(6, deleteDuplicates(input));

    input = new ArrayList<>(List.of(2, 3, 3, 3, 3, 3, 3, 3, 3));

    Assertions.assertEquals(2, deleteDuplicates(input));

    input = new ArrayList<>(List.of(3, 3, 3, 3, 3, 3, 3, 3, 3));

    Assertions.assertEquals(1, deleteDuplicates(input));

    input = new ArrayList<>();

    Assertions.assertEquals(0, deleteDuplicates(input));

    input = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

    Assertions.assertEquals(9, deleteDuplicates(input));
  }

  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
