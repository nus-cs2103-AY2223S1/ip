# Duke User Guide

## Summary
Welcome to Duke! Duke is a quick and easy-to-use task management and tracking application
which can be used in both online and offline settings.

Its command-line interface (CLI) offers a robust means of
providing Duke with commands to manage existing and new tasks.

## Features
Duke allows you to add the following types of tasks:
- To Dos
- Deadlines
- Events

Duke also handles a multitude of commands for existing tasks:
- Deleting tasks
- Finding tasks
- Listing tasks
- Marking tasks as completed / uncompleted
- Getting reminders for tasks nearing their deadlines

### Task Description:
__Todo__ tasks are simple tasks that do not have a deadline.

__Event__ and __Deadline__ tasks are tasks that have deadlines.
Events happen at a certain date and time, while deadlines
must be completed by a certain date and time.

### Task Saving:
Don't worry, Duke will save your tasks automagically every
time a change is made, so you can be sure that you won't
lose your list of tasks. :)

## Usage
Duke can be used right out of the box. Just download the released 
JAR file, and run it with Java.

## Command Syntax

### Simple Commands
`list` - Lists all tasks in a readable format.

Duke wil display all of your current stored tasks in a list
which is sorted by the order in which the tasks were added.


`help` - Provides user help and syntax for commands.

Duke will display a help dialog which will provide some
useful information regarding what commands are currently
available and the syntax required to use them.


`reminders` - Generates a list of tasks that are due soon.

Duke will generate a list of tasks that have their deadlines
within 5 days of the current date, and display it in a
readable format on the screen.

`bye` - Exits Duke.

Duke will exit after a short delay.

### Adding of tasks

The general syntax for these commands would be as follows:

```[task type] [-d DESCRIPTION] [-at DD-MM-YYYY HH:MM] [-by DD-MM-YYYY HH:MM]```

`task type` can take 3 possible values, corresponding to the
three different types of tasks available:
- `todo`
- `deadline`
- `event`

The `-d` tag marks the start of the description of the task.
It can be as long or as short as you want. _Note that all tasks need
to have a valid description._

The `-at` tag marks the time for which an `event` task is going to occur.
Note that if you want to create an event, a valid `at` time must
be specified according to the guidelines.

The `-by` tag marks the time for which a `deadline` task
must be completed. Similar to an event task, in order for 
a valid deadline task to be created, a syntactically correct
`by` time must be supplied.

Note that a user cannot supply both `-by` and `-at` tags
at the same time. The order of the tags supplied does not matter.

For example:

`todo -d New Todo`: Creates a new Todo task with description "New Todo".

`deadline -d New Deadline -by 01-01-2023 23:59`: Creates a new
Deadline task with description "New Deadline", due on Jan 01 2023 at 2359H.

`deadline -d Wrong Input -by 01-01-2023 23:59 -at 01-01-2023 23:59`: Invalid command.

`todo`: Invalid command, no description supplied.

### Filtering tasks
You can also filter tasks by using the `find` command. Its 
syntax is as follows:

`find [KEYWORDS]`

For each keyword in keywords (whitespace separated),
Duke will look through its list of tasks and find one-to-one
matches for tasks which contain the keyword in its title.
Duke will then return all of these tasks (no duplicates).

For example:

`find new abc`: Returns a list of tasks that have "new" and "abc" 
in their titles. No duplicate tasks will be returned.

### Marking and unmarking tasks
You can mark tasks as having been completed or not using the `mark` and `unmark` commands.
Their syntax is as follows:

`mark / unmark [-t TASK INDEX]`

The `-t` tag marks the index of the task you would like to mark / unmark,
which is the index of the task in the list.

For example:
`delete -t 5`: Marks or unmarks the 5th task in the list.

### Deleting tasks
The syntax for deleting tasks is similar to that of marking
and unmarking tasks.

`delete [-t TASK INDEX]`

For example:
`delete -t 5`: Deletes the 5th task in the list.







