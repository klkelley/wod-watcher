package me.karakelley

import com.typesafe.config.Config

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

trait WodChecker extends (() ⇒ Future[Unit])

class NewWodChecker(
  wodRetrievalService: WodRetrievalService,
  smsClient: SMSClient,
  config: Config
) extends WodChecker {

  private val wodBlog = config.getString("wodBlog.url")

  override def apply(): Future[Unit] = {
    wodRetrievalService.check(wodBlog) match {
      case Some(wod) ⇒
        Try(smsClient.send(wod)) match {
        case Success(_) ⇒ Future.successful()
        case Failure(exception) ⇒ Future.failed(exception)
        }
      case _ ⇒ Future.successful()
    }
  }
}
