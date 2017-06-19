lazy val root = project.in(file(".")).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalactic" %%% "scalactic" % "3.0.1" % "test",
      "org.scalatest" %%% "scalatest" % "3.0.1" % "test"
    )
  )

lazy val sharedJVM = shared.jvm
lazy val sharedJS = shared.js

lazy val commonSettings = Seq(
  startYear             := Some(2016),
  scalaVersion          := "2.12.1",
  scalacOptions         ++= Seq("-target:jvm-1.8", "-deprecation", "-unchecked", "-Xcheckinit", "-encoding", "utf8", "-feature"),
  scalacOptions         ++= Seq(
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:reflectiveCalls",
    "-language:higherKinds"
  ),

  // configure prompt to show current project
  shellPrompt           := { s => Project.extract(s).currentProject.id + " > " },

  initialCommands in console :=
    """
      |import java.nio.file._
      |import scala.concurrent._
      |import scala.concurrent.duration._
      |import ExecutionContext.Implicits.global
    """.stripMargin
)
