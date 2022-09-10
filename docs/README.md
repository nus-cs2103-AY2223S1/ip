# User Guide 🤖
Digital Dad is a CLI task manager. 

## Features 📖

### Manage tasks

Digital Dad allows you to keep track of 3 types of tasks: Todos, Events and Deadlines. 
You can mark tasks as completed, or delete obsolete tasks.

### Search tasks

Give Digital Dad a keyword, 
and it will help you search for relevant tasks stored in your task list. 

### Local storage

Your tasks can be stored so that you won't lose your data when you close the app!

## Usage 💼

### `todo <title>` - Create a TODO

Add a new "todo" into your task list.

Example of usage: `todo eat banana`

```
ADD TASK:
[T][] eat banana
Now you have _ task(s) remaining!
```

### `event <title> /at <date>` - Create an event

Add a new event into your task list.

Example of usage: `event rocket launch /at 2023-12-20`

```
ADD TASK:
[E][] rocket launch (at: Dec 20 2023)
Now you have _ task(s) remaining!
```

### `deadline <title> /by <date>` - Create a deadline

Add a new deadline into your task list.

Example of usage: `deadline finish iP /by 2023-09-16`

```
ADD TASK:
[D][] finish iP (by: Sep 16 2023)
Now you have _ task(s) remaining!
```

### `list` - Show all remaining tasks

Show all remaining tasks in your task list.

```
1. [T][] eat banana
2. [E][] rocket launch (at: Dec 20 2023)
3. [D][] finish iP (by: Sep 16 2023)
```

### `mark <index>` - Mark a task as completed

Mark a task in your task list as completed.

Example of usage: `mark 1`

```
Good job! I've marked this task as done:
[T][X] eat banana
```

### `unmark <index>` - Unmark a task as not completed

Mark a task in your task list as uncompleted.

Example of usage: `unmark 1`

```
OK then, I've marked this task as not done yet:
[T][] eat banana
```

### `delete <index>` - Delete a task

Delete a task in your task list.

Example of usage: `delete 1`

```
DELETE TASK:
[T][] eat banana
Now you have _ task(s) left!
```

### `find <keyword>` - Search for tasks

Search for matching tasks in your task list using a keyword.

Example of usage: `find banana`

```
These tasks in your list match your query:
[T][] eat banana
```

### `bye` - Exits the app

Exits the task manager.


