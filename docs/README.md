# User Guide of Neko Neko

![Unfortunately the image of the Neko Neko application failed to load :(](https://github.com/yixiann/ip/blob/master/docs/Ui.png)

## About

Neko Neko is an efficient task management application that will help you keep track of what needs to be done.

It has the added benefit of relieving your stress as you look at the cute cat, Neko.

## Features

### Add three different types of tasks to a list

- Add either a task to be done, a deadline, or an event that will occur to your list!
- [T] indicates a todo, [D] indicates a deadline and [E] indicates an event.

### Check and Uncheck tasks

- Mark a task as either done or undone.
- [ X ] indicated done while [  ] indicated undone.

### Find tasks

- Search for all tasks that contain a certain text.

### Delete tasks

- Delete tasks from the list.

### Receive help

- View a list of all the instructions and how to use them.

## Usage

### `todo` - Add a task to be done to the task list

Example of usage:

- Format: `todo (Required: Task Name)`
- Usage: `todo Mow the lawn`

Expected outcome:

- A todo indicated by [T] will be added to your list.
- The next [ ] indicates that the task is not done yet.
- Neko will let you know that the task has been added successfully.

```
Meow Meow! 
Successfuly added: Mow the lawn 
Current Tasking
1) [T][ ] Mow the lawn
Number of tasking: 1
Meow Moow!
```

### `deadline` - Add a task to be done to the task list that must be finished before a certain date

Example of usage:

- Format: `deadline (Required: Task Name) /by (Required: date [yyyy-mm-dd])`
- Usage: `deadline Finish Biology Homework /by 2022-09-11`

Expected outcome:

- A deadline indicated by [D] will be added to your list.
- The next [ ] indicates that the deadline has not been met.
- Neko will let you know that the deadline has been added successfully.

```
Meow Meow! 
Successfuly added: Finish Biology Homework 
Current Tasking
1) [T][ ] Mow the lawn
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
Number of tasking: 2
Meow Moow!
```

### `event` - Add a task to be done to the task list that will occur on a certain date

Example of usage:

- Format: `event (Required: Task Name) /at (Required: date [yyyy-mm-dd])`
- Usage: `event Attend Work Party /at 2022-10-19`

Expected outcome:

- An event indicated by [E] will be added to your list.
- The next [ ] indicates that the event is not done yet.
- Neko will let you know that the event has been added successfully.

```
Meow Meow! 
Successfuly added: Attend Work Party
Current Tasking
1) [T][ ] Mow the lawn
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
3) [E][ ] Attend Work Party
(at: 19 Oct 2022)
Number of tasking: 3
Meow Moow!
```

### `list` - List all tasks

Example of usage:

- Usage: `list`

Expected outcome:

- Neko will show you all the tasks in the list.

```
Current Tasking
1) [T][ ] Mow the lawn
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
3) [E][ ] Attend Work Party
(at: 19 Oct 2022)
Number of tasking: 3
Meow Meow!
```

### `find` - Find all tasks with a certain keyword

Example of usage:

- Format: `find (Required: Search Text)`
- Usage: `find Finish`

Expected outcome:

- Neko will show you all tasks in the list with the search text.

```
Meow Meow!

Search Results
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
Number of tasking: 1
```

### `check` - Mark a task as done

Example of usage:

- Format: `check (Required: index of task in the list. Refer to the "list" command.)`
- Usage: `check 3`

Expected outcome:

- Task will be marked as done as indicated by [X] instead of [ ].
- Neko will let you know that the task has been marked as done successfully.

```
Meow Meow! 
Nice! I have marked (Attend Work Party) as done!
Current Tasking
1) [T][ ] Mow the lawn
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
3) [E][X] Attend Work Party
(at: 19 Oct 2022)
Number of tasking: 3
Meow Meow!
```

This is shown when running the command `check 3` as "Attend Work Party" is at index 3 in the list.

### `uncheck` - Mark a task as undone

Example of usage:

- Format: `uncheck (Required: index of task in the list. Refer to the "list" command.)`
- Usage: `uncheck 3`

Expected outcome:

- Task will be marked as undone as indicated by [ ] instead of [X].
- Neko will let you know that the task has been marked as undone successfully.

```
Meow Meow! 
Nice! I have marked (Attend Work Party) as undone!
Current Tasking
1) [T][ ] Mow the lawn
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
3) [E][ ] Attend Work Party
(at: 19 Oct 2022)
Number of tasking: 3
Meow Meow!
```

This is shown when running the command `uncheck 3` as "Attend Work Party" is at index 3 in the list.

### `delete` - Delete a task

Example of usage:

- Format: `delete (Required: index of task in the list. Refer to the "list" command.)`
- Usage: `delete 3`

Expected outcome:

- Task will be deleted.
- Neko will let you know that the task has been deleted successfully.

```
Meow Meow! 
Successfully deleted: Attend Work Party!
Current Tasking
1) [T][ ] Mow the lawn
2) [D][ ] Finish Biology Homework 
(by: 11 Sep 2022)
Number of tasking: 2
Meow Meow!
```

This is shown when running the command `delete 3` as "Attend Work Party" was originally at index 3 in the list.

### `help` - View list of all instructions and how to use them.

Example of usage:

- Usage: `help`

Expected outcome:

- Neko will let you know all instructions and how to use them.

```
Meow Meow! 
What can I do for you?
- todo (task name) 
- deadline (task name) /by (date) 
- event (task name) /at (date) 
- list
- find (value)
- check (index)
- uncheck (index)
- delete (index)
- help 
- bye 

EXAMPLES: 
help 
todo cut hair 
deadline cut hair /by 2022-09-11 
event cut hair /at 2022-09-11 
list
find cut
check 1
uncheck 1
delete 1
bye 
```

### `bye` - Say goodbye to Neko and shut the application down.

Example of usage:

- Usage: `bye`

Expected outcome:

- Neko will sadly say goodbye.

```
Meow Meow!
Goodbye
```

## Additional Information.

Information in the task list is only saved if you execute the `bye` command.

The information is stored in /data/dukeData.txt

For each task, it is stored as the text needed to be entered to create the task.

Under it indicates whether the task is UNDONE or DONE.

Failing to adhere to this convention will clear the entire file.

Example:

```
todo Mow the lawn
UNDONE
deadline Finish Biology Homework /by 2022-09-11
UNDONE
event Attend Work Party /at 2022-10-19
DONE
```

Have fun using Neko Neko!!

