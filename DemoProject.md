# Build and run demo project #

For build and run demo project follow these steps:

## Checkout demo project source from SVN ##
Checkout instructions are in Source page of this project. Checkout directory for demo project is:
```
svn/trunk/gxt-multi-upload-demo/
```

## Build project ##
Before build project using Maven, you have to install GXT MultiUpload jar module into your Maven repository. For build project, just type:
```
mvn clean compile -Dgwt.compile
```
where '-Dgwt.compile' parameter runs ANT task for compiling GWT source code using GWT compiler.

## Run project using Maven ##
Maven project descriptor file contains dependency and configuration for Jetty plugin. [Jetty](http://jetty.codehaus.org/jetty/) is a lightweight servlet container which can be used also for debugging of your project in your favorite IDE.
For running demo project, just type:
```
mvn jetty:run
```
Demo project is now available on port 8080 with application path 'demo'. For example, on your localhost is: http://localhost:8080/demo.

## Optional: import project into your IDE ##
Create Maven project files for your favorite IDE, e.g. for Eclipse type:
```
mvn eclipse:eclipse
```

## Optional: running project hosted mode using Eclipse ##
Just launch project using Jetty and then run demo-hosted.launch file in Eclipse.