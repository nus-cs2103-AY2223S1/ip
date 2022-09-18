# User Guide

Duke program is a Desktop app for recording a list of tasks to do via Command Line Interface (CLI) with the aid of
Graphical User Interface (GUI).

- [Quick start](http://shawnchew.github.io/ip/#quick-start)
- [Features](http://shawnchew.github.io/ip/#features)
    - [Listing all tasks: `list`](http://shawnchew.github.io/ip/#listing-all-tasks)
    - [Adding a task: `todo` ](http://shawnchew.github.io/ip/#adding-a-task)
    - [Adding a task with deadline: `deadline`](http://shawnchew.github.io/ip/#adding-a-task-with-deadline)
    - [Adding an event: `event`](http://shawnchew.github.io/ip/#)
    - [Deleting a task: `delete`](http://shawnchew.github.io/ip/#)
    - [Marking a task: `mark`](http://shawnchew.github.io/ip/#)
    - [Unmarking a task: `unmark`](http://shawnchew.github.io/ip/#)
    - [Setting a priority level for task: `priority`](http://shawnchew.github.io/ip/#)
    - [Finding a task: `find`](http://shawnchew.github.io/ip/#)
    - [Terminating the program: `bye`](http://shawnchew.github.io/ip/#)
- [FAQ](http://shawnchew.github.io/ip/#)
- [Summary](http://shawnchew.github.io/ip/#)
---

## Quick start
1. Ensure you have Java `11` or above install in your Computer.
2. Download the latest duke.jar from [here]().
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   Note how the app contains sample data.
   ![SampleGUI](/SampleGUI.png)
5. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
    - `list`: List all tasks.
    - `todo laundry`: Adds a laundry task to the list of tasks.
    - `delete 3`: Deletes the 3rd task on the current list.
    - `mark 2`: Marks the 2nd task on the current list.
    - `bye`: Terminates the program.
6. Refer to [Features]() below for details of each command.
---

## Features
> Notes about command format:
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.

### Listing all tasks: `list`
Lists all tasks on the list of tasks.
Format: `list`

### Adding a task: `todo`
Adds a todo task to the list of tasks.
Format: `todo TASK`
Examples:
- `todo homework`

### Adding a task with deadline: `deadline`
Adds a task with deadline to the list of tasks.
Format: `deadline TASK /by DEADLINE`
- When `DEADLINE` is given in the format of yyyy-mm-dd, it will be translated to
  a more readable format. Eg. 2022-09-15 will be converted to Sep 15 2022

Examples:
- `deadline return book /by Sunday`

### Adding an event: `event`
Adds an event to the list of tasks.
Format: `event TASK /at DATE`
- When `DATE` is given in the format of yyyy-mm-dd, it will be translated to
  a more readable format. Eg. 2022-09-15 will be converted to Sep 15 2022

Examples:
- `event project meeting /at Mon 2-4pm`

### Deleting a task: `delete`
Deletes the specified task from the current list of tasks.
Format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, …

Examples:
- `list` followed by `delete 3` deletes the 3rd task on the list.

### Marking a task: `mark`
Marks the specified task from the current list of tasks.
Format: `mark INDEX`
- Marks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.

Examples:
- `mark 5`

### Unmarking a task: `unmark`
Unmarks the specified task from the current list of tasks.
Format: `unmark INDEX`
- Unmarks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.

Examples:
- `unmark 5`

### Setting a priority level for task: `priority`
Sets the priority level for the specified task.
Format: `priority INDEX LEVEL`
- Set priority level for the specified INDEX.
- The index refers to the index number shown in the displayed list.
- LEVEL can take any arbitrary level that the user define.

Examples:
- `priority 2 high`

### Finding a task: `find`
Finds all task in the list which contains the specified word.
Format: `find WORD`

Examples:
- `find book`

### Terminating the program: `bye`
Saves the current list and ends the program.
Format: `bye`

---

## FAQ
No Questions yet.

## Command summary
| Action   | Format, Examples                                                        |
|----------|-------------------------------------------------------------------------|
| list     | `list`                                                                  |
| todo     | `todo TASK`<br/> e.g., todo homework                                    |
| deadline | `deadline TASK /by DEADLINE`<br/> e.g., deadline return book /by Sunday |
| event    | `event TASK /at DATE`<br/> e.g., event project meeting /at Mon 2-4pm    |
| delete   | `delete INDEX`<br/> e.g., delete 3                                      |
| mark     | `mark INDEX`<br/> e.g., mark 5                                          |
| unmark   | `unmark INDEX`<br/> e.g., unmark 5                                      |
| priority | `priority INDEX LEVEL`<br/> e.g., priority 2 high                       |
| find     | `find WORD`<br/> e.g., find book                                        |
| bye      | `bye`                                                                   |
