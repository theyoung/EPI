package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  //pivoitIndex보다 작으면 왼쪽 크면 오른쪽
  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    // TODO - you fill in here.
    int left = 0;
    int right = A.size()-1;
    int pivot = A.get(pivotIndex).ordinal();

    for (int i = 0; i < A.size(); i++) {
      if(A.get(i).ordinal() < pivot){
        Collections.swap(A,i,left);
        left++;
      }
    }

    for (int i = A.size()-1; 0 <= i; i--) {
      if(pivot < A.get(i).ordinal()){
        Collections.swap(A,i,right);
        right--;
      } else if(A.get(i).ordinal() < pivot) {
        break;
      }
    }
    return;
  }

  @Test
  void test(){
    List<Color> list = new ArrayList<>();
    list.add(Color.RED);
    list.add(Color.WHITE);
    list.add(Color.BLUE);
    list.add(Color.WHITE);
    list.add(Color.WHITE);
    list.add(Color.RED);
    list.add(Color.BLUE);

    dutchFlagPartition(1, list);

    int[] expects = List.of(Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLUE).stream().mapToInt(i->i.ordinal()).toArray();
    int[] results = list.stream().mapToInt(i->i.ordinal()).toArray();
    Assertions.assertArrayEquals(expects, results);

    dutchFlagPartition(2, list);

    expects = List.of(Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLUE).stream().mapToInt(i->i.ordinal()).toArray();
    results = list.stream().mapToInt(i->i.ordinal()).toArray();
    Assertions.assertArrayEquals(expects, results);

    dutchFlagPartition(0, list);

    expects = List.of(Color.RED,Color.RED,Color.WHITE,Color.WHITE,Color.WHITE,Color.BLUE,Color.BLUE).stream().mapToInt(i->i.ordinal()).toArray();
    results = list.stream().mapToInt(i->i.ordinal()).toArray();
    Assertions.assertArrayEquals(expects, results);
  }

  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
