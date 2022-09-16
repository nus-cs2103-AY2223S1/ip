# User Guide for Zeus
Zeus is an application that is supported by multiple OSes like macOS, Windows and Linux. It is designed to manage tasks.
It is also optimised for users who are more familiar with Command-Line-Interface(CLI).

- Pre-requisites
- Quick start
- Features

## Pre-requisites
1. Ensure that you have at least Java 11 or above installed in your computer.
2. Download the latest version of [Zeus](https://github.com/drkkjh/ip/releases/download/A-Jar3/zeus.jar)

## Quick start
1. Move the file ```zeus.jar``` to a folder that you want to set as the home directory for Zeus. *Note that your data will be stored here*
2. Double-click to start the app.
3. Type any command mentioned below in the corresponding format and press *Enter* to execute it.
4. Refer to *Features* below for more details for each command.

## Features

### Feature - Todo

Add a Todo-type task to a list of tasks.

Usage: ```todo stats tutorial```

### Feature - Deadline

Add a Deadline-type task to a list of tasks.

Usage: ```deadline finish iP project /by 2022-09-16```

### Feature - Event  

Add a Event-type task to a list of tasks.

Usage: ```event group meeting /at 2022-09-17```

### Feature - Delete

Delete a task from the list of tasks, based on its position in the list of tasks.

Usage: ```delete 3```

### Feature - Find

Finds all tasks that has its description related to the keyword specified.

Usage: ```find meeting```

### Feature - List

Lists all the tasks currently in the list of tasks.

Usage: ```list```

### Feature - Mark

Mark a task as completed with an X, based on its position in the list of tasks.

Usage: ```mark 1```

### Feature - Unmark

Unmark a task as completed, based on its position in the list of tasks.

Usage: ```unmark 1```

### Feature - On Date

List all the tasks that is happening on the specified date.

Usage: ```on 2022-09-16```

### Feature - Tag

Tag a task with a tagging, based on its position in the list of tasks.

Usage: ```tag 1 #IMPORTANT```

### Feature - Untag

Untag a task if it is tagged previously, based on its position in the list of tasks.

Usage: ```untag 1```

### Feature - Bye

Exits the application.

Usage: ```bye```
