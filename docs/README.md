# User Guide: Anya
:peanuts: Anya :peanuts: is a desktop application that helps you keep track :heavy_check_mark: of your tasks. 
It utilises the Command Line Interface (CLI) :keyboard: and intuitive commands to 
provide a quick and easy way to manage your tasks. 

Say :wave: goodbye to forgetting tasks and missing deadlines!

- Quick Start
- Features & Usage
  - Adding a task
  - Marking a task as complete or incomplete
  - Deleting a task
  - Listing all tasks
  - Finding a task using keyword
  - Clearing all data and creating a new list
  - Getting the total number of tasks completed
  - Exiting the program
  - Saving the data

## Quick Start
1. Ensure you have java 11 or above installed in your Computer.
2. Download the latest Anya.jar from here.
3. Navigate to the folder containing the Anya.jar file.
4. Double-click the file to start the app.
5. Congratulations! You have successfully installed and open Anya.
Try typing some commands and press Enter to execute it. 

The list of available commands can be found under Feature & Usage

## Features & Usage
### Adding a task

Adds a task to the list of tasks. There are three types of tasks as shown in the table below:

| Type of Task | Detail | Command |
| ------------ | ------ | ------- |
| Todo | A task without any date or time attached to it | `todo <description>` |
| Deadline | A task that needs to be completed before a specific date & time| `deadline <description> /by <date> <time>'` |
| Event | A task that has additional information about the location | `event <description> /at <location>` |

Format:
- `<description>` has no required formatting
- `<date>` is in the format dd/MM/yyyy. Example for valid date: `01/01/2022`
- `<time>` is in the format hhmm. Example for valid time: `0800` or `2030`
- `<location>` has no required formatting

Example usage: 
- `todo clean and tidy up room`
- `deadline assignment 1 /by 01/10/2022 2359`
- `event birthday dinner /at japanese restaurant`

### Marking a task as complete or incomplete
Changes the status of a incompleted task to complete and vice versa. 

Command:
- `mark <task index>` marks the task at the specified index 
- `unmark <task index>` unmarks the task at the specified index

Format:
- `<task index>` to the index number shown in the task list. The index must be a positive integer

Example usage:
- `mark 1`
- `unmark 1`

### Deleting a task
Deletes a task from the list of tasks at the specified index.

Command:
`delete <task index>` 

Format:
`<task index>` to the index number shown in the task list. The index must be a positive integer

Example usage:
`delete 1`

### Listing all tasks
Displays a list of your current tasks.

Command:
`list`

### Finding a task using keyword
Finds all matching tasks containing the keyword. The keyword is case sensitive.

Command:
`find <keyword>` 

Format:
`<keyword>` has no required formatting

Example usage:
`find assignment`

### Clearing all data and creating a new list
Clears all existing data and creates a new empty task list. 

Command: 
`new`

### Getting the total number of tasks completed.
Gets the total number of tasks completed, including the deleted tasks. 
It keeps track of all completed tasks since the task list was created. 

Command:
`statistics`

### Exiting the program
Exits the application and saves the current data.

Command:
`bye`
