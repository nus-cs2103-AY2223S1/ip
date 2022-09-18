# User Guide

GustavoBot is a desktop chatbot application that
manages and stores tasks added by the user. Users
interact with it through a Command Line Interface
(CLI) which also has a Graphical User Interface 
(GUI) that is pleasing to the eye.

Use GustavoBot to be part of the Los Pollos Hermanos
family today!

# Table of Contents

- [Command Summary](#command-summary)
- [Getting Started](#getting-started)
- [Features](#features)

## Command Summary

| Command                                        | Usage                               |
|------------------------------------------------|-------------------------------------|
| [hello](#greeting-you-hello)                   | `hello`                             |
| [list](#displaying-your-tasks-list)            | `list`                              |
| [todo](#adding-a-todo-task-todo)               | `todo [DESCRIPTION]`                |
| [deadline](#adding-a-deadline-task-deadline)   | `deadline [DESCRIPTION] /by [DATE]` |
| [event](#adding-a-event-task-event)            | `event [DESCRIPTION] /at [DATE]`    |
| [mark](#marking-a-task-as-complete-mark)       | `mark INDEX`                        |
| [unmark](#marking-a-task-as-incomplete-unmark) | `unmark INDEX`                      |
| [tag](#tagging-a-task-tag)                     | `tag INDEX KEYWORD`                 |
| [delete](#deleting-a-task-delete)              | `delete KEYWORD`                    |
| [find](#locating-a-task-by-keyword-find)       | `find [KEYWORD]`                    |

## Getting Started

1. Ensure you have Java `11` installed in your computer.
2. Download the latest `duke.jar` from [here](github.com/cxyterence/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the _home folder_ for GustavoBot.
4. Open up your command prompt/terminal and navigate to the _home folder_.
5. Run the file by typing in the command `java -jar duke.jar` and pressing Enter. You can also double click the file to run it. The app should appear in a few seconds.
6. Type in your commands into the text box and press Enter or click the Send button to interact with GustavoBot.
7. Refer to the Command Summary and Features below for a list and details of all commands.

## Features 

### Greeting you: `hello`

Warmly greets and welcomes you.

Format: `hello`

### Displaying your tasks: `list`

Shows a list of all the tasks stored in GustavoBot.

Format: `list`

### Adding a ToDo task: `todo`

Creates a ToDo task and stores in into GustavoBot.

Format: `todo [DESCRIPTION]`

Example: `todo Buy pizza`

### Adding a Deadline task: `deadline`

Creates a Deadline task and stores in into GustavoBot.

Format: `deadline [DESCRIPTION] /by [DATE]`

- Creates a task that is due by `DATE`.
- Supports date formatting when `DATE` is entered in the format `yyyy-mm-dd`.

Example: `deadline Meet Jesse /by 2023-05-18`

### Adding a Event task: `event`

Creates a Event task and stores in into GustavoBot.

Format: `event [DESCRIPTION] /at [DATE]`

- Creates a task that occurs on `DATE`.
- Supports date formatting when `DATE` is entered in the format `yyyy-mm-dd`.

Example: `event Meet Hank /at 2023-05-18`

### Marking a task as complete: `mark`

Marks a specified task as complete.

Format: `mark INDEX`

- Marks the task at the specified `INDEX` as complete.
- The index refers to the index number shown in the displayed task list.
- The index __must be a positive integer 1, 2, 3, ...__

Example: `mark 3`

### Marking a task as incomplete: `unmark`

Marks a specified task as incomplete.

Format: `unmark INDEX`

- Marks the task at the specified `INDEX` as incomplete.
- The index refers to the index number shown in the displayed task list.
- The index __must be a positive integer 1, 2, 3, ...__

Example: `unmark 3`

### Tagging a task: `tag`

Tags a specified task with a keyword.

Format: `tag INDEX KEYWORD`

- Tags the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index __must be a positive integer 1, 2, 3, ...__

Example: `tag 3 cook`

### Deleting a task: `delete`

Deletes a specified task from GustavoBot.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index __must be a positive integer 1, 2, 3, ...__

Example: `delete 2`

### Locating a task by keyword: `find`

Find tasks which descriptions contain the given keyword.

Format: `find [KEYWORD]`

- The search is case-sensitive.
- The order of the keywords matter.

Example: `find Gus`

### Saving the data

GustavoBot data are saved in the hard disk automatically, there is no need to save manually.
