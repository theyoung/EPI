package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeInorder {

  private static class NodeAndState {
    public BinaryTreeNode<Integer> node;
    public Boolean leftSubtreeTraversed;

    public NodeAndState(BinaryTreeNode<Integer> node,
                        Boolean leftSubtreeTraversed) {
      this.node = node;
      this.leftSubtreeTraversed = leftSubtreeTraversed;
    }
  }

  @EpiTest(testDataFile = "tree_inorder.tsv")
  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    Deque<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    var cur = tree;
    var result = new ArrayList<Integer>();

    while(!queue.isEmpty() || cur != null){
      if(cur != null){//append left
        queue.addFirst(cur);
        cur = cur.left;
      } else {// append left and center and bring right
        cur = queue.pollFirst();
        result.add(cur.data);//left most
        if(cur.right != null) cur = cur.right;
        else cur = null;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
