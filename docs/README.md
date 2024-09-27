# User Guide

## Features 
- Create tasks
- Mark task
- Unmark task
- List all tasks
- Help
### Create tasks

Create 3 different types of tasks - todo, deadline and event

## Usage

### `todo` - Add a todo task

Todo task is added into task list

Example of usage: 

`todo read book`

Expected outcome:

Chad bot returns the following output

```
expected output
Got it. I've added this task:
[T][] read book
```

### `deadline` - Add a deadline task

Deadline task is added into task list

Example of usage:

`deadline return book /by 2/12/2019 1800`

Expected outcome:

Chad bot returns the following output

```
expected output
Got it. I've added this task:
[D][] return book (by: 02 Decemeber 2019 18:00 PM)
```

### `event` - Add a event task

Event task is added into task list

Example of usage:

`event party /at 2/12/2020 1800`

Expected outcome:

Chad bot returns the following output

```
expected output
Got it. I've added this task:
[E][] party (at: 02 Decemeber 2020 18:00 PM)
```