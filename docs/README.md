# User Guide for GigaDuke

Manage your daily tasks with GigaDuke

-------------------------------------------------------------------------------------
## Features 

-------------------------------------------------------------------------------------

### Create tasks

There are 3 types of Tasks in GigaDuke
- ToDo: for tasks with no timestamp
- Deadline: for tasks that need to be finished by a certain time
- Event: for tasks that occur at a certain date and time

### Mark and Un-mark tasks

Have the ability to mark completed tasks as done. 

You can also choose to un-mark tasks that you marked as completed.

### Delete tasks

You can delete tasks from your task list if you no longer need it.

### List all tasks

You can see all your tasks at once.

### Filter tasks by name

You can filter your tasks list to see only tasks that match certain keywords.

### Update tasks

You have the option to update Deadline and Event tasks timestamps.

### Saving

All your tasks are automatically saved after every command.

They will be loaded in everytime you re-start the application.

## Usage

-------------------------------------------------------------------------------------

### `todo` - Adding a ToDo task

Adds a Todo Task to the GigaDuke task tracker.

format: `todo <task_name>`

Example of usage: `todo read book`

Expected outcome:

A message indicating the Todo task has been successfully added.

```
Got it. I've added this task:
 [T][] read book
Now you have 1 tasks in the list.
```

### `deadline` - Adding a Deadline task

Adds a Deadline Task to the GigaDuke task tracker.

format: `deadline <task_name> /by <timestamp>`

Example of usage: `deadline return book /by 2022-12-11`

Expected outcome:

A message indicating the Deadline task has been successfully added.

```
Got it. I've added this task:
 [D][]read book (by: Dec 11 2022)
Now you have 2 tasks in the list.
```

### `event` - Adding an Event task

Adds an Event Task to the GigaDuke task tracker.

format: `event <task_name> /at <timestamp>`

Example of usage: `event library fair /at 2022-12-12`

Expected outcome:

A message indicating the Event task has been successfully added.

```
Got it. I've added this task:
 [E][]library fair (at: Dec 12 2022)
Now you have 3 tasks in the list.
```

### `mark` - Marks a task as completed

Marks a task as completed.

format: `mark <task_index>`

Example of usage: `mark 1`

Expected outcome:

A message indicating the task has been successfully marked as completed.

```
Nice! I've marked this task as done:
 [T][X] read book
```

### `unmark` - Marks a task as uncompleted

Marks a task as uncompleted.

format: `unmark <task_index>`

Example of usage: `unmark 1`

Expected outcome:

A message indicating the task has been successfully marked as uncompleted.

```
OK, I've marked this task as not done yet:
 [T][] read book
```

### `delete` - Deletes a task from GigaDuke

Removes the task from GigaDuke task tracker.

format: `delete <task_index>`

Example of usage: `delete 1`

Expected outcome:

A message indicating the task has been successfully deleted.

```
Noted. I've removed this task:
 [T][] read book
Now you have 2 tasks in the list.
```

### `list` - Shows all tasks

Displays all the tasks.

format: `list`

Example of usage: `list`

Expected outcome:

A message showing all the tasks in task tracker.

```
Here are the tasks in your list:
 1. [D][]read book (by: Dec 11 2022)
 2. [E][]library fair (at: Dec 12 2022)
```

### `find` - Filters the list

Displays all tasks that match the keywords.

format: `find <keywords> ...`

Example of usage: `find read`

Expected outcome:

A message showing all the tasks that match the keywords.

```
Here are the matching tasks in your list:
 1. [D][]read book (by: Dec 11 2022)
```

### `update` - Marks a task as uncompleted

Updates the timestamp of certain tasks.

format: `update <task_index> <timestamp>`

Example of usage: `update 1 2000-12-01`

Expected outcome:

A message indicating the task has been successfully updated to the new timestamp.

```
OK, I've updated the task to:
    [D][]read book (by: Dec 01 2000)
```

### `bye` - Ends GigaDuke

Close the task tracker.

format: `bye`

Example of usage: `bye`

Expected outcome:

Application closes.

```
Bye. Hope to see you again soon!
```