# Duke User Guide

## What is Duke?
Duke is an interactive chat-bot which helps **you** to manage your tasks! Duke puts all your tasks into a nice list and
helps you keep track of whether they are done, due dates, and other details!

Duke supports 3 types of tasks:
1. To dos
2. Deadlines
3. Events

## Features

## Add Tasks

**Add a task to the list.**

``` todo <description> ``` - Adds a **todo** to the list of tasks

``` deadline <description> /by <YYYY-MM-DD> ``` - Adds a **deadline** with the given due date to the list of tasks

``` event <description> /at <YYYY-MM-DD> ``` - Adds an **event** with the given date to the list of tasks

![`add` command usage](src/main/resources/images/todo_deadline_ss.png)

### Tasks can be added with a note.
Adding ```/note``` allows you to add the task, **with an accompanying note**, to the list.

``` todo <description> /note <note> ```

``` deadline <description> /by <YYYY-MM-DD> /note <note> ```

``` event <description> /at <YYYY-MM-DD> /note <note> ```

Adds a todo, deadline or event respectively to the list, with the accompanying <note>.

![`note` command usage](src/main/resources/images/event_note_ss.png)

## Delete Tasks

**Delete a task from the list.**

``` delete <task index> ``` - deletes the task with the given index from the list.

![`delete` command usage](src/main/resources/images/delete_ss.png)

## Mark and Unmark tasks
Mark tasks as done, or unmark them if they are not.

``` mark <task index> ``` - marks the task with the given index as done.

![`mark` command usage](src/main/resources/images/mark_ss.png)

``` unmark <task index> ``` - unmarks the task with the given index.

![`unmark` command usage](src/main/resources/images/unmark_ss.png)

## Search tasks
**Search for tasks in the list that contain the given string.**

``` find <string> ``` - searches for tasks in the current task list which contain the given string.

![`find` command usage](src/main/resources/images/find_ss.png)

## Edit/Delete task notes
**Edit the note of a task by using the ``` note ``` command.**

``` note /task <task index> /edit <new note> ``` - edits the note of the task with the given index to the new note.

![`edit note` command usage](src/main/resources/images/edit_note_ss.png)

the ``` note ``` command can also be used to **delete** a note.

``` note /task <task index> /delete ``` - deletes the existing note of the task with the given index.

![`delete note` command usage](src/main/resources/images/delete_note_ss.png)

## List all tasks
**View all the tasks in the list.**
``` list ``` - lists down all the current tasks, with their status (whether they are done) and notes.

![`list` command usage](src/main/resources/images/list_ss.png)

## Exit the application
``` bye ``` - exits the application.
