package com.shaiyallin

import org.specs2.mutable.Specification

/**
 * @author shaiyallin
 * @since 7/17/14
 */

class TowersOfHanoiTest extends Specification {

  def aSolutionForTwoDiscs(from: Int, via: Int, to: Int) = Seq(Move(from, via), Move(from, to), Move(via, to))

  case class Move(from: Int, to: Int)
  object Hanoi {
    def solve(discs: Int = 1, from: Int = 1, via: Int = 2, to: Int = 3): Seq[Move] = {
      if (discs == 1) {
        Seq(Move(from, to))
      } else {
        Seq(
          Move(from, via),
          Move(from, to),
          Move(via, to)
        )
      }
    }
  }

  "it" should {
    "solve for a single disc" in {
      Hanoi.solve(from = 1, to = 3) must_== Seq(Move(1, 3))
    }

    "solve for another single disc" in {
      Hanoi.solve(from = 2, to = 3) must_== Seq(Move(2, 3))
    }

    "solve for two discs" in {
      Hanoi.solve(discs = 2, from = 1, to = 3) must_== aSolutionForTwoDiscs(from = 1, to = 3, via = 2)
    }

    "solve for two discs from 3 to 1" in {
      Hanoi.solve(discs = 2, from = 3, to = 1) must_== aSolutionForTwoDiscs(from = 3, to = 1, via = 2)
    }

    "solve for 3 discs form 1 to 3" in {
      Hanoi.solve(discs = 3, from = 1, to = 3, via = 2) must_==
        aSolutionForTwoDiscs(from = 1, to = 2, via = 3) ++
        Seq(Move(1, 3)) ++
        aSolutionForTwoDiscs(from = 2, to = 3, via = 1)
    }
  }
}