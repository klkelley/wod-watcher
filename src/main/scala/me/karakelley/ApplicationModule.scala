package me.karakelley

import com.google.inject.AbstractModule
import com.typesafe.config.Config
import net.codingwell.scalaguice.ScalaModule

class ApplicationModule(config: Config) extends AbstractModule with ScalaModule{

  override def configure(): Unit = {
    bind[Config].toInstance(config)
    val wodBlog = config.getString("wodBlog.url")
    val checkInterval = config.getString("wodCheck.scheduler.interval")

  }
}
