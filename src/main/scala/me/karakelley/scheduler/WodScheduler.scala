package me.karakelley.scheduler

import akka.actor.{Actor, ActorLogging, Props, Timers}
import me.karakelley.ContentChecker
import me.karakelley.scheduler.WodScheduler.{WodCheck, wodCheckInterval}


class WodScheduler(wodCheck: ContentChecker) extends Actor with Timers with ActorLogging{

  private val interval = context.system.settings.config.getDuration(wodCheckInterval)
  timers.startPeriodicTimer(key = WodCheck, msg = WodCheck, interval = interval)

  override def receive: Receive = {
    case WodCheck ⇒ wodCheck()
    case unknown ⇒ log.info("something blew up")
  }
}

case object WodScheduler {
  private case object WodCheck
  private final val wodCheckInterval = "wodCheck.scheduler.interval"

  def props(wodcheck: ContentChecker): Props = {
    Props(new WodScheduler(wodcheck))
  }
}

