# User Guide to Duke Bot

## Features 

### Add tasks
Adds the various kinds of tasks to the Duke Bot. These include
1. ToDo tasks 
2. Deadline tasks 
3. Event tasks

### Delete tasks

Removes the task from the duke bot

### List tasks

Lists out the tasks in the duke bot

### Mark tasks

Marks tasks in the duke bot which are completed

### Unmark tasks

Unmarks tasks in the duke bot which have been previously marked

### Find tasks

Searches for task which contains the keyword specified

### Undo commands

Undoes any previous command made by the user 



## Guide on how to use various commands

### `ToDo` 

**Adds a Todo task to the Duke bot.**

**Sample input** : 

`todo running`

**Expected reply from Duke** :

```
Nice! This task has been successfully added!
        [T][] running
You currently have 3 tasks in the list
```

---
### `Deadline`

**Adds a Deadline task to the Duke bot. Note that the format for date is
DD-MM-YYYY.**


**Sample input** :

`deadline submit homework /by 14-09-2022`

**Expected reply from Duke** :

```
Nice! This task has been successfully added!
        [D][] submit homework (by: Sep 14 2022)
You currently have 4 tasks in the list
```

---

### `Event`

**Adds an Event task to the Duke bot. Note that the format for date is
DD-MM-YYYY.**


**Sample input** :

`event attend meeting /at 14-09-2022`

**Expected reply from Duke** :

```
Nice! This task has been successfully added!
        [E][] attend meeting (at: Sep 14 2022)
You currently have 5 tasks in the list
```
---

### `Delete`
**Deletes a task which has been added into Duke bot based on position given.**

**Sample input** :

`delete 1`

**Expected reply from Duke** :

```
Noted! This task has been successfully removed!
        [T][] running
You currently have 2 tasks in the list
```
---

### `List`
**List the tasks which have been added to the Duke bot.**

In this example,
1. [D][] submit homework (by: Sep 14 2022)
2. [E][] attend meeting (at: Sep 14 2022)
3. [T][] running

are currently stored in DukeBot

**Sample input** :

`list`

**Expected reply from Duke** :

```
1. [D][] submit homework (by: Sep 14 2022)
2. [E][] attend meeting (at: Sep 14 2022)
3. [T][] running
```
---

### `Mark`

**Marks a task in the Duke bot based on the position given.**


**Sample input** :

`mark 3`

**Expected reply from Duke** :

```
Congratulations! This task has been marked as done!
        [T][X] running
```
---

### `Unmark`

**Unmarks a task in the Duke bot based on the position given.**


**Sample input** :

`unmark 3`

**Expected reply from Duke** :

```
Congratulations! This task has been successfully unmarked!
        [T][] running
```
---

### `Find`

**Filters out all tasks which contains the keyword specified.**

In this example,
1. [D][] submit homework (by: Sep 14 2022)
2. [E][] running marathon (at: Sep 14 2022)
3. [T][] running 

are currently stored in the Duke Bot.

**Sample input** :

`find running`

**Expected reply from Duke** :

```
Here are the list of matching tasks!
        1. [E][] running marathon (at: Sep 14 2022)
        2. [T][] running 
```
---

### `Undo`

**Undoes the previous command made by the user**

In this example, the user has previously marked
the task located at position 1.

**Sample input** :

`undo`

**Expected reply from Duke** :

```
This task has just been unmarked!
        [T][] running
```
---

### `Bye`

**To exit the application.**

**Sample input** :

`bye`

**Expected reply from Duke** :

```
So Long, Farewell!
```
