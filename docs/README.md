# User Guide

## Features

### 1. Feature - List tasks

Displays all your tasks.

### 2. Feature - Add todo

Adds a todo to your list of tasks.

### 3. Feature - Add deadline

Adds a deadline to your list of tasks.

### 4. Feature - Add event

Adds an event to your list of tasks.

### 5. Feature - Mark Done

Indicates a task as done.

### 6. Feature - Unmark Done

Indicates a task as not done.

### 7. Feature - Delete task

Deletes a task from your list of tasks.

### 8. Feature - Find task

Finds all the tasks containing the specified keyword.

### 9. Feature - Undo action

Reverses the last modifying action performed.

### 10. Feature - Quit chatbot

Quits the chatbot.

## Usage

### 1. `list` - Displays all your tasks

Entering "list" displays all your tasks.

Example of usage:

`list`

Expected outcome:

Displays all tasks.

```
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (at: Mon 2-4pm)
```

### 2. `todo` - Adds a todo to your task list

Entering "todo <task name>" adds the todo to your task list.

Example of usage:

`todo borrow book`

Expected outcome:

Adds the todo to task list.

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task in the list.
```

### 3. `deadline` - Adds a deadline to your task list

Entering "deadline <task name> /by yyyy-mm-dd" adds the deadline to your task list.

Example of usage:

`deadline return book /by 2022-03-21`

Expected outcome:

Adds the deadline to task list.

```
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
```

### 4. `event` - Adds an event to your task list

Entering "event <task name> /at yyyy-mm-dd" adds the event to your task list.

Example of usage:

`event project meeting /at 2021-10-15`

Expected outcome:

Adds the event to task list.

```
Got it. I've added this task:
[E][ ] project meeting (at: Mon 2-4pm)
Now you have 3 tasks in the list.
```

### 5. `mark` - Marks a task as done

Entering "mark <index>" puts an X for task at that index.

Example of usage:

`mark 1`

Expected outcome:

Marks the task as done.

```
Nice! I've marked this task as done:
[X] borrow book
```

### 6. `unmark` - Marks a task as done

Entering "unmark <index>" removes the X for task at that index.

Example of usage:

`unmark 1`

Expected outcome:

Unmarks the task as done.

```
Ok, I've marked this task as not done yet:
[ ] borrow book
```

### 7. `delete` - Deletes a task from your task list

Entering "delete <index>" deletes the task at that index.

Example of usage:

`delete 3`

Expected outcome:

Deletes the task from task list.

```
Noted. I've removed this task:
[D][] project meeting
Now you have 2 tasks in the list. 
```

### 8. `find` - Finds all tasks with keyword in your task list

Entering "find <keyword>" finds all task containing <keyword> in your task list.

Example of usage:

`find book`

Expected outcome:

Finds all corresponding tasks.

```
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
```

### 9. `undo` - Reverses the last modifying command

Entering "undo" reverses the last modifying command.

Example of usage:

`undo`

Expected outcome:

Reverses the last modifying command.

```
Noted. I've removed this task:
[T][ ] do laundry
Now you have 3 tasks in the list.
```

### 10. `bye` - Quits the chatbot

Entering "bye" quits the chatbot.

Example of usage:

`bye`

Expected outcome:

Displays quit message.

```
Bye. Hope to see you again soon!
```