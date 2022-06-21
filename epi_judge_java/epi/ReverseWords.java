package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ReverseWords {
  public static final void swap(char[] arr, int pos1, int pos2) {
    char tmp = arr[pos1];
    arr[pos1] = arr[pos2];
    arr[pos2] = tmp;
  }

  public static void reverseWords(char[] input) {
    // TODO - you fill in here.
    if(input.length < 1) return;
    int left = 0;
    int right = input.length-1;

    while(left < right){
      swap(input, left++, right--);
    }

    int slow = 0;
    int fast = 0;
    for(;fast < input.length; fast++){
      if(input[fast] == ' '){
        int tmp = fast-1;
        while(slow < tmp){
          swap(input, slow++, tmp--);
        }
        slow = fast+1;
      }
    }
    if(slow < --fast){
      while(slow < fast){
        swap(input, slow++, fast--);
      }
    }

    return;
  }
  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  @Test
  void test(){
    Executable SIMPLE = ()-> {
      char[] input = new char[]{'a','b','c',' ','a','b','c'};
      reverseWords(input);
      Assertions.assertArrayEquals(new char[]{'a','b','c',' ','a','b','c'}, input);
    };

    Executable SIMPLE2 = ()-> {
      char[] input = new char[]{'a','b','d',' ','a','b','c'};
      reverseWords(input);
      Assertions.assertArrayEquals(new char[]{'a','b','c',' ','a','b','d'}, input);
    };

    Assertions.assertAll(SIMPLE,SIMPLE2);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
