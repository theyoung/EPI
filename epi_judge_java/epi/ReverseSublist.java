package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    if (start == finish) {
      return L;
    }
    ListNode<Integer> dummyHead = new ListNode<>(0, L);
    ListNode<Integer> sublistHead = dummyHead;
    int k = 1;
    while(k++ < start){
      sublistHead = sublistHead.next;
    }

    ListNode<Integer> sublistIter = sublistHead.next;

    while(start++ < finish){
      ListNode<Integer> startNode = sublistIter.next;
      sublistIter.next = startNode.next;
      startNode.next = sublistHead.next;
      sublistHead.next = startNode;
    }

    return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
