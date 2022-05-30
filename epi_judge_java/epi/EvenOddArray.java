package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class EvenOddArray {

  //왼쪽에 짝수, 오른쪽에 홀수를 배열하라
  public static void evenOdd(List<Integer> A) {
    // TODO - you fill in here.
    int left = 0;
    int right = A.size()-1;

    while(left < right){
      if(A.get(left)%2 == 1){//홀수
        Collections.swap(A, left, right);
        right--;
      } else {//짝수
        left++;
      }
    }

    return;
  }
  @EpiTest(testDataFile = "even_odd_array.tsv")
  public static void evenOddWrapper(TimedExecutor executor, List<Integer> A)
      throws Exception {
    List<Integer> before = new ArrayList<>(A);
    executor.run(() -> evenOdd(A));

    boolean inOdd = false;
    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) % 2 == 0) {
        if (inOdd) {
          throw new TestFailure("Even elements appear in odd part");
        }
      } else {
        inOdd = true;
      }
    }
    List<Integer> after = new ArrayList<>(A);
    Collections.sort(before);
    Collections.sort(after);
    if (!before.equals(after)) {
      throw new TestFailure("Elements mismatch");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
