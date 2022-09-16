# User Guide

## Features 

### Add tasks

User can add either a ToDo, Deadline or Event task.

### List tasks

User can list all tasks.

### Mark tasks as done

User can mark a task as done.

### Mark tasks as undone

User can mark a task as undone.

### Delete tasks

User can delete a task.

### Find tasks

User can find tasks by keyword.

### Exit program

User can exit the program.

### Set priority

User can set priority to a task.

## Usage

### `todo` - Adds a ToDo task

User can add a ToDo task by typing `todo <description>`.

Example of usage: 

`todo read book`

Expected outcome:

Adds a ToDo task with description `read book`.

```
expected output
    Got it. I've added this task:
    [T][✘] read book
    Now you have 1 tasks in the list.
```

### `deadline` - Adds a Deadline task

User can add a Deadline task by typing `deadline <description> /by <date>`.
Date must be in the format `YYYY-MM-DD`.

Example of usage:

`deadline return book /by 2020-02-20`

Expected outcome:

Adds a Deadline task with description `return book` and date `2020-02-20`.

```
expected output
    Got it. I've added this task:
    [D][✘] return book (by: Feb 20 2020)
    Now you have 2 tasks in the list.
```

### `event` - Adds an Event task

User can add an Event task by typing `event <description> /at <date>`.
Date must be in the format `YYYY-MM-DD`.

Example of usage:

`event project meeting /at 2020-02-20`

Expected outcome:

Adds an Event task with description `project meeting` and date `2020-02-20`.

```
expected output
    Got it. I've added this task:
    [E][✘] project meeting (at: Feb 20 2020)
    Now you have 3 tasks in the list.
```

### `list` - Lists all tasks

User can list all tasks by typing `list`.

Example of usage:

`list`

Expected outcome:

Lists all tasks.

```
expected output
    Here are the tasks in your list:
    1. [T][✘] read book
    2. [D][✘] return book (by: Feb 20 2020)
    3. [E][✘] project meeting (at: Feb 20 2020)
```

### `mark` - Marks a task as done

User can mark a task as done by typing `mark <task number>`.

Example of usage:

`mark 1`

Expected outcome:

Marks the first task as done.

```
expected output
    Nice! I've marked this task as done:
    [T][✓] read book
```

### `unmark` - Marks a task as undone

User can mark a task as undone by typing `unmark <task number>`.

Example of usage:

`unmark 1`

Expected outcome:

Marks the first task as undone.

```
expected output
    Nice! I've marked this task as undone:
    [T][✘] read book
```

### `delete` - Deletes a task

User can delete a task by typing `delete <task number>`.

Example of usage:

`delete 1`

Expected outcome:

Deletes the first task.

```
expected output
    Noted. I've removed this task:
    [T][✓] read book
    Now you have 2 tasks in the list.
```

### `find` - Finds tasks

User can find tasks by typing `find <keyword>`.
Keyword can be a single word or a phrase.

Example of usage:

`find book`

Expected outcome:

Finds all tasks with description containing `book`.

```
expected output
    Here are the matching tasks in your list:
    1. [D][✘] return book (by: Feb 20 2020)
```

### `H` - Sets high priority to a task

User can set high priority to a task by typing `H <task number>`.

Example of usage:

`H 1`

Expected outcome:

Sets high priority to the first task.

```
expected output
    Nice! I've set high priority to this task:
    [D][✘] return book (by: Feb 20 2020)
```

### `M` - Sets medium priority to a task

User can set medium priority to a task by typing `M <task number>`.

Example of usage:

`M 1`

Expected outcome:

Sets medium priority to the first task.

```
expected output
    Nice! I've set medium priority to this task:
    [D][✘] return book (by: Feb 20 2020)
```

### `L` - Sets low priority to a task

User can set low priority to a task by typing `L <task number>`.

Example of usage:

`L 1`

Expected outcome:

Sets low priority to the first task.

```
expected output
    Nice! I've set low priority to this task:
    [D][✘] return book (by: Feb 20 2020)
```

### `bye` - Exits the program

User can exit the program by typing `bye`.

Example of usage:

`bye`

Expected outcome:

Exits the program.

```
expected output
    Bye. Hope to see you again soon!
```

## Command Summary

| Action              | Format, Examples                                                                     |
|---------------------|--------------------------------------------------------------------------------------|
| **Todo**            | `todo <description>` <br> e.g., `todo read book`                                     |
| **Deadline**        | `deadline <description> /by <date>` <br> e.g., `deadline return book /by 2020-02-20` |
| **Event**           | `event <description> /at <date>` <br> e.g., `event project meeting /at 2020-02-20`   |
| **List**            | `list`                                                                               |
| **Mark**            | `mark <task number>` <br> e.g., `mark 1`                                             |
| **Unmark**          | `unmark <task number>` <br> e.g., `unmark 1`                                         |
| **Delete**          | `delete <task number>` <br> e.g., `delete 1`                                         |
| **Find**            | `find <keyword>` <br> e.g., `find book`                                              |
| **High Priority**   | `H <task number>` <br> e.g., `H 1`                                                   |
| **Medium Priority** | `M <task number>` <br> e.g., `M 1`                                                   |
| **Low Priority**    | `L <task number>` <br> e.g., `L 1`                                                   |
| **Bye**             | `bye`                                                                                |

## Acknowledgements

* [JavaFX Tutorial](https://se-education.org/guides/tutorials/javaFx.html)
* [JavaFX Tutorial Part 2](https://se-education.org/guides/tutorials/javaFxPart2.html)
* [JavaFX Tutorial Part 3](https://se-education.org/guides/tutorials/javaFxPart3.html)
* [JavaFX Tutorial Part 4](https://se-education.org/guides/tutorials/javaFxPart4.html)

## Appendix A: Product Scope

### Target user profile

* has a need to manage a list of tasks
* prefers typing over mouse input
* can type fast
* prefers desktop apps over other types

### Value proposition

* manage tasks more efficiently than a typical mouse/GUI driven app

