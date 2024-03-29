# User Guide
SlaveDuke is your one and only ~~slave~~ personal assistant chatbot that manages your tasks for you!
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).
## Features 

### Track and Manage your tasks

Having lots of tasks to do, deadlines to finish, or events to attend my master?

Add your todo, deadlines, and events all to SlaveDuke and let it manage for you. Easily retrieve the full list and also
mark tasks as done / rank priority / delete tasks at any time. 
### Local Storage

Want to upgrade to an Alienware computer?

SlaveDuke saves all your tasks in a file stored in your hard disk drive so you can move the file to a new device 
and continue using SlaveDuke any time.
### Search your tasks
SlaveDuke can search tasks for you by keywords or by dates so you don't need to go through the whole list by yourself.

## Usage

### `todo` - Add a todo task
Syntax: `todo [description]`

Add a todo task to SlaveDuke.

Example of usage: `todo eat`

Expected outcome:

A todo task(represented by T) is added to SlaveDuke.

```
Got it. I've added this task:
[T][] eat
Now you have 1 task in your list.
```

### `deadline` - Add a deadline task
Syntax: `deadline [description] /by [date]`

Add a deadline task to SlaveDuke.

Example of usage: 
1. `deadline return book /by 2022-09-06`
2. `deadline return book /by 2022-09-06 2200`
3. `deadline return book /by Saturday`

Expected outcome:

A deadline task(represented by D) is added to SlaveDuke.

Example for usage 1:

```
Got it. I've added this task:
[D][] return book (by: Sep 6 2022)
Now you have 2 tasks in your list.
```
Example for usage 2:
```
Got it. I've added this task:
[D][] return book (by: Sep 6 2022 10:00PM)
Now you have 3 tasks in your list.
```
Example for usage 3: 
```
Got it. I've added this task:
[D][] return book (by: Saturday)
Now you have 4 tasks in your list.
```

### `event` - Add an event task
Syntax: `event [description] /at [date]`

Add an event task to SlaveDuke.

Example of usage:
1. `event graduation /at 2022-09-06`
2. `event graduation /at 2022-09-06 2200`
3. `event graduation /at Saturday`

Expected outcome:

An event task(represented by E) is added to SlaveDuke.

Example for usage 1:

```
Got it. I've added this task:
[E][] graduation (at: Sep 6 2022)
Now you have 5 tasks in your list.
```
Example for usage 2:
```
Got it. I've added this task:
[E][] graduation (at: Sep 6 2022 10:00PM)
Now you have 6 tasks in your list.
```
Example for usage 3:
```
Got it. I've added this task:
[E][] graduation (at: Saturday)
Now you have 7 tasks in your list.
```

### `list` - List added tasks
Syntax: `list`

List all the tasks added to SlaveDuke.

Example of usage: `list`

Expected outcome:

A list of all the tasks you added to SlaveDuke.

```
Here are the tasks in your list: 
1.[T][] eat
2.[D][] return book (by: Sep 6 2022)
3.[E][] graduation (at: Saturday)
```

### `delete` - Delete a task
Syntax: `Delete [task_number]`

Delete a task in SlaveDuke.

Example of usage: `delete 1`

Expected outcome:

The task specified is deleted from SlaveDuke.

```
Noted. I've removed this task:
[T][] eat
Now you have 2 tasks in the list.
```

### `mark` - Mark a task
Syntax: `mark [task_number]`

Mark a task as done in SlaveDuke.

Example of usage: `mark 1`

Expected outcome:

The task specified is marked with a X.

```
Nice! I've marked this task as done:
[T][X] eat
```

### `unmark` - Unmark a task
Syntax: `mark [task_number]`

Mark a task as undone in SlaveDuke.

Example of usage: `unmark 1`

Expected outcome:

The task specified is no longer marked with a X.

```
Ok! I've marked this task as not done yet:
[T][] eat
```

### `priority` - Rank task's priority
Syntax: `priority [priority_description]`

Rank a task's priority in SlaveDuke.

Example of usage: `priority 1 HIGH`

Expected outcome:

The task specified is ranked with a priority.

```
[T][] eat has been marked as HIGH priority.
```

### `find` - Find tasks by a keyword
Syntax: `find [keyword]`

Find all tasks in SlaveDuke that contains specified keyword.

Example of usage: 
1. `find hi`
2. `find eat`

Expected outcome:

A list with all tasks in SlaveDuke that contains specified keyword.

Example for usage 1:
```
There is no matching task in your list.
```
Example for usage 2:
```
Here are the matching tasks in your list:
1.[T][X] eat
2.[E][X] eat (at: Sep 6 2022)
3.[T][] eat 
```

### `date` - Find tasks by a date
Syntax: `date [date]`

Find all tasks in SlaveDuke that is on the specified date.

Example of usage:
1. `date 2002-09-06`
2. `date 2022-09-06`

Expected outcome:

A list with all tasks in SlaveDuke is on the specified date.

Example for usage 1:
```
YAY!You have no deadlines/events on this day.
```
Example for usage 2:
```
[D][] return book (by: Sep 6 2022)
[D][] return book (by: Sep 6 2022 10:00PM)
[E][] graduation (at: Sep 6 2022)
Shag man. You have 3 deadlines/events on this day. 
```
### `bye` - Exit from SlaveDuke
Syntax: `bye`

Exit the SlaveDuke GUI.

Expected outcome:

The SlaveDuke GUI closes and waits for your next launch!

