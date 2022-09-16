# MONKE User Guide

![alt text](https://github.com/CFSY/ip/blob/master/src/main/resources/images/monke2.png?raw=true)

## Features 

### Adding and Deleting Tasks

Add 3 types of tasks to your list.
The 3 types include: `event`, `todo` and `deadline`


### Listing Your Tasks

List out all the tasks that you have added.


### Find a Task

Find tasks that you have added containing a keyword.


### Mark as done or undone

Mark your tasks as done or undone.


### Assigning and Removing Custom Keywords

Assign or remove custom keywords to existing commands. (custom keywords cannot be assigned to easter eggs)
Refer to the full list of commands below.


### Easter Eggs

Have some fun with the monkey helper!


### Help

Shows instructions for using the commands.



## Usage

### `event` - Add an event task

Format: `event <task description> /at <time>`

Example of usage: 

`event eat banana /on friday`

Expected outcome:

```
hoohoohaha, monke has added the following task to the list!
  [E][ ] eat banana (on: friday)
u currently have 1 task.
```

### `todo` - Add a todo task

Format: `todo <task description>`

Example of usage: 

`todo throw banana`

Expected outcome:

```
hoohoohaha, monke has added the following task to the list!
  [T][ ] throw banana
u currently have 2 tasks.
```

### `deadline` - Add a deadline task

Format: `deadline <task description> /by <time>`

Example of usage: 

`deadline clean banana peel /by saturday`

Expected outcome:

```
hoohoohaha, monke has added the following task to the list!
  [D][ ] clean banana peel (by: saturday)
u currently have 3 tasks.
```

### `list` - List out all your current tasks

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
u monke gotta do these:
  1. [E][ ] eat banana (on: friday)
  2. [T][ ] throw banana
  3. [D][ ] clean banana peel (by: saturday)
```

### `find` - Search tasks with a keyword

Format: `find <keyword>`

Example of usage: 

`find clean`

Expected outcome:

```
u monke has these monkey doos:
  3. [D][ ] clean banana peel (by: saturday)
```

### `mark` - Mark task as done

Format: `mark <task index>`

Example of usage: 

`mark 1`

Expected outcome:

```
HOOHOOHAHA! GOOD MONKE!!
monke has marked this task as done!
  [E][X] eat banana (on: friday)
```

### `unmark` - Mark task as undone

Format: `unmark <task index>`

Example of usage: 

`unmark 1`

Expected outcome:

```
hoohoohaha, monke has marked this task as undone.
  [E][ ] eat banana (on: friday)
```

### `delete` - Delete a task

Format: `delete <task index>`

Example of usage: 

`delete 2`

Expected outcome:

```
hoohoohaha, monke has removed the following task from the list!
  [T][ ] throw banana
u currently have 2 tasks.
```

### `addkw` - Assign a custom keyword to a command

Format: `addkw <custom keyword> <command>`

Example of usage: 

`addkw del delete`

Expected outcome:

```
monke has assigned del to delete.
```

### `delkw` - Delete a custom keyword

Format: `delkw <custom keyword>`

Example of usage: 

`delkw del`

Expected outcome:

```
monke has removed del as a keyword.
```

### `bye` - Say bye to the monkey helper!

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

The app will close.

### `help` - Display instructions for commands

Format: `help <command>`

Example of usage: 

`help event`

Expected outcome:

```
Add an event task.
Format: event <task description> /on <time>
```

### `banana / bananas` - give the monkey bananas

Format: `banana` or `bananas`

Example of usage: 

`banana`

Expected outcome:

```
HOOHOOHAHA nomnom
```

### Easter Eggs

Supported easter eggs: 'hello', 'u monke', 'hoohoohaha', 'bruh' and 'monkey see'
Have fun with them!!! :D

Format: `<easter egg>`

Example of usage: 

`u monke`

Expected outcome:

```
NO YOU ARE THE MONKE!
```
