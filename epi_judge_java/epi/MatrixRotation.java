package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    for(int row = 0; row < squareMatrix.size()/2; row++){
      for (int col = row; col < squareMatrix.size() - row - 1; col++) {
        int len = squareMatrix.size() - row - 1;
        int first = squareMatrix.get(row).get(col);
        int second = squareMatrix.get(col).get(len);
        int third = squareMatrix.get(len).get(len-col+row);
        int fourth = squareMatrix.get(len-col+row).get(row);

        squareMatrix.get(row).set(col, fourth);
        squareMatrix.get(col).set(len, first);
        squareMatrix.get(len).set(len-col+row, second);
        squareMatrix.get(len-col+row).set(row, third);
      }
    }

    return;
  }

  @Test
  void test(){
    //  1, 2, 3, 4
    //  5, 6, 7, 8
    //  9,10,11,12
    // 13,14,15,16
    List<List<Integer>> squareMatrix = new ArrayList<>();
    squareMatrix.add(new ArrayList<>(List.of(1,2,3,4,5)));
    squareMatrix.add(new ArrayList<>(List.of(6,7,8,9,10)));
    squareMatrix.add(new ArrayList<>(List.of(11,12,13,14,15)));
    squareMatrix.add(new ArrayList<>(List.of(16,17,18,19,20)));
    squareMatrix.add(new ArrayList<>(List.of(21,22,23,24,25)));

    rotateMatrix(squareMatrix);
  }
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
