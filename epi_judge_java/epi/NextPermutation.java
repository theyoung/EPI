package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  //<1,0,3,2>
  //-> <1,2,0,3>

  //<3,2,1,0>
  //-> <>


  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // TODO - you fill in here.
    int left = perm.size()-2;
    while(0 <= left && perm.get(left) >= perm.get(left+1)) left--;

    if(left < 0) return List.of();

    for(int right = perm.size()-1; left < right; right--){
      if (perm.get(left) < perm.get(right)) {
        Collections.swap(perm, left, right);
        break;
      }
    }

    Collections.reverse(perm.subList(left + 1, perm.size()));

    return perm;
  }

  @Test
  void test(){
    List<Integer> input = new ArrayList<>(List.of(0,1,2,3));
    nextPermutation(input);
    Assertions.assertEquals(List.of(0,1,3,2), input);

    input = new ArrayList<>(List.of(0,1,3,2));// cur < cur+1 > cur+1
    nextPermutation(input);
    Assertions.assertEquals(List.of(0,2,1,3), input);

    input = new ArrayList<>(List.of(0,2,1,3));
    nextPermutation(input);

    Assertions.assertEquals(List.of(0,2,3,1), input);

    input = new ArrayList<>(List.of(0,2,3,1));
    nextPermutation(input);

    Assertions.assertEquals(List.of(0,3,1,2), input);

    input = new ArrayList<>(List.of(0,3,1,2));
    nextPermutation(input);

    Assertions.assertEquals(List.of(0,3,2,1), input);

    input = new ArrayList<>(List.of(0,3,2,1));
    nextPermutation(input);

    Assertions.assertEquals(List.of(1,0,2,3), input);

    input = new ArrayList<>(List.of(1,0,2,3));//<- cur > cur+1
    nextPermutation(input);

    Assertions.assertEquals(List.of(1,0,3,2), input);

    input = new ArrayList<>(List.of(1,0,3,2)); //<- cur > cur+1
    nextPermutation(input);

    Assertions.assertEquals(List.of(1,2,0,3), input);

    input = new ArrayList<>(List.of(3,1,4,6,0,2,5)); //<- cur > cur+1
    nextPermutation(input);

    //                              3,1,4,6,0,5,2
    Assertions.assertEquals(List.of(3,1,4,6,0,5,2), input);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
