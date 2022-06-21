package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
    // TODO - you fill in here.
    var leftNode = L1;
    var rightNode = L2;
    var resultRoot = new ListNode<Integer>(0, null);
    var node = resultRoot;
    var carry = 0;

    while(leftNode != null || rightNode != null){
      int left = leftNode == null ? 0 : leftNode.data;
      int right = rightNode == null ? 0 : rightNode.data;

      int sum = left + right + carry;
      int remained = sum % 10;
      carry = (int)(sum / 10);

      node.next = new ListNode<>(remained, null);
      node = node.next;

      leftNode = leftNode != null ? leftNode.next : null;
      rightNode = rightNode != null ? rightNode.next : null;
    }

    if(0 < carry){
      node.next = new ListNode<>(carry, null);
    }

    return resultRoot.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
