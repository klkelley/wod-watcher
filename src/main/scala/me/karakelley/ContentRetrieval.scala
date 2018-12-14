package me.karakelley

import scalaj.http.{Http, HttpResponse}

class ContentRetrieval(url: String) {
  def getNewContent(
    url: String,
    connectTimeout: Int = 5000,
    readTimeout: Int = 5000
  ): HttpResponse[String] = {
    Http(url).timeout(connTimeoutMs=connectTimeout, readTimeoutMs=readTimeout).asString
  }
}
