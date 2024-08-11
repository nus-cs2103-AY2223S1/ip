# User Guide

Super Duke is a chatting bot that helps you **manage your tasks**.

## Features 

All features of Super Duke are described below in the table.

Please take a look!

| **Feature** | **Description**                                     |
|:-----------:|-----------------------------------------------------|
|    date     | lists all current tasks on a given date             |
|  deadline   | adds a task due by a given date                     |
|   delete    | removes a task from the task list                   |
|    event    | adds a task that takes place on a given date        |
|     bye     | says goodbye to you                                 |
|    find     | finds all the tasks that contains the given keyword |
|    list     | lists all current tasks                             |
|    mark     | marks the given task as done                        |
|    todo     | adds a task with no date                            |                                              
|   unmark    | indicates the given task as not done yet            |

## Usage

### `date` - Lists tasks on a given date

Super Duke describes to you a list of tasks (either a `deadline` or an `event`) that take place on a specified date.

**Example of usage:**

`date 2022-09-04`

`date 2022/09/04`

**Expected outcome:**

Shows a list of tasks with matching date as that entered by the user, such as below.

```
Here are the matching tasks in your list:
1. [D][X] 2101 essay (by: Sep 04 2022)
2. [D][ ] submit report (by: Sep 04 2022)
```

-------------------

### `deadline` - Adds a deadline

Super Dukes adds a task with a specified deadline to storage.

**Example of usage:**

`deadline prepare slides /by 2022-09-14`

`deadline submit ip /by 2022/09/16`

**Expected outcome:**

Adds the given deadline indicated by `[D]` successfully to storage!

Shows the total number of current tasks.

```
Got it. I've added this task:
 [D][ ] prepare slides (by: Sep 14 2022)
Now you have 7 tasks in the list.
```

Super Duke **checks for duplicates**, so if you enter the same task more than once, Super Duke will be annoyed!

```
Don't you realise the input task has already been stored in the task list?
```

***The checking applies to `event` and `deadline` features as well.***

-------------------

### `delete` - Deletes a task

Super Duke removes from storage the task specified by its index in the task list.

**Example of usage:**

`delete 7`

**Expected outcome:**

Shows to the user the specified task and removes it from storage successfully.

Shows the total number of current tasks.

```
Got it. I've removed this task:
 [D][ ] prepare slides (by: Sep 14 2022)
Now you have 6 tasks in the list.
```

-------------------

### `event` - Adds an event

Super Duke adds a task that takes place on a specific date to storage.

**Example of usage:**

`event presentation /at 2022-09-14`

`event lecture /at 2022/09/16`

**Expected outcome:**

Adds the given event indicated by `[E]` successfully to storage!

Shows the total number of current tasks.

```
Got it. I've added this task:
 [E][ ] presentation (at: Sep 14 2022)
Now you have 7 tasks in the list.
```

-------------------

### `bye` - Says goodbye

Super Duke sends you a farewell message. (*Emmm is it really a farewell massage?*)

**Example of usage:**

`bye`

**Expected outcome:**

Prints a goodbye message to the user as shown below.

After a moment, the program is closed.

```
Never wanna see you again!
```

-------------------

### `find` - Finds tasks

Super Duke finds all tasks that contain the given keyword.

**Example of usage:**

`find book`

**Expected outcome:**

Shows a list of tasks whose description contains "book".

```
Here are the matching tasks in your list:
1. [T][] read book
2. [T][] return book
```

-------------------

### `list` - Lists all tasks

Super Duke shows you a list of all tasks in storage.

**Example of usage:**

`list`

**Expected outcome:**

Shows a list of all current tasks with numbering.

```
Here are the tasks in your list:
1. [T][X] watch tv
2. [T][ ] read book
3. [T][ ] return book
4. [D][X] 2101 essay (by: Sep 04 2022)
5. [E][ ] presentation (at: Sep 14 2022)
```

-------------------

### `mark` - Marks a task

Super Duke marks a task in storage as done by its index in the storage list.

**Example of usage:**

`mark 2`

**Expected outcome:**

Shows the task to be marked to the user and marks the task with `[X]`.

```
Alright, I've marked this task as done:
 [T][X] read book
```

-------------------

### `todo` - Adds a task with no date

Super Duke adds a task with no specified date to storage.

**Example of usage:**

`todo watch tv`

**Expected outcome:**

Adds the given to-do indicated by `[T]` successfully to storage!

Shows the total number of current tasks.

```
Got it. I've added this task:
 [T][ ] watch tv
Now you have 1 tasks in the list.
```

-------------------

### `unmark` - Unmarks a task

Super Duke indicates a task as not done yet by its index in the storage list.

**Example of usage:**

`unmark 2`

**Expected outcome:**

Shows to the user the task to be unmarked and replaces `[X]` with `[ ]`.

```
OK, I've marked this task as not done yet:
 [T][ ] read book
```
