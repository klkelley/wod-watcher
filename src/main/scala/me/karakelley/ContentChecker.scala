package me.karakelley

import scala.concurrent.Future

trait ContentChecker extends (() ⇒ Future[Unit])

class NewWodChecker extends ContentChecker {
 // this class will take the content retrieval and feed reader
  override def apply(): Future[Unit] = {
    // check for new content  and send the text message
    print("we are here")
    Future.successful()
  }
}
