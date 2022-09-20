# User Guide
Duke is a task management app that allows you to manage your todos, deadlines, and events. 
## Features 

- Add and delete tasks.
- Mark / unmark a task as done.
- List and find tasks.
- Add a recurring event.


## Usage

### `todo <description>` - Add a todo

Example of usage: 

`todo do laundry`

Expected outcome:

```
Added a todo: [T][ ] do laundry
```

### `event <description> /at <dd/MM/yyyy>` - Add an event

Example of usage:

`event attend talk /at 08/08/2022`

Expected outcome:

```
Added a event: [E][ ] attend talk (at: Aug 08 2022)
```

### `deadline <description> /by <dd/MM/yyyy>` - Add a deadline

Example of usage:

`deadline do assignment /by 08/08/2022`

Expected outcome:

```
Added a deadline: [D][ ] do assignment (by: Aug 08 2022)
```
### `revent <description> /at <StartDate> /end <EndDate>` - Add a recurring event
Currently, the recurring event will be fixed to have a weekly interval.

#### Expected behaviour
The next recurring event will only be added automatically if you mark the last event as done.
It will not be deleted if you mark the last event as undone afterwards.
Note that you should **avoid deleting** any of the recurring event because it may affect the recurrence rule.


### `mark <index>` - Mark a task as done
### `unmark <index>` - Mark a task as undone
### `list` - List all tasks
### `save` - Save all tasks to disk
### `bye` - Exit the app
