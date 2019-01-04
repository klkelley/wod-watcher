package me.karakelley

import scala.concurrent.Future

class WodRetrievalService(
  contentRetrieval: ContentRetrieval,
  feedReader: FeedReader
) {

  def check(url: String): Future[Unit] = {
    newContent(url) match {
      case true ⇒ Future.successful(println("sms here"))
      case _ ⇒ {
        Future.successful(())
      }
    }
  }

  def newContent(url: String): Boolean = {
   val content = contentRetrieval.getNewContent(url, 5000, 5000)
    val lastBuildDate = feedReader.contentLastBuildDate(content)
    java.time.LocalDate.now() == lastBuildDate
  }
}
