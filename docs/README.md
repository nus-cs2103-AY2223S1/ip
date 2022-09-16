# User Guide

Duke is a desktop app that that lets you add in new tasks and manage them. It makes 
use of Command Line Interface (CLI) with Graphical User Interface (GUI). If you want to easily
manage and keep track of what you need to do, Duke is the program for you!!
- [Quick start](#quick-start) 
- [Features](#features)
   - [View help: `help`](#view-help-help)
   - [Create a new task: `todo`](#create-a-new-task-todo)
   - [Create a new deadline: `deadline`](#create-a-new-deadline-deadline)
   - [Create a new event: `event`](#create-a-new-event-event)
   - [View tasks: `list`](#view-tasks-list)
   - [Find a task: `find`](#find-a-task-find)
   - [Mark a task: `mark`](#mark-a-task-mark)
   - [Unmark a task: `unmark`](#unmark-a-task-unmark)
   - [Exit Duke: `bye`](#exit-duke-bye)
- [FAQ](#faq)

-------------------------------------------

## Quick Start

1. Download the latest release from [here]()
2. Make sure you have Java 11 or higher installed in your computer
3. Double click or run `java -jar duke.jar` in your terminal (Remember to cd to the directory containing duke.jar)
4. Input commands in the text box at the bottom of the chat box
5. Read further down to Features to see what commands you can use

## Features 

## View help: `help`

Shows a message to help users what command they can use with Duke, as well as the format of those commands

Format: `help`

Expected response:
```
Welcome to Duke! Here are the commands you can type:
                Note the date should be in format yyyy-mm-dd HH:mm
                ** To add new Tasks to your list, you can type: 
                todo [description of your task]
                deadline [description of your task] /by [date]
                event [description of your task] /at [date]
                ** To see all the tasks you added type: list
                ** To see mark or unmark a task type: mark [index] or unmark [index]
                ** To delete a task type: delete [index]
                ** To find tasks containing certain words type: find [keywords]
                ** To exit the program type: bye
```
## Create a new task: `todo`

Add a new Todo type task with its description. The task is automatically marked as not done

Format: `todo [DESCRIPTION]`

Expected response:
```
Got it! I've added this task:
[T][] eat
Now you have 1 tasks in the list
```

## Create a new deadline: `deadline`

Add a new Deadline type task with its description and deadline date and time.
- Date and time must be in the format yyyy-MM-dd HH:mm

Format: `deadline [DESCRIPTION] /by [DATE]`

Expected response: 
```
Got it! I've added this task:
[D][] do homework (by: 2022-09-16 00:00)
Now you have 2 tasks in the list
```
## Create a new event: `event`

Add a new Event type task with its description and event date and time it will occur.
- Date and time must be in the format yyyy-MM-dd HH:mm

Format: `event [DESCRIPTION] /at [DATE]`

Expected response:
```
Got it! I've added this task:
[E][] Attend lecture (at: 2022-09-07 21:00)
Now you have 3 tasks in the list
```

## View tasks: `list`

Displays all the tasks stored in the program at the time

Format: `list`

Expected response:
```
Here are the tasks in your list:
1. [T][] eat
2. [D][] do homework (by: 2022-09-16 00:00)
3. [E][] Attend lecture (at: 2022-09-07 21:00)

```

## Find a task: `find`

Search in the task list and return tasks containing the keyword being searched

Format: `find [KEYWORD]`

Expected response:
```
Here are the matching tasks in your list:
1. [D][] do homework (by: 2022-09-16 00:00)
```
 
## Mark a task: `mark`

Marks a task as finished or done
- Index must be an integer and should not exceed the total number of tasks

Format: `mark [INDEX OF TASK]`

Expected response:
```
Nice! I've marked this task as done:
[D][X] do homework (by: 2022-09-16 00:00)
```

## Unmark a task: `unmark`

Marks a task as not finished/undone
- Index must be an integer and should not exceed the total number of tasks

Format: `Unmark [INDEX OF TASK]`

Expected response:
```
Nice! I've marked this task as not done:
[D][] do homework (by: 2022-09-16 00:00)
```

## Exit Duke: `bye`

Closes the program

Format: `bye`

Expected response:
```
Bye. Hope to see you again soon!
```

## FAQ
**Q:** Can I extract the list of tasks to a text file
**A:** Yes! All the tasks used in Duke are stored in the `Tasks.txt` file


