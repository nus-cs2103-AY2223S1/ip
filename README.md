# User Guide

> We are excited to show you how to use Wagwan
>
> Wagwan is a desktop app that helps you keep track of your tasks, so you'll never have to!

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest Wagwan.jar from [here](https://github.com/linuschui/ip/releases/tag/v0.2).

3. Copy Wagwan.jar into an empty folder you want to use as the home folder for Wagwan.

4. Open a command window in that folder.

5. Run the command java -jar Wagwan.jar (i.e., run the command in the same folder as the jar file)

6. The GUI similar to the image below should appear in a few seconds.

7. Type the command in the command box and press Enter to execute it. Refer to the Features below for details of each command.

![Ui](./docs/Ui.png)

## Features

### 1. Keep track of your tasks

You can use Wagwan to help you store your to-dos, upcoming events, and also deadlines for your tasks !

### 2. Set dates for your events and tasks

You can add a date to your event and deeadlines for easy tracking !

### 3. Find tasks by keywords

You can quickly search for tasks using keywords and Wagwan will show you all tasks that matches the given keyword !

## Usage

### `todo` - Adds a todo to the stored tasks

Adds a todo to the stored tasks, initialised as undone.

Format:

`todo [TODO]`

Example of usage:

Format:

`todo iP6 README`

Expected outcome:

Adds the todo to the stored tasks.

```
Here are the tasks in your list:
Got it. I've added this task:
[T][ ] iP6 README
Now you have 1 tasks in the list.
```

### `deadline` - Adds a deadline to the stored tasks.

Adds a deadline to the stored tasks, initialised as undone.
Deadline must be of the form yyyy-mm-dd

Format:

`deadline [DEADLINE] /by [yyyy-mm-dd]`

Example of usage:

`deadline Submit iP6 /by 2022-09-16`

Expected outcome:

Adds the deadline to the stored tasks.

```
Here are the tasks in your list:
Got it. I've added this task:
[D][ ] Submit iP6 (by: 16/09/2022)
Now you have 2 tasks in the list.
```

### `event` - Adds an event to the stored tasks.

Adds an event to the stored tasks, initialised as undone. Date or time can be of any format

Format:

`event [EVENT] /at [DATE/TIME]`

Example of usage:

`event KR Bash /at 16/09/2022`

Expected outcome:

Adds the deadline to the stored tasks.

```
Here are the tasks in your list:
Got it. I've added this task:
[E][ ] KR Bash (at: 16/09/2022)
Now you have 3 tasks in the list.
```

### `list` - Lists out all stored tasks

Lists out all stored tasks which are indexed, and you can see the task type (todo/event/deadline) as well as it's status (done/undone).

Format:

`list`

Example of usage:

`list`

Expected outcome:

A list of all the stored tasks

```
Here are the tasks in your list:
1. [T][ ] iP6 README
2. [D][ ] Submit iP6 (by: 16/09/2022)
3. [E][ ] KR Bash (at: 16/09/2022)
```

### `mark` - Marks a task as completed

Marks a task by index as completed and updates the status with "X"

Format:

`mark [INDEX]`

Example of usage:

`mark 1`

Expected outcome:

Marks the task as done, reflected with "X"

```
ayo im watching you man ! keep going brotha! I've marked this task as done:
[T][X] iP6 README
```
### `unmark` - Marks a task as not completed

Marks a task by index as not completed and removes the "X" status

Format:

`unmark [INDEX]`

Example of usage:

`unmark 1`

Expected outcome:

Marks the task as undone, "X" status is removed

```
cmon man you gotta do better! I've marked this task as undone:
[T][ ] iP6 README
```

### `delete` - Deletes a task from the stored tasks.

Deletes a task by index. Index must be a number and in the range of the number of tasks.

Format:

`delete [INDEX]`

Example of usage:

`delete 1`

Expected outcome:

Deletes the task from the stored tasks.

```
yessir, I have removed
[T][ ] iP6 README
you have 2 tasks in the list.
```
### `find` - Searches for a task from the stored tasks.

Searches a task with description that matches given keyword.

Format:

`find [KEYWORD]`

Example of usage:

`find bash`

Expected outcome:

Returns a list of all tasks with the matching keyword

```
Here are the matching tasks in your list:
1. [E][ ] KR Bash (at: 16/09/2022)
```

### `update` - Update a task's description

Updates the description of the task with the given index. Does not change the index of the task in the stored task list.

Format:

`update [INDEX] [NEW DESCRIPTION]`

Example of usage:

`update 2 Mom's Birthday`

Expected outcome:

Updates the task of given index with the new description.

```
yessir, I have updated task 2 with the new description: Mom's Birthday
```
### `bye` - Exit program.

Exits program and saves all changes made to the tasks.

Format:

`bye`

Example of usage:

`bye`

Expected outcome:

Exits program, saves changes made to tasks.

```
in a bit, peace brotha
```