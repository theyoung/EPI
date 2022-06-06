package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {
    // TODO - you fill in here.
    int result = 0;
    for (int i = 0; i < col.length(); i++) {
      int cur = col.charAt(i) - 'A' + 1;
      result = (result * 26) + cur;
    }

    return result;
  }

  @Test
  @DisplayName("Default Test Cases")
  void test(){
    Executable A = ()-> Assertions.assertEquals(1,ssDecodeColID("A"));
    Executable C = ()-> Assertions.assertEquals(3,ssDecodeColID("C"));
    Executable D = ()-> Assertions.assertEquals(4,ssDecodeColID("D"));
    Executable Z = ()-> Assertions.assertEquals(26,ssDecodeColID("Z"));

    Assertions.assertAll("Simple",A,C,D,Z);

    Executable AA = ()-> Assertions.assertEquals(27,ssDecodeColID("AA"));
    Executable ZZ = ()-> Assertions.assertEquals(702,ssDecodeColID("ZZ"));
    Runnable runnable = () -> System.out.println("hello");
    runnable.run();
    Assertions.assertAll("Double chars",AA,ZZ);

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
