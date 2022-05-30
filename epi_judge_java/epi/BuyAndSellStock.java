package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
public class BuyAndSellStock {

  //<310,315,275,295,260,270,290,230,255,250>
  // 260->290 30
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // TODO - you fill in here.
    if(prices.size()==0) return 0;

    double profit = 0;
    int min = 0;

    for (int i = 1; i < prices.size(); i++) {
      if(prices.get(min) < prices.get(i)){
        profit = Math.max(prices.get(i) - prices.get(min),profit);
      } else {
        min = i;
      }
    }

    return profit;
  }

  @Test
  public void test(){
    List<Double> input = List.of(310d,315d,275d,295d,260d,270d,290d,230d,255d,250d);
    Assertions.assertEquals(30, computeMaxProfit(input));

    input = List.of(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d);
    Assertions.assertEquals(9, computeMaxProfit(input));

    input = List.of(10d,9d,8d,7d,6d,5d,4d,3d,2d,1d);
    Assertions.assertEquals(0, computeMaxProfit(input));

    input = List.of(10d,10d,10d,10d,10d,10d,10d,10d,10d,10d);
    Assertions.assertEquals(0, computeMaxProfit(input));

    input = List.of();
    Assertions.assertEquals(0, computeMaxProfit(input));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
