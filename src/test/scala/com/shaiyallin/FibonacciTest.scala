package com.shaiyallin

import org.specs2.mutable.Specification
import scala.annotation.tailrec

/**
 * @author shaiyallin
 * @since 4/1/14
 */

class FibonacciTest extends Specification {

  object Fib {

    def fibRecursive(n: Int): Int =
      if (n < 0)
        throw new IllegalArgumentException
      else if (n < 2)
        1
      else
        fibRecursive(n - 2) + fibRecursive(n - 1)

    def memoFib(n: Int): Int = {
      import scala.collection.mutable
      val memo = mutable.Map.empty[Int, Int]

      def fib(n: Int) = memo.getOrElseUpdate(n,
        if (n < 0)
          throw new IllegalArgumentException
        else if (n < 2)
          1
        else
          fibRecursive(n - 2) + fibRecursive(n - 1))

      fib(n)
    }

    def fibTailRec(n: Int): Int = {

      @tailrec
      def fib(n: Int, cur: Int, sum: Int): Int = {
        if (n == 0)
          sum
        else
          fib(n - 1, sum, sum + cur)
      }

      if (n < 0)
        throw new IllegalArgumentException
      else
        fib(n, 0, 1)
    }

    def streamingFib(n: Int): Int = {
      if (n < 0)
        throw new IllegalArgumentException

      lazy val fibs: Stream[Int] = 1 #:: fibs.scanLeft(1)(_ + _)

      fibs.take(n + 1).last
    }
  }

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