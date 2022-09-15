# User Guide

## Quickstart
1. [Installation](#installation) 
2. [Features](#features)

    * [Create new tasks](#create-new-tasks)
    * [Manage your tasks](#manage-your-tasks)
    * [View your tasks](#view-your-tasks)
    * [Load and save your tasks](#load-and-save-your-tasks)
    * [Close Duke](#close-duke)
3. [Usage](#usage) 

    * [`todo` - Create a new *todo*](#todo---create-a-new-todo)
    * [`deadline` - Create a new *deadline*](#deadline---create-a-new-deadline)
    * [`event` - Create a new *event*](#event---create-a-new-event)
    * [`mark` - Mark a task as done](#mark---mark-a-task-as-done)
    * [`unmark` - Mark a task as not done](#unmark---mark-a-task-as-not-done)
    * [`delete` - Delete a task](#delete---delete-a-task)
    * [`list` - View your tasks](#list---view-all-tasks)
    * [`find` - Find a task](#find---find-a-task)
    * [`schedule` - View schedule on a day](#schedule---view-schedule-on-a-day)
    * [`sort` - Sort your tasks](#sort---sort-your-tasks)
    * [`format` - Change format for date and time](#format---change-format-for-date-and-time)
    * [`bye` - Close Duke](#bye---close-duke)

## Installation
Download the latest release and run `java -jar duke.jar`.

If you wish to customise Duke,
* clone this repository
* make your changes
* start the new Duke app with command `gradlew run`

## Features 

### Create new tasks

You can create new tasks of one of the 3 types (*todo*, *event*, *deadline*).
* A **todo** is a simple task with a description about the task.
* A **deadline** also has a description and additionally, date and time to represent a cutoff time.
* Similar to a deadline, an **event** has a description and date and time to represent the time when the event takes place for an **event**.

### Manage your tasks

You can manage your tasks with Duke using the following commands (*mark*, *unmark*, *delete*).
* **mark** is used to denote a task has been completed. It's like ticking a checkbox in a physical todo list!
* **unmark** is used to denote a task has not been completed.
* **delete** is used to remove a task (done or not done) from your list.

### View your tasks

Duke supports various different commands for you to view your tasks and look for the tasks that you need (*list*, *find*, *schedule*, *sort*, *format*).
* **list** is used to display every task you currently have in your list.
* If you only wish to view a few specific tasks, you can use **find** to look for the tasks by specifying specific key words or phrases.
* You can also look for tasks on a specific date using the command **schedule**.
* You can also **sort** your tasks to view your tasks sorted by date.
* **format** is used to change the format in which date and time are displayed and view the tasks with the new format.

### Load and save your tasks

Duke automatically loads after the app is opened from a *txt* file. After closing the app, your tasks will be saved into the same file.

### Close Duke

You can close the app with the command **bye**.

## Usage

### `todo` - Create a new *todo*

Create a new todo with a given description.

**Example of usage:** `todo <description>`

**Expected outcome:** A new **todo** is created and added to the list of existing tasks.

```
Got it. I've added this todo:
  [T][ ] buy groceries
Now you have 1 tasks in the list.
```

### `deadline` - Create a new *deadline*

Create a new deadline with a given description and the cutoff date and time to complete the task by.

**Example of usage:** `deadline <description> /by (<dd/MM/yyyy>) (<HH:mm>)`

At least 1 of date or time must be provided.

**Expected outcome:** A new **deadline** is created and added to the list of existing tasks. The current date will be used if date is not provided. Midnight (00:00) will be used if time is not provided.

```
Got it. I've added this deadline:
  [D][ ] submit homework (by: 16/09/2022 00:00)
Now you have 2 tasks in the list.
```

### `event` - Create a new *event*

Create a new event with a given description and the date and time when the event takes place.

**Example of usage:** `event <description> /at (<dd/MM/yyyy>) (<HH:mm>)`

At least 1 of date or time must be provided.

**Expected outcome:** A new **event** is created and added to the list of existing tasks. The current date will be used if date is not provided. Midnight (00:00) will be used if time is not provided.

```
Got it. I've added this event:
  [E][ ] meet friends (at: 15/09/2022 12:00)
Now you have 3 tasks in the list.
```

### `mark` - Mark a task as done

**Example of usage:** `mark INDEX`

`INDEX` must be between 1 and the size of the current list of tasks.

**Expected outcome:** The task with the given index will be marked as done. There will be no change if the task has already been marked previously.

```
Nice! I've marked this task as done:
  [T][X] buy groceries
```

### `unmark` - Mark a task as not done

**Example of usage:** `unmark INDEX`

`INDEX` must be between 1 and the size of the current list of tasks.

**Expected outcome:** The task with the given index will be marked as not done. There will be no change if the task has not been marked previously.

```
Nice! I've marked this task as not done yet:
  [T][ ] buy groceries
```

### `delete` - Delete a task

**Example of usage:** `delete INDEX`

`INDEX` must be between 1 and the size of the current list of tasks.

**Expected outcome:** The task with the given index will be deleted. This task will no longer be available in your list of tasks and will not be displayed in the future.

```
Noted. I've removed this task:
  [T][ ] buy groceries
Now you have 2 tasks in the list.
```

### `list` - View all tasks

Display all tasks from your list of tasks.

**Example of usage:** `list`

**Expected outcome:** All tasks with their description, dates, and status (done/not done) are displayed in one message.

```
Here are the tasks in your list:
1.[D][ ] submit homework (by: 16/09/2022 00:00)
2.[E][ ] meet friends (at: 15/09/2022 12:00)
```

### `find` - Find a task

Find a task by given keywords or phrases.

**Example of usage:** `find <keyword>`

**Expected outcome:** All tasks with the keyword in their description are displayed.

```
Here are the tasks in your list:
1.[D][ ] submit homework (by: 16/09/2022 00:00)
```

### `schedule` - View schedule on a day

View all your tasks on a specific day.

**Example of usage:** `schedule <dd/MM/yyyy>`

**Expected outcome:** All tasks on that day are displayed.

```
Here are the tasks in your list:
1.[E][ ] meet friends (at: 15/09/2022 12:00)
```

### `sort` - Sort your tasks

View all your tasks after they are sorted according to date, status (done/not done) and description.

**Example of usage:** `sort`

**Expected outcome:** All tasks with their description, dates, and status (done/not done) are displayed in one message. The tasks are sorted by *date* (closest dates first), then *status* (incomplete tasks first) and finally by *description* according to alphabetical order.

```
Here are the tasks in your list:
1.[E][ ] meet friends (at: 15/09/2022 12:00)
2.[D][ ] submit homework (by: 16/09/2022 00:00)
```

### `format` - Change format for date and time

View all your tasks after the specified format for date and time is applied.

**Example of usage:** `format`

**Expected outcome:** All tasks with their description, status (done/not done) and dates in the new format are displayed in one message.

```
Here are the tasks in your list:
1.[D][ ] submit homework (by: 16-Sep-22 00:00)
2.[E][ ] meet friends (at: 15-Sep-22 12:00)
```

### `bye` - Close Duke

Close the app and save your tasks into the save file.

**Example of usage:** `bye`

**Expected outcome:** Duke is closed and all your tasks are now recorded in your save file. The saved data will be available for the next time you open Duke.

```
Data is saved successfully.
Bye. Hope to see you again soon!
```
