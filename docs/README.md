# User Guide

Duke is a chatbot that helps you keep track of tasks in the form of ToDo, Deadline and Event. 

## Features 

### Adding a new Task

#### `ToDo`

This adds a new ToDo task to the list.

Example Usage:

```
todo read book 
```
Expected Output 

```
  Got it. I just added the task: 
	[T][ ] read book 
	Now you have 1 tasks in the list.
```

#### `Deadline`

This adds a new Deadline task to the list 

Example Usage:

```
deadline ip /by 22/09/2022 2359
```

Expected Output 

```
  Got it. I just added the task: 
	[D][ ] ip (by: Sep 22 2022)
	Now you have 2 tasks in the list.
```

#### `Event`

This adds a new Event task to the list 

Example Usage:

```
event meeting /at 22/09/2022 1600
```

Expected Outcome

```
  Got it. I just added the task: 
	[E][ ] meeting (at: Sep 22 2022)
	Now you have 2 tasks in the list.
```

### Marking a task 

#### `Mark`

Marks a task as done in the task list 

Example Usage:

```
mark 1 
```
Expected Outcome

```
 Excellent! I have marked the task as done: 
 [T][X] read book 
```


#### `Unmark`

Marks a task in the task list as not done. 

Example Usage:

```
unmark 1 
```

Expected Outcome

```
 Excellent! I have marked the task as done: 
 [T][] read book 
```


### Deleting a Task: 

#### `Delete`

Deletes a task from the task list 

Example Usage:

```
delete 1 
```

Expected Outcome

```
 Noted. I've remove this task: 
	[T][X] read book 
	Now you have 2 tasks in the list.
```


### List 

List all the task in the task list 

Example Usage 

```
list
```

Expected Outcome

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

Example Usage:

```
find read
```

Expected Outcome:

```
[T][] read book 
```

### Exit the Program 

#### `Bye`

Terminates the execution of the program 

Example Usage

```
bye
```
Expected Outcome
```
Bye. Looking forward to chating with you soon again!
```


