# Duke User Guide
Hi there! Welcome to the Duke User Guide.
This manual will help you understand the features of your personal task tracker!

## Getting Started
1. Ensure you have Java 11 installed on your device
2. Download the latest version of Duke [here](https://github.com/gowribhat/ip/releases/tag/A-Release)! <!--- add link here after JAR realease -->
3. Double-click on JAR file to start Duke :> 

## Features List
1. [Add tasks such as Todos, Deadlines and Events](#1-add-tasks)
2. [Mark tasks as done or not done](#2-marking-tasks)
3. [Delete tasks](#3-delete-tasks)
4. [Search for tasks using keywords](#4-search-tasks)
5. [Manage short notes](#5-manage-short-notes)
6. [View all your tasks and notes](#6-view-all-your-tasks-and-notes)
7. [Exit Duke](#7-exit-duke)

## Features


###  1. Add Tasks

### `todo <description>` - Add Todo task

Adds a new Todo task with a task description to your task list.

Example of usage:

`todo borrow books`

Expected response from Duke:

```
Got it. I've added task:
 [T][] borrow books
Now you have have 3 tasks in the list
 ```

### `deadline <description> /by <date>` - Add Deadline task

Adds a new Deadline task with a task description and deadline date to your task list.
`<date>` needs to be entered in the format `YYYY-MM-DD`.

Example of usage:

`deadline finish painting /by 2022-09-20`

Expected response from Duke:

```
Got it. I've added task:
 [D][] finish painting (by: Sep 20 2022)
Now you have have 4 tasks in the list
 ```

### `event <description> /at <date>` - Add Event task

Adds a new Event task with a task description and event date to your task list.
`<date>` needs to be entered in the format `YYYY-MM-DD`.

Example of usage:

`event party /at 2022-10-09`

Expected response from Duke:

```
Got it. I've added task:
 [E][] party (at: Oct 09 2022)
Now you have have 5 tasks in the list
 ```


###  2. Marking Tasks

### `mark <index>` - Marks task at specified index as done

Marks tasks at specified index as done and reminds you if index entered is invalid.

Example of usage:

`mark 3`

Expected response from Duke:

```
Nice! I've marked this task as done:
 [T][X] borrow books
 ```

### `unmark <index>` - Marks task at specified index as not done

Marks tasks at specified index as not done and reminds you if index entered is invalid.

Example of usage:

`unmark mark 3`

Expected response from Duke:

```
OK, I've marked this task as not done yet:
 [T][] borrow books
 ```


###  3. Delete Tasks

### `delete <index>` - Deletes task at specified index

Deletes task at specified index and reminds you if index entered is invalid.

Example of usage:

`delete 3`

Expected response from Duke:

```
Notes. I've removed:
 [T][] borrow books
Now you have have 4 tasks in the list
 ```

###  4. Search Tasks

### `find <keyword>` - Searches your task list to return matching tasks

Searches your task list to return task with a description that matches the entered keyword(s).

Example of usage:

`find paint`

Expected response from Duke:

```
Here are the matching tasks in your list:
 1. [T][X] buy paints
 2. [D][] finish painting (by: Sep 20 2022)
 ```


###  5. Manage short notes

### `addnote <description>` - Add short note to your notes list

Adds a new note to your notes list.

Example of usage:

`addnote height: 170cm`

Expected response from Duke:

```
Got it. I've added note:
 height: 170cm
Now you have have 3 note in the list
 ```
### `deletenote <index>` - Delete note at specified index from your notes list

Delete note at specified index from your notes list and reminds you if index entered is invalid.

Example of usage:

`delete 3`

Expected response from Duke:

```
Notes. I've removed:
 height: 170cm
Now you have have 2 notes in the list
 ```


###  6. View all your tasks and notes

### `list` - Shows your list of tasks and notes

Shows your entire list of tasks and notes separately.

Example of usage:

`list`

Expected response from Duke:

```
Tasks
1. [T][X] buy paints
2. [E][X] cca meeting (at: Aug 30 2022)
3. [D][] finish painting (by: Sep 20 2022)
4. [E][] party (at: Oct 09 2022)
Notes
1. cost of shoes: $80
2. shopping list: apples, potatoes
 ```


###  7. Exit Duke

### `bye` - Exits the Duke Application

Saves all your tasks and notes , and exits the application.

Example of usage:

`bye`

Expected response from Duke:

```
Bye. Hope to see you again soon!
 ```

[Back To Top](#duke-user-guide)
