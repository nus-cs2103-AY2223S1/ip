# User Guide

## Quick Start 

1. Ensure that you have Java `11` installed on your computer.
2. Download the latest duke.jar from here.
3. Double-click the file to start the app. The GUI should appear in a few seconds.
4. Type the command in the command box and press Enter to execute it. e.g. typing `--help` and pressing Enter will execute the help command.
   Some example commands you can try:

   * `list` : Lists all the tasks.

   * `todo coding homework` : Adds a Todo task to the list.

   * `mark 1` : Marks the 1st task as completed.

   * `delete 1` : Deletes the 1st task in the list.

   * `bye` : Terminates the Chat Bot.
   
5. Refer the Features below for details on each command.

## Features

### `todo` - Adds a Todo task.

Creates a Todo Task and adds it into the list of tasks.

Format:

`todo (Task Description)`

Example of usage: 

`todo Literature Essay`

Expected outcome:

A Todo task is created and stored in the list of tasks.

```
________________________________________
Got it. I have added this task:
  [T][] Literature Essay
Now you have 1 task in the list.
________________________________________
```
### `deadline` - Adds a Deadline task.

Creates a Deadline Task and adds it into the list of tasks. Use the '/by' followed
by the date format of ```YYYY-MM-DD``` (e.g., ```2022-11-22```)to generate it in the 
different format such as ```MMM dd yyyy``` e.g., (```Nov 22 2022```).

Format:

`deadline Task Description /by YYYY-MM-DD` or `deadline Task Description /by Additional Information`

Example of usage: 

1. `deadline Literature Essay /by 2022-11-22`

2. `deadline Literature Essay /by tmr 2359`

Expected outcome:

1. A Deadline task is created and stored in the list of tasks (Date formatted).

```
________________________________________
Got it. I have added this task:
  [D][] Literature Essay (by: Nov 22 2022)
Now you have 2 tasks in the list.
________________________________________
```

2. A Deadline task is created and stored in the list of tasks (without formatting).

```
________________________________________
Got it. I have added this task:
  [D][] Literature Essay (by: tmr 2359)
Now you have 2 tasks in the list.
________________________________________
```

### `event` - Adds an Event task.

Creates an Event Task and adds it into the list of tasks. Use the '/at' followed
by the date format of ```YYYY-MM-DD``` (e.g., ```2022-09-20```)to generate it in the 
different format such as ```MMM dd yyyy``` e.g., (```Sep 20 2022```).

Format:

`event Task Description /at YYYY-MM-DD` or `event Task Description /at Additional Information`

Example of usage: 

1. `event Coldplay Concert /at Stadium`

2. `event Coldplay Concert /at 2022-09-20`

Expected outcome:

1. An Event task is created and stored in the list of tasks (without formatting).

```
________________________________________
Got it. I have added this task:
  [E][] Coldplay Concert (at: Stadium)
Now you have 3 tasks in the list.
________________________________________
```

2. An Event task is created and stored in the list of tasks (Date formatted).

```
________________________________________
Got it. I have added this task:
  [E][] Coldplay Concert (at: Sep 20 2022)
Now you have 3 tasks in the list.
________________________________________
```

### `list` - Lists all the tasks.

Prints out all the tasks within the list.

Format:

`list`

Example of usage: 

`list`

Expected outcome:

All the tasks within the list are printed.

```
________________________________________
Here are the tasks in your to-do list:
1. [T][] Literature Essay
2. [D][] Literature Essay (by:Nov 22 2022)
3. [E][] Coldplay Concert (at: Stadium)
________________________________________
```

### `mark` - Marks a task as completed.

Marks a specified task number as completed.

Format:

`mark (Task Number)`

Example of usage: 

`mark 1`

Expected outcome:

Task number 1 in the list will be marked with an 'X' to indicate that it
is completed.

```
________________________________________
Good Job! I have marked this task as done:
  [T][X] Literature Essay
________________________________________
```

### `unmark` - Marks a task as uncompleted.

Marks a specified task number as uncompleted.

Format:

`unmark (Task Number)`

Example of usage: 

`unmark 1`

Expected outcome:

Task number 1 in the list will be marked with a '' to indicate that it
is uncompleted.

```
________________________________________
Alright! I have marked this task as not done yet:
  [T][] Literature Essay
________________________________________
```

### `find` - Search for tasks with matching keywords.

Searches the list for tasks with matching keywords. You may add additional keywords
by separating them with a `,`.

Format:

`find (Keyword)` or `find (Keyword1, Keyword2, ...)`

Example of usage: 

1. `find Literature`

2. `find Literature, swimming`

Expected outcome:

1. Tasks with the keyword `Literature` will be collated and printed

```
________________________________________
Here are the matching tasks in your to-do list:
1. [T][] Literature Essay
2. [D][] Literature Essay (by:Nov 22 2022)
________________________________________
```

2. Tasks with the keyword `Literature` and `swimming` will be collated and printed

```
________________________________________
Here are the matching tasks in your to-do list:
1. [T][] Literature Essay
2. [D][] Literature Essay (by:Nov 22 2022)
4. [T][] swimming with friends
________________________________________
```

### `delete` - Deletes a task.

Deletes the specified task from the list.

Format:

`delete (Task Number)`

Example of usage: 

`delete 1`

Expected outcome:

Task number 1 will be deleted from the list.

```
________________________________________
Noted. I have removed this task:
  [T][] Literature Essay
Now you have 3 tasks in the list.
________________________________________
```

### `undo` - Returns the Chat Bot to the state before executing the previous command.

It will undo the previous command executed, returning the Chat Bot to its previous state. This
only works for commands such as `delete`, `todo`, `deadline`, `event`, `mark` and `unmark`.

Format:

`undo`

Example of usage: 

`undo`

Expected outcome:

The Chat Bot will return to the state before executing the previous command. The outcome
depends on the type of command executed, the example below indicates `undo` on a delete command.

```
________________________________________
Undo Complete! I added back the deleted task.
________________________________________
```

### `--help` - Prints the list of commands available.

Prints out the list of commands and simple description of its functions.

Format:

`--help`

Example of usage: 

`--help`

Expected outcome:

A list of commands and its basic description will be printed out.

```
________________________________________
list -> list out all the tasks
todo -> creates a todo task
deadline -> creates a deadline task, add /by to specify deadline
event -> creates an event task, add /at to specify location/date
mark -> marks a task as done in the task list
unmark -> marks a task as not done in the task list
delete -> removes the task from the task list
find -> searches for task with the same keyword
bye -> end and close the ChatBot
________________________________________
```

### `bye` - Ends the Chat Bot.

Terminates the Chat Bot.

Format:

`bye`

Example of usage: 

`bye`

Expected outcome:

The Chat Bot will be terminated 5 seconds after execution of `bye` command.

```
________________________________________
Sayonara! Mata ne!
--- The window will close in 5 seconds ---
________________________________________
```
