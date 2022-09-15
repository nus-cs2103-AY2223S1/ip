# USER GUIDE
###  DUKE Task ChatBot (Among Us Edition)
Duke Task Chatbot is a one stop solution to managing all your tasks.

## Quick Start
1. Ensure Java 11 is installed on your computer
2. Download the latest release of Duke.jar from the releases section [here](https://github.com/Vinodjayakumar124/ip/releases)
3. Double click on the `Duke.jar` icon to launch it
4. Type any command from the 'Usage' section below to get started

## Features

### Overview of Tasks

Generate a list of all outstanding tasks.

### Add Tasks

Add a variety of Tasks (ToDos, Events, Deadlines) to your list of Tasks.

### Remove Tasks

Delete any of the Tasks (ToDos, Events, Deadlines) from your list of Tasks.

### Mark or Un-mark Tasks

Track the progress of your tasks by marking them as you complete them or un-mark your tasks if you have yet to complete them.

### Find specific Tasks

Generate a list of Tasks that match a word or phrase you have provided.

### Reminders

Filter your existing list of Tasks to Events that take place or Deadline that occur within the next 7 days.

## Usage

### 1. `todo` - Add a ToDo Task

Adds a Todo Task to your list of Tasks.

Required Format: `todo TASK_DESCRIPTION`

Example of usage: `todo read book`

Expected outcome: 

Acknowledgement that ToDo Task has been added to your list of Tasks.

```
Got it. I've added this mission:
[T][] read book
Now you have 1 missions on the list.
```

### 2. `event` - Add a Event Task

Adds a Event Task to your list of Tasks.

Required Format: `event TASK_DESCRIPTION /at YYYY-MM-DDTHH:MM`

Example of usage: `event eat dinner /at 2022-09-14T20:30`

Expected outcome:

Acknowledgement that Event Task has been added to your list of Tasks.

```
Got it. I've added this mission:
[E][] eat dinner (at: Sep 14 2022 @ 20:30)
Now you have 2 missions on the list.
```


### 3. `deadline` - Add a Deadline Task

Adds a Deadline Task to your list of Tasks.

Required Format: `deadline TASK_DESCRIPTION /by YYYY-MM-DDTHH:MM`

Example of usage: `deadline submit proposal /by 2022-09-20`

Expected outcome:

Acknowledgement that Deadline Task has been added to your list of Tasks.

```
Got it. I've added this mission:
[D][] submit proposal (by: Sep 20 2022)
Now you have 3 missions on the list.
```

### 4. `mark` - Mark a Task

Marks a Task as completed based on the positive integer index supplied.

Required Format: `mark INDEX`

Example of usage: `mark 1`

Expected outcome:

Acknowledgement that Task has been marked as completed.

```
Great Execution! I've marked this mission as done:
[T][X] read book
```

### 5. `unmark` - Un-marks a Task

Un-marks a Task as incomplete based on the positive integer index supplied.

Required Format: `unmark INDEX`

Example of usage: `unmark 1`

Expected outcome:

Acknowledgement that Task has been marked as incomplete.

```
OK, I've marked this mission as incomplete:
[T][] read book
```

### 6. `delete` - delete a Task

Deletes a Task from the list of Tasks, based on positive integer supplied.

Required Format: `delete INDEX`

Example of usage: `delete 2`

Expected outcome:

Acknowledgement that Task has been deleted.

```
Noted. I've cancelled this mission:
[E][] eat dinner (at: Sep 14 2022 @ 20:30)
Now you have 2 missions in the list.
```

### 7. `find` - find a Task

Generate a list of Tasks that match a word or phrase supplied.

Required Format: `find WORD`

Example of usage: `find book`

Expected outcome:

List of Tasks which match the word or phrase given as input.

```
Here're the matching missions in your list:
[T][] read book
```


### 8. `list` - list out all Tasks

Generates a list of all current Tasks.

Required Format: `list`

Example of usage: `list`

Expected outcome:

A full list of the current Tasks.

```
Here are missions in your list:
  1. [T][] read book
  2. [D][] submit proposal (by: Sep 20 2022)
```

### 9. `remind` - remind yourself of upcoming Tasks

Filters existing list of Tasks to Events or Deadlines that occur within the next 7 days.

Required Format: `remind`

Example of usage: `remind`

Expected outcome:

List of Tasks which occur in the next 7 days.

```
Here're the matching missions in your list:
[T][] read book
```


### 10. `bye` - end the chat session

End the current chat session with the DUKE Task ChatBot.

Required Format: `bye`

Example of usage: `bye`

Expected outcome:

A goodbye message.

```
Alright, you can leave the spaceship now. Cya!
```


