name := "carpools-uh"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
   "mysql" % "mysql-connector-java" % "5.1.21",
   "com.typesafe" %% "play-plugins-mailer" % "2.2.0"
)     

play.Project.playJavaSettings
