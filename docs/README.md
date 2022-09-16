# User Guide

## Features 

### Add tasks

The Duke bot allows  the user to add 3 types of tasks: ToDo, Deadline and Event.

A ToDo task requires the description of the activity.

A Deadline task requires the description, due date and time of the activity.

An Event task requires the description, start date and time of the activity.

### Edit task status

The user is able to change the status of a task by marking the task as done or unchecking it as not done.

### Set priority for tasks

The user can set a priority for each task: High, Medium or Low.

### Delete tasks

The user can delete tasks from the task list.

### Find tasks

The user can find tasks by searching for a keyword.

### List tasks

The user can see the list of tasks that he/she has.

## Usage

### `todo` - Add a ToDo task

Typing "todo" with the description of the task will add a ToDo task to the list.

Command format:

`todo <description>`

Example of usage:

`todo study`

Expected outcome:

```
Got it. I've added this task:
 [T][ ][ ] study
Now you have 1 task(s) in the list.
```
Description of the outcome.

```
A ToDo task has been created and added to the list.
```


### `deadline` - Add a Deadline task

Typing "deadline" with the description, followed by "/by", and then the due date and time of the
task will create the deadline task.

The due date must be typed in "YYYY-MM-DD" format or "DD/MM/YYYY" format whereas the time must be typed
24-hour format.

Command format:

`deadline <description> /by <date> <time>`

Example of usage:

`deadline homework /by 2/8/2022 1500`

Expected outcome:

```
Got it. I've added this task:
 [D][ ][ ] homework (by: Oct 02 2022 15:00)
Now you have 2 task(s) in the list.
```

Description of the outcome.

```
A Deadline task has been created and added to the list.
```

### `event` - Add a Event task

Typing "event" with the description, followed by "/at", and then the start date and time of the
task will create the event task.

The start date must be typed in "YYYY-MM-DD" format or "DD/MM/YYYY" format whereas the time must be typed
24-hour format.

Command format:

`event <description> /at <date> <time>`

Example of usage:

`event match /at 12/10/2022 1300`

Expected outcome:

```
Got it. I've added this task:
 [E][ ][ ] match (at: Oct 12 2022 13:00)
Now you have 3 task(s) in the list.
```

Description of the outcome.

```
A Event task has been created and added to the list.
```

### `list` - Shows the list of all tasks

Typing "list" into the text field commands the Duke bot to show the user the list of all tasks.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ][ ] study
2.[D][ ][ ] homework (by: Oct 02 2022 15:00)
3.[E][ ][ ] match (at: Oct 12 2022 13:00)
```

Description of the outcome.

```
The Duke bot shows the list of all tasks.

The format is as shown:
[Type][Status][Priority] description (due date and time/ start date and time)

First set of brackets:
[T] - ToDo task
[D] - Deadline task
[E] - Event task

Second set of brackets:
[X] - Marked as done
[ ] - Not done yet

Third set of brackets:
[L] - Low priority
[M] - Medium priority
[H] - High priority

by: - due date and time (Deadline tasks)
at: - start date and time (Event tasks)
```

### `mark` - Marks the task as done

Typing "mark" and the task number into the text field commands the Duke bot
to mark the task as done.

Command format:

`mark <task number>`

Example of usage:

`mark 3`

Expected outcome:

```
Nice! I've marked this task as done:
 [E][X][ ] match (at: Oct 12 2022 13:00)
```

Description of the outcome.

```
Task number 3's status is marked as done, as can be seen from [X].
```
### `unmark` - Unmarks the task as not done

Typing "unmark" and the task number into the text field commands the Duke bot
to unmark the task as not done.

Command format:

`unmark <task number>`

Example of usage:

`unmark 3`

Expected outcome:

```
OK, I've marked this task as not done yet:
 [E][ ][ ] match (at: Oct 12 2022 13:00)
```

Description of the outcome.

```
Task number 3's status is unmarked as not done, as can be seen from [ ].
```

### `high` - Marks the task as "High" priority

Typing "high" and the task number into the text field commands the Duke bot
to mark the task's priority as "high".

Command format:

`high <task number>`

Example of usage:

`high 3`

Expected outcome:

```
Nice! I've set this task as high priority:
 [E][ ][H] match (at: Oct 12 2022 13:00)
```

Description of the outcome.

```
Task number 3's priority is marked as high, as can be seen from [H].
```
### `medium` - Marks the task as "Medium" priority

Typing "medium" and the task number into the text field commands the Duke bot
to mark the task's priority as "medium".

Command format:

`medium <task number>`

Example of usage:

`medium 1`

Expected outcome:

```
Nice! I've set this task as high medium priority:
 [T][ ][M] study
```

Description of the outcome.

```
Task number 1's priority is marked as medium, as can be seen from [M].
```

### `low` - Marks the task as "Low" priority

Typing "low" and the task number into the text field commands the Duke bot
to mark the task's priority as "low".

Command format:

`low <task number>`

Example of usage:

`low 2`

Expected outcome:

```
Nice! I've set this task as low priority:
 [D][ ][L] homework (by: Oct 02 2022 15:00)
```

Description of the outcome.

```
Task number 2's priority is marked as low, as can be seen from [L].
```

### `delete` - Deletes the task

Typing "delete" and the task number into the text field commands the Duke bot
to delete that particular task.

Command format:

`delete <task number>`

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
 [E][ ][ ] match (at: Oct 12 2022 13:00)
Now you have 2 tasks in the list.
```

Description of the outcome.

```
Task number 2 is deleted from the list of tasks.
```

### `find` - Find tasks with the keyword

Typing "find" and the keyword commands the Duke bot to show the list of tasks containing that keyword.

Command format:

`find <keyword>`

Example of usage:

`find study`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][ ][M] study
```

Description of the outcome.

```
All of the tasks with the keyword "study" will be shown.
```

### `bye` - Exit the program

Typing "bye" will cause the program to exit after a few seconds.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

Description of the outcome.

```
The Duke bot bids goodbye to the user.
```

