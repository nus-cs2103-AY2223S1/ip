# Duke User Guide

## Introduction

Introducing Duke, your very own text-based personal assistant! Duke keeps track of all your tasks, from ToDos (with no
associated date), Deadlines (with a due by date), and Events (with a date which it happens)!

## Features

### ➕🗑️ Add and Delete tasks!

Easily add and delete ToDos (with no associated date), Deadlines (with a due by date), and Events (with a date which it
happens).

### ✅ Mark tasks as done!

When you are done with tasks, Duke helps you keep track.

### 🔍 Find tasks!

If you have many tasks, you can search for them in Duke!

### 💾 Automatically saves tasks!

Duke will remember the tasks you have entered each session.

## Setup

To setup Duke, follow these steps:

1. Make sure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
   installed
2. Download the latest Duke release `.jar` file from the [release page](https://github.com/mjgui/ip/releases)
3. Double click the `.jar` file to run it!

## Usage

Type commands into the text box for Duke to follow!

### `todo` or `t` - Add new ToDo task

Adds a task (without any date attached) to Duke. Pass in a title of the task after the `todo` keyword.

Example usage:

`todo CS2103 User Guide` OR `t CS2103 User Guide`

Expected outcome:

```
Got it. I've added this task:
 [T] [X] CS2103 User Guide
Now you have 1 task in the list.
```

### `deadline` or `d` - Add new Deadline task

Adds a deadline to Duke. Pass in a title of the task after the `deadline` keyword, followed by the `/by` keyword,
followed by the deadline of the task in `YYYY-MM-DD` format.

Example usage:

`deadline CS2103 Final Submission /by 2022-09-19` OR `d CS2103 Final Submission /by 2022-09-19`

Expected outcome:

```
Got it. I've added this task:
 [D] [ ] CS2103 Final Submission (by: Sep 19 2022)
Now you have 1 task in the list.
```

### `event` or `e` - Add new Event task

Adds an event to Duke. Pass in a title of the event after the `event` keyword, followed by the `/at` keyword, followed
by the date of the event in `YYYY-MM-DD` format.

Example usage:

`event Graduation Day /at 2022-09-19` OR `d Graduation Day /at 2022-09-19`

Expected outcome:

```
Got it. I've added this task:
 [E] [ ] Graduation Day (at: 2022-09-19)
Now you have 1 task in the list.
```

### `list` - Lists all tasks in Duke

Example usage:

`list`

Expected outcome:

Tasks that you have previously added will be listed. The letters `[D]`, `[E]` and `[T]` represent Deadlines, Events and
ToDos respectively, while `[ ]`/`[X]` represents whether the task is not done / done.

```
1. [D] [ ] CS2103 Final Submission (by: Sep 19 2022)
2. [T] [X] CS2103 User Guide
```

### `mark` - Mark a task as done

Pass in the index of the task (starting from 1, based on the `list` command above) to be marked as done after the `mark`
keyword.

Example usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
 [D] [X] CS2103 Final Submission (by: Sep 19 2022)
```

### `delete` or `del` - Delete a task

Pass in the index of the task (starting from 1, based on the `list` command above) to be deleted after the `delete`
keyword.

Example usage:

`delete 1` or `del 1`

Expected outcome:

```
Noted. I've removed this task:
 [D] [X] CS2103 Final Submission (by: Sep 19 2022)
Now you have 1 task in the list.
```

### `find` or `f` - Find a task

Search for a task based on a certain string. Pass in the search string  `find` keyword.

Example usage:

`find CS2103` or `f CS2103`

Expected outcome:

```
Here are the matching tasks in your list:
 1. [D] [X] CS2103 Final Submission (by: Sep 19 2022)
```

### `bye` - Exit duke

Example usage:

`bye`

Expected outcome:

Duke will exit.