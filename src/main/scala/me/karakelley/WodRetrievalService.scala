package me.karakelley

import java.time.{LocalDate, OffsetDateTime}

import scala.concurrent.Future

class WodRetrievalService(
  contentRetrieval: ContentRetrieval,
  feedReader: FeedReader
) {

  def check(url: String): Future[Unit] = {
    newContent(url) match {
      case true ⇒ Future.successful(println("sms here"))
      case _ ⇒ Future.successful(())
    }
  }

  def newContent(url: String): Boolean = {
   val content = contentRetrieval.getNewContent(url, 5000, 5000)
    val lastPostedWod: OffsetDateTime = feedReader.contentLastBuildDate(content)
    val tenMinutesAgo: OffsetDateTime = java.time.OffsetDateTime.now().minusMinutes(10)
    val now: OffsetDateTime = java.time.OffsetDateTime.now()
    (lastPostedWod.isAfter(tenMinutesAgo) || lastPostedWod.isEqual(tenMinutesAgo)) && lastPostedWod.isBefore(now)
  }
}
