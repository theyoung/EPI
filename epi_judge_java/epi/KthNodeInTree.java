package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class KthNodeInTree {
  public static class BinaryTreeNode<T> extends TreeLike<T, BinaryTreeNode<T>> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public int size;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right, int size) {
      this.data = data;
      this.left = left;
      this.right = right;
      this.size = size;
    }

    @Override
    public T getData() {
      return data;
    }

    @Override
    public BinaryTreeNode<T> getLeft() {
      return left;
    }

    @Override
    public BinaryTreeNode<T> getRight() {
      return right;
    }
  }

  public static BinaryTreeNode<Integer>
  findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
    Deque<BinaryTreeNode<Integer>> deque = new LinkedList<>();
//    var result = new ArrayList<BinaryTreeNode<Integer>>();
    var cur = tree;

    while (cur != null && k <= cur.size) {
      //1. 왼쪽의 갯수 + 1 = cur
      //2. k <= 왼쪽의 갯수 = cur.left
      //3. (왼쪽의 갯수 + 1) < k = k - (왼쪽의 갯수+1) = cur.right
      //4. null size = 0
      int leftSize = cur.left != null? cur.left.size : 0;
      int rightSize = cur.right != null? cur.right.size : 0;

      if(leftSize + 1 == k) return cur;

      if(k <= leftSize){
        cur = cur.left;
      } else if(leftSize+1 < k){
        k = k - (leftSize+1);
        cur = cur.right;
      }
    }

//    System.out.println(Arrays.toString(result.stream().map((node)->(node.data)).toArray()));
    return null;
  }
  public static BinaryTreeNode<Integer>
  convertToTreeWithSize(BinaryTree<Integer> original) {
    if (original == null)
      return null;
    BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
    BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
    int lSize = left == null ? 0 : left.size;
    int rSize = right == null ? 0 : right.size;
    return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
  }

  @EpiTest(testDataFile = "kth_node_in_tree.tsv")
  public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
                                                 BinaryTree<Integer> tree,
                                                 int k) throws Exception {
    BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

    BinaryTreeNode<Integer> result =
        executor.run(() -> findKthNodeBinaryTree(converted, k));

    if (result == null) {
      throw new TestFailure("Result can't be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthNodeInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
