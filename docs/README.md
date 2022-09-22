# Mia User Guide

![](Ui.png)

## Features

- Create and delete tasks
- Toggles a task status between done and not done
- Manual editing of tasks via configuration data file
- Support for 3 different types of tasks: todos, events, and deadlines

## Usage

### `bye` - Exits the application

Describe the action and its outcome.

Example of usage:

`bye`

Expected outcome:

The application closes. There may or may not be enough time for the exit message (_"See you!"_) to be seen before the application closes.

```
See you!
```

### `list` - Lists all saved tasks

Lists all tasks that are stored in the data file. A task can have one of three types:

- Todos
- Deadlines
- Events

Tasks have a name, and a status indicating whether the task is done or not done.

Example of usage:

`list`

Expected outcome:

A numbered list of tasks, each containing the task type, the task status, the task name, and any other information pertaining to the task.

```
Your Tasks:
1. 📜 ☐ Some Todo
2. ‼ ☐ A Deadline (by 2022-01-23 at
08:00)
3. 📆 ☐ This Event (from 2022-01-23 08:00
to 2022-01-23 08:00)
4. 📜 ☐ Another Todo
6. 📜 ☐ Read a book
```

### `delete` - Deletes a task

Deletes a task.

Example of usage:

`delete TASK_NUMBER`, where `TASK_NUMBER` corresponds to the number seen in the `list` command

Expected outcome:

The task will be deleted and removed from the saved data file.

```
Task has been deleted successfully!
```

If the task number is invalid, the application will say the error and no change will be made.

```
Something went wrong when deleting task TASK_NUMBER! Likely, you specified a task number that is out of range.
```

### `mark` - Marks a task as done

Marks (ticks) a task as done, toggling its status.

Example of usage:

`mark TASK_NUMBER`, where `TASK_NUMBER` corresponds to the number seen in the `list` command

Expected outcome:

The task will be marked as done.

```
Task has been marked as done!
```

If an invalid task is specified, be it the task is already done, or the task does not exist, the application will say the error and no change will be made.

```
Task not modified! Either the task is already done, or you specified an invalid task number TASK_NUMBER.
```

### `unmark` - Marks a task as not done

Unmarks (unticks) a task, toggling its status. In other words, marks a task as not done.

Example of usage:

`unmark TASK_NUMBER`, where `TASK_NUMBER` corresponds to the number seen in the `list` command

Expected outcome:

The task will be marked as not done.

```
Task has been marked as done!
```

If an invalid task is specified, be it the task is still not done, or the task does not exist, the application will say the error and no change will be made.

```
Task not modified! Either the task is still not done, or you specified an invalid task number TASK_NUMBER.
```

### `find` - Searches for a task by name

Describe the action and its outcome.

Example of usage:

`find PARTIAL_NAME_WORDS`, where `PARTIAL_NAME_WORDS` are partial words that form the name of the task that the user wants to query.

Expected outcome:

A numbered list of tasks, each containing the task type, the task status, the task name, and any other information pertaining to the task, similar to the output of the `list` command. However, this list of tasks will only contain tasks that match the following criteria:

- Searches are case-insensitive

  E.g.: "tit" matches "Tit", "tIt", "TIT", and so on.

- Every partial-word is present, in order, in the name

  E.g.: "tit b" matches "tit bird", "title bird" and "brown tit bird that lays eggs" and "petite duck", but not "b tit".

```
Finding tasks that match "tle bi"...
```

```
Matching Tasks:
1. 📜 ☐ TiTle bIrd
```

### `todo` - Creates a new todo task item

Creates a new todo task item with the specified name.

Example of usage:

`todo TASK_NAME`, where `TASK_NAME` is the name of the task you want to create.

Expected outcome:

The specified task will be created and saved in the data file.

```
Added todo "TASK_NAME" to tasks list!
```

### `deadline` - Creates a new deadline task item

Creates a new deadline task item with the specified name and deadline.

Example of usage:

`deadline TASK_NAME /by DATE TIME`, where `TASK_NAME` is the name of the task you want to create, as well as `DATE`, its date, and `TIME`, its time.

Expected outcome:

The specified task will be created and saved in the data file.

```
Added "TASK_NAME" (task with deadline) to tasks list!
```

### `event` - Creates a new event task item

Creates a new event task item with the specified name as well as event date and time.

Example of usage:

`event TASK_NAME /at DATE TIME`, where `TASK_NAME` is the name of the task you want to create, as well as `DATE`, its date, and `TIME`, its time.

Expected outcome:

The specified task will be created and saved in the data file.

```
Added new event "TASK_NAME" to tasks list!
```
