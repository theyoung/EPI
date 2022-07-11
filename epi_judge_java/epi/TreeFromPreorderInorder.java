package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  public static BinaryTreeNode<Integer>
  binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    // TODO - you fill in here.
    var map = new HashMap<Integer,Integer>();
    for (int i = 0; i < inorder.size(); i++) {
      map.put(inorder.get(i), i);
    }

    return Helper(preorder, inorder, map);
  }

  public static BinaryTreeNode<Integer>
  Helper(List<Integer> preorder, List<Integer> inorder, HashMap<Integer,Integer> map) {
    // TODO - you fill in here.
    if(preorder == null || inorder == null || preorder.size() == 0 || inorder.size() == 0) return null;

    var root = new BinaryTreeNode<>(preorder.get(0));

    if(preorder.size() == 1) return root;

//    var location = inorder.indexOf(root.data);
    var location = map.get(root.data);

    var inLeft = inorder.subList(0, location);
    var inRight = inorder.subList(location + 1, inorder.size());
    var preLeft = preorder.subList(1, inLeft.size()+1);
    var preRight = preorder.subList(inLeft.size()+1, preorder.size());

    root.left = binaryTreeFromPreorderInorder(preLeft, inLeft);
    root.right = binaryTreeFromPreorderInorder(preRight, inRight);

    return root;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
