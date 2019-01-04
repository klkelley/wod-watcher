package me.karakelley

import java.time.OffsetDateTime

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FreeSpec, Matchers}

class WodRetrievalServiceSpec extends FreeSpec with MockFactory with Matchers {
  "For WodRetrievalService" - {
    "Given a new WOD has been posted" - {
      "in the last 10 minutes" - {
        "It returns true" in {
          val mockFeedReader = mock[FeedReader]
          val mockContentRetrieval = mock[ContentRetrieval]
          val tenMinutesAgo = OffsetDateTime.now().minusMinutes(9).minusSeconds(59)

          (mockContentRetrieval.getNewContent _)
            .expects("someUrl", 5000, 5000)
            .returning("some value")

          (mockFeedReader.contentLastBuildDate _)
            .expects(*)
            .returning(tenMinutesAgo)

          val wodRetrievalService = new WodRetrievalService(mockContentRetrieval, mockFeedReader)

          val result = wodRetrievalService.newContent("someUrl") shouldBe true
        }
      }
      "11 minutes ago" - {
        "It returns false" in {
          val mockFeedReader = mock[FeedReader]
          val mockContentRetrieval = mock[ContentRetrieval]
          val elevenMinutesAgo = OffsetDateTime.now().minusMinutes(11)

          (mockContentRetrieval.getNewContent _)
            .expects("someUrl", 5000, 5000)
            .returning("some value")

          (mockFeedReader.contentLastBuildDate _)
            .expects(*)
            .returning(elevenMinutesAgo )

          val wodRetrievalService = new WodRetrievalService(mockContentRetrieval, mockFeedReader)

          val result = wodRetrievalService.newContent("someUrl") shouldBe false
        }
      }
    }
  }
}

// if posted -> sends SMS
// if not posted ->  no sms sent
// if posted and sms already sent -- does not send another one

