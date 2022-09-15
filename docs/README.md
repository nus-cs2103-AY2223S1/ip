# User Guide

---

## Features

### Keep track of various tasks

Duke can help you keep track of several types of tasks:

- Todos
- Deadlines
- Events
- Recurring tasks

---

## Usage

### `todo` - Creates a todo task

Example usage:

`todo DESCRIPTION`

Example expected outcome:

```
Got it. I've added this task:
  [T][ ] DESCRIPTION
Now you have 5 tasks in the list
```

---

### `deadline` - Creates a deadline task

Example usage:

`deadline DESCRIPTION /by YYYY-MM-DD`

Example expected outcome:

```
Got it. I've added this task:
  [D][ ] DESCRIPTION (by: Sep 15 2022)
Now you have 6 tasks in the list
```

---

### `event` - Creates an event task

Example usage:

`event DESCRIPTION /at PERIOD`

Example expected outcome:

```
Got it. I've added this task:
  [E][ ] DESCRIPTION (at: PERIOD)
Now you have 7 tasks in the list
```

---

### `recurring` - Creates a recurring task

Example usage:

`recurring DESCRIPTION /repeats INTERVAL`

Example expected outcome:

```
Got it. I've added this task:
  [R][ ] DESCRIPTION (repeats: INTERVAL)
Now you have 8 tasks in the list
```

---

### `list` - Lists all tasks

Usage:

`list`

Example expected outcome:

```
Here are the tasks in your list:
1.[T][X] read book
2.[E][X] project meeting (at: Aug 6th 2-4pm)
3.[T][ ] borrow book
4.[E][ ] project meeting (at: Mon 2-4pm)
5.[T][ ] DESCRIPTION
6.[D][ ] DESCRIPTION (by: Sep 15 2022)
7.[E][ ] DESCRIPTION (at: PERIOD)
8.[R][ ] DESCRIPTION (repeats: INTERVAL)
```

---

### `mark` - Marks a task as completed

Example usage:

`mark INDEX`

Example expected outcome:

```
Nice! I've marked this task as done:
  [R][X] DESCRIPTION (repeats: INTERVAL)
```

---

### `unmark` - Marks a task as not completed

Example usage:

`unmark INDEX`

Example expected outcome:

```
OK, I've marked this task as not done yet:
  [R][ ] DESCRIPTION (repeats: INTERVAL)
```

---

### `delete` - Deletes a task

Example usage:

`delete INDEX`

Example expected outcome:

```
Noted. I've removed this task:
  [R][ ] DESCRIPTION (repeats: INTERVAL)
Now you have 7 tasks in the list.
```

---

### `find` - Finds tasks

Example usage:

`find SEARCH_TERM`

Example expected outcome:

```
Here are your search results:
1.[T][X] read book
2.[T][ ] borrow book
```

---

### `bye` - Exits Duke

Usage:

`bye`

---
