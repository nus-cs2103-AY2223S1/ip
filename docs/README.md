# User Guide

Need help managing your tasks? Let Charmposter do it for you!


## Features 


### Add a todo: ```todo```

Adds a todo to your task list.

Format: ```todo DESCRIPTION```

Examples:

- ```todo fishing```
- ```todo running laps```


### Add a deadline: ```deadline```

Adds a deadline to your task list.

Format: ```deadline DESCRIPTION /by YYYY-MM-DD```

Examples:

- ```deadline do homework /by 2022-09-15```
- ```deadline pay bills /by 2022-01-09```


### Add an event: ```event```

Adds an event to your task list.

Format: ```event DESCRIPTION /at START_DATE END_DATE ```

- Format of dates must be in ```YYYY-MM-DD```

Examples:

- ```event career fair /at 2022-09-15 2022-09-20```
- ```event marathon registration /at 2021-03-10 2021-03-25```


### List all tasks: ```list```

Shows all the tasks in your task list.

Format: ```list```


### Delete a task: ```delete```

Deletes a task from your task list.

Format: ```delete INDEX```

- Deletes the task at the specified ```INDEX```.
- The index refers to the numbers shown in the displayed task list.

Examples:

- ```delete 1```


### Mark a task as done: ```mark```

Marks a task from your task list as done.

Format: ```mark INDEX```

- Marks the task at the specified ```INDEX``` as done.
- The index refers to the numbers shown in the displayed task list.

Examples:

- ```mark 1```


### Mark a task as not done: ```unmark```

Marks a task from your task list as not done.

Format: ```unmark INDEX```

- Marks the task at the specified ```INDEX``` as not done.
- The index refers to the numbers shown in the displayed task list.

Examples:

- ```unmark 1```


### Search tasks by description: ```find```

Finds tasks which have descriptions that contain the keyword.

Format: ```find KEYWORD```

- The search is **case-insensitive**. e.g fishing will match Fishing.
- The index refers to the numbers shown in the displayed task list.
- The ```KEYWORD``` must form a single part or whole of a task's description. e.g. ```run laps```will not find ```run 10 laps```.
- Only the description is searched.

Examples:

- ```find fishing```
- ```find marathon registration```
- ```find running laps```


### Undo the last change: ```undo```

Reverts the last command that made changes to the task list.

Format: ```undo```

- Only the **most recent** change can be reverted.

Examples:

- ```undo```


### Exit the program: ```bye```

Exits the program.

Format: ```bye```


### Saving the data:

Charmposter data is saved in the hard disk automatically after any command that changes the data.
<br>
There is no need to save manually.


### Editing the data:

Charmposter data is saved as a txt file ```[JAR file location]/duke.txt```. 
Advanced users are welcome to update data directly by editing that data file.