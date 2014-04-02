package com.shaiyallin

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

/**
 * @author shaiyallin
 * @since 4/1/14
 */

class FibonacciTest extends Specification {

  trait Context extends Scope with Streaming

  "fibonacci generator" should {
    "fail for negative numbers" in new Context {
      fib(-1) must throwA[IllegalArgumentException]
    }

    "return 0 when n == 0" in new Context {
      fib(0) must_== 0
    }

    "return 1 when n == 1" in new Context {
      fib(1) must_== 1
    }

    "return 3 when n == 4" in new Context {
      fib(4) must_== 3
    }

    "return 55 when n == 10" in new Context {
      fib(10) must_== 55
    }

    "return a really big number when n == 75" in new Context {
      fib(75) must_== 2111485077978050L
    }
  }
}