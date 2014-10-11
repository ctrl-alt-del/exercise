HT Exercise
===========
This is an exercise to use Google Place API through Retrofit REST Client and other libraries.

## Before getting start to code...
Before starting a project, I tend to think about how it would fit into the project management triangle, namely the scope, cost, and time.

It comes down to several questions:
1. **What am I coding?**
<br>
Namely, the requirements.  In this case, I am coding an app that has ActionBar, use SearchView, and interact with Google Place Autocomplete and Place Details APIs.

2. **Who am I coding for?**
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
