package me.karakelley

case class RssFeed(
  channelTitle: String,
  channelUrl: String,
  wods: Seq[Wod]
)
