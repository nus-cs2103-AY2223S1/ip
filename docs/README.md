# Duke User Guide

Duke is a desktop app to help keep track of all your tasks! It's purposed for users who prefer typing to clicking 
as it makes use of a Command Line Interface (CLI).

## Features 

### Task Tracking

This version of Duke allows you to keep track of 4 different tasks, namely:
- `todo` A general task that needs to be done with no specific deadline
- `deadline` A deadline to be completed by a specific date
- `event` An event that occurs at a specific date
- `doafter` A task that needs to be done after a specific task

View your current list of tasks using the `list` command.

### Task Management

Allows you to keep tabs on the various tasks that you have on your list:
- `delete` Deletes a specific task from the task list
- `find` Find specific tasks by searching for a keyword
- `mark` Marks a specific task as done
- `unmark` Unmarks a specific task, labelling it as incomplete

### Local Data Saving / Retrieval

All changes made will be saved on your local device using the `bye` command.

## Usage

### `todo` - Creates a Todo task

Creates a general task that needs to be done with no specific deadline, adding it to the task list.

Example of usage: 

`todo read book`

Expected outcome:

A Todo task with description "read book" is created and added to the task list.

```
I've added this task to the list:
  [T][ ] read book
You have a total of 1 task in the list.
```

### `deadline` - Creates a Deadline task

Creates a task that needs to be completed by a specific date, adding it to the task list. The codeword `/by` is 
used to separate the description of the task from its deadline. Dates **must** be entered in one of the following 
formats:

- dd/mm/yyyy
- yyyy/mm/dd
- dd-mm-yyyy
- yyyy-mm-dd

Example of usage:

`deadline return book /by 06-06-2022`

Expected outcome:

A Deadline task with description "return book" and deadline "6 June 2022" is created and added to the task list.

```
I've added this task to the list:
  [D][ ] return book (by: 6 Jun 2022)
You have a total of 2 tasks in the list.
```

### `event` - Creates an Event task

Creates an event that occurs on a specific date, adding it to the task list. The codeword `/at` is
used to separate the description of the task from its date of occurrence. Dates **must** be entered in one of the 
following formats:

- dd/mm/yyyy
- yyyy/mm/dd
- dd-mm-yyyy
- yyyy-mm-dd

Example of usage:

`event project meeting /at 06-09-2022`

Expected outcome:

An Event task with description "project meeting" and date "6 September 2022" is created and added to the task list.

```
I've added this task to the list:
  [E][ ] project meeting (at: 6 Sep 2022)
You have a total of 3 tasks in the list.
```

### `doafter` - Creates a DoAfter task

Creates a task that needs to be done after a specific task, adding it to the task list. The codeword `/after` is
used to separate the description of the task from the other task.

Example of usage:

`doafter eat cake /after swimming`

Expected outcome:

A DoAfter task with description "eat cake" and specific task "swimming" is created and added to the task list.

```
I've added this task to the list:
  [A][ ] eat cake (after: swimming)
You have a total of 4 tasks in the list.
```

### `list` - Displays the current task list

Displays all tasks that are saved on the task list, based on your current and past inputs on Duke.

Example of usage:

`list`

Expected outcome:

All tasks on the task list is displayed.

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 6 Jun 2022)
3. [E][ ] project meeting (at: 6 Sep 2022)
4. [A][ ] eat cake (after: swimming)
```

### `delete` - Deletes a task

Deletes a specific task, based on its current index number on the task list. It is permanently deleted from memory and 
can no longer be retrieved.

Example of usage:

`delete 3`

Expected outcome:

The third task in the task list is permanently deleted from the task list.

```
Got it! Task 3 has been deleted from the list:
  [E][ ] project meeting (at: 6 Sep 2022)
You have a total of 3 tasks in the list.
```

### `find` - Displays a list of tasks containing a specific keyword

Find tasks that are currently on the task list by searching with a keyword.

Example of usage:

`find book`

Expected outcome:

All tasks on the task list with the keyword "book" are displayed.

```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 6 Jun 2022)
```

### `mark` - Marks a task as completed

Marks a specific task as completed, based on its current index number on the task list.

Example of usage:

`mark 1`

Expected outcome:

The first task in the task list is marked as done.

```
Good job! Task 1 has been completed:
  [T][X] read book
```

### `unmark` - Unmarks a task, labelling it as incomplete

Unmarks a specific task to label it as incomplete, based on its current index number on the task list.

Example of usage:

`unmark 1`

Expected outcome:

The first task in the task list is unmarked.

```
Got it! Task 1 has not yet been completed:
  [T][ ] read book
```

### `bye` - Saves the current task list and stops the app

All changes made on the current use of Duke is saved into local data.

Example of usage:

`bye`

Expected outcome:

The current task list is saved into local data for future reference.

```
Many thanks from Duke. Have a nice day!
```
