package me.karakelley

import scala.concurrent.Future

trait WodCheck extends (() ⇒ Future[Unit])

class WodChecker extends WodCheck {
 // this class will take the content retrieval and feed reader
  override def apply(): Future[Unit] = {
    // check for new content  and send the text message
   ???
  }
}
