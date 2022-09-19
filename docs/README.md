# User Guide to us Duke

## What is Duke?

Duke is a chat bot designed to help users manage their tasks. It is a command-line
software that processes user input and displays immediate responses. Furthermore, 
tasks added by users to Duke will be stored and can be retrieved each 
time Duke is opened.

## Features 

### Add Tasks

Tasks can be added to the list of tasks via one of the following categories:
1. To-do task
2. Deadline task
3. Event task

### View Tasks
Tasks can be viewed as a list.

### Mark Tasks
Tasks can be marked as completed.

### Un-mark Tasks
Tasks can be un-marked to show they are  still incomplete.

### Delete tasks
Tasks can be deleted from the list of tasks.

### Find tasks
Tasks can be found from the list of tasks.

## Features Details

### `todo` - Adds a to-do task

**Adds a to-do task to the list of tasks.**

**Format:** `todo TASK_DESCRIPTION`

- The task description **must not** be empty.

**Example of usage:**
`todo Physics homework`

**Expected outcome:**
A to-do task will be successfully added to the list of tasks.

```
______________________________________________________
Got it. I've added this task:
[T][] Physics homework
Now you have 1 tasks in the list.
______________________________________________________
```
<br>
    
### `event` - Adds an event task

**Adds an event task to the list of tasks.**

**Format**: `event TASK_DESCRIPTION /at TIME_OR_PLACE`

- the task description **must not** be empty.
- the time or place **must not** be empty.

**Example of usage:**
`event Sara's birthday /at office`


**Expected outcome:**
An event task will be successfully added to the list of tasks.

```
______________________________________________________
Got it. I've added this task:
[E][] Sara's birthday (at: restaurant)
Now you have 1 tasks in the list.
______________________________________________________
```
<br>

### `deadline` - Adds a deadline task

**Adds a deadline task to the list of tasks.**

**Format:** `deadline TASK_DESCRIPTION /by DATE`

- The task description **must not** be empty.
- The date **must adhere** to the YYYY-MM-DD format.

**Example of usage:**
`deadline Physics assignment /by 2022-10-11`

**Expected outcome:**
A deadline task will be successfully added to the list of tasks.

```
______________________________________________________
Got it. I've added this task:
[D][] Physics assignment (by: Oct 11 2022)
Now you have 1 tasks in the list.
______________________________________________________
```
<br>


### `list` - displays all tasks as a list

**Allows user to view tasks as a list.**

**Example of usage:**
`list`

**Expected outcome:**
A list of all the user's tasks will be shown.

```
______________________________________________________
Here are the tasks in your list:
1. [T][] Physics homework
2. [E][] Sara's birthday (at: restaurant)
3. [D][] Physics assignment (by: Oct 11 2022)
______________________________________________________
```
<br>


### `mark` - marks a task as complete

**Marks a task in the list of tasks according to the specified index to suggest 
the task is completed.**

**Format:** `mark TASK_INDEX`

- Task index **must not** exceed the number of total tasks and must be more than 0. 

**Example of usage:**
`mark 1`

**Expected outcome:**
The first task in the list of tasks will be marked as completed.

```
______________________________________________________
Nice! I've marked this task as done:
[T][X] Physics homework
______________________________________________________
```
<br>


### `unmark` - un-marks a task to show it is still incomplete

**Un-marks a task in the list of tasks according to the specified index to suggest
that the task is still incomplete.**

**Format:** `unmark TASK_INDEX`

- Task index **must not** exceed the number of total tasks and must be more than 0.
  
**Example of usage:**
`unmark 1`

**Expected outcome:**
The first task in the list of tasks will be unmarked.

```
______________________________________________________
OK. I've marked this task as not done yet:
[T][] Physics homework
______________________________________________________
```
<br>


### `delete` - deletes a task from the list of tasks

**Deletes a task in the list of tasks according to the specified index.**

**Format:** `delete TASK_INDEX`

- Task index **must not** exceed the number of total tasks and must be more than 0.

**Example of usage:**
`delete 1`

**Expected outcome:**
The first task in the list of tasks will be deleted.

```
______________________________________________________
Noted. I've removed this task:  
[T][] Physics homework
Now you have 0 tasks in the list.
______________________________________________________
```
<br>


## `find` - finds a task from the list of tasks

**Finds a task in the list of tasks with a keyword corresponding to the 
description of the task.**

**Format:** `find KEYWORD`

- If no task descriptions matching the keyword are found, the list of tasks returned
will be empty.

**Example of usage:**
`find homework`

**Expected outcome:**
A list of tasks with descriptions matching the keyword `homework`.

```
______________________________________________________
Here are the matching tasks in your list: 
[T][] Physics homework
______________________________________________________
```
<br>


## `help` - displays commands to use Duke

**Displays basic commands to help the user use basic features of Duke.**

**Example of usage:**
`help`

**Expected outcome:**
A list of basic commands to utilise Duke.

```
______________________________________________________
Hello! Here is some commands to help you use this app better!

1. bye:
Ends the session and app will close

2. todo {task description}:
Adds a to-do task to your list of tasks

3. event {task description} /at {time or place}:
Adds a event task to your list of tasks

4. deadline {task description} /by {date time in YYYY-MM-DD}:
Adds a deadline task to your list of tasks

5. list:
Returns all tasks in the task list

6. help!:
More advanced Duke features!
______________________________________________________
```
<br>


## `help!` - displaying advanced commands to use Duke

**Displays advanced commands to help the user use advanced features of Duke.**

**Example of usage**:
`help!`

**Expected outcome**:
A list of advanced commands to utilise Duke.

```
______________________________________________________
More advanced commands here!

1. mark {task number}:
marks the task with index corresponding to the task number as done

2. unmark {task number}:
un-marks the task with index corresponding to the task number as done

3. delete {task number}:
deletes the task with index corresponding to the task number

4. find {keyword}:
find the task with description corresponding to the keyword input
______________________________________________________
```
<br>

## `bye` - exits Duke

**Exits the Duke application.**

**Example of usage:**
`bye`

**Expected outcome:**
A bye message is displayed and the application closes.
```
______________________________________________________
Bye! I'll be closing soon, till we meet again!
______________________________________________________
```

