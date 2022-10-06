# DUKE User Guide
Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
## Features
### Keep Track of ToDo, Event and Deadline

The user can record tasks using Duke. Tasks are classified as ToDo, Event and Deadline.
The user can record the done/undone status of the Task.
The user can record the time of Event and Deadline.
## Usage


### 1. `bye` - Shutdown and terminate the program

DUKE will shutdown after receiving this command.

Format:

`bye`

Example of usage:

`bye`

Output:

```
***************************************
Bye. Hope to see you again soon!
***************************************
```

### 2. `todo` - Add a ToDo task to the task list

DUKE will add the ToDo Task to the task list.

Format:

`todo <Task Information>`


Example of usage:

`todo read book`

Output:

```
***************************************
Got it. I've added this Task:
[T][ ] read book
Now you have 1 tasks in the list.
***************************************
```
Where `[T]` indicates that this task is a ToDo task, and the second `[ ]` indicates that this task is not done.

### 3. `event` - Add an event task to the task list

DUKE will add the event Task to the task list.

Format:

`event <Task Information> /at <Time in yyyy-mm-dd hh:mm>`


Example of usage:

`event read chapter 2 /at 2022-10-01 11:22`

Output:

```
***************************************
Got it. I've added this task:
[E][ ] read chapter 2 (at: 2022-10-01 11:22)
Now you have 2 tasks in the list.
***************************************
```
Where `[E]` indicates that this task is an Event task, and the second `[ ]` indicates that this task is not done.

### 4. `deadline` - Add a deadline task to the task list

DUKE will add the deadline Task to the task list.

Format:

`deadline <Task Information> /by <Time in yyyy-mm-dd hh:mm>`


Example of usage:

`deadline chapter 2 refelction /by 2022-10-07 18:26`

Output:

```
***************************************
Got it. I've added this task:
[D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
Now you have 3 tasks in the list.
***************************************
```
Where `[D]` indicates that this task is a Deadline task, and the second `[ ]` indicates that this task is not done.


### 5. `list` - List all tasks in the Task List.

DUKE will list all tasks previously added.

Format:

```
list
```

Example of usage:

`list`

Output:

```
***************************************
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
3. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```

### 6. `mark` - Mark a Task as done

The duke will mark a Task with given index in the task list as done.

Format:
```
mark <Index>
```

Example of usage in sequence:

`list`,
`mark 2`,
`list`

Outputs in sequence:

`> list`
```
***************************************
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
3. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
`> mark 2`
```
***************************************
Nice! I've marked this task as done:
[E][X] read chapter 2 (at: 2022-10-01 11:22)
***************************************
```
`> list`
```
***************************************
Here are the tasks in your list:
1. [T][ ] read book
2. [E][X] read chapter 2 (at: 2022-10-01 11:22)
3. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
Where `[ ]` indicates that this task is not done, and `[X]` indicates that this task is done.

### 7. `unmark` - Mark a Task as not done

The duke will mark a Task with given index in the task list as not done.

Format:
```
unmark <Index>
```

Example of usage in sequence:

`list`,
`unmark 2`,
`list`

Outputs in sequence:

`> list`
```
***************************************
Here are the tasks in your list:
1. [T][ ] read book
2. [E][X] read chapter 2 (at: 2022-10-01 11:22)
3. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
`> unmark 2`
```
***************************************
Nice! I've marked this task as not done:
[E][ ] read chapter 2 (at: 2022-10-01 11:22)
***************************************
```
`> list`
```
***************************************
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
3. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
Where `[ ]` indicates that this task is not done.

### 8. `delete` - Delete the task from task list

DUKE will delete the task with given index in the task list. The deleted index will be used by latter tasks.

Format:

`delete <Index>`

Example of usage in sequence:

`list`, `delete 1`, `list`

Outputs in sequence:

`> list`
```
***************************************
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
3. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
`> delete 1`
```
***************************************
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
***************************************
```
`> list`
```
***************************************
Here are the tasks in your list:
1. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
2. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
Note: The indices of Task 2 and 3 changed to 1 and 2 when original Task 1 is deleted.

### 9. `find` - Filter tasks that contain given information
DUKE will list the tasks with target information.

Format:

`find <target>`

Example of usage in sequence:

`list`, `find chapter 2`, `find read`

Outcomes in sequence:

`> list`
```
***************************************
Here are the tasks in your list:
1. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
2. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
3. [T][ ] open the first page of chapter 3
4. [T][ ] read chapter 3
***************************************
```
`> find chapter 2`
```
***************************************
Here are the matching tasks in your list:
1. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
2. [D][ ] chapter 2 refelction (by: 2022-10-07 18:26)
***************************************
```
`> find read`
```
***************************************
Here are the matching tasks in your list:
1. [E][ ] read chapter 2 (at: 2022-10-01 11:22)
2. [T][ ] read chapter 3
***************************************
```

