---
layout: default
title: Commands
nav_order: 2
permalink: /commands
---

# Commands
{: .no_toc}

<details open markdown="block">
  <summary>
    Table of contents
  </summary>
  {: .text-delta }
- TOC
{:toc}
</details>

## Task creation

### `deadline` - Create a new Deadline

Create a new task that is a deadline. Refer to [date/time syntax](syntax.md) for date and time formats recognised.

Example of usage:

`deadline return book /by 23 Sep 6pm`

Expected outcome:

Creates a new deadline, with description "return book", and deadline of 23 Sep (of the current year), at 6pm.

```
Got it. I've added this task:
[D][] return book (by: Sep 23, 2022 6:00PM)
Now you have 4 tasks in the list.
```

### `event` - Create a new Event

Create a new task that is an event. Refer to [date/time syntax](syntax.md) for date and time formats recognised.

Example of usage:

`event bookfair /at 25 Sep 3.30pm`

Expected outcome:

Creates a new event, with description "bookfair", and time of 25 Sep (of the current year), at 3:30pm.

```
Got it. I've added this task:
[E][] bookfair (at: Sep 25, 2022 3:30PM)
Now you have 5 tasks in the list.
```

### `todo` - Create a new Todo

Create a new task that is a todo.

Example of usage:

`todo clean room`

Expected outcome:

Creates a new todo, with description "clean room".

```
Got it. I've added this task:
[T][] clean room
Now you have 6 tasks in the list.
```

## Task management

### `list` - List all your tasks

List the tasks you have.

Expected outcome:

A list of your tasks.

```
Here are the tasks in your list:
1. [T][] clean room
2. [D][] return book (by: Aug 24, 2022 6:00PM)
3. [E][] bookfair (at: Aug 25, 2022 5:00PM)
```

### `find` - Search your tasks for a keyword

Find a keyword in your task list.

Example of usage:

`find (search term)`

Expected outcome:

Tasks which contain the search term.

```
find book

Here are the matching tasks in your list:
2. [D][] return book (by: Aug 24, 2022 6:00PM)
3. [E][] bookfair (at: Aug 25, 2022 5:00PM)
```

### `sortby` - Sort your tasks

Sort your tasks according to some criteria.

- `time` sorts by chronological order, from first to last
- `type` follows the following order: Deadline, Event, Todo
- `desc` sorts the descriptions in alphabetical order
- `status` places undone tasks before tasks which are done

Example of usage:

`sortby (time / type / desc / status)`

Expected outcome:

Task list is sorted according to stated criteria.

```
Here are the tasks in your list:
1. [D][] return book (by: Aug 24, 2022 6:00PM)
2. [E][] bookfair (at: Aug 25, 2022 5:00PM)
3. [T][] clean room
```

### `mark` - Mark a task as done

Mark a task as done.

Example of usage:

`mark (index)`

Expected outcome:

Marks task as done.

```
Nice! I've marked this task as done:
2. [E][X] bookfair (at: Aug 25, 2022 5:00PM)
```

### `unmark` - List all your tasks

Mark a task as not done.

Example of usage:

`unmark (index)`

Expected outcome:

Marks task as not done.

```
OK, I've marked this task as not done yet:
2. [E][] bookfair (at: Aug 25, 2022 5:00PM)
```


### `delete` - Delete a task

Delete a task from the task list.

Example of usage:

`delete (index)`

Expected outcome:

Task is deleted from the task list.

```
Noted. I've removed this task:
6. [T][] clean room
Now you have 5 tasks in the list.
```

## Duke controls

### `bye` - Exit Duke

Exit Duke.