# User Guide: Duke 
Duke is an interactive task management chatbot that allows you to add, edit and delete your tasks, and stores tasks into storage. 

- [Installation Guide](#quick-start)
- [Features](#features)

## Installation Guide 
1. Ensure you have Java `11` or above installed in your device. 
2. Download the latest duke.jar attached to the latest release.
3. Launch terminal in that folder
4. Run the command `java -jar duke.jar`

## Features 
### Add tasks 
There are three types of tasks you can create:
- ToDos (command: `todo <todo name>`)
- Events (command: `event <event name> /at <location>`)
- Deadlines (command: `deadline <deadline name> /by <date in YYYY-MM-DD>`)

### Delete tasks 
Delete an existing task in the list. (command: `delete <task index>`)

### Mark a Task 
Mark an existing task in the list as done. (command: `mark <task index>`)

### Unmark a Task
Unmark an existing task in the list as done. (command: `unmark <task index>`)

### List all Tasks
List all tasks in the list. (command: `list`)

### Find Tasks
Lists all tasks containing the keyword. (command: `find <keyword>`)

### Exit 
Exit the program and saves existing tasks into local storage. (command: `bye`)



