HT Exercise
===========
This is an exercise to use Google Place API through Retrofit REST Client and other libraries.

* [Before Coding](#before-coding)
  * [Project Outlines](#project-outlines)
  * [Learn about the technology involved](#learn-about-the-technology-involved)
  * [Plan the project](#plan-the-project)
* [Questions](#questions)
  * [Error Handling](#error-handling)
  * [Ensure Smooth User Experience](#ensure-smooth-user-experience)
  * [Potential Improvements](#potential-improvements)
* [Installation](#installation)
* [Reference](#reference)
* [Appendix A: Development Environment and Conventions](#appendix-a-development-environment-and-conventions)
* [Appendix B: Get Ready for Development](#appendix-b-get-ready-for-development)


## Before coding
Before starting a project, I estimate its scope, cost and time, a.k.a the [Project Management Triangle](http://en.wikipedia.org/wiki/Project_management_triangle).

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

4. **API call failure due to varies reasons (401, 404, 500)**
<br>
Use the failure() method of Retrofit and display a message to ask the user to try again later.

The errors that I had avoided on my [planning stage](#plan-the-project) are **java.lang.IndexOutOfBoundsException** and **java.lang.ClassCastException**.


### Ensure Smooth User Experience
**Where could the user experience break and how did I prevent those?**

There are some place that the user experience (UX) could break and they could either due to **API connection** issue or **app responsiveness issue**.

#### API Connection Issues
1. **No internet is connected**
<br>
If users use the service while no internet is available, Retrofit will show a toast message saying that host is not found.  The message makes no sense to average users, neither does it suggest any solution to them.
<br><br>
Therefore, I add connectivity check on my API client.  If users attempt to use the service without internet access, the app will show them a toast message that asking them to connect to internet.

2. **API call failure**
<br>
As I mentioned in above, it is an error that I had encountered.  Solving it is pretty straightforward thanks to the Retrofit's default failure() method.
<br><br>
When this condition hits, failure() method is called, and I display a toast message to ask the users to try again later.

#### App Responsiveness Issues
1. **When to send API request for autocompletes**
<br>
This question seems trivial at first, but it has a big impact on UX, and I spent quite a bit of time to make it feel smooth.
<br><br>
First of all, no API call will be made if the user type in spaces.  The first API call for autocompletes will be sent when users type in their first character.  The list view will only be visible if there are autocompletes available, otherwise it should be invisible.  New API call will be made every time users type in 2 addition characters.

2. **Clear the old autocompletes before populate the new ones**
<br>
Once the new autocompletes arrive, clear the list and populate the new ones.

3. **What should happen when clicking on "search" on keyboard?**
<br>
This is one of those minor places that would make user feel weird if the app doesn't response.
<br><br>
When users press "search", if their query is contained on the autocompletes, show the details of that query, otherwise, show place details of the first one on the autocomplete list.


### Potential Improvements
**What other improvements can be made?**

#### Checked List
1. **Instruction on Search Widget**
<br>
When user first click on the search widget, its hint will show a message suggesting what user should type.

2. **Auto fill up query**
<br>
When user clicks on the autocomplete, the query on search widget will be changed to it accordingly

3. **Proper height of autocomplete list**
<br>
Make the row higher so that it is easier to press.


#### Todo List

1. **Prevent potential double tapping issue**
<br>
Although I have not seen it when I debug the app, I know it could happen and it is something that I would like to improve if I have more time.


## Installation
Just user the run button of Eclipse IDE to build and run the app, all libraries are provided under `libs` folder.  I decided to not to use Gradle this time because of the scope of this project.

## Reference
1. [Creating a Search Interface](http://developer.android.com/guide/topics/search/search-dialog.html)
2. [Adding Custom Suggestions](http://developer.android.com/guide/topics/search/adding-custom-suggestions.html)
3. [Adding an Action View](http://developer.android.com/guide/topics/ui/actionbar.html#ActionView)
4. [Adding Search Functionality](https://developer.android.com/training/search/index.html)
5. [Iconography](http://developer.android.com/design/style/iconography.html)
6. [Android Actionbar Search widget implementation In ListFragment](http://stackoverflow.com/questions/9556795/android-actionbar-search-widget-implementation-in-listfragment)
7. [An MVP Pattern for Android](http://magenic.com/BlogArchive/AnMVPPatternforAndroid)
8. [MVP for Android: how to organize the presentation layer](http://antonioleiva.com/mvp-android)
9. [Nationwide Android Application Development & Integration](http://magenic.com/Services/MobileEnterpriseDevelopment/AndroidApplicationDevelopment)
10. [Using Model-View-Presenter (MVP) pattern in Compact Framework](http://breathingtech.com/2009/using-model-view-presenter-mvp-pattern-in-compact-framework)
11. [Model View Presenter (MVP) design pattern and data binding](http://www.c-sharpcorner.com/UploadFile/john_charles/model-view-presenter-mvp-design-pattern-and-data-binding)
12. [Google Place Autocomplete API](https://developers.google.com/places/documentation/autocomplete)
13. [Google Place Details API](https://developers.google.com/places/documentation/details)
14. [BEST PRACTICES FOR CONSUMING APIS ON ANDROID](http://engineering.meetme.com/2014/03/best-practices-for-consuming-apis-on-android)


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
I have tried using the Android ADT bundle, but its eclipse always says it is broken and unable to start.  Therefore, I just went with the standard [eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/lunasr1).

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
