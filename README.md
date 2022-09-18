# User Guide

Bobby is your friendly crewmate that will help you
to keep track of your tasks!
With Bobby, you will never forget another task again!

![This is a screenshot of the UI](https://github.com/ruihan00/ip/blob/master/docs/UI.png?raw=true)
## Features

### Managing Task

Bobby assist you in keeping track of 3 types of tasks. Todos, Events and Deadlines
You are able to add and delete task as well as toggle the completion status of the task
### Find Tasks

Bobby will help you find tasks based on your query


### Swtich data files

Bobby allows you to switch data files easily so you can organise your tasks even better!

### Auto Save

Bobby will automatically save your task into your local data file

## Usage

### `todo [description]` - Creates a new Todo

Adds a new Todo to your task list

Example of usage:

`todo buy groceries`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[T][] buy groceries
you have 1 tasks now...
```

### `deadline [description] /by [deadline]` - Creates a Deadline

Adds a new Deadline to your task list

Example of usage:

`deadline submit assignment 1 /by 2022-10-06 15:00`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[D][] submit assignment 1 (by: 06 Oct 2022, 3.00pm) 
you have 2 tasks now...
```

### `event [description] /by [start time] - [end time]` - Creates a Event

Adds a new Event to your task list


### Same-day Events
Example of usage :

`event Samantha's birthday /at 2022-11-10 19:00 - 21:00`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[E][] Samantha's birthday (at 10 Nov 2022, 7.00pm to 9.00pm) 
you have 3 tasks now...
```

###Multi-day Events
Example of usage:

`event Australia trip /at 2022-12-11 08:00 - 2022-12-19 23:59`

Expected outcome:

Description of the outcome.

```
Oh god you have another task
[E][] Australia trip (at 11 Dec 2022, 7.00pm to 19 Dec 2022 11.59pm) 
you have 4 tasks now...
```
### `list` - View tasks

List all the task in your task list

Example of usage:

`list`

Expected outcome:

```
Wow i am so useful
1. [T][] buy groceries
2. [D][] submit assignment 1 (by: 06 Oct 2022, 3.00pm) 
3. [E][] Samantha's birthday (at 10 Nov 2022, 7.00pm to 9.00pm) 
4. [E][] Australia trip (at 11 Dec 2022, 7.00pm to 19 Dec 2022 11.59pm) 
```
### `delete [task index]` - delete task

Delete a specified task

Example of usage:

`delete 4`

Expected outcome:

```
Thats right tasks are just a concept
[E][] Australia trip (at 11 Dec 2022, 7.00pm to 19 Dec 2022 11.59pm) 
You have 3 tasks now...
```

### `mark [task index]` - Mark task as completed

Mark a specified task as completed

Example of usage:

`mark 1`

Expected outcome:

```
Wow so effishun
[T][X] buy groceries
```

### `unmark [task index]` - Mark task as uncompleted

Mark a specified task as uncompleted

Example of usage:

`unmark 1`

Expected outcome:

```
SUS you didnt finish the task
[T][] buy groceries
```

### `find [keywords]` - Search for task

Searches for task that includes the keywords

Example of usage:

`find assignment`

Expected outcome:

```
Scanning all file
[D][] submit assignment 1 (by: 06 Oct 2022, 3.00pm) 
Wow i am the best
```

### `switch [file name]` - change data source

Switch the data file being used by the task manager

Example of usage:

`switch school.txt`

Expected outcome:

```
File switched successfully to school.txt
```

### `bye` - exit

Exits bobby

Example of usage:

`bye`

Expected outcome:

```
Bobby was ejected
```
