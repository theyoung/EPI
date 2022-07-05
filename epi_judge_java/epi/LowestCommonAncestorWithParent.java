package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    if(node0 == null || node1 == null) return null;
    // TODO - you fill in here.
    var left = node0;
    var right = node1;

    var leftCount = 0;
    var rightCount = 0;

    while(left.parent != null){
      left = left.parent;
      leftCount++;
    }
    while (right.parent != null) {
      right = right.parent;
      rightCount++;
    }

    if(leftCount < rightCount){
      var tmp = node1;
      node1 = node0;
      node0 = tmp;
      var tmpCount = rightCount;
      rightCount = leftCount;
      leftCount = tmpCount;
    }

    for (var i = 0; i < (leftCount - rightCount); i++) {
      node0 = node0.parent;
    }

    while(node1 != null && node1 != node0){
      node0 = node0.parent;
      node1 = node1.parent;
    }

    return node1;
  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
