package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularQueue {

  public static class Queue {
    Integer[] queue;
    int length = 0;
    int head = 0;
    int tail = 0;

    public Queue(int capacity) {
      this.queue = new Integer[capacity];
    }
    public void enqueue(Integer x) {
      // TODO - you fill in here.
      if(length == queue.length){
        Collections.rotate(Arrays.asList(queue), -head);
        head = 0;
        tail = length;
        queue = Arrays.copyOf(queue, (int) (queue.length * 2));
      }

      queue[tail] = x;
      tail = (tail + 1) % queue.length;
      length++;
    }
    public Integer dequeue() {
      // TODO - you fill in here.
      if(0 < length){
        length--;
        int result = queue[head];
        queue[head] = null;
        head = (head + 1) % queue.length;
        return result;
      }
      throw new NoSuchElementException("NO!");
    }
    public int size() {
      // TODO - you fill in here.
      return length;
    }
    @Override
    public String toString() {
      // TODO - you fill in here.
      return super.toString();
    }
  }
  @EpiUserType(ctorParams = {String.class, int.class})
  public static class QueueOp {
    public String op;
    public int arg;

    public QueueOp(String op, int arg) {
      this.op = op;
      this.arg = arg;
    }

    @Override
    public String toString() {
      return op;
    }
  }

  @EpiTest(testDataFile = "circular_queue.tsv")
  public static void queueTester(List<QueueOp> ops) throws TestFailure {
    Queue q = new Queue(1);
    int opIdx = 0;
    for (QueueOp op : ops) {
      switch (op.op) {
      case "Queue":
        q = new Queue(op.arg);
        break;
      case "enqueue":
        q.enqueue(op.arg);
        break;
      case "dequeue":
        int result = q.dequeue();
        if (result != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, result);
        }
        break;
      case "size":
        int s = q.size();
        if (s != op.arg) {
          throw new TestFailure()
              .withProperty(TestFailure.PropertyName.STATE, q)
              .withProperty(TestFailure.PropertyName.COMMAND, op)
              .withMismatchInfo(opIdx, op.arg, s);
        }
        break;
      }
      opIdx++;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CircularQueue.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
