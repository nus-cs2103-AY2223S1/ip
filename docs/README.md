# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line 
Interface (CLI). If you can type fast, Duke can get your task management done
faster than traditional GUI apps.

## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the _home folder_.
4. Double-click the file to start the app. 
5. Refer to the Features below for details of each command.

## Features 

:left_speech_bubble: Notes about the command format:

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be
used as `todo read book`.
- Extraneous parameters for commands that do not take in parameters
  (such as `list` and `bye`) will be ignored.
e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Adding a task: `todo` `deadline` `event`

Duke supports tracking three types of tasks:

1. To-do: task without any date/time attached to it *_e.g., visit new theme park_* 
:memo:
2. Deadline: task that need to be done before a specific date *_e.g., submit
report by 11/10/2019_* :calendar:
3. Event: task that start at a specific time and ends at a specific time *_e.g., 
team project meeting on 2/10/2019 2-4pm_* :alarm_clock:

Format: `todo DESCRIPTION`, `deadline DESCRIPTION /by DATE`,  
`event DESCRIPTION /at DATETIME`

*:warning: `DATE` in deadline can only take in date in "yyyy-MM-dd" format, e.g., 
2022-12-06*

Examples:
- todo read book
- deadline return book /by 2022-12-06
- event project meeting /at Aug 6th 2-4pm

### Listing all tasks: `list`

Shows a list of all tasks in Duke.

Format: `list`

### Marking/Unmarking a task as completed: `mark` `unmark`

Marks/Unmarks a task in Duke as done/not done yet.

Format: `mark INDEX`, `unmark INDEX`

:left_speech_bubble: `INDEX` follows 1-based indexing and should be an integer.

### Deleting a task: `delete`

Deletes a task in Duke.

Format: `delete INDEX`

:left_speech_bubble: `INDEX` follows 1-based indexing and should be an integer.

### Finding task by name: `find`

Finds any task which the description contains the exact given keyword(s).

Format: `find KEYWORDS`

- The search is case-insensitive. e.g., `book` will match `Book`
- The search is partial matching. e.g., `oo` will match `book`

### Archiving tasks: `archive`

Archive the tasks saved in Duke to back-up file.

Format: `archive`

:warning: After this operation, all the tasks in Duke will be cleared!

### Exiting the program: `bye`

Exits the program.

Format: `bye`

### Saving the data:

Duke data are saved in the hard disk automatically after any command. There is 
no need to save manually.

### Editing the data file

Duke data and back-up data are saved as a txt file in 
`[JAR file location]/duke.txt` and `[JAR file location]/duke-backup.txt` respectively.
Advanced users are welcome to update data directly by editing that data file.

:warning: If your changes to the data file makes its format invalid, Duke will 
discard all data and start with an empty data file at the next run.

### Retrieving data from archive [coming in v2.0]

Details coming soon...