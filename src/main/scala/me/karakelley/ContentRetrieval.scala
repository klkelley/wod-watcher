package me.karakelley

import scalaj.http.Http

class ContentRetrieval(url: String) {
  def getNewContent(
    url: String,
    connectTimeout: Int = 5000,
    readTimeout: Int = 5000
  ): String = {
    Http(url).timeout(connTimeoutMs=connectTimeout, readTimeoutMs=readTimeout).asString.body
  }
}
