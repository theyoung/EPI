package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {
    // TODO - you fill in here.
    int left = 0;
    int right = s.length()-1;

    while(left < right){
      if(!Character.isLetterOrDigit(s.charAt(left))){
        left++;
        continue;
      }
      if(!Character.isLetterOrDigit(s.charAt(right))){
        right--;
        continue;
      }
      if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
      left++;
      right--;
    }

    return true;
  }

  @Test
  @DisplayName("String Palindromic")
  void test(){
    Executable GENERAL = ()-> Assertions.assertTrue(isPalindrome("ABCDEFGFEDCBA"));
    Executable SPACE = ()-> Assertions.assertTrue(isPalindrome("ABC DEFGF EDC BA"));
    Executable SPACE_COMMA = ()-> Assertions.assertTrue(isPalindrome("ABC, DEF,GF ED,C B,A"));
    Executable SPACE_FAIL = ()-> Assertions.assertFalse(isPalindrome("ABC DEFGF EDCK BA"));
    Executable SUCC = ()-> Assertions.assertTrue(isPalindrome("A man, a plan, a canal, Panama."));
    Executable FALSE = ()-> Assertions.assertFalse(isPalindrome("7}y"));
    Assertions.assertAll(GENERAL, SPACE,SPACE_COMMA, SPACE_FAIL,SUCC,FALSE);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
