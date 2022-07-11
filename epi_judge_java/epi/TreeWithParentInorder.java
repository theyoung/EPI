package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    // TODO - you fill in here.
    List<Integer> result = new ArrayList<>();
    BinaryTree<Integer> node = tree;

    while(node != null){
        if(node.left != null){
          while(node.left != null) node = node.left;
        } else {
          result.add(node.data);
          if(node.right != null){
            node = node.right;
          } else {
            while(node.parent != null && node.parent.right == node) node = node.parent;
            node = node.parent;
            if(node != null) node.left = null;
          }
        }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
