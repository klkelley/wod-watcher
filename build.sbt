name := "wod-watcher"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.rometools" % "rome" % "1.8.1", 
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scalaj" %% "scalaj-http" % "2.3.0"
)