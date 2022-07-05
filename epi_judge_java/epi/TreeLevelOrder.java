package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.

    List<List<Integer>> result = new ArrayList<>();
    if(tree == null) return result;

    Deque<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    queue.add(tree);

    while(!queue.isEmpty()){
      List<Integer> subResult = new ArrayList<>();
      int len = queue.size();

      for (int i = 0; i < len; i++) {
        BinaryTreeNode<Integer> node = queue.pollFirst();
        subResult.add(node.data);
        if(node.left != null) queue.add(node.left);
        if(node.right != null) queue.add(node.right);
      }
      result.add(subResult);
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
