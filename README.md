HT Exercise
===========
This is an exercise to use Google Place API through Retrofit REST Client and other libraries.

* [Before Coding](#before-coding)
  * [Project Outlines](#project-outlines)
  * [Learn about the technology involved](#learn-about-the-technology-involoved)
  * [Plan the project](#plan-the-project)
* [Questions](#questions)
  * [Error Handling](#error-handling)
  * [Ensure Smooth User Experience](#ensure-smooth-user-experience)
  * [Potential Improvements](#potential-improvements)
* [Appendix A: Development Environment and Conventions](#appendix-a-development-environment-and-conventions)
* [Appendix B: Get Ready for Development](#appendix-b-get-ready-for-development)


## Before coding
Before starting a project, I estimate its scope, cost and time, which also known as the [Project Management Triangle](http://en.wikipedia.org/wiki/Project_management_triangle).

### Project Outlines
I ask myself these questions to outline a project.

1. **What are the requirements? / What am I coding?**
<br>
I am coding an **Android app** that has **ActionBar**, use **SearchView**, and interact with internet through **Google Place Autocomplete** and **Google Place Details** APIs.

2. **What is the scope of the project? / Who are the users? / Who am I coding for?**
<br>
This is a broad question, but in this contrast, my main interest of asking this question is to know **what is the minimum SDK version that I should support**.
<br><br>
I consult the [Google Dashboard](https://developer.android.com/about/dashboards/index.html#Platform), which tells me that **87.9%** of the circulating Android devices are using SDK 15 or above.  Therefore, I believe it is reasonable for me to set the minimum SDK version of this project to **15**.
<br><br>
If the requirement specifies the minimum SDK version to be lower than 15, I can implement the [**actionbarsherlock extension**](http://actionbarsherlock.com/) to meet the requirement.

3. **How much time do I have? / When is the deadline?**
<br>
Few hours after work for 3 weekdays, 1 Saturday and 0.5 Sunday, which sums up approximately **24 workable hours**.

4. **What are my resources?**
<br>
Any helps, references and libraries available online.

### Learn about the technology involved
Learning is a lifetime job, it is inevitable, or in fact, normal to encounter some new knowledge on a new project.  Therefore, I spent some time to learn and understand about them, such as their official documents and online tutorials, before starting to code.

The full list is attached under the [Reference](#reference) section.

### Plan the project
Model View Presenter (MVP) design pattern requires a bit of time to get it perfect.  Since this project is relatively small and time sensitive, I decided to go with the **plan-code-improve** approach.

I first planned out the project's rough structure, such as relationship between classes, interfaces and methods, on paper.

I quickly crafted the structure of API client using Retrofit to get the communication part done because it is a bit easier and has less ambiguity.

I then started focus on getting an working prototype, connect it with my API client, and perform iterative improvements to achieve MVP.


## Questions
### Error Handling
**What error conditions did I encounter and how did I handled them?**

Since I planned my steps before starting to code, I only encounter few error conditions.

1. **Our infamous buddy: java.lang.NullPointerException**
<br>
I traced the log to see where did it come from, then I either fixed the code structure to make sure it will never be null if null is not allowed, or I add if condition to handle the response if null is permitted in the situation.

2. **java.lang.ClassNotFoundException**
<br>
This error came to me when I refactor some class names, yet the AndroidManifest.xml doesn't get updated automatically or at least properly.  Therefore, I just correct the class names on AndroidManifest.xml manually.

3. **Missing Internet Access Permission**
<br>
Declare the permission in AndroidManifest.xml solves it.

4. **API call failure due to varies reasons (401,404,500)**
<br>
Use the failure() method of Retrofit and display a message to ask the user to try again later.

The errors that I had avoided on my [planning stage](#plan-the-project) are **java.lang.IndexOutOfBoundsException** and **java.lang.ClassCastException**.


### Ensure Smooth User Experience
Where could the user experience break? How will you prevent this?





## Potential Improvements
What other improvements can be made?

When user clicks on the suggestion, the text on search widget will be changed to it accordingly

When user first click on the search widget, its hint will show a message suggesting what user should type.

make the row higher so it is easier to press

To keep code easier to read, I avoid using large if else if statement by keeping if blocks small.


## Reference
1. [Creating a Search Interface](http://developer.android.com/guide/topics/search/search-dialog.html)
1. [Adding Custom Suggestions](http://developer.android.com/guide/topics/search/adding-custom-suggestions.html)
1. [Adding an Action View](http://developer.android.com/guide/topics/ui/actionbar.html#ActionView)
1. [Adding Search Functionality](https://developer.android.com/training/search/index.html)
1. [Iconography](http://developer.android.com/design/style/iconography.html)
1. [Android Actionbar Search widget implementation In ListFragment](http://stackoverflow.com/questions/9556795/android-actionbar-search-widget-implementation-in-listfragment)
1. [An MVP Pattern for Android](http://magenic.com/BlogArchive/AnMVPPatternforAndroid)
1. [MVP for Android: how to organize the presentation layer](http://antonioleiva.com/mvp-android)
1. [Nationwide Android Application Development & Integration](http://magenic.com/Services/MobileEnterpriseDevelopment/AndroidApplicationDevelopment)
1. [Using Model-View-Presenter (MVP) pattern in Compact Framework](http://breathingtech.com/2009/using-model-view-presenter-mvp-pattern-in-compact-framework)
1. [Model View Presenter (MVP) design pattern and data binding](http://www.c-sharpcorner.com/UploadFile/john_charles/model-view-presenter-mvp-design-pattern-and-data-binding)
1. [Google Place Autocomplete API](https://developers.google.com/places/documentation/autocomplete)
1. [Google Place Details API](https://developers.google.com/places/documentation/details)
1. [BEST PRACTICES FOR CONSUMING APIS ON ANDROID](http://engineering.meetme.com/2014/03/best-practices-for-consuming-apis-on-android)


## Appendix A: Development Environment and Conventions
### Development Environment
| Item                  | Value                  |
| :-------------------- | :--------------------- |
| Operating System      | Mac OS X 10.9.5        |
| Processor             | 1.8 GHz Intel i5       |
| Memory                | 8 GB 1600MHz DDR3      |
| IDE                   | Eclipse Luna 4.4.1     |
| Test Device           | Samsung GS4 with 4.4.2 |

### Naming Convention
* `lowerCamelCase` is used for naming Java methods and variables
* `CamelCase` is used for naming Java classes
* `snake_case` is used for naming XML files and variables


## Appendix B: Get Ready for Development
### Setup Eclipse IDE

### Setup Android SDK
First of all, we need to setup Android SDK on you machine.  we can follow my instruction [here](https://github.com/ctrl-alt-del/devenv#android-sdk) to setup the Android SDK on Mac.

Once Android SDK is setup, make sure your packages are up to date.

In order to use some of the libraries that are introduced in this exercise, you may also need to install the following packages through the SDK Manager.
```sh
/Extra/Goolge Support Library
/Extra/Goolge Support Repository
/Extra/Goolge Play service
/Extra/Goolge Repository
```

### Setup Android Developer Tool (ADT) Plugin
Make sure the ADT Plugin for Eclipse is up to date.  This is very important, otherwise, the IDE may not load the project properly.

### Standard Iconography
You can get the standard icons for different device from [here](http://developer.android.com/design/style/iconography.html).
In this exercise, we will use the standard search icons that are available in [this zip](http://commondatastorage.googleapis.com/androiddevelopers/design/Android_Design_Icons_20131106.zip).
