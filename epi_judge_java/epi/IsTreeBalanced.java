package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {
//                -3
  //           -7    4
  //         -11 -1 -1 6
  //     null -3 0 11
  static boolean skewed = false;
  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    skewed = false;
    // TODO - you fill in here.
    helper(tree);
    return !skewed;
  }

  public static int helper(BinaryTreeNode<Integer> tree){
    if(tree == null) return 0;
    if(skewed) return 0;
    int left = helper(tree.left);
    int right = helper(tree.right);
    int diff = Math.abs(left - right);
    if(1 < diff){
      skewed = true;
    }
    return Math.max(left, right) + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
