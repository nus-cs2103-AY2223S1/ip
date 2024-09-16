# User Guide
Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
>The name Duke was chosen as a placeholder name, in honor of [Duke, the Java Mascot.](https://www.oracle.com/java/duke/)

## Features 


>***Notes about command format***
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo Homework`.
  
* Date formats must be ***exactly*** as stated.
* Commands are case-sensitive


#### [Closing chatbot - `bye`](#bye---closing-chatbot)
#### [Listing all tasks - `list`](#list---listing-all-tasks)
#### [Marking a task - `mark`](#mark---marking-a-task)
#### [Unmarking a task - `unmark`](#unmark---unmarking-a-task)
#### [Delete a task - `delete`](#delete---delete-a-task)
#### [Find a task - `find`](#find---find-a-task)
#### [Add a "todo" task - `todo`](#todo---add-a-todo-task)
#### [Add a "deadline" task - `deadline`](#deadline----add-a-deadline-task)
#### [Add a "event" task - `event`](#event----add-a-event-task)

## Usage

### `bye` - Closing chatbot

Closes chatbot window and saves the current tasks.

Format: `bye`



### `list` - Listing all tasks

Lists all current tasks with the respective descriptions and time.

Format: `list`



### `mark` - Marking a task

Marks a specified task as done.

Format: `mark INDEX`

Example of usage: 

`list` followed by `mark 1` to mark the first task as done.



### `unmark` - Unmarking a task

Unmarks a specified task as undone.

Format: `unmark INDEX`

Example of usage: 

after `mark 1` , `unmark 1` can be used to unmark the task 1.



### `delete` - Delete a task

Delete a task from the list of tasks to no longer keep track of it.

Format: `delete INDEX`

Example of usage: 

after `mark 1`, `delete 1` can be used to no longer track a finished task.



### `find` - Find a task

Search through the list of tasks to find all tasks matching the provided **keyword**

Format: `find KEYWORD`

Example of usage: 

`find book` can be used to find all tasks related to books.



### `todo` - Add a "todo" task

Add a simple task with no deadline or timeline.

Format: `todo DESCRIPTION`

Example of usage: 

`todo buy food` can be used to add a task with unspecified deadlines/timelines



### `deadline` -  Add a "deadline" task

Adds a task with a deadline (Date & Time)

Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:MM`

Example of usage: 

`deadline Finish homework /by 2020-10-10 12:00` to add a task with a deadline of specified dead and time



### `event` -  Add a "event" task

Add a task with a timeline (Start - End Date & Time)

Format: `event DESCRIPTION /at YYYY-MM-DD HH:MM YYYY-MM-DD HH:MM`
>Start date/time followed by end date/time

Example of usage: 

`event Attend school /at 2020-05-05 08:00 2020-08-05 08:00`

