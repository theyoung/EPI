package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubstringMatch {
  @EpiTest(testDataFile = "substring_match.tsv")

  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String t, String s) {
    if(s.length() == 0) return 0;
    if(t.length() == 0) return -1;
    if(t.length() < s.length()) return -1;

    long sHash = hashCode(s);
    long tSplitHash = hashCode(t.substring(0, s.length()));
    if(sHash == tSplitHash) return 0;

    for(int i = 1; i <= t.length()-s.length(); i++){
      long minus = (t.charAt(i-1)%31) * (long)Math.pow(31,s.length()-1);// minus
      long plus = (t.charAt(i + s.length() - 1))%31;//plus

      tSplitHash = (tSplitHash - minus)*31 + plus;
      if(sHash == tSplitHash) return i;
    }
    return -1;
  }

  public static long hashCode(String word){
    long hash = 0;
    for (int i = 0; i < word.length(); i++) {
      hash = (hash*31) + (word.charAt(i)%31);
    }
    return hash;
  }

  @Test
  void test(){
    Assertions.assertEquals(2, rabinKarp("abcdefg", "cd"));
    //a = 4, z=29
//    System.out.println(hashCode("aa")); // 4*31 + 4 = 128
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
