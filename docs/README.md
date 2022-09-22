# Knave of Hearts - User Guide

## Features

### todo, event, deadline

Knave of Hearts supports these three different types of task. **todo** is a normal task, **event** is a task occuring at a specific date and **deadline** is a task with a due date.

### mark, unmark

**mark** a task as done, or **unmark** it if you realised you have not completed it

### tag, untag

**tag** your task or **untag** to remove the tag

### list, find, delete, bye

**list** all your tasks or **find** the task by keyword, **delete** deletes the task, **bye** exits the application

## Usage

### `todo` - Add todo task

Example of usage:
`todo math assignment`

Expected outcome:
image.png

### `event` - Add event task

Example of usage:
`event orientation /at 2022-09-18`

Expected outcome:
image.png

### `deadline` - Add deadline task

Example of usage:
`deadline essay /by 2022-09-19`

Expected outcome:
image.png

### `mark` - Mark task as done

Example of usage:
`mark 1`

Expected outcome:
image.png

### `unmark` - Unmark a task to undone

Example of usage:
`unmark 1`

Expected outcome:
image.png

### `tag` - Tag a task

Example of usage:
`tag 1 /t fun`

Expected outcome:
image.png

### `untag` - Untag a task

Example of usage:
`untag 1`

Expected outcome:
image.png

### `list` - List all tasks

Example of usage:
`list`

Expected outcome:
image.png

### `find` - Find task by keyword

Example of usage:
`find orientation`

Expected outcome:
image.png

### `delete` - Delete a task

Example of usage:
`delete 1`

Expected outcome:
image.png

### `bye` - Exit application

Example of usage:
`bye` + press any key

Expected outcome:
image.png

## Command Summary

| Action   | Format                       | Example                            |
| :------- | :--------------------------- | :--------------------------------- |
| ToDo     | `todo <TASK>`                | `todo math assignment`             |
| Event    | `event <TASK> /at <date>`    | `event orientation /at 2022-09-18` |
| Deadline | `deadline <TASK> /by <DATE>` | `deadline essay /by 2022-09-19`    |
| Mark     | `mark <TASK_NUMBER>`         | `mark 3`                           |
| Unmark   | `unmark <TASK_NUMBER>`       | `unmark 4`                         |
| Tag      | `tag <TASK_NUMBER> /t <TAG>` | `tag 1 /t fun`                     |
| Untag    | `untag <TASK_NUMBER>`        | `untag 1`                          |
| List     | `list`                       | `list`                             |
| Find     | `find <KEYWORD>`             | `find hello world`                 |
| Delete   | `delete <TASK_NUMBER>`       | `delete 1`                         |
| Exit     | `bye`                        | `bye`                              |

## Showcase

![ui](Ui.png)
