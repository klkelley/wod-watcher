package me.karakelley

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import scala.xml.{Elem, XML}

class FeedReader {

  def convertStringToXmlElem(feedAsAString: String): Elem = {
    XML.loadString(feedAsAString)
  }

  def getRssFeed(rssFeedXml: Elem): RssFeed = {
    // the list of stories in the rss feed

    // really should just go through this loop once to grab the first WOD - refactor this
    val rssItems = (rssFeedXml \\ "item")
    val rssFeedItems = for {
      i <- rssItems
      title = (i \ "title").text
      desc  = (i \ "description").text
    } yield Wod(title, desc)
    RssFeed(
      getChannelTitle(rssFeedXml),
      getChannelUrl(rssFeedXml),
      rssFeedItems
    )
  }

  def parseLatestWod(content: String) = {
    val contentAsXML: Elem = convertStringToXmlElem(content)
    getRssFeed(contentAsXML).wods.head
  }

  def contentLastBuildDate(content: String): OffsetDateTime = {
   val xmlElem = convertStringToXmlElem(content)
    getChannelLastBuildDate(xmlElem)
  }

  def getChannelTitle(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "title").text

  def getChannelUrl(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "link").text

  def getChannelDescription(rssFeedXml: Elem): String = (rssFeedXml \ "channel" \ "description").text

  private def getChannelLastBuildDate(rssFeedXml: Elem): OffsetDateTime = {
    val stringLastBuildDate = (rssFeedXml \ "channel" \ "lastBuildDate").text
    val formatter = DateTimeFormatter.RFC_1123_DATE_TIME
    OffsetDateTime.parse(stringLastBuildDate, formatter)
  }
}

