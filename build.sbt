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
    "org.specs2" %% "specs2-core" % "3.7.2" % "test",
    // Dependencies for reflection
    "org.reflections" % "reflections" % "0.9.10",
    "com.google.code.findbugs" % "annotations" % "2.0.1"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")