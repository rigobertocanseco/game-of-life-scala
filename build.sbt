name := "game-of-life-scala"
//version := "0.1"
//scalaVersion := "2.12.7"
//libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"

lazy val buildSettings = Seq(
    version := "0.0.1-SNAPSHOT",
    organization := "com.rigobertocanseco",
    scalaVersion := "2.12.7",
    scalacOptions := Seq("-deprecation", "-unchecked"),
    resolvers += Resolver.sonatypeRepo("public")
)

lazy val root = (project in file(".")).
    settings(buildSettings: _*).
    settings(name := "game-of-life-scala")

lazy val library = (project in file("library")).
    settings(buildSettings: _*)

// Add dependency on ScalaFX library
libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.2-R18"

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
}

// Add dependency on JavaFX libraries, OS dependent
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m =>
    "org.openjfx" % s"javafx-$m" % "12.0.2" classifier osName
)
