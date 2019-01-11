package me.karakelley

import akka.actor.ActorSystem
import com.google.inject.Guice
import com.typesafe.config.ConfigFactory
import me.karakelley.scheduler.WodScheduler

object Main extends App {
  val config = ConfigFactory.load()
  val injector = Guice.createInjector(new ApplicationModule(config))

  val system: ActorSystem = ActorSystem("wodscheduler", config)

  lazy val contentRetrieval = new ContentRetrieval()

  lazy val wodRetrievalService = new WodRetrievalService(contentRetrieval, new FeedReader)
  system.actorOf(WodScheduler.props(new NewWodChecker(wodRetrievalService, new SMSClient(config), config)))
}
