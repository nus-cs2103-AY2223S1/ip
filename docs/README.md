# User Guide

Welcome to _**Duke**_, your personalised Task List Manager!
Duke is not just your average application to add tasks into a list. The extremely useful functions that come with it allows you to control your task list as well! It is:

- Text-based
- Easy to learn
- Incredibly flexible

Want to use it? Follow these steps below:

1. Download the .JAR file from [this website](https://github.com/TYKCodes/ip/releases)
2. Place it into a folder
3. Open command prompt from the folder
4. Run the following command: `java -jar Duke.jar`
5. Start adding and controlling your tasks

## Features 

### Adding of tasks with a datetime

You can add tasks and assign them a datetime so that you can keep track of time.
_<br>Note: This is only applicable to `Event` and `Deadline` tasks_

### Find tasks based on certain keywords

You can search for specific task(s) by entering a keyword from the description of the task(s)

### Mark tasks as done or not done

You can mark tasks as done or not done from the list of task.

## Usage

### `todo` - Adds a Todo task

Adds a Todo task with a description of the task is about

Example of usage: 

`todo Clean the dishes`

Expected outcome:

Creates a Todo task and adds it to your list of tasks. If successful, the following output will be shown:

```
Got it! I have added this task to your list:
  [T][] Clean the dishes
Now you have 1 tasks in the list.
```

### `event` - Adds an Event task

Adds an Event task with a description of the task and the datetime of the event

Example of usage:

`event Maroon 5 Concert /at 12/02/2022 2000`

Expected outcome:

Creates an Event task and adds it to your list of tasks. If successful, the following output will be shown:

```
Got it! I have added this task to your list:
  [E][] Maroon 5 Concert (at: 12 February 2022 20:00)
Now you have 2 tasks in the list.
```

### `deadline` - Adds a task with a deadline

Adds a task with a description of the task and the deadline for the task

Example of usage:

`deadline Coding Assignment /by 25/03/2022 2359`

Expected outcome:

Creates a Deadline task and adds it to your list of tasks. If successful, the following output will be shown:

```
Got it! I have added this task to your list:
  [D][] Coding Assignment (by: 25 March 2022 23:59)
Now you have 3 tasks in the list.
```

### `list` - List all tasks

Lists all of the tasks you have entered so far

Example of usage:

`list`

Expected outcome:

The list of all of your tasks will be shown. For example:

```
Here are the tasks in your list:
1.[T][] Clean the dishes
2.[E][] Maroon 5 Concert (at: 12 February 2022 20:00)
3.[D][] Coding Assignment (by: 25 March 2022 23:59)
```

### `mark` - Mark a task as done

Marks a task as done from the list of tasks

Example of usage:

`mark 1`

Expected outcome:

The task specified will be marked with an 'X' in the 2nd square bracket.
If successful, the following output will be shown:

```
Okay, I have marked this task as done:
[T][X] Clean the dishes
```

### `unmark` - Mark a task as not done

Marks a task as not done from the list of tasks

Example of usage:

`unmark 1`

Expected outcome:

The 2nd square bracket of the task will be emptied.
If successful, the following output will be shown:

```
Okay, I have marked this task as not done:
[T][] Clean the dishes
```

### `delete` - Delete a task

Deletes a task from the list of tasks

Example of usage:

`delete 1`

Expected outcome:

The task will be deleted from the list. If successful, the following output
will be shown:

```
Okay, I have removed this task from the list:
  [T][] Clean the dishes
Now you have 2 tasks in the list.
```

### `snooze` - Snooze/Change the datetime of a task

Changes the datetime of the task
<br>_Note: The task must be an `Event` or a `Deadline` task_

Example of usage:

`snooze 1 /to 01/04/2022 2359`

Expected outcome:

The datetime of the task will be updated to what is specified in the command.
If successful, the following output will be shown:

```
Successfully rescheduled the task's datetime from 12 February 2022 20:00 to 01 April 2022 2359
```
