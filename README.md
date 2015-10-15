
Introduction
--------------

This system is meant to create a carpool system for the University of Hawaii faculty and student body. It is meant to be a safe and easy to use system that will only allow people within the UH system to create carpools to the UH campus. The alternate purpose is to help reduce traffic and make commuting to UH a safer and more comfortable experience.

![screenshot](https://raw.github.com/CarpoolsUH/ui-mockup/gh-pages/img/cover.png)

![screenshot](https://raw.github.com/CarpoolsUH/ui-mockup/gh-pages/img/main.png)

**Live system** (http://carpoolsuh.kpaekn.cloudbees.net/)

Wiki Links
-------------

**User Evaluations** (https://github.com/CarpoolsUH/carpools-uh/wiki/User-Evaluation)

**Team Meetings** (https://github.com/CarpoolsUH/carpools-uh/wiki/Team-Meetings)

**Use Cases** (https://github.com/CarpoolsUH/carpools-uh/wiki/Use-Cases---Final)

**User Guide** (https://github.com/CarpoolsUH/carpools-uh/wiki/User-Guide)

**Developer Guide** (https://github.com/CarpoolsUH/carpools-uh/wiki/Developer-Guide)


Screencasts
--------------
**For Users**
<a href="http://youtu.be/Otk8dOHi9iA">![screenshot](https://raw.github.com/CarpoolsUH/ui-mockup/gh-pages/img/main.png)</a>

**For Developers**

Usage
--------------
Find or create carpools to UH Manoa at various times and locations.

Dependancies

**Git** [http://git-scm.com/downloads](http://git-scm.com/downloads)

**Play** Framework [http://www.playframework.com/download](http://www.playframework.com/download)

**Classic Distribution**

**SQL Database**

Make sure Git and Play are added to you system's path. To check, test the commands:


    git --version
    git version 1.8.4.msysgit.0

    play --version
    sbt launcher version 0.13.0


Get the Source

Using Git, clone the repository

    git clone https://github.com/CarpoolsUH/carpools-uh.git
    Cloning into 'carpools-uh'...
    remote: Counting objects: 1015, done.
    remote: Compressing objects: 100% (465/465), done.
    remote: Total 1015 (delta 537), reused 940 (delta 513)
    Receiving objects: 100% (1015/1015), 959.41 KiB | 324.00 KiB/s, done.
    Resolving deltas: 100% (537/537), done.
    Checking connectivity... done

Change directories

    cd carpools-uh

    
Set up your SQL Database using local SQL by setting system variables

Run the application

    play run
    [info] Loading project definition from C:\Users\Kevin\carpools-uh\project
    
    This project uses Play 2.2.2!
    Update the Play sbt-plugin version to 2.2.1 (usually in project/plugins.sbt)
    
    [info] Set current project to carpools-uh (in build file:/C:/Users/Kevin/carpools-uh/)
    [info] Updating {file:/C:/Users/Kevin/carpools-uh/}carpools-uh...
    [info] Resolving org.scala-lang#scala-library;2.10.3 ...
      [info] Resolving com.typesafe.play#play-java-jdbc_2.10;2.2.2 ...
      // ...
      [info] Resolving org.fusesource.jansi#jansi;1.4 ...
    [info] Done updating.
    
    --- (Running the application from SBT, auto-reloading is enabled) ---

    [info] play - Listening for HTTP on /0:0:0:0:0:0:0:0:9000

    (Server started, use Ctrl+D to stop and go back to the console...)


View the application at `http://localhost:9000`.
