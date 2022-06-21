package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    // TODO - you fill in here.

    var evenHead = new ListNode<>(0, null);
    var oddHead = new ListNode<>(0, null);
    var evenNode = evenHead;
    var oddNode = oddHead;
    var node = L;
    var count = 0;

    while (node != null) {
      var nextNode = node.next;
      if(count%2 == 0){ //even
        evenNode.next = node;
        evenNode = evenNode.next;
        evenNode.next = null;
      } else {
        oddNode.next = node;
        oddNode = oddNode.next;
        oddNode.next = null;
      }
      node = nextNode;
      count++;
    }

    evenNode.next = oddHead.next;

    return evenHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
