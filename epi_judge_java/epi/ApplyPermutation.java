package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ApplyPermutation {
  //perm : <2,0,1,3> -> <0,1,2,3>
  //A : <a,b,c,d> -> <b,c,a,d>
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {

    for (int i = 0; i < perm.size(); i++) {
      int cur = i;//0
      int next = perm.get(i);//2
      while(0 <= next && 0 <= perm.get(next) && next != perm.get(next)){
        int temp = perm.get(next);//1
        Collections.swap(perm,cur,next);//2,0,1,3 -> 1,0,2,3
        Collections.swap(A,cur,next); //2,0,1,3
        perm.set(next, perm.get(next) * -1);//2,0,-1,3
        next = perm.get(cur);//1
      }
    }
    return;
  }

  @Test
  void test(){
    List<Integer> perm = new ArrayList<>(List.of(2,0,1,3));
    List<Integer> list = new ArrayList<>(List.of(2,0,1,3));
    applyPermutation(perm, list);
    Assertions.assertEquals(List.of(0,1,2,3), list);
//
    perm = new ArrayList<>(List.of(1,0));
    list = new ArrayList<>(List.of(1,0));
    applyPermutation(perm, list);
    Assertions.assertEquals(List.of(0,1), list);

    perm = new ArrayList<>(List.of(1,5,4,3,6,7,0,2));
    list = new ArrayList<>(List.of(1,5,4,3,6,7,0,2));
    applyPermutation(perm, list);
    Assertions.assertEquals(List.of(0,1,2,3,4,5,6,7), list);
  }

  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
