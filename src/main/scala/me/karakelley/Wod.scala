package me.karakelley

case class Wod(
  date: String,
  description: String,
  link: String
)

object Wod {
  implicit def converter(wod: Wod) = {
    val pattern = "<p>(.*?)</p>".r
    val formattedWodDescription = pattern
      .findAllIn(wod.description)
      .matchData
      .flatMap{m => m.subgroups}
      .toList
      .head

    wod.copy(description = formattedWodDescription)
  }
}
