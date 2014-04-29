package com.shaiyallin

import org.specs2.mutable.Specification
import org.specs2.matcher.Matcher

/**
 * @author shaiyallin
 * @since 4/29/14
 */

class NumberPuzzleTest extends Specification {

  val indices = Seq(Seq(0,1,2), Seq(3,4,5), Seq(6,7,8), Seq(0,3,6), Seq(1,4,7), Seq(2,5,8), Seq(0,4,8), Seq(2,4,6))
  val sumIndex: Seq[Int] => Seq[Int] => Int = solution => _ map solution sum

  def beAValidSolution: Matcher[Seq[Int]] = be_==(1) ^^ {solution: Seq[Int] => indices.map(sumIndex(solution)).distinct.size}

  def solutions: TraversableOnce[Seq[Int]] = Nil

  "numbers sequence" should {
    "contain all numbers from 1 to 9" in {
      solutions must containTheSameElementsAs(1 to 9).forall
    }

    "solve the puzzle" in {
      solutions must beAValidSolution.forall
    }
  }
}