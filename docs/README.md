# User Guide

Duke is a chatbot that is used on desktop for task management, Duke can help to track and manage tasks using a GUI.
## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest version v2 of Duke from [here](https://github.com/Guanzhou03/ip/releases)
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
## Features 
- Add tasks
- Delete tasks
- Unmark/mark tasks as done
- List out current tasks
- Snooze a task for a day
- Save tasks
- Exit app
### Add task
Adds a specified deadline, event, or todo task to the task list.<br>
1. Format for adding a deadline: ```deadline do hw /by 2022-12-12``` <br>
2. Format for adding an event: ```event clean the beach /at 2022-01-17``` <br>
3. Format for adding a todo: ```todo clean the car```

### Delete task
Deletes a specific task specified by the task number <br>
For example, you can delete task 1 using: ```delete 1```

### Unmark/mark tasks as done
Marks or unmarks a task as done. <br>

For example, you can mark task 1 as done using: ```mark 1``` <br>
Similarly, you can set a task as undone using: ```unmark 1```

### List
Shows a list of all the current tasks <br>
Format: ```list```

### Snooze
Delays the date for a deadline or event task by 1 day. <br>
For example, snooze task 2 for a day using: ```snooze 2```

### Saving the data
The task list data are saved automatically in your machine after any command. <br>
There is no need to save the tasks manually. <br>
You can access the same tasks even after quitting and re-launching Duke.

### Exit
Exits the app using the bye command. <br>
Format: ```bye```

