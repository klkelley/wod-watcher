package me.karakelley

import java.time.LocalDate

import org.scalatest.{FreeSpec, Matchers}


class FeedReaderSpec extends FreeSpec with Matchers with RssTestKit {
  "NewContentChecker" - {
    "given valid xml" - {
      "it parses the xml and rss feed" - {
        "And returns an RssFeed" in {
          val contentRetrieval = new FeedReader()
          val rssFeed = contentRetrieval.convertStringToXmlElem(sampleRss)
          val feed = contentRetrieval.getRssFeed(rssFeed)

          feed.channelTitle.contains("WOD Blog") shouldBe true
        }
      }
    }

    "it parses the last build date" - {
      "and returns the date" in {
        val contentRetrieval = new FeedReader()
        val buildDate = contentRetrieval.contentLastBuildDate(sampleRss)

        buildDate.getYear shouldBe 2018
      }
    }
  }
}
