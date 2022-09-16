# User Guide -- Duke 

This is the user guide for Duke, a platform for you to manage your task efficiently.

## Features

### Manage Tasks 

In Duke, we support three types of tasks:

- `Todo`: a task to do in the future
- `Deadline`: a task that dues on a certain time
- `Event`: an event that task place on a certain time

To manage all your tasks, you can:

- `todo` Add a todo task to your task list.
- `deadline` Add a deadline task to your task list.
- `event` Add an event task to your task list.
- `done` Mark a task as done.
- `delete` Remove a task from your task list.
- `bye` Exit the content or time of your task (**to be finished**)

### List Tasks

In Duke, we offer support to list all your tasks with all information you need, including task description, task status and task date (if applicable).

### Find Relevant Tasks

In Duke, we offer support to find tasks based on what you want. Simply type in some keywords, then you can get all relevant tasks.

### Sort Your Deadlines/Tasks

In Duke, we offer support to sort all your tasks(especially deadlines) based on the task emergency, showing the priority clearly and intuitively.

## Usage

### `todo` - Add a Todo task

This command adds a Todo task to your task list.

#### Format and Examples of usage
```
todo <DESCRIPTION>

todo CS2103T hw1
```

### `deadline` - Add a Deadline task

This command adds a Deadline task to your task list.

#### Format and Examples of usage

```
deadline <DESCRIPTION> /<DATE> [TIME]

deadline CS2103T hw1 2022-09-16
deadline CS2103T hw1 2022-09-16 23:59
```
#### Notes for the command:

- `<DATE>` should be in the format `YYYY-MM-DD`.
- `[TIME]` should be in the format `HH:MM`.
- `[TIME]` is `Optional`, and the default value is `00:00`

### `event` - Add an Event task

This command adds an Event task to your task list.

#### Format and Examples of usage

```
event <DESCRIPTION> / <DATE> <TIME>

event CS2103T hw1 / 2022-09-16 23:59
```
#### Notes for the command:

- `<DATE>` should be in the format `YYYY-MM-DD`.
- `[TIME]` should be in the format `HH:MM`.

### `list` - List all the tasks

This command shows all the tasks.
#### Format and Examples of usage
```
list
```

### `delete` - Delete a task
This command deletes the task based on its `INDEX`.
#### Format and Examples of usage
```
delete <INDEX>

delete 1
```
#### Notes for the command:
- `INDEX` is the index of the task in the current `list` (1-based).

### `done` - Mark a task as done
This command marks the task based on its index `INDEX` as done.
#### Format and Examples of usage
```
done <INDEX>

done 2
```
#### Notes for the command:

- `INDEX` is the index of the task in the current `list` (1-based).

### `find` - Find task containing the keywords

This command finds all tasks that contain the keywords.

#### Format and Examples of usage
```
find <KEYWORDS>

find CS2103T
find CS2103T HW
```
#### Notes for the command:

- `KEYWORDS` should be **substring** of the task description.

### `sort` - Sort all tasks based on emergency

This command sorts all the tasks based on emergency and list them in a new order.
#### Format and Examples of usage
```
sort
```

### `sort Deadline` - Sort all deadlines based on emergency

This command sorts all the deadlines based on emergency and list them in a new order.
#### Format and Examples of usage
```
sort Deadline
```

### `bye` - Exit the program

This command allows you to exit the program.
#### Format and Examples of usage
```
bye
```