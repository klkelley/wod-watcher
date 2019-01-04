package me.karakelley

import scala.concurrent.Future

trait ContentChecker extends (() ⇒ Future[Unit])

class NewWodChecker(
  wodRetrievalService: WodRetrievalService
) extends ContentChecker {
 // this class will take the content retrieval and feed reader
  override def apply(): Future[Unit] = {
    // check for new content  and send the text message
    // actiually there needs to be a service composed on the RSSFeed Reader and content checker
    // the service will do the logic of determining if there is a new WOD based on date, etc and then it will
    // need to send the text message
    wodRetrievalService.check("https://atlasperformance.com/blog/feed/")
  }
}
