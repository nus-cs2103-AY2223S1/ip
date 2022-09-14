# User Guide

## Step-by-Step Guide: Features and Usage

### Learn the features available by following the listed order, and by keying in the example usages into your app!

---

1. ### `Todo`: Add Todo

Create a new Todo with a description. No additional information
is required for Todo.

Example of usage:

`todo finish CS2103T iP assignment`

Expected outcome:

```
Duck says:

Got it. I've added this task:
  [T][] finish CS2103T iP assignment
Now you have 1 task in the list.
```
---

2. ### `Event`: Add Event

Create a new Event with a description and time period.
Keyword used is `/at`, where the text after this keyword is
considered as the time period. There are no restrictions to the
format of the time period.

Example of usage:

`event attend Meta Software Engineering Conference /at Monday 1-3pm`

Expected outcome:

```
Duck says:

Got it. I've added this task:
  [E][] attend Meta Software Engineering Conference (at: Monday 1-3pm)
Now you have 2 tasks in the list.
```
---

3. ### `Deadline`: Add Deadline

Create a new Deadline with a description, date and time of the deadline.
Keyword used is `/by`. After the keyword, please take note that:
- date must follow DD/MM/YYYY format
- time must follow HH:MM in 24-hour format
- there must be a space between the date and time

Example of usage:

`deadline return book /by 02/08/2019 18:00`

Expected outcome:

```
Duck says:

Got it. I've added this task:
  [D][] return book (by: 02/08/2019 18:00)
Now you have 3 tasks in the list.
```
---
4. ### `list`: View all Tasks

View all the Tasks (whether it be a Todo, Event or Deadline) at a glance.

Example of usage:

`list`

Expected outcome:

```
Duck says:

1. [T][] finish CS2103T iP assignment
2. [E][] attend Meta Software Engineering Conference (at: Monday 1-3pm)
3. [D][] return book (by: 02/08/2019 18:00)
```
---
5. ### `sort`: Sort all Tasks

Sorts the tasks in increasing lexicographical order by description.

Example of usage:

`sort`

Expected outcome:

```
Duck says:

Tasks are sorted successfully, quack!
```

When you use the command `list` after, you will see the following output:

```
1. [E][] attend Meta Software Engineering Conference (at: Monday 1-3pm) 
2. [T][] finish CS2103T iP assignment
3. [D][] return book (by: 02/08/2019 18:00)
```
---
6. ### `find`: Find Tasks containing a keyword

Find all the tasks containing the keyword you specified.

Example of usage:

`find Meta`

Expected outcome:

```
Duck says:

Here are the matching tasks in your list:
1. [E][] attend Meta Software Engineering Conference (at: Monday 1-3pm)
```
---
7. ### `delete`: Delete a Task

Delete the task in the specified index, considering the current ordering of the tasks
(use `list` to check the ordering). Note that this feature works with 1-indexing. 
The index 0 is not a valid index.

Example of usage:

`delete 1`

Expected outcome:

```
Duck says:

Noted. I've removed this task:
[E][] attend Meta Software Engineering Conference (at: Monday 1-3pm)
```
---
8. ### `mark`: Mark a Task as completed

Marks a task in a specified index as completed, as indicated by an X symbol.
Note that index starts at 1 and follows the current ordering of tasks, as
seen when you call the command `list`.

Example of usage:

`mark 1`

Expected outcome:

```
Duck says:

Nice! I've marked this task as done:
  [T][X] finish CS2103T iP assignment
```
---
9. ### `unmark`: Mark a Task as incomplete

Marks a task in a specified index as incomplete, as indicated by the lack of an X symbol.
Note that index starts at 1 and follows the current ordering of tasks, as
seen when you call the command `list`.

Example of usage:

`unmark 1`

Expected outcome:

```
Duck says:

OK, I've marked this task as not done yet:
  [T][] finish CS2103T iP assignment
```
---
10. ### `bye`: Close the program (saving is automatic when you exit)

Saves the tasks in the current list into the hard disk (preserving the current
ordering). Then, the program exits and closes the JavaFX UI window automatically.

Example of usage:

`bye`

Expected outcome:

The app saves the tasks in the current list (preserving the current ordering),
and closes the app automatically.
