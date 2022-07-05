package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
public class StackWithMax {
  private static class CachedElement<T>{
    T elem;
    T max;

    public CachedElement(T elem, T max){
      this.elem = elem;
      this.max = max;
    }

    public T getElem() {
      return elem;
    }

    public void setElem(T elem) {
      this.elem = elem;
    }

    public T getMax() {
      return max;
    }

    public void setMax(T max) {
      this.max = max;
    }
  }

  public static class Stack {
    Deque<CachedElement<Integer>> list = new LinkedList<>();
    public boolean empty() {
      // TODO - you fill in here.
      return list.isEmpty();
    }
    public Integer max() {
      // TODO - you fill in here.
      if(list.size() < 1) return Integer.MIN_VALUE;
      return list.peek().getMax();
    }
    public Integer pop() {
      // TODO - you fill in here.
      return list.pop().getElem();
    }
    public void push(Integer x) {
      // TODO - you fill in here.
      list.push(new CachedElement<>(x,this.max() < x? x : this.max()));
    }
  }

  @Test
  public void test(){
    StackWithMax.Stack stack = new StackWithMax.Stack();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    Assertions.assertEquals(3, stack.max());
    Assertions.assertEquals(3, stack.pop());
    Assertions.assertEquals(2, stack.pop());
    Assertions.assertEquals(1, stack.max());

  }

  @EpiUserType(ctorParams = {String.class, int.class})
  public static class StackOp {
    public String op;
    public int arg;

    public StackOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }
  }

  @EpiTest(testDataFile = "stack_with_max.tsv")
  public static void stackTester(List<StackOp> ops) throws TestFailure {
    try {
      Stack s = new Stack();
      int result;
      for (StackOp op : ops) {
        switch (op.op) {
        case "Stack":
          s = new Stack();
          break;
        case "push":
          s.push(op.arg);
          break;
        case "pop":
          result = s.pop();
          if (result != op.arg) {
            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "max":
          result = s.max();
          if (result != op.arg) {
            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(result));
          }
          break;
        case "empty":
          result = s.empty() ? 1 : 0;
          if (result != op.arg) {
            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                  ", got " + String.valueOf(s));
          }
          break;
        default:
          throw new RuntimeException("Unsupported stack operation: " + op.op);
        }
      }
    } catch (NoSuchElementException e) {
      throw new TestFailure("Unexpected NoSuchElement exception");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
