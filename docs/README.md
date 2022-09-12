# AvaBot User Guide

_Ava_ is a GUI Based chat bot to manage your tasks! 
Talk to Ava and let her keep track of your tasks in a list, nice and neat!

## Contents
1. [Starting Up](#starting-up)
2. [Features](#features)
3. [Usage](#usage)
    1. [`add`](#add)
    2. [`list`](#list)
    3. [`find`](#find)
    4. [`done`](#done)
    5. [`delete`](#delete)
    6. [`sort`](#sort)
4. [Exiting the app](#exiting-the-app)
5. [Troubleshooting](#troubleshooting)
6. [Reporting an issue](#reporting-an-issue)

## Starting Up
To start interacting with _Ava_, simply double-click the coffee cup.
_Ava_ will boot up right away:
![AinsleyBot Ui](Ui.png)

## Features
### Add Tasks ➕
- _Todo:_ a task with description, simple and sweet.
- _Event:_ a **dated** task with a start date and a specific time. Great for events. Denoted with an `at` keyword.
- _Deadline:_ a **dated** task with a single date and time. Great for deadlines. Denoted with a `is due by` keyword.

### Mark Tasks as Done ☑️
After you are done with the task, mark it as done! The task you marked as done will be denoted with `✅`.

### Mark Tasks as Undone ❎️
Wrongly marked an undone task as done? No worries! The task you marked as undone will be denoted with `❌`.

### Delete Tasks ❌
Remove a specific task from the application!

### View All Tasks 👀
See the list of all your tasks!

### Find Your Tasks 🔎
Find tasks by querying a keyword!

### Sort Tasks by Date ↕️
View all your **dated** tasks in chronological order!

## Usage
Here are some of the steps in using commands to tell _Ava_! Please keep in mind that _Ava_ is a chatbot, so she can't
understand you unless the exact commands in the exact formats are used! The commands are not sensitive. However, your 
tasks are!

### `Todo`
Adds a new todo task

#### **General format:**

`todo [description]`

#### **Example of usage:**

`todo code cs2103 IP`

### `Event`
Adds a new event task

#### **General format:**

`event [description] /at [yyyy-MM-dd HH:MM]`

#### **Example of usage:**

`event CS2100 Midterm /at 2022-10-08 09:00`

### `Deadline`
Adds a new deadline task

#### **General format:**

`deadline [description] /by [yyyy-MM-dd HH:MM]`

#### **Example of usage:**

`deadline CS2103T Quiz /by 2022-09-16 16:00`

### `List`
Shows all your tasks in the form of a list.

#### **General format:**

`list`

### `Find`
Shows all the tasks that contains your keyword in the form of a list.

#### **General format:**

`find [keyword]`

#### **Example of usage:**

`find meeting`

### `Mark`
Marks the task at the specified index as done. Marked tasks are denoted with ✅.

#### **General format:**

`mark [1-based index]`

#### **Example of usage:**

`mark 1`

### `Unmark`

Marks the task at the specified index as undone. Unmarked tasks are denoted with ❌.

#### **General format:**

`unmark [1-based index]`

#### **Example of usage:**

`unmark 1`

### `Delete`

Removes the specified task from the list.

#### **General format:**

`delete [1-based index]`

#### **Example of usage:**

`delete 1`

### `Sort`

Displays the list of dated task sorted chronologically. 
The list will not contain Todos as they do not have a date.

#### **General format:**

`sort`

## Exiting the App
To exit the application, just type `bye`. _Ava_ will save your changes you've made to the data and quits gracefully
in 2 seconds.

## Troubleshooting
- ### `Ava not starting up?`
   Make sure that your machine has Java 11 installed!
- ### `Command xxx not found`
   Check that you have no typos!
- ### `Missing task description!`
   Please add description. _Ava_ does not allow for tasks with empty descriptions!
- ### `Input format is wrong. Please input in yyyy-MM-dd HH:mm format.`
   Check your date and time format! It **MUST** be in the format of yyyy-mm-dd hh:mm
- ### `The time is missing!`
   Remember to input your date and time. This is **NOT** optional! Be sure that you have the correct delimiiter `/by` or `/at`!
- ### `Couldn't find your task using find keyword?`
   Be aware that your task is case-sensitive. Please type a whole word (not a substring), with the correct cases.
- ### `Task is not available! Please enter the right number!`
   Providing the wrong index (i.e. negative numbers / number > total tasks) 
   to mark/unmark/delete commands will throw you this error!

   Enter the correct index number !
- ### `Your tasks are not saved?`
   Quit the application using the keyword `bye` and avoid exiting forcefully.

## Reporting an Issue
If you have any queries or want to report a bug, please contact me [here](https://github.com/midnightfeverrr/ip/issues)
