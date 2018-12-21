package me.karakelley.scheduler

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import me.karakelley.WodCheck
import scala.concurrent.duration._

sealed trait Message
case object Tick extends Message

class WodScheduler(wodCheck: WodCheck) extends Actor with ActorLogging{
  override def receive: Receive = {
    case Tick ⇒ wodCheck()
    case unknown ⇒ log.info("something blew up")
  }

}

case object WodScheduler {
  val system = ActorSystem("wodscheduler")

  val actor = system.actorOf(Props[WodScheduler], "WodScheduler")

  import system.dispatcher
  val cancellable = system.scheduler.schedule(
    0 milliseconds,
    10 minutes,
    actor ,
    Tick)
}

