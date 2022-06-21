package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsListPalindromic {

  static ListNode<Integer> head;

  @EpiTest(testDataFile = "is_list_palindromic.tsv")
  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    // TODO - you fill in here.
    var slow = L;
    var fast = L;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    var leftNode = L;
    var rightNode = reverse(slow);

    while(leftNode != null && rightNode != null){
      if (leftNode.data.equals(rightNode.data)) {
        leftNode = leftNode.next;
        rightNode = rightNode.next;
      } else {
        return false;
      }
    }

    return true;
  }

  // 1->2->3->4->5
  // dummy->1->null
  // dummy->2->1->null
  public static ListNode<Integer> reverse(ListNode<Integer> root){
    ListNode<Integer> dummy = new ListNode<>(0, null);
    var node = root;

    while(node != null){
      var tmp = dummy.next;
      dummy.next = node;
      node = node.next;
      dummy.next.next = tmp;
    }
    return dummy.next;
  }

  // 1->2->3->2->1
  //stack overflow
  public static boolean helper(ListNode<Integer> node){
    if(node == null) return true;

    if(helper(node.next)){
      if(node.data == head.data) {
        head = head.next;
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
