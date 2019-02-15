package me.karakelley

import java.time.OffsetDateTime

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FreeSpec, Matchers}

class WodRetrievalServiceSpec extends FreeSpec with MockFactory with Matchers {
  "For WodRetrievalService" - {
    "Given a new WOD has been posted" - {
      "in the last 10 minutes" - {
        "It returns the WOD" in {
          val mockFeedReader = mock[FeedReader]
          val mockContentRetrieval = mock[ContentRetrieval]
          val tenMinutesAgo = OffsetDateTime.now().minusMinutes(9).minusSeconds(59)
          val wod = Wod("date", "some value", "some lnk")
          (mockContentRetrieval.getNewContent _)
            .expects("someUrl", 5000, 5000)
            .returning("some value")

          (mockFeedReader.contentLastBuildDate _)
            .expects(*)
            .returning(tenMinutesAgo)

          (mockFeedReader.parseLatestWod _)
            .expects("some value")
            .returning(wod)

          val wodRetrievalService = new WodRetrievalService(mockContentRetrieval, mockFeedReader)

          val result = wodRetrievalService.check("someUrl") shouldBe Some(wod)
        }
      }
      "11 minutes ago" - {
        "It returns None" in {
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

          val result = wodRetrievalService.newContent("someUrl") shouldBe None
        }
      }
    }
  }
}


