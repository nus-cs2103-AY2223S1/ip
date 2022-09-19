# User Guide

KKBot is a desktop app that helps the individual keep track of their tasks! 

It uses a Command Line Interface (CLI) and allows the user to interact with it like a chatbot!

## Features

KKBot supports a variety of commands to help you keep track of your tasks **easily and efficiently**!

Currently, KKBot supports these features:
1. Adding tasks (of 3 different types) to the list
2. Listing out all tasks
3. Deleting a task from the list
4. Finding tasks that contain keywords

## Usage

### Adding a task: 'todo/deadline/event'

Adds a task (of a specified type) to your task list.

Format:
- 'todo <description>'
- 'deadline <description> /by DD/MM/YYYY HHMM'
- 'event <description> /at DD/MM/YYYY HHMM'

Example: 'deadline do homework /by 01/10/2022 2359'

### Listing out tasks: 'list'

Lists out all tasks that have been added.

Format: 'list'

Example: 'list'

### Marking a task as complete/incomplete: 'mark/unmark'

Toggles the completion status of a specified task.

Format:
- 'mark <task serial number>'
- 'unmark <task serial number>'

Example: 'unmark 3'

### Deleting a task: 'delete'

Removes a task from the list.

Format: 'delete <task serial number'

Example: 'delete 2'

### Finding tasks: 'find'

Retrieves and displays all tasks containing the input phrase(s).

Format: 'find <phrase>'

Example: 'find meeting'

### Exiting KKBot: 'bye'

Closes KKBot program.

Format: 'bye'

Example: 'bye'
