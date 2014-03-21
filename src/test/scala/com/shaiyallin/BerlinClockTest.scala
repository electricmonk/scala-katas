package com.shaiyallin

import org.scalatest.{Matchers, FlatSpec, WordSpec}

/**
 * @author shaiyallin
 * @since 3/21/14
 */

class BerlinClockTest extends FlatSpec with Matchers {

  "Yellow lamp" should "blink on/off every two seconds" in {
    BerlinClock.seconds(0) should be ("Y")
    BerlinClock.seconds(1) should be ("O")
    BerlinClock.seconds(59) should be ("O")
  }

  "Top hours" should "have 4 lamps" in {
    BerlinClock.topHours(7).length should be (4)
  }

  it should "light a red lamp for every 5 hours" in {
    BerlinClock.topHours(0) should be ("OOOO")
    BerlinClock.topHours(13) should be ("RROO")
    BerlinClock.topHours(23) should be ("RRRR")
    BerlinClock.topHours(24) should be ("RRRR")
  }

  "Bottom hours" should "have 4 lamps" in {
    BerlinClock.bottomHours(5).length should be (4)
  }

  it should "light a red lamp for every hour left from top hours" in {
    BerlinClock.bottomHours(0) should be ("OOOO")
    BerlinClock.bottomHours(13) should be ("RRRO")
    BerlinClock.bottomHours(23) should be ("RRRO")
    BerlinClock.bottomHours(24) should be ("RRRR")
  }

  "Top minutes" should "have 11 lamps" in {
    BerlinClock.topMinutes(34).length should be (11)
  }

  it should "have 3rd, 6th and 9th lamps in red to indicate first quarter, half and last quarter" in {
    val minutes32 = BerlinClock.topMinutes(32)
    minutes32(2) should be ('R')
    minutes32(5) should be ('R')
    minutes32(8) should be ('O')
  }

  it should "light a yellow lamp for every 5 minutes unless it's first quarter, half or last quarter" in {
    BerlinClock.topMinutes(0) should be ("OOOOOOOOOOO")
    BerlinClock.topMinutes(17) should be ("YYROOOOOOOO")
    BerlinClock.topMinutes(59) should be ("YYRYYRYYRYY")
  }

  "Bottom minutes" should "have 4 lamps" in {
    BerlinClock.bottomMinutes(0).length should be (4)
  }

  it should "light a yellow lamp for every minute left from top minutes" in {
    BerlinClock.bottomMinutes(0) should be ("OOOO")
    BerlinClock.bottomMinutes(17) should be ("YYOO")
    BerlinClock.bottomMinutes(59) should be ("YYYY")
  }

  "Berlin Clock" should "result in array with 5 elements" in {
    BerlinClock.convertToBerlinTime("13:17:01").size should be (5)
  }

  it should "result in correct seconds, hours and minutes" in {
    val berlinTime = BerlinClock.convertToBerlinTime("16:37:16")
    val expected = Array("Y", "RRRO", "ROOO", "YYRYYRYOOOO", "YYOO")
    berlinTime should equal (expected)
  }

}
