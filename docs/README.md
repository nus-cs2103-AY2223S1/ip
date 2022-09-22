# Duke User Guide
Duke is a chatbot based task manager using a text-based interface. 
It is optimized for users who prefer typing over using a mouse. 
All data is stored locally on your computer.

No internet connection is required! :+1:

## Quick Start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/junwei-tan/ip/releases)
3. Double-click the file to start Duke.
4. Type the command in the command box and press `Enter` to execute it.
5. Refers to the Features section below for details of each command.

## Features 
There are 3 types of tasks (_todo, event, and deadline_), each with different characteristics. 
All tasks have a description and a completion status.
Descriptions cannot be edited once tasks are added.
Completion status is indicated by a tick or cross and can be edited.

### Add a `todo`
Todos are tasks with only a description and no other information.

Format: `todo [description]`
- `description` cannot be blank, must not contain "||"

Example of usage:
- `todo homework` adds a todo task with description `homework`
- `todo exercise` adds a todo task with description `exercise`

### Add a `event`
Events are tasks with only a description and date.

Format: `event [description] \at [date]`
- `description` cannot be blank, must not contain "||"
- `date` must be specified in the `yyyy-mm-dd` format.

Example of usage:
- `event party \at 2022-02-14` adds a event with description `party` and date `Feb 14, 2022`

### Add a `deadline`
Deadline are tasks with only a description and date.

Format: `deadline [description] \at [date]`
- `description` cannot be blank, must not contain "||"
- `date` must be specified in the `yyyy-mm-dd` format

Example of usage:
- `deadline assignment \by 2022-02-14` adds an event with description `assignment` and date `Feb 14, 2022`

### Delete a task by task number
Each tasks has a task number based on its order in the task list. 
To find the task number, use the `list` command. 

Note: Do not to use the task number from the `find` command.

Format: `delete [task number]`
- `task number` task number as shown in the task list using `list` command

Example of usage:
- `delete 1` deletes the first task in the task list

### Delete a task by description
Delete all tasks with descriptions that contain the specified phrase.

Format: `delete -f [phrase]`
- `phrase` phrase can be any combination of characters but cannot contain "||"
- `phrase` can be left blank to delete entire task list

Example of usage:
- `delete -f homework` deletes all tasks with the work "homework" in their description

### Find a task by description
Search all tasks with descriptions that contain the specified phrase.

Format: `find [phrase]`
- `phrase` phrase can be any combination of characters but cannot contain "||"

Example of usage:
- `find homework` finds all tasks with the work "homework" in their description

### List all tasks
To view all tasks, their descriptions and completion status.
An empty list will be shown if there are no tasks.

Format: `list`

Expected outcome:
Format: [Task Type][Task Completion Status] (task description) (date)
- `Task Type` is either `T` - todo, `E` - event or `D` - deadline
- `Task Completion Status` is either `X` - completed or ` ` - not completed
```
Sample Output:
list
Here are the tasks in your list:
1. [T][ ] homework
```

### Set a Task as completed
Before setting a task as completed, use the `list` command to find the task number.
Note: Do not to use the task number from the `find` command.

Format: `mark [task number]`
- `task number` task number as shown in the task list using `list` command

Example of usage:
- `mark 1` sets the first task as "completed"

### Set a Task as not completed
Before setting a task as not completed, use the `list` command to find the task number.
Note: Do not to use the task number from the `find` command.

Format: `unmark [task number]`
- `task number` task number as shown in the task list using `list` command

Example of usage:
- `unmark 1` sets the first task as "not completed"

### Exit Duke
To exit duke, use the `bye` command or close the window.

Format: `bye`

## Summary of Commands
| Feature                                    | Command                                           | Sample Command                   |
|--------------------------------------------|---------------------------------------------------|----------------------------------|
| Exit the program                           | bye                                               | bye                              |
| List all tasks                             | list                                              | list                             |
| Find tasks that contain specified phrase   | find [phrase]                                     | find assignments                 |
| Add new "Todo"                             | todo [task name]                                  | todo buy groceries               |
| Add new "Deadline"                         | deadline [deadline name] /by [date in yyyy-mm-dd] | deadline homework /by 2030-05-05 |
| Add new "Event"                            | event [event name] /at [date in yyyy-mm-dd]       | event party /at 2012-12-12       |
| Delete a task by task no.                  | delete [task's number (starting from 1)]          | delete 1                         |
| Delete tasks that contain specified phrase | delete [phrase]                                   | delete -f assignments            |
| Mark a task as done                        | mark [task's number (starting from 1)]            | mark 1                           |
| Mark a task as not done                    | unmark [task's number (starting from 1)]          | unmark 1                         |