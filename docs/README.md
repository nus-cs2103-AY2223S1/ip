# User Guide: JRH2000
JRH2000 is a desktop application which **keeps track of the tasks that the user performs**. 

## Before You Start
1. The application runs on ```Java 11```, be sure to have it installed in your computer
2. Download the latest JAR file to run the application

## Features 

### Listing tasks - ```list```

Displays a list of all the tasks you currently have.

Format: ```list```

### Reminding upcoming tasks - ```remind```

Displays a list of all the upcoming tasks to be done within 7 days.

Format: ```remind```

### Marking a task - ```mark```

Marks a task as done.

Format: ```mark INDEX```

* Marks the task at the specified ```INDEX```. The index refers to the index number shown in the displayed task list.
The index **must be a positive integer** 1, 2, 3...
* The ```INDEX``` must be provided

Examples:

* ```mark 1``` Marks the task at the index 1.


### Unmarking a task - ```unmark```

Unmarks a task as done.

Format: ```unmark INDEX```

* Unmarks the task at the specified ```INDEX```. The index refers to the index number shown in the displayed task list.
  The index **must be a positive integer** 1, 2, 3...
* The ```INDEX``` must be provided

Examples:

* ```unmark 1``` Unmarks the task at the index 1.


### Locating tasks by keyword: ```find```

Finds tasks whose descriptions which contain the given keyword.

Format: ```find KEYWORD```

* The search is case-sensitive
* The order of the keywords matter
* The ```KEYWORD``` must be provided

Examples:
* ```find hello``` returns ```[T][ ] say hello to someone```

### Deleting a task: ```delete```

Deletes a specified task.

Format: ```delete INDEX```

* Deletes the task at the specified ```INDEX```. The index refers to the index number shown in the displayed task list.
  The index **must be a positive integer** 1, 2, 3...
* The ```INDEX``` must be provided

Examples:
* ```delete 1``` Deletes the task at the index 1.


### Adding a Todo: ```todo```
Adds a Todo.

Format: ```todo DESCRIPTION```
* Adds a Todo with the specified ```DESCRIPTION```
* The ```DESCRIPTION``` must be provided

Examples:
* ```todo eat lunch```


### Adding an Event: ```event```
Adds an Event.

Format: ```event DESCRIPTION /at TIME```

* Adds an Event with the specified ```DESCRIPTION``` and ```TIME```
* The ```DESCRIPTION``` and ```TIME``` must be provided
* ```TIME``` is in the format ```YYYY-MM-DD```

Examples:
* ```event eat lunch /at 2022-09-16```

### Adding a Deadline: ```deadline```
Adds a Deadline.

Format: ```deadline DESCRIPTION /by TIME```

* Adds a Deadline with the specified ```DESCRIPTION``` and ```TIME```
* The ```DESCRIPTION``` and ```TIME``` must be provided
* ```TIME``` is in the format ```YYYY-MM-DD```

Examples:
* ```deadline eat lunch /by 2022-09-16```

### Exiting the Program: ```bye```

Exits the program.

Format: ```bye```

## Command Summary
| Action      | Format, Examples |
| ----------- | ----------- |
| List        | ```list```       |
| Remind      | ```remind```       |
| Mark        | ```mark INDEX```, e.g. ```mark 1```        |
| Unmark        | ```unmark INDEX```, e.g. ```unmark 1```        |
| Find   | ```find KEYWORD```, e.g. ```find hello```        |
| Delete      | ```delete INDEX```, e.g. ```delete 1```       |
| Add Todo   | ```todo DESCRIPTION```, e.g. ```todo eat lunch```        |
| Add Event      | ```event DESCRIPTION /at TIME```, e.g. ```event eat lunch /at 2022-09-16```       |
| Add Deadline   | ```deadline DESCRIPTION /by TIME```, e.g. ```deadline eat lunch /by 2022-09-16```        |
| Exit      | ```bye```       |
