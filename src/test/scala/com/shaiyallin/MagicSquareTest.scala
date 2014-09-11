package com.shaiyallin

import org.specs2.mutable.Specification
import org.specs2.matcher.Matcher

/**
 * @author shaiyallin
 * @since 5/1/14
 */

class MagicSquareTest extends Specification {

  val indices = Seq(Seq(0,1,2), Seq(3,4,5), Seq(6,7,8),
                     Seq(0,3,6), Seq(1,4,7), Seq(2,5,8),
                     Seq(0,4,8), Seq(2,4,6))

   val sumIndex: Seq[Int] => Seq[Int] => Int = solution => _ map solution sum
   def beAValidSolution: Matcher[Seq[Int]] =
     be_==(1) ^^ {solution: Seq[Int] => indices.map(sumIndex(solution)).distinct.size}

  def solutions: TraversableOnce[Seq[Int]] =
    (1 to 9).permutations.filter(solution => (indices map sumIndex(solution) distinct).size == 1)

  def magicSquare = solutions.toSeq.head

  "magic square" should {
    "be 9 elements long" in {
      magicSquare must haveSize(9)
    }

    "contain all numbers from 1 to 9" in {
      magicSquare must containTheSameElementsAs(1 to 9)
    }

    "have the same sum for all vertical, horizontal and diagonal triplets" in {

      solutions must beAValidSolution.forall
    }
  }

  "sumIndex" should {
    "return the correct sum" in {
      sumIndex(1 to 9)(Seq(2, 4, 6)) must_== 15
      sumIndex(1 to 9)(Seq(0, 1, 2)) must_== 6
    }
  }
}