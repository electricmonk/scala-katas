scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

name := "skala-katas"

organization := "com.shaiyallin"

version := "0.1.0-SNAPSHOT"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.2" % "test"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.0.6"
