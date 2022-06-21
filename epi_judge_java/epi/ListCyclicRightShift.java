package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,int k) {
    if(L == null) return L;

    //find tail and length
    int len = 1;
    var tail = L;
    while(tail.next != null){
      tail = tail.next;
      len++;
    }
    k = k % len;
    if(k == 0) return L;

    tail.next = L;

    var moveCount = len - k;
    var realTail = L;
    while(1 < moveCount--){
      realTail = realTail.next;
    }
    var head = realTail.next;
    realTail.next = null;

    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
