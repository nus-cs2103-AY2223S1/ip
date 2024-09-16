# Yuna User Guide

---
Yuna is a personalized chat bot that helps you keep track of your tasks, events and deadlines! It comes included 
with a gui to help you visualize the tasks better.

[![yunabot.png](https://i.postimg.cc/cHGHtzg2/yunabot.png)](https://postimg.cc/JyKm9PQ5)

## Features 

---

### Keeps track of your tasks

Yuna helps you to keep track of your tasks! You can view, add, delete, mark as complete and incomplete.
Manage your deadlines with Yuna!

### Supports date format

Yuna allows you to keep track of deadlines and events by tagging them with dates in the (yyyy-mm-dd) format.

### Search by date or keyword

Yuna allows you to search for tasks by their date or keyword.

---

## Usage

### `list` - Listing all tasks

Shows a list of all tasks.

Example of usage: 

`list`

Expected outcome:

[![yuna-list.png](https://i.postimg.cc/sXLTJpzk/yuna-list.png)](https://postimg.cc/N918s25D)

Yuna returns the list of tasks that you have.

```
Your schedule!
1. [T][] Homework
2. [E][] Football (at: Sat, 17 Sep 2022)
3. [D][] Assignment (by: Sun,18 Sep 2022)
```

### `todo` - Adding a to-do task

Adds a to-do type task to the list.

Example of usage:

`todo Homework`

Expected outcome:

[![yuna-todo.png](https://i.postimg.cc/fTyBrqy6/yuna-todo.png)](https://postimg.cc/XpMfZ8w8)

Yuna adds the to-do task to the list of tasks.

```
I've added this to your schedule!
[T][] Homework
You got 1 task to do :)
```

### `event` - Adding a event task

Adds an event type task to the list.

Format:

`event` event_name `/at` yyyy-mm-dd

Example of usage:

`event Football /at 2022-09-17`

Expected outcome:

[![yuna-event.png](https://i.postimg.cc/gk3KwxS2/yuna-event.png)](https://postimg.cc/KRvTw8h6)

Yuna adds the event task to the list of tasks.

```
I've added this to your schedule!
[E][] Football (at: Sat, 17 Sep 2022)
You got 2 tasks to do :)
```

### `deadline` - Adding a deadline task

Adds a deadline type task to the list.

Format:

`deadline` event_name `/by` yyyy-mm-dd

Example of usage:

`deadline Assignment /by 2022-09-18`

Expected outcome:

[![yuna-deadline.png](https://i.postimg.cc/rmprwbBT/yuna-deadline.png)](https://postimg.cc/VS3677sZ)

Yuna adds the deadline task to the list of tasks.

```
I've added this to your schedule!
[D][] Assignment (by: Sun, 18 Sep 2022)
You got 3 tasks to do :)
```

### `mark` - Marking a task 

Marks the task of that index as complete in the list.

Format:

`mark` index (The index is based on the position of the task in the list!)

Example of usage:

`mark 3`

Expected outcome:

[![yuna-mark.png](https://i.postimg.cc/NjJ2LYc0/yuna-mark.png)](https://postimg.cc/21WSXs3p)

Yuna marks the task as complete.

```
Good job for finishing this! ^^
[D][X] Assignment (by: Sun, 18 Sep 2022)
```

### `unmark` - Unmarking a task

Marks the task of that index as uncompleted in the list.

Format:

`unmark` index (The index is based on the position of the task in the list!)

Example of usage:

`unmark 3`

Expected outcome:

[![yuna-unmark.png](https://i.postimg.cc/m2hsBG8j/yuna-unmark.png)](https://postimg.cc/RNr8L2rJ)

Yuna marks the task as incomplete.

```
Unmarked!! You should finish this soon! >:(
[D][] Assignment (by: Sun, 18 Sep 2022)
```

### `on` - Finds a task using date

Shows a list of tasks on the said date.

Format:

`on` yyyy-mm-dd 

Example of usage:

`on 2022-09-17`

Expected outcome:

[![yuna-on.png](https://i.postimg.cc/WbcHfLxt/yuna-on.png)](https://postimg.cc/0rZnb3kq)

Yuna shows all the tasks on that date.

```
You have these on Sat,17 Sep 2022:
1.[E][] Football (at: Sat, 17 Sep 2022)
```

### `find` - Finds a task using keyword

Shows a list of tasks with the said keyword.

Format:

`find` keyword

Example of usage:

`find Football`

Expected outcome:

[![yuna-find.png](https://i.postimg.cc/RFr7V97H/yuna-find.png)](https://postimg.cc/nskj0ywF)

Yuna shows all the tasks with the keyword.

```
Here you go!
1.[E][] Football (at: Sat, 17 Sep 2022)
```

### `delete` - Deletes a task

Deletes the task of the specified index.

Format:

`delete` index (The index is based on the position of the task in the list!)

Example of usage:

`delete 1`

Expected outcome:

[![yuna-delete.png](https://i.postimg.cc/0NtNcCQ2/yuna-delete.png)](https://postimg.cc/3yv7wmmP)

Yuna deletes the task of that index.

```
I've taken this out from your schedule!
[T][] Homework
You got 2 tasks to do :)
```

### `bye` - Exits Yuna

This exits the bot, Yuna.

Example of usage:

`bye`

Expected outcome:

[![yuna-bye.png](https://i.postimg.cc/yNWKYC1s/yuna-bye.png)](https://postimg.cc/JywvPgH2)

The chat will be closed after seeing this message.

```
See you later :)
```