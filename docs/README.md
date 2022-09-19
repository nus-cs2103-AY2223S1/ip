# Duke User Guide

## What is Duke?
Duke is an interactive chat-bot which helps you to manage your tasks! Duke puts all your tasks into a nice list and
helps you keep track of whether they are done, due dates, and other details!

Duke supports 3 types of tasks:
1. To dos
2. Deadlines
3. Events

## Features 

## Add Tasks

Add a task to the list.

``` todo <description> ``` - Adds a todo to the list of tasks
``` deadline <description> /by <YYYY-MM-DD> ``` - Adds a deadline with the given due date to the list of tasks
``` event <description> /at <YYYY-MM-DD> ``` - Adds an event with the given date to the list of tasks

### Tasks can be added with a note.
Adding ```/note``` allows you to add the task, with an accompanying note, to the list.
For example,
``` todo <description> /note <note> ```
``` deadline <description> /by <YYYY-MM-DD> /note <note> ```
``` event <description> /at <YYYY-MM-DD> /note <note> ```
Adds a todo, deadline or event respectively to the list, with the accompanying <note>.

## Delete Tasks

Delete a task from the list.

``` delete 3 ``` - deletes the task with index 3 from the list.

## Mark and Unmark tasks

## Search tasks

## Edit/Delete task notes

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
