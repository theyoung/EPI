package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Test;

public class SumRootToLeaf {


  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {

      if(tree==null) {
        return 0;
      }
      int result = helper(tree, 0);
      System.out.println(result);
      return result;

  }

  public static int helper(BinaryTreeNode<Integer> tree, int val){

    if(tree.left == null && tree.right == null) return val + tree.data;

    var left = tree.left != null ? helper(tree.left, (val + tree.data) << 1) : 0;
    var right = tree.right != null ? helper(tree.right, (val + tree.data) << 1) : 0;

    return left + right;
  }

  @Test
  void test(){
    var root = new BinaryTreeNode<Integer>(1);
    root.left = new BinaryTreeNode<Integer>(0);
    root.left = new BinaryTreeNode<Integer>(1);

    System.out.println(sumRootToLeaf(root));
  }

  public static void main(String[] args) {

    System.exit(
            GenericTest
                    .runFromAnnotations(args, "SumRootToLeaf.java",
                            new Object() {
                            }.getClass().getEnclosingClass())
                    .ordinal());

  }
}
