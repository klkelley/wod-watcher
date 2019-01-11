package me.karakelley.scheduler

import akka.actor.{Actor, ActorLogging, Props, Timers}
import me.karakelley.WodChecker
import me.karakelley.scheduler.WodScheduler.{WodCheck, wodCheckInterval}


class WodScheduler(wodCheck: WodChecker) extends Actor with Timers with ActorLogging{

  private val interval = context.system.settings.config.getDuration(wodCheckInterval)
  timers.startPeriodicTimer(key = WodCheck, msg = WodCheck, interval = interval)

  override def receive: Receive = {
    case WodCheck ⇒ wodCheck()
    case _ ⇒ log.info("something blew up")
  }
}

case object WodScheduler {
  private case object WodCheck
  private final val wodCheckInterval = "wodCheck.scheduler.interval"

  def props(wodcheck: WodChecker): Props = {
    Props(new WodScheduler(wodcheck))
  }
}

