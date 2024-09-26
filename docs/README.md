# User Guide
Introducing Mr Robot, your friendly task manager robot!
## Features

### Manage tasks

Mr Robot helps you to keep track of 3 types of tasks: *Todos*, *Events* and *Deadlines*.
You can add a new task or delete an old task. You can also mark a task as completed or unmark it as uncompleted.

### Find tasks

Mr Robot can help you search for tasks stored in your task list based on the keyword you enter!

### Auto-Save

Mr Robot automatically saves your tasks in local storage, so you can always retrieve the data again even after closing the application!

### Statistics

Mr Robot consolidates the total tasks you have completed and total tasks you have yet to complete as statistics for your reference!
## Usage

### `todo <description>` - Creates a todo task

Adds a new "todo" into your task list.

Example of usage: `todo go to gym`

```
Okay! I've added this task: 
[T][] go to gym
Now you have 1 task(s) in the list.
```


### `event <description> /at <date>` - Creates an event

Adds a new event into your task list.

Example of usage: `event meeting /at 2023-12-12`

```
Okay! I've added this task:
[E][] meeting (at: Dec 12 2023)
Now you have 2 task(s) in the list.
```

### `deadline <description> /by <date>` - Creates a deadline

Adds a new deadline into your task list.

Example of usage: `deadline watch lecture /by 2023-12-12`

```
Okay! I've added this task:
[D][] watch lecture (by: Dec 12 2023)
Now you have 3 task(s) in the list.
```

### `list` - Shows all current tasks with statistics summary

Shows all current tasks in your task list with statistics summary.

```
Here are the task(s) in your list so far!
1. [T][] go to gym
2. [E][] meeting (at: Dec 12 2023)
3. [D][] watch lecture (by: Dec 12 2023)
You have completed 0 task(s) so far.
3 more task(s) to go! Keep it up!
```

### `mark <index>` - Marks a task as done

Marks a task in your task list as done.

Example of usage: `mark 1`

```
Yay! I've marked this task as done:
[T][X] go to gym
```

### `unmark <index>` - Unmarks a task as not done

Marks a task in your task list as undone.

Example of usage: `unmark 1`

```
Okay.. I've marked this task as not done yet:
[T][] go to gym
```

### `delete <index>` - Deletes a task

Deletes a task in your task list.

Example of usage: `delete 1`

```
Noted. I've removed this task:
[T][] go to gym
Now you have 2 task(s) in the list.
```

### `find <keyword>` - Searches for tasks

Searches for matching tasks in your task list using a keyword.

Example of usage: `find meeting`

```
Here are the task(s) with your keyword!
1. [E][] meeting (at: Dec 12 2023)
```

### `bye` - Exits the app

Exits the task manager.