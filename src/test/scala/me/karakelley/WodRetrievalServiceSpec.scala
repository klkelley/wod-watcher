package me.karakelley

import java.time.OffsetDateTime

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FreeSpec, Matchers}

class WodRetrievalServiceSpec extends FreeSpec with MockFactory with Matchers {
  "For WodRetrievalService" - {
    "Given a new WOD has been posted" - {
      "It returns true" in {
        val mockFeedReader = mock[FeedReader]
        val mockContentRetrieval = mock[ContentRetrieval]
        val todaysDate =  OffsetDateTime.now().toLocalDate

        (mockContentRetrieval.getNewContent _)
          .expects("someUrl", 5000, 5000)
          .returning("some value")

        (mockFeedReader.contentLastBuildDate _)
          .expects(*)
          .returning(todaysDate)

        val wodRetrievalService = new WodRetrievalService(mockContentRetrieval, mockFeedReader)

        val result = wodRetrievalService.newContent("someUrl") shouldBe true
      }
    }
  }
}
