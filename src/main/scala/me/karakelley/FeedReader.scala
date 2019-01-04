package me.karakelley

import java.time.{LocalDate, OffsetDateTime}
import java.time.format.DateTimeFormatter

import scala.xml.{Elem, XML}

class FeedReader {

  def convertStringToXmlElem(feedAsAString: String): Elem = {
    XML.loadString(feedAsAString)
  }

  def getRssFeed(rssFeedXml: Elem): RssFeed = {
    // the list of stories in the rss feed
    val rssItems = (rssFeedXml \\ "item")
    val rssFeedItems = for {
      i <- rssItems
      title = (i \ "title").text
      url   = (i \ "link").text
      desc  = (i \ "description").text
    } yield Wod(title, url, desc)
    RssFeed(
      getChannelTitle(rssFeedXml),
      getChannelUrl(rssFeedXml),
      rssFeedItems
    )
  }

  def contentLastBuildDate(content: String): LocalDate = {
   val xmlElem = convertStringToXmlElem(content)
    getChannelLastBuildDate(xmlElem)
  }

  def getChannelTitle(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "title").text

  def getChannelUrl(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "link").text

  def getChannelDescription(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "description").text

  private def getChannelLastBuildDate(rssFeedXml: Elem): LocalDate = {
    val stringLastBuildDate = (rssFeedXml \ "channel" \ "lastBuildDate").text
    val formatter = DateTimeFormatter.RFC_1123_DATE_TIME
    OffsetDateTime.parse(stringLastBuildDate, formatter).toLocalDate
  }
}

