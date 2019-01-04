package me.karakelley.scheduler

import akka.actor.{Actor, ActorLogging, Props, Timers}
import me.karakelley.ContentChecker
import me.karakelley.scheduler.WodScheduler.WodCheck


class WodScheduler(wodCheck: ContentChecker) extends Actor with Timers with ActorLogging{

  private val interval = java.time.Duration.ofMillis(200)
  timers.startPeriodicTimer(key = WodCheck, msg = WodCheck, interval = interval)

  override def receive: Receive = {
    case WodCheck ⇒ wodCheck()
    case unknown ⇒ log.info("something blew up")
  }
}

case object WodScheduler {
  private case object WodCheck

  def props(wodcheck: ContentChecker): Props = {
    Props(new WodScheduler(wodcheck))
  }
}

