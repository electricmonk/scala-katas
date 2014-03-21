package com.shaiyallin

/**
 * @author shaiyallin
 * @since 3/21/14
 */

object BerlinClock {

  val Y = "Y"
  val R = "R"
  val O = "O"

  type Color = (Int => String)

  val onWhen: (Int => Boolean) => Color => PartialFunction[Int, String] = condition => {
    color => {
      case num: Int if condition(num) => color(num)
    }
  }

  val off: PartialFunction[Int, String] = { case _ => O }

  val Red: Color  = { case _ => R }
  val Yellow: Color  = { case _ => Y }

  private def lamps(length: Int)(color: PartialFunction[Int, String]) = (1 to length map (color orElse off)).mkString

  def seconds(seconds: Int) = lamps(1)(onWhen(_ => seconds % 2 == 0)(Yellow))
  def topHours(hours: Int) = lamps(4)(onWhen(_ <= hours / 5)(Red))
  def bottomHours(hours: Int) = lamps(4)(onWhen(_ <= hours % 5)(Red))
  def topMinutes(minutes: Int) = lamps(11)(onWhen(_ <= minutes / 5)(lamp => if (lamp % 3 == 0) R else Y))
  def bottomMinutes(minutes: Int) = lamps(4)(onWhen(_ <= minutes % 5)(Yellow))

  def convertToBerlinTime(time: String): Array[String] = time.split(':') match {
    case Array(intOf(h), intOf(m), intOf(s)) => Array(
      seconds(s),
      topHours(h),
      bottomHours(h),
      topMinutes(m),
      bottomMinutes(m)
    )
    case _ => Array()
  }

  object intOf {
    def unapply(s : String) : Option[Int] = try {
      Some(s.toInt)
    } catch {
      case _ : java.lang.NumberFormatException => None
    }
  }
}