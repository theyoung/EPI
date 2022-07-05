package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Consumer;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.
    String[] strs = expression.split(",");

    Deque<Integer> numbers = new LinkedList<>();
    Deque<String> operators = new LinkedList<>();

    Arrays.stream(strs).forEach((str)-> {
      if(str.equals("+")){
        int right = numbers.removeLast();
        int left = numbers.removeLast();
        numbers.addLast(left + right);
      } else if(str.equals("-")){
        int right = numbers.removeLast();
        int left = numbers.removeLast();
        numbers.addLast(left - right);
      } else if(str.equals("*")){
        int right = numbers.removeLast();
        int left = numbers.removeLast();
        numbers.addLast(left * right);
      } else if(str.equals("/")){
        int right = numbers.removeLast();
        int left = numbers.removeLast();
        numbers.addLast(left / right);
      } else {
        numbers.addLast(Integer.parseInt(str));
      }
    });

    return numbers.pop();
  }

  @Test
  void test(){
    Executable E =()-> Assertions.assertEquals(3,EvaluateRpn.eval("1,2,+"));
    Executable B =()-> Assertions.assertEquals(-1,EvaluateRpn.eval("1,2,-"));
    Executable C =()-> Assertions.assertEquals(2,EvaluateRpn.eval("1,2,*"));
    Executable D =()-> Assertions.assertEquals(2,EvaluateRpn.eval("2,1,/"));
    Executable A =()-> Assertions.assertEquals(9,EvaluateRpn.eval("1,2,+,3,*"));
    Executable F =()-> Assertions.assertEquals(-1,EvaluateRpn.eval("1,-2,+"));
    Executable G =()-> Assertions.assertEquals(402,EvaluateRpn.eval("11,8,10,*,+,7,12,*,+,12,7,*,+,9,14,*,+,17,+"));

    Assertions.assertAll(A,B,C,D,E,F,G);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
