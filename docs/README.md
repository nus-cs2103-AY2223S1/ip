# User Guide

Duke is a desktop app for the highest of achievers to manage their tasks.
It is optimised for use via a Command Line Interface (CLI). If you are a hard
worker and a visionary among your contemporaries, Duke can manage your tasks
faster than traditional GUI apps, provided that you can type fast.

## Quick Start

1. Ensure you have Java 11 installed in your computer.
2. Download Duke.jar
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Duke GUI](https://https://github.com/doimoiboi/ip/blob/master/resources/duke-gui.png)

5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features section below for details of the commands.

## Features


### Adding tasks : `todo/deadline/event/doafter`

Adds a task of the specified type to the task list.

Format:
- `todo <description>`
- `deadline <description> /by DD/MM/YYYY HHMM`
- `event <description> /at DD/MM/YYYY HHMM`
- `doafter <description> /after DD/MM/YYYY HHMM`


### List : `list`

Lists out all tasks and their status of completion.

Format: `list`


### Changing status of completion: `mark/unmark`

Changes the status of completion of the specified task to either done
or not done.

Format: 
- `mark <task index>`
- `unmark <task index>`


### Deleting tasks: `delete`

Removes the specified task from the task list.

Format: `delete <task index>`


### Finding tasks: `find`

Displays all the tasks that contain the text that is searched for.

Format: `find <text to find>`


### Exiting Duke: `bye`

Closes the Duke program.

Format: `bye`