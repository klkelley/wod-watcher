package me.karakelley

import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe, _}
import me.karakelley.WodSchedulerSpec.{MockWodChecker, Ticked}
import me.karakelley.scheduler.WodScheduler
import org.scalatest.{BeforeAndAfterAll, FreeSpecLike}

import scala.concurrent.Future
import scala.concurrent.duration._

class WodSchedulerSpec extends TestKit(ActorSystem("WodCheckerSpec")) with FreeSpecLike with BeforeAndAfterAll {
  "For WodSchedulerSpec" - {
    "given a configured interval" - {
      "it must perform the check on the expected interval" in {
        val probe = TestProbe()
        val wodCheck = new MockWodChecker(probe)

        within(3.second.dilated) {
          system.actorOf(WodScheduler.props(wodCheck))
          0 to 3 foreach (_ ⇒ probe.expectMsg(Ticked))
        }
      }
    }
  }

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
}


object WodSchedulerSpec {
  case object Ticked

  class MockWodChecker(probe: TestProbe) extends ContentChecker {
    override def apply(): Future[Unit] =
      Future.successful(probe.ref ! Ticked)
  }
}
