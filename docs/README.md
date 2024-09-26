# User Guide

## Features 

### Manage Tasks
Add, delete, mark, unmark, find and list all current and/or incomplete tasks.

### Help
Get help for what commands to use easily.

## Usage

### `todo` - Add todo task
Add a new todo task into current task list.

Example of usage:\
`todo TASK`

Expected outcome:\
A todo task of description TASK should be added to task list.

```
Okay! Remember to complete the task:

      [T][ ] TASK
```

### `event` - Add event task
Add a new event task into current task list.

Example of usage:\
`event TASK /at TIME`

Expected outcome:\
An event task of description TASK and event time TIME should be added to task list.

```
Okay! Remember to complete the task:

      [E][ ] TASK (at: TIME)
```

### `deadline` - Add deadline task
Add a new deadline task into current task list.

Example of usage:\
`deadline TASK /by TIME`

Expected outcome:\
A deadline task of description TASK and deadline time TIME should be added to task list.

```
Okay! Remember to complete the task:

      [D][ ] TASK (by: TIME)
```

### `delete` - Delete task
Delete a task from the current task list.

Example of usage:\
`delete TASK_NUM`

Expected outcome:\
The task with task number TASK_NUM, description TASK and time TIME should be removed from the current task list, with TASK_LEFT_COUNT number of tasks left.

```
Neat, I've removed the task:

      [D][ ] TASK (by: TIME)
      
Now there's TASK_LEFT_COUNT tasks left, good job! ^_^
```

### `mark` - Mark task as completed
Mark a task from current task list as completed.

Example of usage:\
`mark TASK_NUM`

Expected outcome:\
The task with task number TASK_NUM, description TASK, and task time TIME will be marked as completed.

```
Nice! I've marked this task as done:

      [E][X] TASK (at: TIME)
```

### `unmark` - Mark task as incomplete
Mark a task from current task list as incomplete.

Example of usage:  
`unmark TASK_NUM`

Expected outcome:  
The task with task number TASK_NUM, description TASK, and task time TIME will be marked as incomplete.

```
Aw man, I've unmarked the following task:

      [T][ ] TASK
```

### `find` - Find task with keyword
Find all tasks with description TASK that contains the keyword KEYWORD.

Example of usage:  
`find KEYWORD`

Expected outcome:  
All tasks that contain KEYWORD will be shown with its relevant task description, time, and completion, but not number.

```
Sure! These are the tasks left:
      
      1. [T][ ] TASK_ONE
      2. [E][ ] TASK_TWO (at: TIME_TWO)
```

### `list` - List current tasks
List out all the current tasks both completed and incomplete in the task list.

Example of usage:  
`list`

Expected outcome:  
All tasks in the current task list will be shown with the correct task description, time, number, and completion.

```
Sure! These are the tasks left:
      
      1. [T][ ] TASK_ONE
      2. [D][ ] TASK_TWO (by: TIME_TWO)
      3. [E][ ] TASK_THREE (at: TIME_THREE)
      4. [E][X] TASK_FOUR (by: TIME_FOUR)
```

### `reminder` - List incomplete tasks
List out all the current incomplete tasks in the task list.

Example of usage:  
`reminder`

Expected outcome:  
All incomplete tasks in the current task list will be shown with the correct task description, time, and completion, but not number.

```
Sure! These are the tasks left:
      
      1. [T][ ] TASK_ONE
      2. [D][ ] TASK_TWO (by: TIME_TWO)
      3. [E][ ] TASK_THREE (at: TIME_THREE)
```

### `help` - Get all commands available
Get the list of all commands available for user input.

Example of usage:  
`help`

Expected outcome:  
All commands that are currently available will be shown to user.

```
These are the commands you can use, try them!

      1. todo <name>  : add new todo
      2. event <name> /at <time> : add new event
      3. deadline <name> /by <time> : add new deadline
      4. list : list all current tasks
      5. delete <task number> : delete task
      6. mark <task number> : mark task as complete
      7. unmark <task number> : unmark task as complete
      8. find <keyword> : find task with keyword
      9. reminder : get all incomplete tasks left
      10. help : get all available commands
      11. bye : save all current tasks and leave app
```

### `bye` - Save and quit app
Save all current tasks and information and quit the application.

Example of usage:  
`bye`

Expected outcome:  
All current tasks will be saved and a goodbye message should appear before app closes.

```
See you next time, goodbye! :P
```
