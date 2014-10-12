HT Exercise
===========
This is an exercise to use Google Place API through Retrofit REST Client and other libraries.

* [Before Coding](#before-coding)
  * [Project Outlines](#project-outlines)
* [Coding Style](#coding-style)
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
This is a broad question, but in this contrast, my main interest of asking this question is to know what is the minimum SDK version that I should support.
<br><br>
I consult the [Google Dashboard](https://developer.android.com/about/dashboards/index.html#Platform), which tells me that **87.9%** of the circulating Android devices are using SDK 15 or above.  Therefore, I believe it is reasonable for me to set the minimum SDK version of this project to **15**.

3. **How much time do I have? / When is the deadline?**
<br>
Few hours after work for 3 weekdays, 1 Saturday and 0.5 Sunday, which sums up approximately **24 workable hours**.

4. **What are my resources?**
<br>
Any helps, references and libraries available online.

## Coding Style
To keep code easier to read, I avoid using large if else if statement by keeping if blocks small.


## Questions
### Error Handling
What error conditions will you encounter? How should these be handled?

java.lang.NullPointerException
java.lang.ClassNotFoundException

Search list is not clear

Missing Internet Access Permission

forget to instantiate the presenter on the view


### Ensure Smooth User Experience
Where could the user experience break? How will you prevent this?


## Potential Improvements
What other improvements can be made?

When user clicks on the suggestion, the text on search widget will be changed to it accordingly

When user first click on the search widget, its hint will show a message suggesting what user should type.

make the row higher so it is easier to press





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
