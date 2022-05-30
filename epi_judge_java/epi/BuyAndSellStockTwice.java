package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    List<Double> profits = new ArrayList<>();
    List<Double> profits2 = new ArrayList<>();

    double max = 0;
    int idx = 0;
    profits.add(0d);

    for(int i = 1; i < prices.size(); i++){
      if(prices.get(idx) < prices.get(i)){
        max = Math.max(prices.get(i) - prices.get(idx), max);
      } else {
        idx = i;
      }
      profits.add(max);
    }

    max = 0;
    idx = prices.size() - 1;
    profits2.add(0d);

    for(int i = prices.size()-2; 0 <= i; i--){
      if(prices.get(idx) <= prices.get(i)){
        idx = i;
      } else {
        max = Math.max(prices.get(idx) - prices.get(i),max);
      }
      profits2.add(max);
    }
    Collections.reverse(profits2);
    //0d,1d,2d,3d,100d,90d,91d,92d,93d,200d
    //0,   1,  2,  3, 100,100,100,100,100,200
    //200,199,198,197,110,110,109,108,107,0

    max = 0;
    for (int i = 0; i < prices.size(); i++) {
      max = Math.max(profits.get(i)+profits2.get(i), max);
    }

    return max;
  }


  @Test
  public void test(){


    List<Double> input = List.of(0d,1d,2d,3d,100d,90d,91d,92d,93d,200d);
    Assertions.assertEquals(210, buyAndSellStockTwice(input));

    input = List.of(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d);
    Assertions.assertEquals(9, buyAndSellStockTwice(input));

    input = List.of(10d,9d,8d,7d,6d,5d,4d,3d,2d,1d);
    Assertions.assertEquals(0, buyAndSellStockTwice(input));

    input = List.of(10d,10d,10d,10d,10d,10d,10d,10d,10d,10d);
    Assertions.assertEquals(0, buyAndSellStockTwice(input));

    input = List.of();
    Assertions.assertEquals(0, buyAndSellStockTwice(input));
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
