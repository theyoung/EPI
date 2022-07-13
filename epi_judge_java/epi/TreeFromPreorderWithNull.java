package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeFromPreorderWithNull {
  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    // TODO - you fill in here.
    Deque<Integer> queue = new LinkedList<>(preorder);


    return helper(queue);
  }

  public static BinaryTreeNode<Integer> helper(Deque<Integer> queue){
    if(queue.size() == 0) return null;

    var cur = queue.pollFirst();

    if(cur == null) return null;

    BinaryTreeNode<Integer> node = new BinaryTreeNode<>(cur);

    node.left = helper(queue);
    node.right = helper(queue);

    return node;
  }


  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
