package me.karakelley

import org.scalatest.{FreeSpec, Matchers}

class MainSpec extends FreeSpec with Matchers {

  "Testing the walking skeleton" - {
    val main = new Main()
    val expectedResult = main.test()

    expectedResult shouldBe true
  }
}
