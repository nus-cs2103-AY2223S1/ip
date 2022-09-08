# User Guide

Duke is a desktop application for **tracking tasks** in the style of a chatbot. Developed with _love_ using JavaFX.

## Features

### GUI

You can interact with Duke in a beautiful GUI developed with JavaFX.

### Persistent storage

You can tell Duke to add tasks and store them in a list. Your tasks will be stored in a text file and will still be available for you the next time you open Duke.

### Marking tasks

Once you have completed a task, you can mark the status of it in Duke.

### Searching

###

## Usage

### `list` - List all tasks you have added

This command will list all the tasks in the task list.

**Usage:** `list`

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `todo` - Add a Todo task to the task list

**Usage:** `todo <task description>`

A todo task with the task description specified will be added to the task list.

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `deadline` - Add a Deadline task to the task list

**Usage:** `deadline <task description> /by <date>`

A deadline task with the specified description and date will be added to the task list.

> Note: the format of `date` should be `YYYY-MM-DD`. <br>
> eg. `2022-10-01` for 1st October 2022

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `event` - Add an Event task to the task list

**Usage:** `Event <task description> /at <date>`

An event task with the specified description and date will be added to the task list.

> Note: the format of `date` should be `YYYY-MM-DD`. <br>
> eg. `2022-10-01` for 1st October 2022

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `delete` - Delete a task from the task list

**Usage:** `delete <index>`

Remove the task specified by the index from the task list and the storage file.

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `mark` - Mark a task

**Usage:** `mark <index>`

Mark the task specified by the index as complete.

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `unmark` - Unmark a task

**Usage:** `unmark <index>`

Mark the task specified by the index as incomplete.

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `update` - Update the description of a task

**Usage:** `update <index> <new description>`

Update the task specified by the index with a new description.

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

### `find` - Look for tasks in the task list

**Usage:** `find <search query>`

Search the task list for a task with a description containing the query specified.

<details>
<summary><b>Expected outcome</b></summary>

```
expected output
```

</details>

# References

JavaFX GUI: https://se-education.org/guides/tutorials/javaFxPart1.html
