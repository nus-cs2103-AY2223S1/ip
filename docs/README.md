# User Guide
---
Hi! Doomba is your personal chatbot that tracks all the tasks that you plan on doing

## Features 

- Different types of tasks to add
  - ToDos
  - Deadlines
  - Events
  - Recurring Tasks

- Multiple commands
  - add
  - delete
  - mark
  - unmark
  - list
  - help
  - bye

- Wrong command handling

- Save your tasks

- Bot with a personality

### Types of Tasks

- ToDo: Just a simple task.
- Deadline: For tasks that needs to be done by a certain date.
- Event: For tasks happening at a particular time.
- Recurring Tasks: For tasks that occurs daily, weekly, monthly or yearly.

### Saving tasks

Your data won't be gone when you exit the program as you can save your tasks.
No need to key in everything again!

## Usage
[ ] - for arguments with description/format specified  
< > - for optional arguments

<br/>

### `todo` - Add ToDo into list  

**Response:**  
Doomba will reply with a confirmation that the todo has been added, along with the task type [T], whether the task has been marked done or not [ ]/[X], the task description, and how many tasks you have in the list.

**Command:** `todo [description]`



**Input:** `todo buy groceries`

**Output:**
```
added: [T][ ] buy groceries
You currently have 1 tasks in the list
```

<br/>

### `deadline` - Add deadline into list:
**Response:**  
Doomba will reply with a confirmation that the deadline has been added, along with the task type [D], whether the task has been marked done or not [ ]/[X], the task description, and how many tasks you have in the list.

**Command:** `deadline [ description ] /by [ date ] < time >`  
- date is in dd/MM/yy format
- time is in HHmm format



**Input:** `deadline submit project /by 06/09/22 2359` - _with optional time argument_

**Output:**
```
added: [D][ ] submit project (by: 06 Sep 22 11:59PM)
You currently have 2 tasks in the list
```

<br/>

### `event` - Add event into list:
**Response:**  
Doomba will reply with a confirmation that the event has been added, along with the task type [E], whether the task has been marked done or not [ ]/[X], the task description, and how many tasks you have in the list.

**Command:** `event [ description ] /at [ dd/MM/yy ] < time >`
- date is in dd/MM/yy format
- time is in HHmm format

**Input:** `event Alec Benjamin Concert /at 04/12/22` - without optional time argument

**Output:**
```
added: [E][ ] Alec Benjamin Concert (at: 04 Dec 22)
You currently have 3 tasks in your list
```

<br/>

### `recurring` - Add recurring task into list:
**Response:**  
Doomba will reply with a confirmation that the recurring task has been added, along with the task type [R], whether the task has been marked done or not [ ]/[X], the task description, and how many tasks you have in the list.

**Command:** `recurring [ description ] /every [ frequency ] </at time> *[ number of times ]`

| Frequency | Format |
|---|---|
| Yearly | dd/MM/yy |
| Monthly | dd (from 1-31) |
| Weekly | 3 letter day of the week, first letter capitalized |
| Daily | HHmm (optional time argument not applicable) |  
If optional time argument is not provided, the default time would be 12:00PM.

**Input:** `recurring go climbing /every Wed /at 1330 *8` - _with optional time argument_

**Output:**
```
added: [R][ ] go climbing (next: 14 Sep 22 1:30PM; remaining: 8)
You currently have 4 tasks in the list
```

<br/>

### `list` - Shows all tasks currently in list:
**Response:**  
Doomba will display all tasks in the list, sorted by order added. Index for each task and total number of tasks in the list will also be shown.

**Command:** `list`

**Input:** `list`

**Output:**
```
Here are your plans:
    1.[T][ ] buy groceries
    2.[D][ ] submit project (by: 06 Sep 22 11:59PM)
    3.[E][ ] Alec Benjamin Concert (at: 04 Dec 22)
    4.[R][ ] go climbing (next: 14 Sep 22 1:30PM; remaining: 8)
You currently have 4 tasks in the list
Pls don't procrastinate on the above tasks!
```

<br/>

### `delete` - Delete a task from the list:
**Response:**  
Doomba will reply with a confirmation, along with the task details.

**Command:** `delete [ index ]`

**Input:** `delete 2`

**Output:**
```
Ok! I've deleted this task:
    [D][ ] submit project (by: 06 Sep 22 11:59PM)
```

<br/>

### `mark` - Mark a task as done:
**Response:**  
Doomba will reply with a confirmation, along with the task details.  
[X] - task is done  

**Command:** `mark [ index ]`

**Input:** `mark 1`

**Output:**
```
nice! I've marked this task as done:
    [T][X] buy groceries
```

<br/>

### `unmark` - Mark a task as not done
**Response:**  
Doomba will reply with a confirmation, along with the task details.  
[ ] - task is not done

**Command:** `unmark [ index ]`

**Input:** `unmark 1`

**Output:**
```
Ok! I've marked this task as not done yet:
    [T][ ] buy groceries
```

<br/>

### `remaining` - Shows the date and time when a particular recurring task occurs
**Response:**  
Doomba will display a list of the past and future dates and timing

**Command:** `remaining [ index ]`

**Input:** `remaining 3`

**Output:**
```
Dates for the task: go climbing

14 Sep 22 1:30PM
21 Sep 22 1:30PM
28 Sep 22 1:30PM
05 Oct 22 1:30PM
12 Oct 22 1:30PM
19 Oct 22 1:30PM
26 Oct 22 1:30PM
02 Nov 22 1:30PM
```

<br/>

### `find` - Search for tasks containing the input keyword.
**Response:**  
Doomba will display a list of all tasks that contains the keyword

**Command:** `find [keyword]`

**Input:** `find buy`

**Output:**
```
I have found the following tasks containing 'buy'

    1.[T][ ] buy groceries
```

<br/>

### `/?` - Show help
**Response:**  
Doomba will show the list of all commands that a user can use.

**Input:** `/?`

<br/>

### `bye` - Saves tasks to file
**Response:**  
Doomba will reply with a goodbye message.

**Input:** `bye`

**Output:**
```
Bye! Hope that I was of service!
```

<br/>
