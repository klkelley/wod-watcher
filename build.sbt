name := "wod-watcher"

maintainer := "Kara Kelley"
version := "0.1"

scalaVersion := "2.12.8"

mainClass := Some("me.karakelley.Main")

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.rometools" % "rome" % "1.8.1",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "com.typesafe.akka" %% "akka-actor" % "2.5.19",
  "com.typesafe" % "config" % "1.3.2",
  "net.codingwell" %% "scala-guice" % "4.2.2",
  "com.twilio.sdk" % "twilio" % "7.15.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.19" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test
)

enablePlugins(JavaAppPackaging)
