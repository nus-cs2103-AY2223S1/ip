# User Guide
Dino :sauropod: is a Personal Assistant Chatbot that helps you keep track of your tasks and notes.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  * [Add a task: `todo`](#1-add-a-task-todo)
  * [Add a deadline: `deadline`](#2-add-a-deadline-deadline)
  * [Add an event: `event`](#3-add-an-event-event)
  * [Add a note: `note`](#4-add-a-note-note)
  * [List all tasks and notes: `list`](#5-list-all-tasks-and-notes-list)
  * [Mark / Unmark a task: `mark` or `unmark`](#6-mark--unmark-a-task-mark-or-unmark)
  * [Delete a task: `delete`](#7-delete-a-task-delete)
  * [Find a task or note: `find`](#8-find-a-task-or-note-find)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest dino.jar from here. 
3. Copy the file to the folder you want to use as the home folder for your Chatbot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![GUI](/docs/GUI.png)

## Features 

### 1. Add a task: `todo`

Add tasks without any date/time attached to it.

_e.g. buy chips_

Format: `todo <description>`

### 2. Add a deadline: `deadline`

Add tasks that need to be done before a specific date.

_e.g. submit ip by 17/9/2022_

Format: `deadline <description> /by <dd/M/yyyy>`

### 3. Add an event: `event`

Add tasks that start at a specific time and ends at a specific time.

_e.g. concert at 8/10/2022 7:30pm_

Format: `event <description> /at dd/M/yyyy HH:mm`

### 4. Add a note: `note`

Add small snippets of textual information the user wants to record.

_e.g. one's own waist size_

Format: `note <description>`

### 5. List all tasks and notes: `list`

Display list of all tasks and notes.

Format: `list`

### 6. Mark / Unmark a task: `mark` or `unmark`

Mark a task as done or undone.

Format: `mark <index of task>` or `unmark <index of task>`

### 7. Delete a task: `delete`

Delete a task from the list.

Format: `delete <index of task>`

### 8. Find a task or note: `find`

Displays all tasks and notes with the given keyword.

_e.g. project_

Format: `find <keyword>`