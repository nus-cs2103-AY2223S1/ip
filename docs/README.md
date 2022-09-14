# User Guide

## **Features** 

### Types of Tasks

There are a total of three types of Tasks that Duke keeps track of. 
- Todo
- Deadline
- Event

All tasks have a description which is one or more words describing what the task is about, an indicator of whether the task is completed or not. 
By default all tasks are marked as not completed when first created.

The **Deadline** task has an associated date and time to be specified as the deadline of the task.

The **Event** task has an associated date to be specified as the date of the task.

The **Todo** task does not have any associated date or time. 
### Archive
All tasks you add during your usage in case you want to check back on all your tasks ever recorded. This will be in a file `data/archive.txt`
## Add Task

Add a task to the list of tasks for Duke to keep track of.

####Todo
Format: `todo description`

Examples:
- `todo eat lunch with friends`
- `todo attend CS2103T Lecture`

####Deadline
Format: `deadline description /by dd/MM/yyyy HHmm`

Examples:
- `deadline Finish math homework /by 01/01/2022 1900`
- `deadline Submit proposal /by 19/04/2023 0800`
>Note: The date and time input for creating a Deadline task **MUST** follow the format: dd/MM/yyyy HHmm

####Event
Format: `event description /at time description`

Examples:
- `event concert /at first Monday of March`
- `event Birthday party /at Sunday`
>Note: The time description of an Event task is just a text and need not follow any specific format


## Usage

### `list` - List out the tasks in your task list.

See the list of existing tasks in your task list.

Example of usage: 

`list`

Expected outcome:

For example if you have a list of 3 tasks (1 Todo, 1 Deadline and 1 Event)

```
Here are the tasks in your list:
1. [T][] first task
2. [D][] second task (by: 01/01/2022 1900)
3. [E][] third task at: not sure
```

###`mark` and `unmark` - Mark a task as completed or uncompleted.
When a task is completed, you can indicate this in your task list by using the `mark n` command where `n`
is the index/position of the task in your task list. The task is then marked with an 'X' to indicate it is completed.

Similarly, if a task was not completed, you can indicate this using the `unmark n` command.
The 'X' mark would be removed to indicate the task is not yet complete.

Example of usage: `mark 3`

Expected outcome:

```
Okay, I have marked this task as done
[E][X] third task at: not sure
```
> Note: The task now is marked with an [X] to show it is completed.
> You can run the `list` command to check that in the full list, the marking is also reflected there.

Example of usage: `unmark 2`

Expected outcome:

```
Okay, I have marked this task as not done
[D][] second task (by: 01/01/2022 1900)
```
> Note: The task now is marked with an [ ] to show it is not completed.

### `delete` - Remove a task in your task list.
You can remove it using the `delete n` command, where `n`
is the index/position of the task in your task list.

Example of usage: `delete 2`

Expected outcome:
```
Okay, I have deleted this task
[D][] second task (by: 01/01/2022 1900)
```
The task is no longer in the list and you can verify this using `list`.

### `find` - Search for task(s).
You can search your task list for all tasks containing a given keyword in their description.

Example usage: `find first`

Expected outcome:

```
Here are the matching tasks in your task list:
1. [T][ ] first task
```

###`bye` - Exit the program.
To exit the program, run `bye` and a goodbye message will appear before the application closes.

Example usage: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
