package com.shaiyallin

import scala.annotation.tailrec

/**
 * @author shaiyallin
 * @since 4/1/14
 */

trait Fibonacci {
  def fib(n: Int): Long
}

trait Recursive extends Fibonacci {

  def fib(n: Int): Long =
    if (n < 0)
      throw new IllegalArgumentException
    else if (n < 2)
      n
    else
      fib(n - 2) + fib(n - 1)
}

trait Memoization extends Fibonacci {
  import scala.collection.mutable
  val memo = mutable.Map.empty[Long, Long]

  def fib(n: Int) = memo.getOrElseUpdate(n,
   if (n < 0)
     throw new IllegalArgumentException
   else if (n < 2)
     n
   else
     fib(n - 2) + fib(n - 1)
  )
}

trait TailRecursive extends Fibonacci {

  def fib(n: Int): Long = {

    @tailrec
    def inner(n: Long, sum: Long, prev: Long): Long = {

      if (n == 0)
        prev
      else if (n == 1)
        sum
      else
        inner(n - 1, sum + prev, sum)
    }

    if (n < 0)
      throw new IllegalArgumentException
    else
      inner(n, 1, 0)
  }
}

trait Streaming extends Fibonacci {
   def fib(n: Int): Long = {
     if (n < 0)
       throw new IllegalArgumentException

     lazy val fibs: Stream[Long] = 0 #:: fibs.scanLeft(1.toLong)(_ + _)

     fibs(n)
   }
 }