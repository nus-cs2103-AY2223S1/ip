# User Guide

Welcome to Duke! Duke manages your tasks and frees your mind of having to remember things you need to do. 

![UI](Ui.png)

## Features 
### [Add tasks](https://github.com/yilinzyl/ip/edit/master/docs/README.md#todo-description---add-a-todo-task)
Add Todo, Deadline or Event to Duke.

### [List tasks](https://github.com/yilinzyl/ip/edit/master/docs/README.md#list---list-all-tasks)
View all tasks that you have added.

### [Mark task](https://github.com/yilinzyl/ip/edit/master/docs/README.md#mark-index---mark-task-as-completed)
Mark a task as done when completed, or unmark it.

### [Delete task](https://github.com/yilinzyl/ip/edit/master/docs/README.md#delete-index---delete-task)
Delete a task when you no longer need it.

### [Find task](https://github.com/yilinzyl/ip/edit/master/docs/README.md#find-keyword---find-tasks-matching-keyword)
Find tasks that match your keyword

### [Sort tasks](https://github.com/yilinzyl/ip/edit/master/docs/README.md#sort---sort-tasks)
Sort deadline and/or events according to their dates

### [Exit](https://github.com/yilinzyl/ip/edit/master/docs/README.md#bye---exit-duke)
Not to worry, all data added are saved to the hard drive and will be automatically loaded the next time you use Duke!

## Usage

### `todo <description>` - Add a todo task

A todo task (without date and time) with the specified description will be added to Duke.

Example of usage: 

`todo study for CS2103T`

Expected outcome:

Todo task will be added and saved to hard drive.

```
Got it. I've added this task:
  [T][ ] study for CS2103T
Now you have 1 task in the list.
```

### `deadline <description> /by <datetime>` - Add a deadline

A deadline task with specified deadline and description will be added to Duke.  
Note that <datetime> has to be of format yyyy-MM-dd HHmm (eg. 2022-09-16 2359).

Example of usage: 

`deadline CS2103T ip /by 2022-09-16 2359`

Expected outcome:

Deadline task will be added and saved to hard drive.

```
Got it. I've added this task:
  [D][ ] CS2103T ip (by: Sep 16 2022 23:59)
Now you have 2 task in the list.
```

### `event <description> /at <datetime>` - Add an event

An event task with specified date that it will occur and description will be added to Duke.  
Note that <datetime> has to be of format yyyy-MM-dd HHmm (eg. 2022-09-17 1400).

Example of usage: 

`event CS2103T meeting /at 2022-09-17 1400`

Expected outcome:

Event task will be added and saved to hard drive.

```
Got it. I've added this task:
  [E][ ] CS2103T meeting (at: Sep 17 2022 14:00)
Now you have 3 task in the list.
```

### `list` - List all tasks

List all added tasks.

Example of usage: 

`list`

Expected outcome:

A list of all added tasks will be displayed in the order in which they are added.

```
1. [T][ ] study for CS2103T
2. [D][ ] CS2103T ip (by: Sep 16 2022 23:59)
3. [E][ ] CS2103T meeting (at: Sep 17 2022 14:00)
```

### `mark <index>` - Mark task as completed

Marks the task at specified index as completed.  
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3...

Example of usage: 

`mark 3`

Expected outcome:

The 3rd task in the task list is marked as completed.

```
Nice! I've marked this task as done:
 [E][X] CS2103T meeting (at: Sep 17 2022 14:00)
```

### `unmark <index>` - Unmark task

Unmarks the task at specified index.  
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3...

Example of usage: 

`unmark 3`

Expected outcome:

The 3rd task in the task list is unmarked.

```
OK, I've marked this task as not done yet:
 [E][ ] CS2103T meeting (at: Sep 17 2022 14:00)
```

### `delete <index>` - Delete task

Deletes the task at specified index.  
- The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3...

Example of usage: 

`delete 1`

Expected outcome:

The 1st task in the task list is deleted.

```
Noted. I've removed this task:
 [T][ ] study for CS2103T
Now you have 2 tasks in the list.
```

### `find <keyword>` - Find tasks matching keyword

Find all tasks with descriptions containing keyword.

Example of usage: 

`find ip`

Expected outcome:

The list of tasks with descriptions matching keyword is displayed.

```
Here are the matching tasks in your list:
1. [D][ ] CS2103T ip (by: Sep 16 2022 23:59)
```

### `sort` - Sort tasks

Sort all deadlines and/or events according to date

Example of usage: 

`sort`

Expected outcome:

The list of all events and deadlines sorted according to their dates is displayed.

```
1. [D][ ] CS2103T W6 quiz (by: Sep 16 2022 15:00)
2. [E][ ] CS2103T W6 lecture (at: Sep 16 2022 16:00)
3. [D][ ] CS2103T ip (by: Sep 16 2022 23:59)
4. [E][ ] CS2103T meeting (at: Sep 17 2022 14:00)
```
`sort deadline`

Expected outcome:

The list of all deadlines sorted according to their due dates is displayed.

```
1. [D][ ] CS2103T W6 quiz (by: Sep 16 2022 15:00)
2. [D][ ] CS2103T ip (by: Sep 16 2022 23:59)
```

`sort event`

Expected outcome:

The list of all events sorted according to their dates is displayed.

```
1. [E][ ] CS2103T W6 lecture (at: Sep 16 2022 16:00)
2. [E][ ] CS2103T meeting (at: Sep 17 2022 14:00)
```

### `bye` - exit Duke

Exit and close Duke application.  

Example of usage: 

`bye`

Expected outcome:

Duke application closes.

```
Bye. Hope to see you again soon!
```
