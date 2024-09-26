# User Guide

Duke allows users to manage tasks using a command line application.
Users can keep track of different tasks such as _Deadlines_, _Events_ and _ToDos_.
Users can _create_, _tag_, _mark_, _unmark_, _list_, _find_, and _delete_ tasks.

---

## Installation

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest release from [here](https://github.com/Marcusgwj/ip).
3. Double-click the file to start the application.
4. Or run `java -jar duke.jar` at the directory that the jar file is located.

---

## Features

### `todo` - Create a Todo task

Creates a ToDo task with the given description.

Example of usage:

`todo borrow book`

Expected outcome:

Creates a ToDo with the description `borrow book`.

```
Got it. I've added this task: 
[T][ ] borrow book
Now you have 1 tasks in the list.
```

### `deadline` - Create a deadline task

Creates a Deadline task with the given description and date.

Example of usage:

`deadline return book /by 2022-09-21`

Expected outcome:

Creates a Deadline with the description `return book` and due date _21 September 2022_.

```
Got it. I've added this task: 
[D][ ] return book (by: Sep 21 2022)
Now you have 2 tasks in the list.
```


### `event` - Create an event task

Creates an Event task with the given description and date.

Example of usage:

`event project meeting /at 2022-09-23`

Expected outcome:

Creates an Event with the description `project meeting` on _23rd September 2022_.

```
Got it. I've added this task: 
[E][ ] project meeting (at: Sep 23 2022)
Now you have 3 tasks in the list.
```

### `mark` - Mark a task as completed

Marks the specified task as completed.

Example of usage:

`mark 1`

Expected outcome:

Marks the task stored at position 1 of the task list as completed.

```
Nice! I've marked this task as done:
[T][X] borrow book
```

### `unmark` - Mark a task as uncompleted

Marks the specified task as uncompleted.

Example of usage:

`unmark 1`

Expected outcome:

Marks the task stored at position 1 of the task list as uncompleted.

```
OK, I've marked this task as not done yet:
[T][ ] borrow book
```

### `tag` - Add tags to a task

Add tags to the specified task.

Example of usage:

`tag 1 math science`

Expected outcome:

Add the tags math and science to the task stored at position 1 of the task list.

```
Nice! I've tagged this task:
[T][ ] borrow book #science #math
```

### `list` - List all tasks

List all tasks stored in the Duke application.

Example of usage:

`list`

Expected outcome:

List all tasks currently stored in the Duke application.

```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sep 21 2022)
3. [E][ ] project meeting (at: Sep 23 2022)
```

### `find` - Find matching tasks

Find tasks with description/tags that matches the specified string.

Example of usage:

`find keywords book`

Expected outcome:

Finds all tasks with description that include the string `book`.

```
Here are the matching tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sep 21 2022)
```

Example of usage:

`find tags math`

Expected outcome:

Finds all tasks with tags that match the string `math`.

```
Here are the matching tasks in your list:
1.[T][ ] borrow book #science #math
2.[D][ ] return book (by: Sep 21 2022) #math
```

### `delete` - Delete a task

Deletes the specified task.

Example of usage:

`delete 3`

Expected outcome:

Deletes the task stored at position 3 of the task list.

```
Noted. I've removed this task:
[E][ ] project meeting (at: Sep 23 2022)
Now you have 2 tasks in the list.
```

### `bye` - Exit

Exits the Duke application.

Example of usage:

`bye`

Expected outcome:

Prints the following line of text on the screen and exits the application.

```
Bye. Hope to see you again soon!
```

---