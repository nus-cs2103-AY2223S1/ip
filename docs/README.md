# Poolsheen User Guide
Poolsheen is a Java app for managing the happenings in your daily life via a Command Line Interface (CLI) while still
having the benefit of a Graphical User Interface (GUI). The Poolsheen program is catered to users that
can type quickly.

## Features 
Users of the Poolsheen program are able to:
- Say **Bye** and close the program
- **Add** tasks 
- **Delete** tasks 
- **Mark** a task to be done 
- **Unmark** a task to be undone 
- **Find** tasks 
- **Update** taks 
- View the **list** of tasks

## Usage

### `bye` - Closes the program

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

Poolsheen says goodbye to you and the program closes after 2 seconds.

```
Goodbye :(
```

### `todo` - Adds a ToDo task

Format: `todo DESCRIPTION`

Parameters:
- DESCRIPTION: Name of the to do task

Example of usage:

`todo Buy Book`

Expected outcome:

Poolsheen adds a ToDo task with the respective description.

```
Poolsheen now remembers: Buy Book
```

### `deadline` - Adds a Deadline task

Format: `deadline DESCRIPTION /by DATE`

Parameters:
- DESCRIPTION: Name of the deadline
- DATE: Date of the task in YYYY-MM-DD

Example of usage:

`deadline Sell Book /by 2022-01-01`

Expected outcome:

Poolsheen adds a Deadline task with the respective description and time.

```
Poolsheen now remembers: Sell Book
```

### `event` - Adds an Event task
Format: `event DESCRIPTION /by DATE`

Parameters:
- DESCRIPTION: Name of the event
- DATE: Date of the task in YYYY-MM-DD

Example of usage:

`event Get Book /by 2022-01-01`

Expected outcome:

Poolsheen adds an Event task with the respective description and time.

```
Poolsheen now remembers: Get Book
```

### `delete` - Removes a task

Format: `delete POSITION`

Parameters:
- POSITION: Number position of the task to be deleted

Example of usage:

`delete 1`

Expected outcome:

Poolsheen delete the task at position 1.

```
Poolsheen has forgot: Buy Book and now you have 2 tasks left
```

### `mark` - Marks a task as done

Format: `mark POSITION`

Parameters:
- POSITION: Number position of the task to be marked

Example of usage:

`mark 1`

Expected outcome:

Poolsheen marks the task at position 1.

```
Poolsheen thinks you are done with: Buy Book
```

### `unmark` - Marks a task as not done

Format: `unmark POSITION`

Parameters:
- POSITION: Number position of the task to be unmarked

Example of usage:

`unmark 1`

Expected outcome:

Poolsheen unmarks the task at position 1.

```
Poolsheen thinks you are not done with: Buy Book
```

### `update` - Updates the details of a task

Format: 
- `update POSITION desc DESCRIPTION`
- `update POSITION time DESCRIPTION`

Parameters:
- POSITION: Number position of the task to be unmarked

Example of usage:

`update 1 desc Sell Book`

Expected outcome:

Poolsheen updates the task description at position 1 to Sell Book.

```
Poolsheen has updated the task at position 1.
```

### `list` - Displays all tasks currently

Format: `list`

Example of usage:

`list`

Expected outcome:

Displays all tasks currently

```
Poolsheen thinks back... and remembers you said:
      1. [T] [X] Get Book
      2. [D] [-] Sell Book
      3. [E] [X] Find Book
```

### `find` - Displays all tasks that have the keyword

Format: `find KEYWORD`

Parameters:
- KEYWORD: The word that will be used to find tasks which has this word

Example of usage:

`find Get`

Expected outcome:

Displays all tasks that has the word "Get" currently

```
Poolsheen thinks back... and remembers you said:
      1. [T] [X] Get Book
```