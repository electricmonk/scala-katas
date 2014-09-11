package com.shaiyallin

import org.specs2.mutable.Specification
import org.specs2.matcher.Matcher

/**
 * @author shaiyallin
 * @since 5/1/14
 */

class MagicSquareTest extends Specification {

  def magicSquare: Seq[Int] = ???

  "magic square" should {
    "be 9 elements long" in {
      magicSquare must haveSize(9)
    }

    "contain all numbers from 1 to 9" in {
      magicSquare must containTheSameElementsAs(1 to 9)
    }

    "have the same sum for all vertical, horizontal and diagonal triplets" in {
      failure // your test comes here
    }
  }

}