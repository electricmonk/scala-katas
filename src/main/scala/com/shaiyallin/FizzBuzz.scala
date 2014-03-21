package com.shaiyallin

import scalaz._
import Scalaz._
/**
 * @author shaiyallin
 * @since 3/21/14
 */

object FizzBuzz {

  type FizzBuzz = Int => Option[String]

  val say: String => Int => FizzBuzz = { word => divisor => num => if (num % divisor == 0) Some(word) else None }

  val fizz = say("fizz")(3)
  val buzz = say("buzz")(5)
  val fizzbuzz = fizz |+| buzz

  val getResult: Int => String = i => fizzbuzz(i) getOrElse i.toString
}