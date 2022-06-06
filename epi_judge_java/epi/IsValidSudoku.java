package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // TODO - you fill in here.
    // rows are valid?
    int[] arr = new int[10];
    for (int row = 0; row < partialAssignment.size(); row++) {
      List<Integer> cols = partialAssignment.get(row);
      Arrays.fill(arr, 0);

      for(int col = 0; col < cols.size(); col++){
        if(cols.get(col)==0) continue;
        if(arr[cols.get(col)] == 0){
          arr[cols.get(col)]++;
        } else {
          return false;
        }
      }
    }

    // cols are valid?
    for (int col = 0; col < 9; col++) {
      Arrays.fill(arr, 0);
      for(int row = 0; row < 9; row++){
        if(partialAssignment.get(row).get(col) == 0) continue;
        if(arr[partialAssignment.get(row).get(col)] == 0){
          arr[partialAssignment.get(row).get(col)]++;
        } else {
          return false;
        }
      }
    }

    // rects are valid
    for (int rectRow = 0; rectRow < 3; rectRow++) {
      int rowStartPosition = rectRow * 3;
      for (int rectCol = 0; rectCol < 3; rectCol++) {
        int colStartPosition = rectCol * 3;
        Arrays.fill(arr, 0);

        for (int i = 0; i < 9; i++) {
          int row = (i / 3) + rowStartPosition;
          int col = (i % 3) + colStartPosition;
          if(partialAssignment.get(row).get(col) == 0) continue;
          if(arr[partialAssignment.get(row).get(col)] == 0){
            arr[partialAssignment.get(row).get(col)]++;
          } else {
            return false;
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
