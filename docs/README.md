# User Guide
Duke is a to-do list that helps the user to keep track of tasks, and is operated by a sleek looking GUI.
## Features
- Add tasks
- Delete tasks
- Mark tasks as done
- Mark tasks as not done 
- List all current tasks
- Find task by keyword
- Detect clashing tasks

Note about command format: words in ```UPPER_CASE``` are the parameters to be supplied by the user.

### Add tasks
Adds either a todo item, deadline, or event to the to-do list.
##### TODO Task
Adds a todo item to the list.
Format: ```todo TASK```

Examples:
- ```todo clean room```
- ```todo shower```

##### DEADLINE Task
Adds a deadline item to the list.
Format: ```deadline TASK /by YYYY-MM-DD```

Examples:
- ```deadline clean room /by 2022-09-16```
- ```deadline shower /by 2022-09-16```

##### EVENT Task
Adds a event item to the list.
Format: ```event TASK /at YYYY-MM-DD```

Examples:
- ```event clean room /at 2022-09-16```
- ```event shower /at 2022-09-16```


### Delete tasks
Delete an existing task from the to-do list.

Format: ```delete INDEX```

Example:
- ```delete 1```
### Mark tasks
Mark an existing task in the to-do list as done.

Format: ```mark INDEX```

Example:
- ```mark 1```
### Unmark tasks
Mark an existing task in the to-do list as not done yet.

Format: ```unmark INDEX```

Example:
- ```unmark 1```
### List tasks
List all existing tasks in the to-do list.

Format: ```list```

### Find tasks
Find specific tasks from the to-do list by matching a input string with the task description.

Format: ```find SUBSTRING```

Example:
- ```find home```

### Detect clashing tasks
Trying to add an event with the same time as an existing event will display an error message:
```
Sorry, there is a clash in the Events!
```

