# User Guide

Super Duke is a chatting bot that helps you **manage your tasks**.

## Features 

All features of Super Duke are described below in the table.

Please take a look!

| **Feature** | **Description**                                     |
|:-----------:|-----------------------------------------------------|
|    date     | lists all current tasks from a given date           |
|  deadline   | adds a task due by a given date                     |
|   delete    | removes a task from the task list                   |
|    event    | adds a task that takes place on a given date        |
|     bye     | says goodbye to you                                 |
|    find     | finds all the tasks that contains the given keyword |
|    list     | lists all current tasks                             |
|    mark     | marks the given task as done                        |
|    todo     | adds a task with no date                            |                                              
|   unmark    | indicates the task as not done yet                  |

## Usage

### `date` - Lists tasks on a given day

Super Duke describes to you a list of tasks (either a `deadline` or `event`) that take place on a specified date.

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

Adds the given deadline successfully to the storage!

Shows the total number of current tasks.

```
Got it. I've added this task:
 [D][ ] prepare slides (by: Sep 14 2022)
Now you have 7 tasks in the list.
```

Super Duke checks for duplicates, so if you enter the same task more than once, Super Duke will be annoyed!

```
☹ Don't you realise the input task has already been stored in the task list?
```

***The checking applies to `event` and `deadline` features as well.***

-------------------

### `delete` - Deletes a task

Super Duke removes the task specified by its index in the list from storage.

**Example of usage:**

`delete 7`

**Expected outcome:**

Shows to the user the specified task and removes it from storage successfully.

Shows the total number of current tasks.

```
Got it. I've removed this task:
 [D][ ] prepare slides (by: Sep 14 2022)
Now you have 7 tasks in the list.
```

-------------------

### `event` - Adds an event

Super Dukes adds a task that takes place on a specific date to storage.

**Example of usage:**

`event presentation /at 2022-09-14`

`event lecture /at 2022/09/16`

**Expected outcome:**

Adds the given event successfully to the storage!

Shows the total number of current tasks.

```
Got it. I've added this task:
 [E][ ] presentation (at: Sep 14 2022)
Now you have 7 tasks in the list.
```

-------------------

### `bye` - Says goodbye to you

Super Dukes sends you a farewell message. (Emmm is it really a farewell massage?)

**Example of usage:**

`bye`

**Expected outcome:**

Prints a goodbye message to the user.

```
Never wanna see you again!
```