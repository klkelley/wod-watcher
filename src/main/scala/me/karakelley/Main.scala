package me.karakelley

import akka.actor.ActorSystem
import me.karakelley.scheduler.WodScheduler

object Main extends App {
  val system: ActorSystem = ActorSystem("wodscheduler")

  lazy val contentRetrieval = new ContentRetrieval("https://atlasperformance.com/blog/feed/")
  system.actorOf(WodScheduler.props(new NewWodChecker(new WodRetrievalService(contentRetrieval, new FeedReader))))
}
