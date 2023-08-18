# Transit Theme

Modern JavaFX theme / style that can be used to give applications a different look and feel.

## Real World Examples
![Sample CAD app using JMetro](./Hero-model-view-1024x555(2).png)
![Sample DansoftOnwer FXTaskbarProgressBar](./JMetroSample-DansoftOwner_FXTaskbarProgressBar.jpg)

## Documentation
Documentation of Transit can be found in this link: [Transit Java, Javafx theme documentation](https://pixelduke.com/transit-java-javafx-theme/).

## Strengths and Key Principles
* Modern look and feel
* Zero tight coupling with this library: 
  * No new controls. Functionality is added "behind the curtains" to existing JavaFX controls through the Skin API (FXSkins library)
  * Setting and unsetting is seamless and easy (only 1 line of code) even on already existing apps that don't use this theme
* Looks integrated on Windows (80%/90% of desktop users use Windows) also works well on other OSes
* Light and Dark versions;
* Easily override and customize colors by overriding JavaFX CSS variables
* Only relies on JavaFX CSS, JavaFX API and Java code (no other "foreign" technologies)
* Samples and theme tester app on samples sub-project
* Leverages lessons learned developing JMetro theme
* Lots of real world, recognized Java apps already using it (NASA's applications, applications used in the White House, etc, etc) (JMetro and Transit)

Check [documentation](https://pixelduke.com/transit-java-javafx-theme/) for a deeper dive.

## Getting Transit
You can get it through Maven Central.

Here are examples for Gradle and Maven (replace version number with the version you want):

#### Gradle
```groovy
implementation 'com.pixelduke:transit:1.0.0'
```

#### Maven
```xml
<dependency>
    <groupId>com.pixelduke</groupId>
    <artifactId>transit</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Source code
The transit-samples subproject has samples that you can run and check out how to use FXSkins.

As of the writing of this document, the code is being compiled on Java 17 and JavaFX 20.

## Pull Requests (PR)
We welcome contributions via PR.  
Before submitting a PR please file an issue for prior discussion. This will avoid you wasting time with a PR that
might not be approved because, for instance, might be outside the intended scope of the project.

### Filing bugs
When filing bugs it's most often good practice to attach a small sample app (as small, simple and with the fewest lines of code as possible). This app when executed, should show the bug happening.  
The reason for this is the limited amount of resources and time I have and also because in the process of filing a bug, developers sometimes discover that the bug isn't in the library but somewhere else.   
Without a small example app the issue might be closed prematurely.

## Feedback request
Please send pictures of your application that is using **transit**, or a site that shows your application. Or share it through Twitter (you can reference
me through my twitter handle @P_Duke if you'd like).   
This is very important for me to know how users are effectively using it and make adjustments accordingly to make FXSkins better. 
Also, and if you allow it, to showcase example uses.    
Seeing this library get used also always motivates me to keep working on it.

## License
Transit uses the ['GNU General Public License, version 2, with the Classpath Exception'](https://openjdk.java.net/legal/gplv2+ce.html)

