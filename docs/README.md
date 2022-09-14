# User Guide
___

Duke is a desktop app that allows users to easily manage tasks 
via a Command Line Interface (CLI). Users can keep track of different 
tasks such as *ToDos*, *Events* and *Deadlines*. Users can *create*, *list*, 
*find*, *mark*, *unmark*, *delete* and *update* tasks. 

___

## Getting started
___

1. Ensure that you have Java `11` or above installed in your computer. 
2. Download the latest release from [here](https://github.com/desmondyst/ip/releases).
3. Copy the file to the folder you want to use as the *home* folder for your Duke.
4. Double-click the file to start the application. 
5. Type the command in the command box and press Enter to execute it. e.g. typing `list` 
and pressing Enter will list all the tasks stored in the application. 
   * `deadline CS2100 Tutorial /by 2022-03-20`: Adds a Deadline task with the description and deadline.  
   * `find CS2100`: Finds all tasks with the keyword `CS2100` in their task description. 
6. Refer to the Features below fo details of each command. 

## Features
___

### `todo` - Create a Todo task
Creates a Todo task with the given description.

Examples of usage:

`todo exercise`

Expected outcome: 

Creates a Todo task with the description `exercise`.


```
Got it. I've added this task:
[T][ ] exercise
Now you have 1 task in the list. 
```
<br />

### `deadline` - Create a DeadLine task
Creates a Deadline task with the given description and date.

Examples of usage:

`deadline CS2100 Tutorial /by 2022-03-20`

Expected outcome:

Creates a Deadline task with the description `CS2100 Tutorial` with due date *20 March 2022*.

```
Got it. I've added this task:
[D][ ] CS2100 Tutorial (by: Mar 20 2022)
Now you have 2 tasks in the list. 
```
<br />

### `event` - Create a event task
Creates a Event task with the given description and date.

Examples of usage:

`event Puaki's birthday celebration /at 2022-12-05`

Expected outcome:

Creates a Event task with the description `Puaki's birthday celebration` which happens on *5 December 2022*.

```
Got it. I've added this task:
[E][ ] Puaki's birthday celebration (at: Dec 5 2022)
Now you have 3 tasks in the list. 
```
<br />

### `mark` - Mark a task as completed
Marks the specified task completed.

Examples of usage:

`mark 1`

Expected outcome:

Marks the task with task number 1 in the task list as completed. 

```
Nice! I've marked this task as done. 
[T][X] exercise
```
<br />

### `unmark` - Mark a task as uncompleted
Marks the specified task uncompleted.

Examples of usage:

`unmark 1`

Expected outcome:

Marks the task with task number 1 in the task list as uncompleted. 

```
Sure! I've marked this task as not done yet. 
[T][ ] exercise
```
<br />

### `update` - Update a task
Updates the specified task.
Examples of usage:

`update 1 todo homework`

Expected outcome:

Updates the task with task number 1 of the task list to a new Todo `homework`.
```
Sure! I've updated this task. 
[T][ ] homework
```
<br />

### `list` - List all tasks 
List all tasks stored in the application. 

Examples of usage:

`list`

Expected outcome:

List all tasks currently stored in the application. 
```
Here are the tasks in your list:
1.[T][ ] homework
2.[D][ ] CS2100 Tutorial (by: Mar 20 2022)
3.[E][ ] Puaki's birthday celebration (at Dec 5 2022)
```
<br />

### `find` - Find matching tasks
Find tasks with description that include the specified string. 

Examples of usage:

`find CS2100`

Expected outcome:

Find tasks with description that include the `CS2100`.
```
Here are the matching tasks in your list:

1.[D][ ] CS2100 Tutorial (by: Mar 20 2022)
```
<br />

### `delete` - Delete a task

Deletes the specified task.

Examples of usage:

`delete 1`

Expected outcome:

Delete the task with task number 1 in the task list.

```
Noted! I've removed this task:
[T][ ] homework
Now you have 2 tasks in the list. 
```
<br />

### `bye` - Exit

Exits the Duke application.

Examples of usage:

`bye`

Expected outcome:

Prints the exit message and exits the application. 

```
Bye. Hope to see you soon! 
```
<br />

## Command summary
| Action   | Format                              |
|----------|-------------------------------------|
| todo     | todo [TASK]                         |
| deadline | deadline [TASK] /by [DATE]          |
| event    | event [TASK] /at [DATE]             |
| mark     | mark [TASK NUMBER]                  |
| unmark   | unmark [TASK NUMBER]                |
| update   | update [TASK NUMBER] [UPDATED TASK] |
| list     | list                                |
| find     | find [KEYWORDS]                     |
| delete   | delete [TASK NUMBER]                |
| bye      | bye                                 |