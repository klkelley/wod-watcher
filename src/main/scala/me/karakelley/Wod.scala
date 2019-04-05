package me.karakelley

import scala.util.matching.Regex

case class Wod(
  date: String,
  description: String,
  link: String
)

object Wod {
  def apply(date: String, description: String, link: String): Wod = new Wod(date, format(description), link)

  private def format(description: String) = {
    val noTags = removeHtmlTags(description)
    val noWhiteSpace = stripWhiteSpaces(noTags)
    formatWodHeading(stripWhiteSpaces(removeHtmlTags(description)))
  }

  private def removeHtmlTags(description: String) = {
    val pTagPattern: Regex = "<p>(.*?)</p>".r
    val formattedWodDescription = pTagPattern
      .findAllIn(description)
      .matchData
      .flatMap{m => m.subgroups}
      .toList
      .headOption

    formattedWodDescription.getOrElse(description)
  }

  private def stripWhiteSpaces(description: String) = {
    val pattern: Regex = "\\s{2}".r
    pattern.replaceAllIn(description, "\n\n")
  }

  private def formatWodHeading(description: String) = {
    val colonPattern: Regex = "\\:".r
    colonPattern.replaceAllIn(description, "\n")
  }
}
