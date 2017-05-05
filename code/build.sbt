name := "deadbolt-java"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

organization := "be.objectify"

libraryDependencies ++= Seq(
  cacheApi,
  "org.mockito" % "mockito-all" % "1.10.19" % "test"
)

resolvers += Resolver.sonatypeRepo("snapshots")

releasePublishArtifactsAction := PgpKeys.publishSigned.value