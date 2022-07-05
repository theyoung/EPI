package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SunsetView {
  public static List<Integer>
  examineBuildingsWithSunset(Iterator<Integer> sequence) {
    // TODO - you fill in here.
    Deque<Integer> height = new LinkedList<>();
    Deque<Integer> indices = new LinkedList<>();
    int index = 0;

    while(sequence.hasNext()){
      int cur = sequence.next();

      while(!height.isEmpty() && height.getLast() <= cur){
        height.removeLast();
        indices.removeLast();
      }
      height.addLast(cur);
      indices.addLast(index++);
    }
    List<Integer> result = new ArrayList<>(indices);
    Collections.reverse(result);
    return result;
  }
  @EpiTest(testDataFile = "sunset_view.tsv")
  public static List<Integer>
  examineBuildingsWithSunsetWrapper(List<Integer> sequence) {
    return examineBuildingsWithSunset(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SunsetView.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  @Test
  void test(){
    Stack<Integer> stack = new Stack<>();
    Deque<Integer> deque = new LinkedList<>();

    stack.push(1);
    deque.push(1);

    stack.push(2);
    deque.push(2);

    stack.push(3);
    deque.push(3);

    stack.pop();
    deque.pop();

    stack.pop();
    deque.pop();
  }
}
