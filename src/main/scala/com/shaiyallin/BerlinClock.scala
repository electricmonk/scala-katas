package com.shaiyallin

/**
 * @author shaiyallin
 * @since 3/21/14
 */

object BerlinClock {

  val Y = "Y"
  val R = "R"
  val O = "O"

  type ConditionFunc = Int => Boolean
  type ColorFunc = Int => String
  type LightState = PartialFunction[Int, String]

  val onWhen: ConditionFunc => ColorFunc => LightState = condition => {
    color => {
      case num: Int if condition(num) => color(num)
    }
  }

  val off: LightState = { case _ => O }

  val Red: ColorFunc  = { case _ => R }
  val Yellow: ColorFunc  = { case _ => Y }

  private def makeLights(length: Int)(color: LightState) = (1 to length map (color orElse off)).mkString

  def seconds(seconds: Int) = makeLights(1)(onWhen(_ => seconds % 2 == 0)(Yellow))
  def topHours(hours: Int) = makeLights(4)(onWhen(_ <= hours / 5)(Red))
  def bottomHours(hours: Int) = makeLights(4)(onWhen(_ <= hours % 5)(Red))
  def topMinutes(minutes: Int) = makeLights(11)(onWhen(_ <= minutes / 5)(light => if (light % 3 == 0) R else Y))
  def bottomMinutes(minutes: Int) = makeLights(4)(onWhen(_ <= minutes % 5)(Yellow))

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