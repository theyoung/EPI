package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class AlternatingArray {
  public static void rearrange(List<Integer> A) {
    // TODO - you fill in here.
    //1,2,3,4,5,6,7
    //7,6,5,4,3,2,1
    //6,7,4,5,2,3,1
    //1,100,200,50,0,2,40
    //1,200,50,100,0,40,2

    for (int i = 0; i < A.size()-1; i++) {
      if(i%2 == 1){ //cur > cur+1
        if(A.get(i) < A.get(i+1)){
          Collections.swap(A,i,i+1);
        }
      } else {//cur < cur+1
        if(A.get(i) > A.get(i+1)){
          Collections.swap(A,i,i+1);
        }
      }
    }

    return;
  }

  @Test
  public void test(){
    List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
    List<Integer> expect = List.of(1,3,2,5,4,7,6);

    rearrange(input);

    Assertions.assertEquals(expect, input);

    input = new ArrayList<>(List.of(7,6,5,4,3,2,1));
    expect = List.of(6,7,4,5,2,3,1);

    rearrange(input);

    Assertions.assertEquals(expect, input);

    input = new ArrayList<>(List.of(1,100,200,50,0,2,40));
    expect = List.of(1,200,50,100,0,40,2);

    rearrange(input);

    Assertions.assertEquals(expect, input);


  }

  private static void checkOrder(List<Integer> A) throws TestFailure {
    for (int i = 0; i < A.size(); ++i) {
      if ((i % 2) != 0) {
        if (A.get(i) < A.get(i - 1)) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.RESULT, A)
              .withMismatchInfo(
                  i, String.format("A[%d] <= A[%d]", i - 1, i),
                  String.format("%d > %d", A.get(i - 1), A.get(i)));
        }
        if (i < A.size() - 1) {
          if (A.get(i) < A.get(i + 1)) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.RESULT, A)
                .withMismatchInfo(
                    i, String.format("A[%d] >= A[%d]", i, i + 1),
                    String.format("%d < %d", A.get(i), A.get(i + 1)));
          }
        }
      } else {
        if (i > 0) {
          if (A.get(i - 1) < A.get(i)) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.RESULT, A)
                .withMismatchInfo(
                    i, String.format("A[%d] >= A[%d]", i - 1, i),
                    String.format("%d < %d", A.get(i - 1), A.get(i)));
          }
        }
        if (i < A.size() - 1) {
          if (A.get(i + 1) < A.get(i)) {
            throw new TestFailure()
                .withProperty(TestFailure.PropertyName.RESULT, A)
                .withMismatchInfo(
                    i, String.format("A[%d] <= A[%d]", i, i + 1),
                    String.format("%d > %d", A.get(i), A.get(i + 1)));
          }
        }
      }
    }
  }

  @EpiTest(testDataFile = "alternating_array.tsv")
  public static void rearrangeWrapper(TimedExecutor executor, List<Integer> A)
      throws Exception {
    List<Integer> result = new ArrayList<>(A);
    executor.run(() -> rearrange(result));

    TestUtils.assertAllValuesPresent(A, result);
    checkOrder(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AlternatingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
