# User Guide

Duke is a simpled command-based task list manager. 

Using the chatbot interface, users can execute commands including:
- Add new tasks
- Marking or unmarking tasks as complete
- Delete tasks 
- Find tasks from the record.

## Features 

### Add task

User can add tasks to their task list. There are currently 3 types of tasks supported:
- `todo`: A time-insenstive task with only has a short description.
- `deadline`: A time-senstive task that has both the description and an attached deadline.
- `event`: A time-senstive task that has both the description and an attached period.

### List task

User can list out all the tasks currently in the list. The task type and completion status
will be shown as well.

### Delete task

User can delete a task from the list using the index of the task.

### Find task

User can search for task in the list based on keyword.

### Mark / Unmark task

User can mark/unmark task as completed based on the index of the task.

## Usage

### `todo` - Add todo task

`todo Task_Description` 

Adds a todo task into the task list with the given description

**Example of usage:** 

`todo borrow book`

**Expected outcome:**

A todo task with the description "borrow book" is added to the list.

```
Got it. I've added this task:
 [T][ ] borrow book
Now you have X tasks in the list.
```
Note: X is the number of tasks in the current list.

### `deadline` - Add deadline task

`deadline Task_Description /at Deadline` 

Adds a deadline task into the task list with the given description and deadline.
The deadline will be separated by the `/by` keyword.
The time should be specified in the `DD.MM.YYYY HH:MM` format.

**Example of usage:** 

`deadline return book /by 17.09.2022 12:00`

**Expected outcome:**

A deadline task with the description "return book" and deadline "17/Sep/2022 12:00" is added to the list.

```
Got it. I've added this task:
 [D][ ] return book (by: 17/Sep/2022 12:00)
Now you have X tasks in the list.
```
Note: X is the number of tasks in the current list.

### `event` - Add event task

`event Task_Description /from Start_time /to End_time` 

Adds an event task into the task list with the given description and time period.
The starting and ending time will be marked by the `/from` and `/to` keywords respectively.
The time should be specified in the `DD.MM.YYYY HH:MM` format.

**Example of usage:** 

`event read book /from 17.09.2022 12:00 /to 17.09.2022 13:00`

**Expected outcome:**

An event task with the description "read book" and time priod from "17/Sep/2022 12:00" to "17/Sep/2022 13:00".
is added to the list

```
Got it. I've added this task:
 [E][ ] read book (at: 17/Sep/2022 12:00 to 17/Sep/2022 13:00)
Now you have X tasks in the list.
```
Note: X is the number of tasks in the current list.

### `list` - Display current task lists

Shows to the current user all the tasks that is currently being tracked by the list.
The indexing of the task shown by this command is the same indexing used for other commands
such as `delete`, `mark` and `unmark`.

**Example of usage:** 

`list`

****Expected outcome:****

Shows the list of tasks currently being tracked.

```
You have X tasks in your list:
1.[ ][ ] task-1
2.[ ][ ] task-2
3.[ ][ ] task-3 ...
```
Note: X is the number of tasks in the current list.

### `delete` - Delete event task

`delete [0, *, Task_Index]`

Deletes the task from the lis with the given index. Mass operation to delete multiple
task is supported when user input more than one index. Using `0` or `*` for the index
will delete all tasks from the list.

Note: Indexing follows that of the `list` command and starts at 1.

**Example of usage:** 

Given the current list:
* [T][ ]  task-1
* [T][ ]  task-2
* [T][ ]  task-3

1. `delete 1`
2. `delete 1 2`
3. `delete *`or `delete 0`

**Expected outcome:**

1. Task at index 1 is deleted.
```
Noted. I've removed this task:
 [T][ ] task-1
Now you have 2 tasks in the list.
```
2. Task at index 1 and 2 are deleted.
```
Noted. I've removed this task:
 [T][ ] task-1
 [T][ ] task-2
Now you have 1 tasks in the list.
```
3. All the tasks are deleted.
```
Noted. I've removed this task:
 [T][ ] task-1
 [T][ ] task-2
 [T][ ] task-3 ...
Now you have 0 tasks in the list.
```

Note: X is the number of tasks in the current list.

### `mark` - Mark event task

`mark [0, *, Task_Index]`

Marks the task from the list with the given index. Mass operation to mark multiple
task is supported when user input more than one index. Using `0` or `*` for the index
will mark all tasks from the list.

Note: Indexing follows that of the `list` command and starts at 1.

**Example of usage:** 

Given the current list:
* [T][ ]  task-1
* [T][ ]  task-2
* [T][ ]  task-3

1. `mark 1`
2. `mark 1 2`
3. `mark *`or `mark 0`

**Expected outcome:**

1. Task at index 1 is marked.
```
Noted. I've marked this task:
 [T][X] task-1
Now you have 3 tasks in the list.
```
2. Task at index 1 and 2 are marked.
```
Noted. I've marked this task:
 [T][X] task-1
 [T][X] task-2
Now you have 3 tasks in the list.
```
3. All the tasks are marked.
```
Noted. I've marked this task:
 [T][X] task-1
 [T][X] task-2
 [T][X] task-3
Now you have 3 tasks in the list.
```

Note: Y is the number of tasks in the current list.

### `unmark` - unmark event task

`unmark [0, *, Task_Index]`

Unmarks the tasks from the task with the given index. Mass operation to unmark multiple
task is supported when user put in more than one index. Using `0` or `*` for the index
will unmark all tasks from the list.

Note: Indexing follows that of the `list` command and starts at 1.

**Example of usage:** 

Given the current list:
* [T][X]  task-1
* [T][X]  task-2
* [T][X]  task-3

1. `unmark 1`
2. `unmark 1, 2`
3. `unmark *`or `unmark 0`

**Expected outcome:**

1. Task at index 1 is unmarked.
```
Noted. I've unmarked this task:
 [T][ ] task-1
Now you have 3 tasks in the list.
```
2. Task at index 1 and 2 are unmarked.
```
Noted. I've unmarked this task:
 [T][ ] task-1
 [T][ ] task-2
Now you have 3 tasks in the list.
```
3. All the tasks are unmarked.
```
Noted. I've unmarked this task:
 [T][ ] task-1
 [T][ ] task-2
 [T][ ] task-3 
Now you have 3 tasks in the list.
```

### `find` - Find task

        `find  Search_String`

Finds from the list the tasks containing the given keyword in its description.
Note: Search string is case-sensitive and will be matched exactly. Keywords
separated by whitespaces or comma will still be considered as the same search string. 
Note: The index of the tasks returned is same as the one return by `list` command.

**Example of usage:** 

Given the current list:
* [T][ ]  hello world
* [T][ ]  hellohello
* [T][ ]  helloworld

1. `find hello`
2. `find hello world`
3. `find Hello world`
4. `find world`

**Expected outcome:**
1. All tasks containing "hello" are found.
'''
Here is what I have found...
1.[T][ ] hello world
2.[T][ ] hello hello
3.[T][ ] helloworld
'''
2. Only the task containing "hello world" is found.
'''
Here is what I have found...
1.[T][ ] hello world
'''
3. The task cannot be found due to non-match letter case.
'''
Well, I can't find any task that matches your keyword :(
'''
4. Both tasks containing "world" is found.
'''
Here is what I have found...
1.[T][ ] hello world
3.[T][ ] helloworld
'''