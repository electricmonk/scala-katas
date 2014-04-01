package com.shaiyallin

import org.specs2.mutable.Specification

/**
 * @author shaiyallin
 * @since 4/1/14
 */

class FibonacciTest extends Specification {

  import Fib.{memoFib => fib}

  "fibonacci generator" should {
    "fail for negative numbers" in {
      fib(-1) must throwA[IllegalArgumentException]
    }

    "return 1 when n == 0" in {
      fib(0) must_== 1
    }

    "return 1 when n == 1" in {
      fib(1) must_== 1
    }

    "return 5 when n == 4" in {
      fib(4) must_== 5
    }

    "return 89 when n == 10" in {
      fib(10) must_== 89
    }
  }
}