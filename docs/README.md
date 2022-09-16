# User Guide for Duke

# Contents
1. [Starting the app](#starting-the-app)
2. [Features](#features)
    - [Adding tasks](#adding-tasks)
    - [Listing saved tasks](#listing-saved-tasks)
    - [Marking and unmarking tasks](#marking-and-unmarking-tasks)
    - [Deleting tasks](#deleting-tasks)
3. [Sample usages](#sample-usages)
4. [Troubleshooting](#troubleshooting)
5. [Report a bug](#reporting-bugs)

## Starting the app
Please ensure that Java 11 is installed on your computer.
Simply click on the program file to start, and the ChatBot will boot up.

## Features
### Adding tasks
- *todo* : A task only with a description.
- *deadline*: A task with a due date.
- *event*: A task with a date that signifies the start of an event.

### Listing saved tasks
Allows you to see all the tasks you have stored, even from days before.

### Marking and unmarking tasks
You can mark tasks as done or not done! Denoted with an [X] if it is marked as done,
and a blank [] if otherwise.

### Deleting tasks
You can also delete tasks according to their index in the list, once you are done with the task.

## Usage
Here is a sample usage of each command, to better familiarise yourself with Duke's functionality.

### `todo`
Creates a new todo task.

#### **Format of command**
    `todo [description]`

#### **Example Usage**
    `todo buy groceries`

### `deadline`
Creates a new deadline task.

#### **Format of command**
    `deadline [description] /by [yyyy-MM-dd HHmm]`

#### **Example Usage**
    `deadline math assignment /by 2022-09-25 2359`

### `event`
Creates a new event task.

#### **Format of command**
    `event [description] /at [yyyy-MM-dd HHmm]`

#### **Example Usage**
    `event project presentation /at 2022-09-30 1900`

### `find`
Finds tasks within the list that contain a specified keyword

#### **Format of command**
    `find [keyword]`

#### **Example Usage**
    `find assignment`

### `unmark`
Unmarks the task specified using list index.

#### **Format of command**
    `unmark [index]`

#### **Example Usage**
    `unmark 1`

### `mark`
Marks the task specified using list index.

#### **Format of command**
    `mark [index]`

#### **Example Usage**
    `mark 1`

### `delete`
Deletes the task specified using list index.

#### **Format of command**
    `delete [index]`

#### **Example Usage**
    `delete 1`

### **list**
Lists out the stored tasks

#### **Format of command**
    `list`

#### **Example Usage**
    `list`

### **help**
Shows a help screen showing the various commands of Duke.

#### **Format of command**
    `help`

#### **Example Usage**
    `help`

### **bye**
Shuts down the app. Your tasks stored will be saved.

#### **Format of command**
    `bye`

#### **Example Usage**
    `bye`

## Troubleshooting
- ### `Errors related to starting the app`
    Please ensure that your computer has Java version 11 installed. You
    may have to switch your version if you already have an incorrect
    version installed.
- ### `Invalid command`
    Ensure the command you called is valid, with no typos. Use `help` 
    command if you are unsure of the various commands.
- ### `No date inserted for deadline`
    Dates are mandatory for deadline and event tasks, 
    please input the information in the correct format.
    Please also ensure the correct delimiter (`/by` for deadline, `at` for event) is used.
- ### `No date inserted for event`
    Dates are mandatory for deadline and event tasks, 
    please input the information in the correct format.
    Please also ensure the correct delimiter (`/by` for deadline, `at` for event) is used.
- ### `Invalid date format!`
    Please give date in yyyy-MM-dd HHmm format.
- ### `Invalid selection for marking`
    Wrong index given for marking, please use `list` command to check index.
- ### `Invalid selection for marking`
    Wrong index given for unmarking, please use `list` command to check index.
- ### `Invalid selection for deletion`  
    Wrong index given for deletion, please use `list` command to check index.

## Reporting bugs

- Please raise any issues or bugs through [this link](https://github.com/domoberzin/ip/issues)