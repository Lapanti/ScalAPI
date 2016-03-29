name := "ScalAPI"

version := "0.01"

scalaVersion := "2.11.7"

scalacOptions += "-target:jvm-1.8"

libraryDependencies ++= {
  val akkaVersion = "2.4.2"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "org.scala-lang" % "scala-reflect" % "2.11.7",
    "org.specs2" %% "specs2-core" % "3.7.2" % "test"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")