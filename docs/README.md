# User Guide
Duke is a Java chatbot for recording tasks through a JFX interface.

There are 3 types of tasks : Event, Deadline and Todos 

## Quick start

Ensure you have Java 11 or above installed in your Computer.


Copy the file to the folder you want to use as the home folder for your Duke.

Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. 

![image description](Ui.png)

Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
Some example commands you can try:

1. add : adds a new task to the list of tasks
2. delete: deletes a task from the list of tasks
3. find : returns all matching task to what you input
4. list: prints all tasks in the list
5. mark/unmark : edits whether a task is marked
6. date: returns all tasks due by or on that date

A full list of the features are found below

## Features 

### Feature - Adding a task: `add`

Allows adding of task. Enter one of 3 strings : (Todo, Event, Deaadlines) followed by the name of the task being added. 

If the task is an event, add a date by addding `/at {date}`

If the task is a deadline, add a date by addding `/by {date}`

Entering `event Birthday /at 2000-01-01` will have the output

```
Got it. I've added this task:
[E][] Birthday (at: Jan 1 2000)
you now have 1 tasks in the list
```

Entering `deadline Excercise /by 2000-01-01` will have the output

```
Got it. I've added this task:
[D][] Excercise (by: Jan 1 2000)
you now have 2 tasks in the list
```

Entering `todo Excercise` will have the output

```
Got it. I've added this task:
[T][] Excercise
you now have 3 tasks in the list
```

### Feature - Deleting a task : `delete`

Allows deletion of tasks. Enter `delete` followed by the index of the task

Entering `delete 1` will have the output

```
Noted. I've removed this task:
[E][] Birthday (at: Jan 1 2000)
you now have 2 tasks in the list
```

### Feature - Finding all tasks that match a string  : `find`

Allows deletion of tasks. Enter `find` followed by the string you want to match

Entering `find Excercise` will have the output

```
Here are the matching tasks in your list:
[D][] Excercise (at: Jan 1 2000)
[T][] Excercise
```
### Feature - Finding all tasks that match a string  : `list`

Prints all the tasks that you currently have stored. Enter `list`

Entering `list` will have the output

```
1. [D][] Excercise (at: Jan 1 2000)
2. [T][] Excercise
```

### Feature - Marking and unmarking tasks  : `mark/unmark`

Marks or unmarks tasks. Enter `mark/unmark` followed by the index of the task you want to mark or unmark.
This feature is intended to allow the user to mark whether a task has been completed

Entering `mark 1` will have the output

```
1. [D][X] Excercise (at: Jan 1 2000)
2. [T][] Excercise
```

Entering `unmark 1` will have the output

```
1. [D][] Excercise (at: Jan 1 2000)
2. [T][] Excercise
```

### Feature - Find all tasks before or during a date  : `date`

Returns all the events during a date, and all deadlines due before the date.
 Enter `date` followed by the date you want to search for, formatted as yyyy-mm-dd

Entering `date 2000-02-02` will have the output

```
1. [D][X] Excercise (at: Jan 1 2000)
2. [T][] Excercise
```
