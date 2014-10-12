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


## Before coding
Before starting a project, I estimate its scope, cost and time, which also known as the [Project Management Triangle](http://en.wikipedia.org/wiki/Project_management_triangle).

### Project Outlines
I ask myself these questions to outline a project.

1. **What are the requirements? / What am I coding?**
<br>
I am coding an **Android app** that has **ActionBar**, use **SearchView**, and interact with internet through **Google Place Autocomplete** and **Google Place Details** APIs.

2. **What is the scope of the project? / Who are the users? / Who am I coding for?**
<br>
This question is to make myself clear about who are going to be the users of my app.  It is a tricky question because it also determines the scope and work hours of the project.
<br><br>
One tricky part is to decide the minimum SDK version I should support.  To minimize works on dealing with support libraries, I would like to set the minimum SDK version as high as reasonable.
<br><br>
Therefore, I consult the [Google Dashboard](https://developer.android.com/about/dashboards/index.html#Platform), which tells me that **87.9%** of the circulating Android devices are using SDK 15 or above, and so I set my minimum SDK version to 15.

3. **When is the deadline?**
<br>
few hours after work on 3 weekdays, 1 Saturday and 0.5 Sunday, where sums up approximately **24 workable hours**.

4. **What resources do I have?**
<br>
Any helps, references and libraries that I can find online.

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
