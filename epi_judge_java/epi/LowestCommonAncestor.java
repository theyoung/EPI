package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  static BinaryTreeNode<Integer> result = null;
  public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {
    result = null;
    // TODO - you fill in here.
    helper(tree, node0, node1);
    return result;
  }

  public static boolean helper(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1){
    if(node == null || result != null) return false;
    boolean flag = false;

    if(node.data.equals(node0.data) || node.data.equals(node1.data)) flag = true;
    if(node.data.equals(node0.data) && node.data.equals(node1.data)) {
      result = node;
      return true;
    }

    boolean left = helper(node.left, node0, node1);
    boolean right = helper(node.right, node0, node1);

    if(left && right || left && flag || flag && right){
      result = node;
    }

    return flag || left || right;
  }


  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> lca(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
