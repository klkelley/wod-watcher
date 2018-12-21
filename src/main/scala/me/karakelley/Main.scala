package me.karakelley

import akka.actor.ActorSystem
import me.karakelley.scheduler.WodScheduler

object Main extends App {
  val system: ActorSystem = ActorSystem("wodscheduler")

  system.actorOf(WodScheduler.props(new NewWodChecker))
}
