# User Guide

## Features 

### Feature-Echo

Copies the user's input and prints it.

### Feature-Todo

Adds a "To-do" type task to the task list. A "To-do" type task has no specific completion time.

### Feature-Deadline

Adds a "Deadline" type task to the task list. A "Deadline" type task has a specific deadline by which it must be completed.

### Feature-Event

Adds an "Event" type task to the task list. An "Event" type task has a specific date when it occurs. It may also be configured to have tentative dates, from which any can be chosen as the confirmed date.

### Feature-Mark

Marks a task as complete.

### Feature-Unmark

Marks a task as incomplete.

### Feature-Delete

Deletes the task at a given index from the task list.

### Feature-Find

Finds a task in the task list that matches the given keywords. Supports multiple keywords and partial matches.

### Feature-List

Displays the current task list.

### Feature-Tentative

Allows the user to either add tentative dates to an "Event" type task or confirms a date for an "Event" type task.

### Feature-Interact

Allows the user to have a "conversation" with Henry. Supports teaching Henry the meaning of words.

## Usage

### `bye` - The command that will exit the program

The task list will be saved to a text file on the user's desktop, named "henry.txt". The program will exit.

Example of usage: 

`bye`

Expected outcome:

The program will exit.

```
Goodbye! Your task list has been saved!
```

### `echo` - The command that activates Henry's "Echo" feature

Echo will copy the user's input and display it back to the user through the GUI.

Example of usage: 

`echo hello`

Expected outcome:

"hello" will be printed to the GUI.

```
hello
```

### `todo` - The command that activates Henry's "To-do" feature

Todo will create a new "To-do" type task in the task list.

Example of usage: 

`todo read book`

Expected outcome:

"read book" will be added to the task list as a new "To-do" task.

```
OK, I added this task to my list:
 [T][ ] read book
```

### `deadline` - The command that activates Henry's "Deadline" feature

Deadline will create a new "Deadline" type task in the task list.

Example of usage: 

`deadline read book /by 14 Jul 2023 13:00`

Expected outcome:

"read book" will be added to the task list as a new "Deadline" task.

```
OK, I added this task to my list:
 [D][ ] read book (by: 14-07-2023 1:00PM)
```

### `event` - The command that activates Henry's "Event" feature

Event will create a new "Event" type task in the task list.

Example of usage: 

`event read book /at 14 Jul 2023 13:00`

Expected outcome:

"read book" will be added to the task list as a new "Event" task.

```
OK, I added this task to my list:
 [E][ ] read book (at: 14-07-2023 1:00PM)
```

### `mark` - The command that activates Henry's "Mark" feature

Mark will set the status of the task at the given index in the task list to complete.

Example of usage: 

`mark 0`

Expected outcome:

The task at index 0 in the task list will be marked as complete.

```
I've marked this task as done:
 [T][X] read book
```

### `unmark` - The command that activates Henry's "Unmark" feature

Unmark will set the status of the task at the given index in the task list to incomplete.

Example of usage: 

`unmark 0`

Expected outcome:

The task at index 0 in the task list will be marked as incomplete.

```
I've marked this task as not done:
 [T][ ] read book
```

### `delete` - The command that activates Henry's "Delete" feature

The task at the given index in the task list will be deleted.

Example of usage: 

`delete 0`

Expected outcome:

The task at index 0 in the task list will be marked as incomplete.

```
I've deleted this task:
 [T][ ] read book
```

### `find` - The command that activates Henry's "Find" feature

All tasks that match the given keywords will be printed to the GUI. Keywords should be preceded by "--".

Example of usage: 

`find --read --book`

Expected outcome:

The task at index 0 in the task list will be marked as incomplete.

```
I've found these matching tasks:
 1) [D][ ] read book (by: 14-07-2023 1:00PM)
 2) [E][ ] read book (at: 14-07-2023 1:00PM)
```

### `list` - The command that activates Henry's "List" feature

All tasks in the task list are printed to the GUI.

Example of usage: 

`list`

Expected outcome:

The task list will be displayed.

```
Here's your current list:

 1) [D][ ] read book (by: 14-07-2023 1:00PM)
 2) [E][ ] read book (at: 14-07-2023 1:00PM)
```

### `tentative` - The command that activates Henry's "Tentative" feature

Additional dates can be added to "Event" type tasks. Any "Event" type tasks can also confirm their date with this command

Example of usage: 
1) tentative (index) (dateTime)
`tentative 0 14 Aug 2023 13:00`
2) tentative (index) --confirm (chosenDateIndex)
`tentative 0 --confirm 0`

Expected outcome:
Usage 1: A new tentative date will be added to the Event
Usage 2: The chosen date will be set as the confirmed date for the Event

Usage 1:
```
OK, I've added a tentative date for this event:
 [E][ ] read book (at: 14-07-2023 1:00PM, 14-08-2023 1:00PM)
```

Usage 2:
```
Date confirmed! This event has been modified:
 [E][ ] read book (at: 14-08-2023 1:00PM)
```

### `interact` - The command that activates Henry's "Interact" feature

Currently only supports teaching Henry new words and definitions.

Example of usage: 

`interact a potato is a plant`

Expected outcome:

Henry will remember that a potato is a plant. This is persistent through restarting the application.

```
Ok, I will remember a potato is a plant.
```

Example of memory:

`interact what is a potato?`

Expected outcome:

Henry will reply that a potato is a plant.

```
A plant.
```
