# User Guide

KKBot is a desktop app that helps the individual keep track of their tasks! 

It uses a Command Line Interface (CLI) and allows the user to interact with it like a chatbot!

## Quick Start
To get start, please follow these steps:
1. Ensure you have Java11 installed on your computer (If you don't, click [here](https://www.openlogic.com/openjdk-downloads)!)
2. Download KKBot.jar
3. Copy the file to a folder of your choice!
4. Start the jar file to run the app! You should see a window like the one below:

![KKBot GUI](Ui.png)

5. You can now use KKBot to help you track your tasks!
6. For a list of all available commands, please refer to the section below!

## Features

KKBot supports a variety of commands to help you keep track of your tasks **easily and efficiently**!

Currently, KKBot supports these features:
1. Adding tasks (of 3 different types) to the list
2. Listing out all tasks
3. Deleting a task from the list
4. Finding tasks that contain keywords

## Usage

### Adding a task: 'todo/deadline/event/doafter'

Adds a task (of a specified type) to your task list.

Format:
- 'todo <description>'
- 'deadline <description> /by yyyy-MM-dd'
- 'event <description> /at yyyy-MM-dd'
- 'doafter <description> /after yyyy-MM-dd'

Example: 'deadline do homework /by 2022-10-01'

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
