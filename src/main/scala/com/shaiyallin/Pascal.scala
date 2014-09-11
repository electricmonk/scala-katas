package com.shaiyallin

/**
 * @author shaiyallin
 * @since 3/24/14
 */

object Pascal {

  def pascal(rows: Int): Seq[Seq[Int]] = 0 to rows map { r =>
    0 to rows map { c =>
      pascalNumber(c, r)
    }
  }


  private def pascalNumber(c: Int, r: Int): Int =
    if (c == 0 || c == r)
      1
    else
      pascalNumber(c - 1, r - 1) + pascalNumber(c, r - 1)

}