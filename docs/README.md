# Deadline Duck

## Features 
**Deadline Duck** is a Personal Assistant Chatbot 
that helps you keep track of your upcoming 
tasks, deadlines! Sometimes, when adding tasks, you may be some 
additional thoughts in your mind. Therefore, **Deadline Duck** allows you 
to pen your thoughts with our Notes feature too!

Types of tasks:
* Todos : simple tasks to be completed
* Deadlines : tasks that need to be completed by a certain date
* Events : tasks that are scheduled to happen at a certain date
* Notes : notes that you can add to keep track of your thoughts

## Features

* Add Tasks such as Todo, Events and Deadlines

## Usage

### Adding a new Todo Task: `todo`

Adds a new `todo` task to the task list.

Format: `todo <TASK_DESCRIPTION>`

### Adding a new Event Task: `event`

Adds a new `event` task to the task list.

Format: `event <TASK_DESCRIPTION> /at <YYYY-MM-DD>`

### Adding a new Deadline Task: `deadline`

Adds a new `todo` task to the task list.

Format: `deadline <TASK_DESCRIPTION> /by <YYYY-MM-DD>`

### Create a new Note: `note`

Creates a new note

Format: `note <NOTE_DESCRIPTION>`

### List out all Tasks and Notes: `list`

Lists out all your tasks

Format: `list`

### Search for task with a keyword: `find`

Finds all task with the matching description

Format: `find <QUERY>`

### Postpones a task: `postpone`

Postpones a task by 1 day

Format: `postpone <TASK_INDEX>`

### Mark a task as done: `mark`

Marks a task as done (Only for `Events` and `Deadlines`)

Format: `mark <TASK_INDEX>`

### Mark a task as done: `unmark`

Unmarks a task (Only for `Events` and `Deadlines`)

Format: `unmark <TASK_INDEX>`

### Mark a task as done: `delete`

Deletes a task or note

Format: `delete <TASK_INDEX>`

### Quack back at you: `quack`

Watch **DeadlineDuck** quack back at you

Format: `quack`

### Quack back at you: `help`

Shows the list of supported commands

Format: `help`

### Exit the application: `bye`

Exits the application

Format: `bye`