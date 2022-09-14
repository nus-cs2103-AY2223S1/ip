# User Guide
Introducing Alfredo, your friendly task manager!
## Features 

### Manage tasks

Alfredo helps you to keep track of 3 types of tasks: *Todos*, *Events* and *Deadlines*.
You can mark tasks as completed, or delete them as you wish.

### Find tasks

Alfredo can help you search for relevant tasks stored in your task list based on the keyword you enter!

### Auto-Save

Alfredo saves your tasks in local storage, so you won't lose your data when you close the app!

## Usage 

### `todo <description>` - Create a todo

Add a new "todo" into your task list.

Example of usage: `todo hit the gym`

```
Got it. I've added this task: 
[T][] hit the gym
Now you have 1 task(s) in the list.
```


### `event <description> /at <date>` - Create an event

Add a new event into your task list.

Example of usage: `event project meeting /at 2023-12-12`

```
Got it. I've added this task:
[E][] project meeting (at: Dec 12 2023)
Now you have 2 task(s) in the list.
```

### `deadline <description> /by <date>` - Create a deadline

Add a new deadline into your task list.

Example of usage: `deadline submit essay /by 2023-12-12`

```
Got it. I've added this task:
[D][] submit essay (by: Dec 12 2023)
Now you have 3 task(s) in the list.
```

### `list` - Show all current tasks

Show all current tasks in your task list.

```
Here are the tasks in your list:
1. [T][] hit the gym
2. [E][] project meeting (at: Dec 12 2023)
3. [D][] submit essay (by: Dec 12 2023)
```

### `mark <index>` - Mark a task as done

Mark a task in your task list as done.

Example of usage: `mark 1`

```
Nice! I've marked this task as done:
[T][X] hit the gym
```

### `unmark <index>` - Unmark a task as not done

Mark a task in your task list as undone.

Example of usage: `unmark 1`

```
OK, I've marked this task as not done yet:
[T][] hit the gym
```

### `delete <index>` - Delete a task

Delete a task in your task list.

Example of usage: `delete 1`

```
Noted. I've removed this task:
[T][] hit the gym
Now you have 2 task(s) in the list.
```

### `find <keyword>` - Search for tasks

Search for matching tasks in your task list using a keyword.

Example of usage: `find project`

```
Here are the matching tasks in your list:
1. [E][] project meeting (at: Dec 12 2023)
```

### `bye` - Exits the app

Exits the task manager.
