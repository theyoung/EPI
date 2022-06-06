package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ReplaceAndRemove {
  public static final void swap(char[] arr, int aPos, int bPos){
    char temp = arr[aPos];
    arr[aPos] = arr[bPos];
    arr[bPos] = temp;
  }

  public static int replaceAndRemove(int size, char[] s) {
    // TODO - you fill in here.
    int slow = 0;
    int fast = 0;
    int count = 0;
    // a,a,b,b,c,c
    while(fast < size){
      if(s[fast] == 'b'){
        s[fast++] = ' ';//2,4
      } else {
        count++;
        if(s[fast] == 'a') count++;
        s[slow] = s[fast];//0,0->1,1->2,2->3,5->4,5
        if(slow < fast) s[fast] = ' ';
        slow++;
        fast++;
      }
    }
    slow--;
    int right = count-1;
    while(0 <= right && 0 <= slow){
      if(s[slow] == 'a'){
        s[right--] = 'd';
        s[right--] = 'd';
      } else {
        swap(s,slow,right);
        right--;
      }
      if(slow < right)
        s[slow] = ' ';
      slow--;
    }

    return count;
  }

  @Test
  @DisplayName("Simple task")
  void test(){
//    Executable ACAA___ = () -> Assertions.assertEquals(7, replaceAndRemove(4, new char[]{'a', 'c', 'a', 'a',' ',' ',' '}));
    Executable ACDBBCA = () -> Assertions.assertEquals(7, replaceAndRemove(7, new char[]{'a', 'c', 'd', 'b','b','c','a'}));

    Assertions.assertAll("",ACDBBCA);

//    Executable COMPLEX = () -> Assertions.assertEquals(7, replaceAndRemove(24, new char[]{'b', 'd', 'c', 'a', 'b', 'a', 'd', 'b', 'd', 'b', 'b', 'a', 'd', 'c', 'c', 'a', 'd', 'a', 'd', 'd', 'd', 'b', 'c', 'c', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}));
//
//    Assertions.assertAll("",COMPLEX);

  }

  @EpiTest(testDataFile = "replace_and_remove.tsv")
  public static List<String>
  replaceAndRemoveWrapper(TimedExecutor executor, Integer size, List<String> s)
      throws Exception {
    char[] sCopy = new char[s.size()];
    for (int i = 0; i < size; ++i) {
      if (!s.get(i).isEmpty()) {
        sCopy[i] = s.get(i).charAt(0);
      }
    }

    Integer resSize = executor.run(() -> replaceAndRemove(size, sCopy));

    List<String> result = new ArrayList<>();
    for (int i = 0; i < resSize; ++i) {
      result.add(Character.toString(sCopy[i]));
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReplaceAndRemove.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
