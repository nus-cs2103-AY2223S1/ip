# DukeAce User Guide

---

Meet DukeAce, your very own task manager
## Features 

---
### Keep Track of Todos, Events and Deadlines

DukeAce, your task manager is able to record your tasks. 
DukeAce is also able to categorise your tasks into 3 types

- Todos
- Events
- Deadlines

Deadlines are able to record the date of the deadlines as well.

---
## Usage

---
### `hi` - DukeAce responds back hi

DukeAce will show greeting message when received a "hi" input.

Example of usage: 

`hi`

Expected outcome:

```
Hello! I'm Darwin :)
What can I do for you?
```

---
### `bye` - DukeAce says back bye

DukeAce will show sending off message when received a "bye" input.

Example of usage:

`bye`

Expected outcome:

```
Aww going so soon :(( 
Hope to see you again soon!
```


---
### `todo` - DukeAce create todo Task

DukeAce will create todo task.

Example of usage:

`todo Homework & Project`

Expected outcome:


```
Oki, nicee. I've added this task:
[T][ ] Homework & Project
Now you have 3 tasks in the list.
```

---
### `event` - To create event Task

DukeAce will create event task.

Example of usage:

`event Party /at my place 3pm`

Expected outcome:

```
Oki, niceee. I've added this task:
[E][ ] Party (at: my place 3pm)
Now you have 4 tasks in the list.
```


---
### `deadline` - To create deadline Task

DukeAce will create deadline task that has a date for deadline.

Example of usage:

`deadline Project Submission /by 2002-07-24`

Input has to be in yyyy-MM-dd as the format for deadline date.

Expected outcome:

Outcome will be in MM dd yyyy format

```
Oki, niceee. I've added this task:
[D][ ] Deadline (by: Jul 24 2021)
Now you have 5 tasks in the list.
```

---
### `mark` - To mark a Task as complete

DukeAce will mark the task as complete.

Example of usage:

`mark 1`

Expected outcome:

Task with order number 1 will be marked.

```
Nice! I've marked this task as done:
[T][X] Project to be completed
```

---
### `unmark` - To mark a Task as incomplete

DukeAce will mark the task as complete.

Example of usage:

`unmark 1`

Expected outcome:

Task with order number 1 will be marked.

```
Aww, I've marked this task as not done yet:
[T][ ] Project to be completed
```