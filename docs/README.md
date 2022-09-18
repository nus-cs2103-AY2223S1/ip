# User Guide
Clevernotbot is a **free desktop java app for managing your task, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

- Quick Start
- Features
    - [Viewing help: `help`](#viewing-help-help)
    - [Finding tasks: `find`](#finding-tasks-find)
    - [Marking a task: `mark`](#marking-a-task-mark)
    - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    - [Listing out all tasks: `list`](#listing-out-all-tasks-list)
    - [Adding a todo Task: `todo`](#adding-a-todo-task-todo)
    - [Adding a event Task: `event`](#adding-a-event-task-event)
    - [Adding a deadline Task: `deadline`](#adding-a-deadline-task-deadline)
    - [Deleting a selected Task: `delete`](#deleting-a-selected-task-delete)
    - [Greet user: `greet`](#greet-user-greet)
    - [Ending chat with chatbot: `bye`](#ending-chat-with-chatbot-bye)
- [FAQ](#FAQ)
- [Command Summary](#Command-summary)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest cleverNotBot.jar from [here](https://github.com/LolfoollorS/ip/releases).
3. Double-click the file to start the app!
4. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.
   - `list` : Lists all tasks.
   - `todo sleep at 9p.m.`: Adds a task to sleep at 9 p.m. . 
   - `deadline super sekrit project /by 02-12-2018 18:00`: Adds a deadline for super sekrit project.
   - `event jar releasing /at today 9p.m.`: Adds an event for jar releasing at 9p.m..
   - `delete 3` : Deletes the 3rd task shown in task list.
   - `undo` : Undo the previous deletion and adding of task.
   - `bye` : Chatbot exits the room. 
5. Refer to the Features below for details of each command.
## Features 

### Viewing help: `help`
Shows a message explaining how to access the help page.

Format: `help`
### Finding tasks: `find`
Finds a task that contains the keyword

Format: `find KEYWORD` 

Examples:
- `find book`
- `find All Hail the great bot`

### Marking a task: `mark`
Marks a task in the task list.

Format: `mark INDEX`
- Marks the task at the specified INDEX. The index refers to the index number shown in the task list. The index must be a positive integer 1, 2, 3, …

Example: 
- `mark 1`
- `mark 10`
### Unmarking a task: `unmark`
Unmarks a task in the task list.

Format: `unmark INDEX`
- Unmarks the task at the specified INDEX. The index refers to the index number shown in the task list. The index must be a positive integer 1, 2, 3, …

Example:
- `unmark 1`
- `unmark 10`
### Listing out all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

Example:
- `list`
### Adding a todo Task: `todo`
Adds a todo task into the task list.

Format: `todo TODOTASKNAME`

Example:
- `todo going to go to airport and take a picture of the famous airport`
### Adding a event Task: `event`
Adds an event task into the task list.

Format: `event EVENTTASKNAME /at EVENTTIME `

Example:
- `event jar releasing /at today 9p.m.`
### Adding a deadline Task: `deadline`
Adds a deadline task into the task list.

Format: `deadline DEADLINETASKNAME /by TIMEIN_dd-MM-yyyy_HH:mm`

Example:
- `deadline super sekrit project /by 02-12-2018 18:00`
### Deleting a selected Task: `delete`
Deletes a task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified INDEX. The index refers to the index number shown in the task list. The index must be a positive integer 1, 2, 3, …

Example:
- `delete 1`
- `delete 3`
### Greet user: `greet`
Greets User.

Format: `greet`

Example:
- `greet`
### Ending chat with chatbot: `bye`
Ends the chat with chatbot.

Format: `bye`

Example:
- `bye`

## Saving the data
Task list are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data
CleverNotBot save data is saved at `/data/cleverNotBot.txt` in txt format.
> :exclamation: **Caution**: If your changes to the data file makes its format invalid, the program will not run!

## FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CleverNotBot home folder.

## Command summary
| Action   | Format, Examples                                                                                                       |
|----------|------------------------------------------------------------------------------------------------------------------------|
| Help     | `help`                                                                                                                 |
| Find     | `find KEYWORD` <br/> e.g.,`find book`                                                                                  |
| Mark     | `mark INDEX` <br/> e.g.,`mark 1`                                                                                       |
| Unmark   | `unmark INDEX` <br/> e.g.,`unmark 1`                                                                                   |
| List     | `list`                                                                                                                 |
| Todo     | `todo TODOTASKNAME` <br/> e.g.,`todo read book`                                                                        |
| Event    | `event EVENTTASKNAME /at EVENTTIME ` <br/> e.g.,`event jar releasing /at today 9p.m.`                                  |
| Deadline | `deadline DEADLINETASKNAME /by TIMEIN_dd-MM-yyyy_HH:mm`<br/> e.g.,`deadline super sekrit project /by 02-12-2018 18:00` |
| Delete   | `delete INDEX`<br/> e.g.,`delete 1`                                                                                    |
| Greet    | `greet`                                                                                                                |
| Bye      | `bye`                                                                                                                  |
