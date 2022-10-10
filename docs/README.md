# User Guide To Duke 
Duke is an app for managing and organising tasks. It is fun and easy to use! Download and use  it now!
## Features 

### Add tasks
Adds todo, event, deadline tasks to a task list 
### Delete tasks
Deletes tasks from a tasklist
### Mark tasks
Marks a task as done 
### Unmark tasks 
Unmarks a task as undone 
### Find tasks
Finds a task which includes the keyword written
### List tasks 
Displays tasks that are stored in the task list




## Guide on the usage of different commands 

### `Todo` 
Adds a Todo task to the task list.

**Example of usage:** 

`todo homework`

**Expected output**:

```
Got it. I've added the task:
[T][] homework 
Now you have 1 task in the list 
```
### `Deadline`
Adds a Todo task to the task list.

**Example of usage:**

`deadline assignment /by 2022-09-20`

**Expected output:**
```
Got it. I've added the task:
[D][] assignment (by Sep 20 2022)  
Now you have 2 task in the list. 
```
### `Event`
Adds a Todo task to the task list.

**Example of usage:**

`event meeting /at 3-5pm`

**Expected output**:

```
Got it. I've added the task:
[E][] meeting (at 3-5pm) 
Now you have 3 task in the list 
```
### `Delete `
Deletes a task to the task list.

**Example of usage:**

`delete 1`


**Expected output**:

```
Noted. I've removed this task: 
[T][] homework
Now you have 2 tasks in the list.
```

### `Mark `
Marks a completed task as done.

**Example of usage:**

`mark 2`


**Expected output**:

```
Nice! I've marked this task as done: 
[D][X] assignment (by Sep 20 2022)  
```

### `Unmark `
Unmarks a task as done.

**Example of usage:**

`unmark 1`


**Expected output**:

```
Ok. I've marked this task as not done yet: 
[D][] assignment (by Sep 20 2022)  
```


### `Find `
Finds tasks that contains the keyword inputted by user
and outputs all such tasks to the user.

**Example of usage:**

`find assignment`


**Expected output**:

```
1. [D][] assignment (by Sep 20 2022)  
```

### `List `
Display tasks that are stored in the task list. 

**Example of usage:**

`list`


**Expected output**:

```
1. [D][] assignment (by Sep 20 2022)  
2. [E][] meeting (at 3-5pm)
```


### `Bye `
Exit the program. 

**Example of usage:**

`bye`


**Expected output**:
```
Bye. Hope to see you again soon!
```
