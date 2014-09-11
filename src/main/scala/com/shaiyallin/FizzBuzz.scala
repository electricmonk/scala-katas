package com.shaiyallin

import scalaz._
import Scalaz._
/**
 * @author shaiyallin
 * @since 3/21/14
 */

object FizzBuzz {

  type FizzBuzz = Int => Option[String]

  val say: (String, Int) => FizzBuzz = {
    case (word, divisor) => num =>
      if (num % divisor == 0)
        Some(word)
      else
        None
  }

  val fizz: FizzBuzz = say("fizz", 3)
  val buzz: FizzBuzz = say("buzz", 5)
  val boom: FizzBuzz = say("boom", 7)
  val fizzbuzz: FizzBuzz = fizz |+| buzz |+| boom

  val getResult: Int => String = i => fizzbuzz(i) getOrElse i.toString

  val l = List(1, 2, 3)
}