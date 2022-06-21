package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer>
  overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
    // TODO - you fill in here.
    var left = l0;
    var lcount = 1;
    var right = l1;
    var rcount = 1;


    //1->2->(3)->4->5
    while(left != null){
      lcount++;
      left = left.next;
    }
    //6->7->8->(3)->4->5
    while(right != null){
      rcount++;
      right = right.next;
    }
    //lcount > rcount
    if(lcount < rcount){
      var tmp = l0;
      l0 = l1;
      l1 = tmp;
      var tmpCount = lcount;
      lcount = rcount;
      rcount = tmpCount;
    }

    for(int i = lcount; 0 < lcount; i--){
      if(rcount < i){
        l0 = l0.next;
      } else {
        if(l0 == l1) return l0;
        else {
          l0 = l0.next;
          l1 = l1.next;
        }
      }
    }

    return null;
  }
  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                 ListNode<Integer> l1, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l0 != null) {
        ListNode<Integer> i = l0;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l0 = common;
      }

      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }
    }

    final ListNode<Integer> finalL0 = l0;
    final ListNode<Integer> finalL1 = l1;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
