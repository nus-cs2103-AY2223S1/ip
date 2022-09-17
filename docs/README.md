# User Guide for SoCCat

## About
SoCCat is your very own Personal Assistant Chatbot that helps to keep track of various
things. Never forget another task again!

## Features
### List
View all your tasks in a single glance!

### Add tasks
You can add up to 3 different type of tasks!
- ToDos
- Deadlines
- Events

### Delete tasks
Delete any task that is not of use anymore.

### Mark and unmark tasks
Easily mark or unmark a task as completed or not completed yet!

### Find
Search any keywords and all the tasks associated with it will appear.

### Undo
Undo any accidental command you make.

## Usage
### `list` - List all the tasks in the task list.

Format:
list (no arguments)

Example of usage: 

`list`

Expected outcome:
- A todo as displayed by [T] will be added to your task list.
- Another empty bracket will appear beside it which indicates its completion status.


```
Here are all the tasks in your list:
1.[T][] borrow books
2.[D][] return book (by: Dec 02 2019 06:30PM)
3.[E][] attend meeting (by: Dec 02 2019 06:30PM)
```

### `todo` - Adds a todo task with a description to the task list.

Format:
todo <description>
  
Example of usage: 

`todo borrow books`

Expected outcome:
- A todo as displayed by [T] will be added to your task list.
- Another empty bracket will appear beside it which indicates its completion status.


```
Got it. I've added this task:
[T][] borrow book
Now you have 1 task in your list.
```

### `deadline` - Adds a deadline task to the task list with a description and date.

Format:
deadline <description> /by <date>
  
Example of usage: 

`deadline return book /by 02/12/2019 18:30`

Expected outcome:
- A deadline as displayed by [D] will be added to your task list.
- Another empty bracket will appear beside it which indicates its completion status.


```
Got it. I've added this task:
[D][] return book (by: Dec 02 2019 06:30PM)
Now you have 2 tasks in your list.
```

### `event` - Adds a event task to the task list with a description and date.
  
Format:
event <description> /at <date>
  
Example of usage: 

`event attend meeting /at 02/12/2019 18:30`

Expected outcome:
- A event as displayed by [E] will be added to your task list.
- Another empty bracket will appear beside it which indicates its completion status.


```
Got it. I've added this task:
[E][] attend meeting (by: Dec 02 2019 06:30PM)
Now you have 3 tasks in your list.
```
  
### `delete` - Deletes the task that corresponds to the index from the task list.
  
Format:
delete <number>
  
Example of usage: 

`delete 1`

Expected outcome:
- The task that was in task list previously will not appear anymore.


```
Noted. I've removed this task:
[T][] borrow book
Now you have 2 tasks in your list.
```

### `mark` - Marks a task in the task list.
  
Format:
mark <number>
  
Example of usage: 

`mark 1`

Expected outcome:
- The task that is marked will have its status bracked marked as [X] instead of [].

```
Nice! I've marked this task as done:
[T][X] borrow book
```

### `unmark` - Unmarks a task in the task list.
  
Format:
unmark <number>
  
Example of usage: 

`unmark 1`

Expected outcome:
- The task that is unmarked will have its status bracked marked as [] instead of [X].

```
Ok! I've marked this task as not done yet:
[T][] borrow book
```
  
### `find` - Find all tasks that corresponds to the given search term in the task list.
  
Format:
find <description>
  
Example of usage: 

`find books`

Expected outcome:
- All tasks that consist of books in the task description will be shown to the user.

```
Here are the matching tasks in your list:
[T][] borrow English books
[T][] borrow Chinese books
```

### `undo` - Undo the most recent command
  
Format:
undo (no arguments)
  
Example of usage: 

`undo`

Expected outcome:
- The last command that is executed will be undid.

```
I have used my magical powers and undid this task:
[T][] borrow book
Now you have 5 tasks in your list.
```

### `bye` - Say goodbye to SoCCat and exit application.
  
Format:
bye (no arguments)
  
Example of usage: 

`bye`

Expected outcome:
- SoCCat will say goodbye and application will close.

```
Goodbye! SoCCat wishes to see you again soon!
```
