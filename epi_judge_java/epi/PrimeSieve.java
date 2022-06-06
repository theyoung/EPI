package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // TODO - you fill in here.
    int[] arr = new int[n+1];
    Arrays.fill(arr,0);

    List<Integer> result = new ArrayList<>();

    for(int i = 2; i <= n; i++){
      if(0 == arr[i]){
        result.add(i);

        for(int j = i; j <= n; j+= i){
          arr[j] = 1;
        }
      }
    }
    return result;
  }

  public static boolean isPrime(int n){
    for(int i = 2; i < Math.sqrt(n); i++){
      if(n%i==0) return false;
    }
    return true;
  }

  @Test
  void test(){
    Assertions.assertTrue(isPrime(2));
    Assertions.assertTrue(isPrime(3));
    Assertions.assertTrue(isPrime(5));
    Assertions.assertTrue(isPrime(7));
    Assertions.assertTrue(isPrime(11));
    Assertions.assertTrue(isPrime(13));
    Assertions.assertTrue(isPrime(17));
    Assertions.assertTrue(isPrime(19));

    Assertions.assertEquals(List.of(2,3),generatePrimes(3));
    Assertions.assertEquals(List.of(2,3),generatePrimes(4));
    Assertions.assertEquals(List.of(2,3,5),generatePrimes(5));
    Assertions.assertEquals(List.of(2,3,5),generatePrimes(6));
    Assertions.assertEquals(List.of(2,3,5,7),generatePrimes(7));
    Assertions.assertEquals(List.of(2,3,5,7),generatePrimes(9));
    Assertions.assertEquals(List.of(2),generatePrimes(2));
    Assertions.assertEquals(List.of(),generatePrimes(1));
    Assertions.assertEquals(List.of(2,3,5,7),generatePrimes(10));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
