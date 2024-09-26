# User Guide

- [User Guide](#user-guide)
   - [Overview](#overview)
   - [Features](#features)
   - [Usage](#usage)
     - [todo](#todo---create-a-todo-task)
     - [deadline](#deadline---create-a-deadline-task)
     - [event](#event---create-an-event)
     - [list](#list---list-all-tasks)
     - [mark](#mark---mark-a-task-as-completed)
     - [unmark](#unmark---unmark-a-task-to-be-not-completed)
     - [update](#update----update-the-description-or-date-of-a-task)
     - [delete](#delete---delete-a-task)
     - [bye](#bye---exit-program)

## Overview
Meet Bob, your friendly task manager chatbot. Bob can help you keep track of the different types tasks you have on hand. With Bob, you can easily manage your tasks and do not have to worry about forgetting your tasks anymore!

## Features

### Add Task
You can get Bob to add three types of task, namely ToDo, Deadline and Event.
- ToDo is a task that has a description but  does not have any date associated with it.
- Deadline is a task with a date associated with it indicating when the task should be done by.
- Event is a task with a date associated with it indicating when the event will be.

### Mark Task
You can get Bob to mark your tasks as completed or not completed (as many times as you wish to change). Thus, making it easier for you to keep track of your task progress.

### Update Task
You can update a task by editing it directly. You can update a task’s description (this will be handy if you make mistakes in your spelling or need to add other information). You can update a task’s date as well.

### Find Task
You may have many tasks in your list, hence to make your life easier, Bob can help you filter out and find a relevant task based on the keyword(s) you provide him.

### Delete Task
You may wish to delete a task when you no longer want it.

### List Tasks
You can have an overview of all the tasks you have on hand.

## Usage

### `todo` - Create a Todo task

Create a todo task by providing a description for it. Bob will add it to your list

Format:
`todo {DESCRIPTION}`

Example of usage: `todo Prepare for interview`

Expected outcome:
```
Got it. I’ve added this Bob Task:
[T] [] Prepare for interview
Now you have 1 task in the list.
```

### `deadline` - Create a deadline task

Create a deadline by providing a description and its associated date. The date must be in YYYY-MM-DD format.

Format:
`deadline {DESCRIPTION} /by {YYYY-MM-DD}`

Example of usage: `deadline Internship Application /by 2022-09-15`

Expected outcome:
```
Got it. I’ve added this Bob Task:
[D] [] Internship Application (by Sep 15 2022)
Now you have 2 tasks in the list.
```

### `event` - Create an event

Create event by providing the description and its associated date. The date must be in YYYY-MM-DD format.

Format:
`event {DESCRIPTION} /at {YYYY-MM-DD}`

Example of usage: `event Birthday Celebration /at 2022-09-19`

Expected outcome:
```
Got it. I’ve added this Bob Task:
[E] [] Birthday Celebration (at Sep 19 2022)
Now you have 3 tasks in the list.
```

### `list` - List all tasks

Shows your current list of tasks that are indexed in order. The type of task of the task will also be displayed.

Format:
`list`

Example of usage: `list`

Expected outcome:
```
There are 3 Bob task(s) in your list:
1. [T] [] Prepare for interview
2. [D] [] Internship Application (by Sep 15 2022)
3. [E] [] Birthday Celebration (at Sep 19 2022)
```

### `mark` - Mark a task as completed

Mark an existing task in your list as completed. The task index based on the list must be provided.

Format:
`mark {TASK_INDEX}`

Example of usage: `mark 1`

Expected outcome:
```
Nice! I have marked this Bob task as done:
[T] [X] Prepare for interview
```

### `unmark` - Unmark a task to be not completed

Mark an existing task as not completed. The task index based on the list must be provided.

Format:
`unmark {TASK_INDEX}`

Example of usage: `unmark 1`

Expected outcome:
```
OK, I’ve marked this Bob task as not done yet:
[T] [] Prepare for interview
```

### `update` -  Update the description or date of a task.

Update the description or date (if task has an associated date) of a task. There are two separate commands to update the description or date respectively. The date format given MUST be in YYYY-MM-DD format.

#### To update the task description:
Format:
`update {TASK_INDEX} description {DESCRIPTION}`

Example of usage: `update 3 description Birthday Party for John`

Expected outcome:
```
Ok, I have updated this task:
[E] [] Birthday Party for John (at Sep 19 2022)
```
#### To update the task date (for deadline and event tasks):
Format:
`update {TASK_INDEX} date {YYYY_MM_DD}`

Example of usage: `update 2 date 2022-09-11`

Expected outcome:
```
Ok, I have updated this task:
[D] [] Internship Application (by: Sep 30 2022)
```

### `find` - find a task based on keyword

Search for a task based on the keyword(s) provided. Bob will search for your keyword(s) in the all the tasks’ description. If your keyword(s) is a substring of the task description, it will show up in the search result. Note that this feature is not case-sensitive.
Format:
`find {KEYWORDS}`

Example of usage: `find interview`

Expected outcome:
```
Here are the tasks found:
1. [T] [] Prepare for interview
2. [E] [] Interview Workshop (at: Oct 10 2022)
```

### `delete` - Delete a task

Delete an existing task in your list. The task index associated with the task must be provided.

Format:
`delete {TASK_INDEX}`

Example of usage: `delete 4`

Expected outcome:
```
Noted, I’ve removed this Bob task:
[E] [] Interview Workshop (at: Oct 10 2022)
Now you have 5 tasks in the list.
```

### `bye` - Exit Program

Exits the Bob Task Manager ChatBot Program.

Format:
`bye`

Example of usage: `bye`

Expected outcome:
```
The program will be closed. This is similar to how you can close the program by clicking on the cross (close button) on the GUI
```