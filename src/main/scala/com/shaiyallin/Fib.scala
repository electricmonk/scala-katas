package com.shaiyallin

import scala.annotation.tailrec

/**
 * @author shaiyallin
 * @since 4/1/14
 */

trait Fibonacci {
  def fib(n: Int): Int
}

trait Recursive extends Fibonacci {

  def fib(n: Int): Int =
    if (n < 0)
      throw new IllegalArgumentException
    else if (n < 2)
      1
    else
      fib(n - 2) + fib(n - 1)
}

trait Memoization extends Fibonacci {
  import scala.collection.mutable
  val memo = mutable.Map.empty[Int, Int]

  def fib(n: Int) = memo.getOrElseUpdate(n,
   if (n < 0)
     throw new IllegalArgumentException
   else if (n < 2)
     1
   else
     fib(n - 2) + fib(n - 1)
  )
}

trait TailRecursive extends Fibonacci {

  def fib(n: Int): Int = {

    @tailrec
    def inner(n: Int, cur: Int, sum: Int): Int = {

      if (n == 0)
        sum
      else
        inner(n - 1, sum, sum + cur)
    }

    if (n < 0)
      throw new IllegalArgumentException
    else
      inner(n, 0, 1)
  }
}

trait Streaming extends Fibonacci {
   def fib(n: Int): Int = {
     if (n < 0)
       throw new IllegalArgumentException

     lazy val fibs: Stream[Int] = 1 #:: fibs.scanLeft(1)(_ + _)

     fibs.take(n + 1).last
   }
 }