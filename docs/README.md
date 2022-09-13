# User Guide
This Duke chatbot allows you to keep track of your tasks! It is

**- Easy to use**
**- Efficient**
**- Super friendly too!**

Download the chatbot [here](https://github.com/KJunWei/ip) and try it now!

## Features 
#### `Bye` - Quits the application
#### `Deadline` - Adds a deadline task
#### `Delete` - Deletes task from the task list
#### `Event` - Adds an event task
#### `Find` - Finds task in task list
#### `List` - Lists out all the tasks
#### `Mark` - Marks task as complete
#### `Reminders` - Checks for upcoming deadlines
#### `Todo` - Adds a todo task
#### `Unmark` - Marks task as incomplete

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `bye` - Quits the application

*Example of usage*: 

`bye`

*Expected outcome*:

```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

### `Deadline` - Adds a deadline task

Formatting should be in the form "deadline __ /by yyyy-mm-dd".

*Example of usage*: 

`deadline sleep /by 2022-12-31`

*Expected outcome*:

Deadline task is added into the list of tasks!

```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] sleep (by: 2022-12-31)
     Now you have 1 task in the list.
    ____________________________________________________________
```

### `Delete` - Deletes task from the task list

Formatting should be in the form "delete x", where x is a non-negative integer.

*Example of usage*: (Assuming 1 task is already in the task list)

Assume the current tasks in the list are
```
     1.[D][ ] sleep (by: 2022-12-31)
```

`delete 1`

*Expected outcome*:

Task is deleted from the task list!

```
    ____________________________________________________________
     Noted. I've removed the task:
       [D][ ] sleep (by: 2022-12-31)
     Now you have 0 task in the list.
    ____________________________________________________________
```

### `Event` - Adds an event task

Formatting should be in the form "event __ /at yyyy-mm-dd".

*Example of usage*: 

`event party /at 2022-10-15`

*Expected outcome*:

Event task is added into the list of tasks!

```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] party (at: 2022-10-15)
     Now you have 1 task in the list.
    ____________________________________________________________
```

### `Find` - Finds task in task list

Searches tasks that contains a string (Could be a date too!)

*Example of usage*: 

Assume the current tasks in the list are
```
     1.[E][ ] party (at: 2022-10-15)
     2.[T][ ] run
     3.[D][ ] project (by: 2022-09-22)
```

`find t`

*Expected outcome*:

Returns filtered task list!

```
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[E][ ] party (at: 2022-10-15)
     2.[D][ ] project (by: 2022-09-22)
    ____________________________________________________________
```

### `List` - Lists out all the tasks

*Example of usage*: 

`list`

*Expected outcome*:

Shows all the task in the task list!

```
    ____________________________________________________________
     Here are the task(s) in your list:
     1.[E][ ] party (at: 2022-10-15)
     2.[T][ ] run
     3.[D][ ] project (by: 2022-09-22)
    ____________________________________________________________
```

### `Mark` - Marks task as complete

Formatting should be in the form "mark x", where x is a non-negative integer.

*Example of usage*: 

Assume the current tasks in the list are
```
     1.[E][ ] party (at: 2022-10-15)
     2.[T][ ] run
     3.[D][ ] project (by: 2022-09-22)
```

`mark 1`

*Expected outcome*:

Marks the first task as completed!

```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [E][X] party (at: 2022-10-15)
    ____________________________________________________________
```

### `Reminders` - Checks for upcoming deadlines

Shows the deadlines that have not been marked as completed yet, even if the date has passed

*Example of usage*: 

Assume the current tasks in the list are
```
     1.[D][X] grocery (by: 2022-09-15)
     2.[T][ ] run
     3.[D][ ] project (by: 2022-09-22)
```

`reminders`

*Expected outcome*:

1 Deadline task is shown!

```
    ____________________________________________________________
     Here are the uncompleted tasks in your list:
     1.[D][ ] project (by: 2022-09-22)
    ____________________________________________________________
```

### `Todo` - Adds a todo task

Formatting should be in the form "todo __".

*Example of usage*: 

`todo wash my car`

*Expected outcome*:

Deadline task is added into the list of tasks!

```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] wash my car
     Now you have 1 task in the list.
    ____________________________________________________________
```

### `Unmark` - Marks task as incomplete

Formatting should be in the form "unmark x", where x is a non-negative integer.

*Example of usage*: 

Assume the current tasks in the list are
```
     1.[E][x] party (at: 2022-10-15)
     2.[T][x] run
     3.[D][x] project (by: 2022-09-22)
```

`unmark 2`

*Expected outcome*:

Marks the second task as not completed!

```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [T][ ] run
    ____________________________________________________________
```
