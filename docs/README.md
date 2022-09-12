# User Guide

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `doge.jar` from [here](https://github.com/njxue/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Doge application.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Doge Gui](Ui.png)
## Annotations
This user guide annotates the usage of each command with the following symbols:
- `<>` denotes a **mandatory** command argument
- `[]` denotes an _optional_ command argument

## Features

### Feature-Todo

Adds a **Todo** task to your list of tasks.

Usage: `todo <task_name>`

eg. `todo walk dog`

You should be able to see the following message from Doge:
```
Got it. I've added this task ^_^:
-----------------------------------
   [T][ ] walk dog
-----------------------------------
You have [1] tasks in the ist O_O
```

### Feature-Deadline

Adds a task with a deadline (a **deadline** task) to your list of tasks.

Usage: `deadline <task_name> /by <YYYY-MM-DD> [HH:MM]`

You should be able to see the following message from Doge:

e.g. `deadline CS2103T assignment 1 /by 2022-09-10 23:59`

If the time was not provided, it will assumed to be `23:59`.
```
Got it. I've added this task ^_^:
-----------------------------------
   [D][ ] CS2103T assignment 1 (by: 10 Sep 2022, 23:59)
-----------------------------------
You have [1] tasks in the ist O_O
```

### Feature-Event

Adds a task (an **event** task) that begins at a given time to your list of tasks.

Usage: `event <task_name> /at <YYYY-MM-DD> [HH:MM]`

e.g. `event CS2103T final exam /at 2022-11-01 09:00`

If the time was not provided, it will assumed to be `23:59`.

You should be able to see the following message from Doge:
```
Got it. I've added this task ^_^:
-----------------------------------
   [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
-----------------------------------
You have [1] tasks in the ist O_O
```

### Feature-Mark

**Marks** a task from your task list **as completed**.

Usage: `mark <task_index>`

e.g. `mark 1`

For example, if you marked a `Todo` as done, you should be able to see the following message from Doge:
```
Sure! I've marked this task as done ^O^:
-----------------------------------
   [T][X] walkdog
-----------------------------------
You have [n] tasks in the ist O_O
```
The `X` denotes that the task has been marked as completed.

### Feature-Unmark

**Unmarks** a task from your task list **as completed**. In other words, marks a task as incomplete. This command is useful if you have accidentally marked a task that is supposedly incomplete as completed.

Usage: `unmark <task_index>`

e.g. `unmark 1`

For example, if you marked a `Todo` as done, you should be able to see the following message from Doge:
```
Sure! I've marked this task as done ^O^:
-----------------------------------
   [T][ ] walk dog
-----------------------------------
You have [1] tasks in the ist O_O
```

### Feature-Delete

**Deletes** a task from your task list.

Usage: `delete <task_index>`
e.g. `delete 1`

For example, if you deleted a `Todo` task, you should be able to see the following message from Doge:
```
Okie. I've deleted this task >_>:
-----------------------------------
   [T][X] walk dog
-----------------------------------
You have [1] tasks in the ist O_O
```


### Feature-List

**Lists** all tasks in your task list.

Usage: `list`

If there is _at least one_ task in your task list, you should be able to see the following message from Doge:
```
Your tasks: [3]
=================================
1. [T][X] walk dog
2. [D][ ] CS2103T assignment 1 (by 2020-09-10, 23:59)
3. [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
=================================
```

If your task list is _empty_, you should see this from Doge:
```
Your tasks: [0]
=================================
YOU HAVE NO TASKS :<
=================================
```

Tasks denoted by `X` are tasks that you have marked as completed.


### Feature-Find

**Finds** tasks in your task list that match a certain search term.

Usage: `find <search_term>`
e.g. `find CS2103T`

If _at least one_ task was found, you should be able to see the following message from Doge:
```
Your tasks: [2]
=================================
1. [D][ ] CS2103T assignment 1 (by 2020-09-10, 23:59)
2. [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
=================================
```

If _no tasks_ were found, you should see this from Doge:
```
Your tasks: [0]
=================================
YOU HAVE NO TASKS :<
=================================
```


### Feature-Sort

**Sorts** the tasks in your task list in a specified **order** and by a specified **metric**.

Usage: `sort [sort_order] [sort_metric]`

The default sort order is ascending order. The default sort metric is the deadline/datetime of the tasks.

If `sort_order` or/and `sort_metric` is/are not specified, they will take on their default values.

The following are the available `sort_order` and `sort_metric`:

| Sort Order    | Meaning             |    
| ------------- | ------------------- |
| A             | Ascending (default) |
| D             | Descending          |

| Sort Metric   | Meaning                     |    
| ------------- | --------------------------- |
| deadline      | Deadline/datetime (default) |
| des           | Description                 |
| new           | Date added                  |
| type          | Type of task                | 

E.g. `sort` sorts the list in **ascending** order, by **deadline/time** of tasks.
You should be able to see the following message from Doge:
```
Your tasks: [3]
-----------------------------------
1. [D][ ] CS2103T assignment 1 (by 10 Sept 2022, 23:59)
2. [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
3. [T][X] walk dog
-----------------------------------
```

E.g. `sort D type` sorts the list in **descending** order, by **type** of task.
You should be able to see the following message from Doge:
```
Your tasks: [3]
-----------------------------------
1. [T][X] walk dog
2. [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
3. [D][ ] CS2103T assignment 1 (by 10 Sept 2022, 23:59)
-----------------------------------
```

E.g. `sort des` sorts the list in **ascending** order, by task **description**.
You should be able to see the following message from Doge:
```
Your tasks: [3]
-----------------------------------
1. [D][ ] CS2103T assignment 1 (by 10 Sept 2022, 23:59)
2. [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
3. [T][X] walk dog
-----------------------------------
```

E.g. `sort D` sorts the list in **descending** order, by **deadline/time** of tasks.
You should be able to see the following message from Doge:
```
Your tasks: [3]
-----------------------------------
1. [T][X] walk dog
2. [E][ ] CS2103T final exam (at: 1 Nov 2022, 09:00)
3. [D][ ] CS2103T assignment 1 (by 10 Sept 2022, 23:59)
-----------------------------------
```

### Feature-Bye

**Exits** the application.

Usage: `bye`