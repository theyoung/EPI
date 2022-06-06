package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrdering {

  // 1,2,3
  // 4,5,6
  // 7,8,9
  // -> 1,2,3,6,9,8,7,4,5

  @EpiTest(testDataFile = "spiral_ordering.tsv")
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // TODO - you fill in here.
    if(squareMatrix.size() == 0) return List.of();
    int rowStart = 0, colStart = 0, rowEnd = squareMatrix.size()-1, colEnd = squareMatrix.get(0).size()-1;
    List<Integer> result = new ArrayList<>();

    while(rowStart <= rowEnd && colStart <= colEnd){
      result.addAll(takeOneLayer(squareMatrix, rowStart, colStart, rowEnd, colEnd));
      rowStart++;
      colStart++;
      rowEnd--;
      colEnd--;
    }
    return result;
  }

  public static List<Integer> takeOneLayer(List<List<Integer>> squareMatrix, int rowStart, int colStart, int rowEnd, int colEnd){

    List<Integer> result = new ArrayList<>();
    //row
    for(int col = colStart; col <= colEnd; col++){
      result.add(squareMatrix.get(rowStart).get(col));
    }

    //right-col
    for(int row = rowStart+1; row <= rowEnd; row++){
      result.add(squareMatrix.get(row).get(colEnd));
    }

    //bottom-reverse-col
    for(int col = colEnd-1; colStart <= col && rowStart < rowEnd; col--){
      result.add(squareMatrix.get(rowEnd).get(col));
    }

    //bottom-up-left-row
    for(int row = rowEnd-1; rowStart < row && colStart < colEnd; row--){
      result.add(squareMatrix.get(row).get(colStart));
    }

    return result;
  }

  @Test
  void test(){
    List<List<Integer>> input = new ArrayList<List<Integer>>();

    input.add(List.of(1,2,3));
    input.add(List.of(4,5,6));
    input.add(List.of(7,8,9));

    Assertions.assertEquals(List.of(1,2,3,6,9,8,7,4,5), matrixInSpiralOrder(input));

    input.clear();

    input.add(List.of(1,2,3,10));
    input.add(List.of(4,5,6,11));
    input.add(List.of(7,8,9,12));

    Assertions.assertEquals(List.of(1,2,3,10,11,12,9,8,7,4,5,6), matrixInSpiralOrder(input));

    input.clear();

    input.add(List.of(1,2,3,10));
    input.add(List.of(4,5,6,11));
    input.add(List.of(7,8,9,12));
    input.add(List.of(16,15,14,13));

    Assertions.assertEquals(List.of(1,2,3,10,11,12,13,14,15,16,7,4,5,6,9,8), matrixInSpiralOrder(input));

    input.clear();

  }

  @Test
  void testTakeOneLayer(){
    List<List<Integer>> input = new ArrayList<List<Integer>>();

    input.add(List.of(1,2,3));
    input.add(List.of(4,5,6));
    input.add(List.of(7,8,9));

    Assertions.assertEquals(List.of(1,2,3,6,9,8,7,4),takeOneLayer(input, 0, 0, 2, 2));
    Assertions.assertEquals(List.of(5),takeOneLayer(input, 0+1, 0+1, 2-1, 2-1));

    input.clear();

    input.add(List.of(1,2,3,10));
    input.add(List.of(4,5,6,11));
    input.add(List.of(7,8,9,12));

    Assertions.assertEquals(List.of(1,2,3,10,11,12,9,8,7,4),takeOneLayer(input, 0, 0, 2, 3));

    input.clear();

    input.add(List.of(1,2,3,10));
    input.add(List.of(4,5,6,11));
    input.add(List.of(7,8,9,12));

    Assertions.assertEquals(List.of(5,6),takeOneLayer(input, 0+1, 0+1, 2-1, 3-1));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
