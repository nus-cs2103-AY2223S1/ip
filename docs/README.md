# Pixel User Guide

![alt text](https://github.com/HakkaNgin/ip/blob/A-Release/docs/Ui.png)

<b> Pixel </b> frees your mind of having to remember things you need to do. It is a task manager chatbot that helps you keep track of your tasks through a simple to navigate interface.

## Features

### Tasks are stored locally
Pixel will create a text file to store all your tasks on your device.
Don't worry about losing track of your tasks after closing Pixel!

### Easy to navigate GUI
Don't worry, Pixel will guide you through how to navigate the interface (trust me, it's simple)
Commands are not case-sensitive

### Three task types
- `todo`: A task with a description
- `event`: An event with a description of what and when it is
- `deadline`: A deadline with a description and due date

### Search
Search for tasks using the `find` command

### Mark and unmark
Mark tasks as done or undone using the `mark` and `unmark` commands

## Usage

##  `list` -  Lists all tasks in the database

#### Example:

`list`

Expected output: A list of all the tasks stored in the system
```
1. [D] [X] Submit CS2103 project (by: SEPTEMBER 19 2022 11:59PM)
2. [E] [] RC Gathering (at: RC4)
```
There are three task types, denoted by the first letter in the list
- T: `Todo`
- E: `Event`
- D: `Deadline`

The `[X]` denotes tasks that have been marked as done

##  `todo` -  Adds a todo task

#### Example:

`todo print lab report`

#### Expected outcome: A new task gets added to the list
```
Got it. I’ve added this task:
[T] [] print lab report
You now have 1 tasks in your list.
```
The number shown may vary depending on how many tasks you already created.

##  `event` -  Adds an event task

#### Example:

`event /d CCA bonding /at Sentosa beach`

#### Expected outcome: An new event gets added to the list
```
Sure! I’ve added this task:
[E] [] CCA bonding (at: Sentosa beach)
You now have 1 task to do.
```
The number shown may vary depending on how many tasks you already created.

##  `deadline` -  Adds a deadline task

#### Example:

`deadline Submit CS2103 project /by 2022-09-19 2359`

#### Expected outcome:
```
Sure! I’ve added this task:
[D] [] Submit CS2103 project (by: SEPTEMBER 19 2022 11:59PM)
You now have 1 task to do.
```
The number shown may vary depending on how many tasks you already created.

##  `mark` - Marks a specified task as done

#### Example:

`mark 1`

#### Expected outcome:
```
Nice! I’ve marked this task as done:
<description of task>
You may enter a new task or command
```

##  `unmark` - Unmarks the specified task to not done

#### Example:

`unmark 1`

#### Expected outcome:
```
OK, I’ve marked this task as not done yet:
<description of task>
You may enter a new task or command
```

##  `delete` - Deletes the task with the specified index

#### Example:

`delete 1`

#### Expected outcome:
```
Noted. I’ve removed this task:
<description of task>
You now have 2 tasks in the list.
You may enter a new task or command
```
The number shown may vary depending on how many tasks you already created.

##  `find` - Finds tasks that contain the specified text in their descriptions

#### Example:

`find CS`

#### Expected outcome: Pixel will list out all the tasks in the database whose description contains the query string
```
Here are the matching tasks in your list:
1. [D] [] Submit CS2103 project (by: SEPTEMBER 19 2022 11:59PM)
You may enter a new task or command
```

##  `bye` - Exits Pixel and terminates the programme

#### Example:
`bye`
