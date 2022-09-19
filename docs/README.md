# Duke User Guide

Duke is a friendly user chatbot that will **always be by your side**! It's even got a 
whole list of useful features that you will surely find useful.

## Features

### Add and Delete Tasks

You can add new tasks and remove unnecessary ones as well!

### Categorize your Tasks

Tasks take all sorts of shapes and forms, from simple Todos to Deadlines and even Events
with specified timings!

### Mark your Tasks

Done with your task? Simply mark it as done with a single command!

### Find your Tasks

Looking for one event in your massive list of tasks? The search command has got your back!

### Sort your Tasks

Pressed on time and wondering what to do first? Sort your tasks to see which
one is due first!

## Usage

### `list` - List Tasks

Lists all the tasks in your task list.

Example of usage: 

`list`

Expected outcome:

Returns list of tasks.

```
1.[E][] cs2101 team meeting (Sep 20 2022 19:00)
2.[T][X] clean room
...
```

### `list` - List Tasks

Lists all the tasks in your task list.

Example of usage:

`list`

Expected outcome:

Returns list of tasks. The first label corresponds to the type of task (E for Event, D for Deadline, T for Todo) 
and the second indicates if the task is done (X for done).

```
1.[E][] cs2101 team meeting (Sep 20 2022 19:00)
2.[T][X] clean room
...
```

### `todo` - Add Todo

Adds a new Todo task into the task list.

Example of usage:

`todo clean room`

Expected outcome:

```
Got it. I've added this task:
clean room
Now you have 3 tasks in the list.
```

### `deadline` - Add Deadline

Adds a new Deadline task into the task list. A date must be specified after
`/by` that takes the format `d-M-yyyy H:m`

Example of usage:

`deadline submit HSH quiz /by 19/9/2022 23:59`

Expected outcome:

```
Got it. I've added this task:
submit HSH quiz
Now you have 3 tasks in the list.
```
### `event` - Add Event

Adds a new Event task into the task list. A date must be specified after
`/at` that takes the format `d-M-yyyy H:m`

Example of usage:

`event attend birthday /by 9/6/2023 16:20`

Expected outcome:

```
Got it. I've added this task:
attend birthday
Now you have 3 tasks in the list.
```

### `delete` - Delete Task

Deletes a task from the specified index in the task list.

Example of usage:

`delete 1`

Expected outcome:

```
I've deleted this task:
[E][] cs2101 team meeting (Sep 20 2022 19:00)
```

### `mark` - Mark Task

Marks a task from the specified index in the task list as done.

Example of usage:

`mark 1`

Expected outcome:

```
I've marked this task as done:
[E][X] cs2101 team meeting (Sep 20 2022 19:00)
```

### `unmark` - Unmark Task

Marks a task from the specified index in the task list as undone.

Example of usage:

`unmark 1`

Expected outcome:

```
I've marked this task as undone:
[E][] cs2101 team meeting (Sep 20 2022 19:00)
```

### `find` - Find Task

Filters the list of tasks, matching tasks with description that contains the specified keyword.

Example of usage:

`find team`

Expected outcome:

```
Here are the matching tasks in your list:
[E][] cs2101 team meeting (Sep 20 2022 19:00)
```

### `sort` - Sort Task List

Sorts the list of tasks according to certain parameters. Available parameters include sorting 
by description name (`alphabetically`) and by time (`chronologically`).

Default sort is ascending, but can be switched to descending by specifying the parameter `descending`.

The parameters do not need to be typed out fully: any section of the starting letters can be used (e.g. `alph` or `d`)

Example of usage:

`sort alpha descending`: sorts the list alphabetically in descending order

`sort chron`: sorts the list chronologically in ascending order

Expected outcome:

```
I've sorted the list!
```

### `bye` - Exit Duke

Exits the program and closes the UI.

Example of usage:

`bye`

Expected outcome:

```
Cya!
```