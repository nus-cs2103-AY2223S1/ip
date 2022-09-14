# User Guide

Falcon is a planner app for aviculturists, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
It has been designed to improve syntax friendliness.

## Quick Start

1. Download the latest duke.jar file.
2. Go to the folder on your local storage where the program was downloaded.
3. For the best experience, open the jar file in IntelliJ.
4. Click run and begin interacting with falcon.
5. Try some simple commands such as:
   - `task t1` then `list` to see the task t1 added to your list.
   - `mark 1` then `list` to see the previously created task as marked done.
   - `bye` to exit the program.
6. Refer to the Features list below for more command details.

## Features 

Note: All commands are case insensitive

1. Basic Commands

### Adding tasks: `task`

&nbsp;&nbsp;Add a task to your planner.

&nbsp;&nbsp;Has aliases `t` and `todo`

&nbsp;&nbsp;Format: `task|t|todo taskDescription`

### Adding Deadlines: `deadline`

&nbsp;&nbsp;Add a deadline to your planner.

&nbsp;&nbsp;Every deadline should have a due date.

&nbsp;&nbsp;Has alias `d`.

&nbsp;&nbsp;Format: `deadline|d deadlineDescription date<YYYY-MM-DD>`

### Adding Events: `event`

&nbsp;&nbsp; Add an event to your planner.

&nbsp;&nbsp;Every event should have a date.

&nbsp;&nbsp;&nbsp;&nbsp;Has alias `e`.

&nbsp;&nbsp;Format: `event|e eventDescription date<YYYY-MM-DD>`

### Viewing list: `list`

&nbsp;&nbsp;View all tasks, events, and deadlines in your planner.

&nbsp;&nbsp;Has alias `l`

&nbsp;&nbsp;Format: `list|l`

### Delete item: `delete`

&nbsp;&nbsp;Delete an item from your planner. 

&nbsp;&nbsp;You need to provide the index of the task to be deleted.

&nbsp;&nbsp;This index is the position at which the task appears in your planner list.

&nbsp;&nbsp;Suggested: use list command to check the index required.

&nbsp;&nbsp;Had aliases: "d", "remove", "r"

&nbsp;&nbsp;Format: `delete|d|remove|r index`

### Exit command: `exit`

&nbsp;&nbsp;Close the planner.

&nbsp;&nbsp;Has aliases "b", "quit", "q", "bye" 

&nbsp;&nbsp;Format: `exit|b|bye|quit|q`

### Help command

&nbsp;&nbsp;Shows a list of all commands supported by Falcon.

&nbsp;&nbsp;Has alias "h".

&nbsp;&nbsp;Format: `h|help`

2. Intermediate Commands

### Mark command: `mark`

&nbsp;&nbsp;Mark task at index as completed. See delete command for a more thorough explanation of an index.

&nbsp;&nbsp;Has alias "m"

&nbsp;&nbsp;Format: `mark|m index`

### Unmark command: `unmark`

&nbsp;&nbsp;Mark task at index as not completed. See delete command for a more thorough explanation of an index.

&nbsp;&nbsp;Has alias "um"

&nbsp;&nbsp;Format: `unmark|um index`

### Find command `find`

&nbsp;&nbsp;Returns all tasks in the planner that contain the given keyword in their description.

&nbsp;&nbsp;This helps speed up search operation on the planner.

&nbsp;&nbsp;Note: indices shown in the returned are not the actual indices as stored in the planner. Commands that rely on indices will not work as expected.

&nbsp;&nbsp; Has alias "f"

&nbsp;&nbsp;Format: `find|f keyword`

### LongDesc command: `longdesc`

&nbsp;&nbsp; Returns a comprehensive description of the task at the given index

&nbsp;&nbsp; Format: `longdesc index`

### isToday command: `istoday`

&nbsp;&nbsp; Returns true if task given at index in the planner is due today.

&nbsp;&nbsp; Format: `istoday index`
