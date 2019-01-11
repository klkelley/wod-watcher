package me.karakelley

import java.time.OffsetDateTime

class WodRetrievalService(
  contentRetrieval: ContentRetrieval,
  feedReader: FeedReader
) {
  def check(url: String): Option[Wod] = {
    newContent(url) match {
      case Some(content) ⇒ Some(feedReader.parseLatestWod(content))
      case _ ⇒ None
    }
  }

  def newContent(url: String): Option[String] = {
    val content: String = contentRetrieval.getNewContent(url, 5000, 5000)

    newWodPosted(content) match {
      case true ⇒ Some(content)
      case _ ⇒ None
    }
  }

  private def newWodPosted(content: String) = {
    val lastPostedWod: OffsetDateTime = feedReader.contentLastBuildDate(content)
    val tenMinutesAgo: OffsetDateTime = java.time.OffsetDateTime.now().minusMinutes(10)
    val now: OffsetDateTime = java.time.OffsetDateTime.now()

    (lastPostedWod.isBefore(now) && lastPostedWod.isAfter(tenMinutesAgo)) || lastPostedWod.isEqual(tenMinutesAgo)
  }
}
