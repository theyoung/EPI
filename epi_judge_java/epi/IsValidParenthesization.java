package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValidParenthesization {
  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")

  public static boolean isWellFormed(String s) {
    // TODO - you fill in here.
    Map<String,String> map = new HashMap<>();
    map.put(")","(");
    map.put("}", "{");
    map.put("]", "[");

    Stack<String> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      String cur = String.valueOf(s.charAt(i));
      if(map.containsKey(cur) && !stack.isEmpty()){
        if(stack.peek().equals(map.get(cur))){
          stack.pop();
        }else{
          return false;
        }
      } else {
        stack.push(cur);
      }
    }

    return stack.size() == 0;
  }

  @Test
  void test(){
    Assertions.assertTrue(isWellFormed("()"));
    Assertions.assertTrue(isWellFormed("[]"));
    Assertions.assertTrue(isWellFormed("{}"));
    Assertions.assertTrue(isWellFormed("{{}}"));
    Assertions.assertTrue(isWellFormed("{[]}"));
    Assertions.assertTrue(isWellFormed("{[]}[]"));
    Assertions.assertFalse(isWellFormed("{[]}[])"));
    Assertions.assertFalse(isWellFormed("{[]}[(])"));
    Assertions.assertFalse(isWellFormed("{[(])"));
    Assertions.assertFalse(isWellFormed("{[(])]"));
    Assertions.assertTrue(isWellFormed(""));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
