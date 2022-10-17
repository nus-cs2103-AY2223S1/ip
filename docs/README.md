# User Guide

Duke is a chatbot that helps you keep track of tasks in the form of ToDo, Deadline and Event. 

## Features 

### Adding a new Task

#### `ToDo`

This adds a new ToDo task to the list.

Format:

`todo <DESCRIPTION>`

Example Usage:

```
todo read book 
```

Expected Output:

A new todo task is added to the task list.
```
  Got it. I just added the task: 
  [T][ ] read book 
  Now you have 1 tasks in the list.
```

#### `Deadline`

This adds a new Deadline task to the list 

Format: 
`deadline <DESCRIPTION> /by <TIME>`

Example Usage:

```
deadline ip /by 22/09/2022 2359
```

Expected Output:

A new deadline task is added to the task list.
```
  Got it. I just added the task: 
  [D][ ] ip (by: Sep 22 2022, 2359)
  Now you have 2 tasks in the list.
```

#### `Event`

This adds a new Event task to the list 


Format: 
`event <DESCRIPTION> /at <TIME>`

Example Usage:

```
event meeting /at 22/09/2022 1600
```

Expected Outcome:

A new event task is added to the task list.
```
  Got it. I just added the task: 
  [E][ ] meeting (at: Sep 22 2022, 2359)
  Now you have 2 tasks in the list.
```

### Marking a task 

#### `Mark`

Marks a task as done in the task list 

Format:

`mark <TASK_ID>`

Example Usage:

```
mark 1 
```
Expected Outcome:

If the first task exists in the task list it is marked as done.
```
 Excellent! I have marked the task as done: 
 [T][X] read book 
```


#### `Unmark`

Marks a task in the task list as not done. 

Format:

`unmark <TASK_ID>`

Example Usage:

```
unmark 1 
```

Expected Outcome:

If the first task exists in the task list it is marked as  not done.
```
 Noted! I have marked the task as not done yet: 
 [T][] read book 
```


### Deleting a Task: 

#### `Delete`

Deletes a task from the task list 

Format:

`delete <TASK_ID>`

Example Usage:

```
delete 1 
```

Expected Outcome:

If the first task exists in the list it is deleted. 
```
 Noted. I've remove this task: 
 [T][X] read book 
 Now you have 2 tasks in the list.
```

### List 

List all the task in the task list 

Format:

Example Usage 

```
list
```

Expected Outcome:

List all the task from the task list. 
```
  1. [E][ ] meeting (at: Sep 22 2022)
  2. [D][ ] ip (by: Sep 22 2022)
```


### Searching for a Task 

#### `Find`

Given a keyowrd, return all the tasks from the task list containing the keyword


Filling up the task list 
```
todo read book
todo write book 
todo understand book
```

Format:

`find <kKEYWORD>`

Example Usage:

```
find read
```

Expected Outcome:

All tasks which contain the word read are showm. 
```
[T][] read book 
```

### Help 

#### `help`

Display a list of all the commands in the application. 

Example Usage

```
help
```
Expected Outcome:

Shows the list of all the available commands. 

### Exit the Program 

#### `Bye`

Terminates the execution of the program 

Example Usage

```
bye
```
Expected Outcome:

Exits the application. 
```
Bye. Looking forward to chating with you soon again!
```


## Command Summary

|Action|Format|Example|
|:-|:-|:-|
|ToDo|`todo <DESCRIPTION>`|`todo Play Games`|
|Deadline|`deadline <DESCRIPTION> /by <TIME>`|`deadline project submission /by 19/10/2022 2359`|
|Event|`event <DESCRIPTION> /at <TIME>`|`event Go to Singapore Zoo /at 10/12/2022 1000`|
|Mark|`mark <TASK_ID>`|`mark 3`|
|Unmark|`unmark <TASK_ID>`|`unmark 4`|
|Delete|`delete <TASK_ID>`|`delete 1`|
|List|`list`|`list`|
|Find|`find <KEYWORD>`|`find read book`|
|Help|`help`|`help`|
|Exit|`bye`|`bye`|


