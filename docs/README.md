# User Guide

Shiro Task Manager is a desktop app that remembers the tasks you need to do. It is friendly and definitely remembers your tasks clearly!

![Ui](D:\Files\NUS\03_Year2Sem1\02_CS2103T\03_Individual_Project\Duke\docs\Ui.png)

## Features 

### Create a task

You can create a task of the following types.

1. Todo: This task has a short description of the task.
2. Deadline: This task has a short description of the task, together with a due date.
3. Event: This task has a short description of the task, together with the event date.

### View all tasks

You can view all the tasks that you have stored.

### Mark or unmark a task

For each of the task, you can either set it to "Done" or "Not done".

### Delete a task

You can delete tasks that Shiro should forget.

### Snooze a task

You can postpone a deadline or event by a day.

### Find tasks

You can find a task by search for a keyword, or by specifying the date range of the task.



## Command Summary

> Words that are in \<CAPS\> are variables that needs to be filled in.

> <DATE_TIME> should follow the format of `yyyy-MM-dd HHmm` where
>
> * `y` stands for year
> * `M` stands for month
> * `d` stands for day
> * `H` stands for hour
> * `m` stands for minute

| Command    | Format                                   |
| ---------- | ---------------------------------------- |
| `todo`     | `todo <DESCRIPTION>`                     |
| `deadline` | `deadline <DESCRIPTION> /by <DATE_TIME>` |
| `event`    | `event <DESCRIPTION> /at <DATE_TIME>`    |
| `list`     | `list`                                   |
| `mark`     | `mark <INDEX>`                           |
| `unmark`   | `unmark <INDEX>`                         |
| `delete`   | `delete <INDEX>`                         |
| `snooze`   | `snooze <INDEX>`                         |
| `find`     | `find <KEYWORD>`                         |
| `between`  | `between <STARTING_DATE_TIME> <ENDING_DATE_TIME>` |
| `bye`      | `bye`                                    |



## Usage

### `todo` - Create a Todo task

> Creates a new Todo task with the given description.

Format: 

`todo <DESCRIPTION>`

Example:

`todo Buy a new headset` creates a todo task with the description "Buy a new headset".



### `deadline` - Create a Deadline task

Creates a new Deadline task with the given description and due date.

Format: 

`deadline <DESCRIPTION> /by <DATE_TIME>`

Example:

`deadline Submit assignment /by 2022-10-11 2359` creates a deadline task with the description "Submit assignment" due on 11 October 2022 at 11:59 pm.



### `event` - Create an Event

Creates a new Event with the given description and event date.

Format: 

`event <DESCRIPTION> /at <DATE_TIME>`

Example:

`event Attend carnival /at 2022-10-13 1200` creates an event with the description "Attend carnival" on 13 October 2022 at 12:00 pm.



### `list` - List all tasks

List all the tasks remembered by Shiro.

Format: 

`list`



### `mark` - Mark a task as "Done"

Marks a task as "Done" to show that it is completed.

Format: 

`mark <INDEX>`

Example:

`mark 1` marks the task at index 1 as "Done".



### `unmark` - Mark a task as "Not done"

Marks a task as "Not done" to show that it is not completed.

Format: 

`unmark <INDEX>`

Example:

`unmark 1` marks the task at index 1 as "Not done".



### `delete` - Deletes a task

Deletes a task so Shiro will not remember it anymore.

Format: 

`delete <INDEX>`

Example:

`mark 1` deletes the task at index 1.



### `snooze` - Snooze the task by a day

Push back the deadline or event date by 1 day.

Format: 

`snooze <INDEX>`

Example:

`deadline Submit assignment /by 2022-09-13 2359` followed by `snooze 1` will postpone the deadline from 13 September 2022, 11:59 pm to 14 September 2022, 11:59 pm.



### `find` - Finds the related tasks

Find tasks that contain the given keyword in their description.

Format: 

`find <KEYWORD>`

Example:

`todo essay on Singapore history` followed by `find history` will find the todo task since it contains the keyword `history`.



### `between` - Find tasks between two dates

Find tasks with their due date or event date occurring within the given date range.

Format: 

`between <STARTING_DATE_TIME> <ENDING_DATE_TIME>`

Example:

`between 2022-09-01 0000 2022-10-15 2359` will find all the tasks with a date-time between 1 September 2022, 12:00 am and 15 October 2022, 11:59 pm.



### `bye` - Exits the program

Says bye to Shiro and exits the program after 3 seconds.

Format: 

`bye`



theme: Cayman