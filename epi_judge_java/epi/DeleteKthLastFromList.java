package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DeleteKthLastFromList {


  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")
  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    var head = new ListNode<>(0, L);
    var node = L;
    // n->1->2->3->4->5
    for (int i = 0; i < k; i++) {
      node = node.next;
    }
    var second = head;
    while(node != null){
      second = second.next;
      node = node.next;
    }
    second.next = second.next.next;
    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
