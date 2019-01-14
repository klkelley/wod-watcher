package me.karakelley

import org.scalatest.{FreeSpec, Matchers}
class WodSpec extends FreeSpec with Matchers {

  "For A WOD" - {
    "The description is parsed and stored in a print friendly format" in {
      val wodDescription = "<p>SPEED 4 Rounds for Time: 25 Calorie Row 10 Burpees over the rower   WOD: Death by&#8230; Wall Balls 20/14 Pull Ups   Alternate movements: Min 1: 1 Wall Ball Min 2: 2 Pull Ups Min 3: 3 Wall Balls&#8230; etc etc</p><p>The post <a rel=\"nofollow\" href=\"https://atlasperformance.com/2019/01/10/friday-january-11-2019/\">Friday, January 11, 2019</a> appeared first on <a rel=\"nofollow\" href=\"https://atlasperformance.com\">Atlas Performance</a>.</p>"
      val wod = Wod("date", wodDescription)

      wod.description shouldBe "SPEED 4 Rounds for Time: 25 Calorie Row 10 Burpees over the rower   WOD: Death by&#8230; Wall Balls 20/14 Pull Ups   Alternate movements: Min 1: 1 Wall Ball Min 2: 2 Pull Ups Min 3: 3 Wall Balls&#8230; etc etc"
    }

  }
}
