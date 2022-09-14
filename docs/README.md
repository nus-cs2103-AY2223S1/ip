# User Guide

Ren is a **desktop task manager app optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). If you can type fast, 
Ren can manage your tasks faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. 
2. Download the latest `ren.jar` from [here](https://github.com/Eugene-Ong-W-X/ip/releases). 
3. Copy the file to the folder you want to use as the _home folder_ for your Ren Task Manager. 
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](/docs/Ui.png)
5. Type a command at the bottom and press Enter or click Send to execute it.
6. Refer to the [Usage](#usage) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Manage Tasks

3 different types of Tasks. Operations supported
* add task
* delete task / empty list
* mark/unmark task

### Organise Tasks

View your list of Tasks. Sort the list. Find a task from the list.

### Saved Data

Your TaskList data is saved in the hard disk automatically after
any command that modifies the data. Have no fear of losing your data!
Have no worries about saving your data manually!

--------------------------------------------------------------------------------------------------------------------

## Usage

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

* Extra parameters for commands that do not take in parameters will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Add a Todo Task - `todo`

Adds a Task, that needs to be done, to the TaskList.

Format: `todo DESCRIPTION`

Example Usage

>`todo Watch recorded lecture videos`

Example Outcome
```
 Understood. I have added the following task:
   [T][ ] Watch recorded lecture videos
 You now have a total of 1 task(s).  
```

### Add a Deadline Task - `deadline`

Adds a Task, that needs to be done by a certain date or time, to the TaskList.

Format: `deadline DESCRIPTION /by DD/MM/YY-HH:MM`
* Time must be in 24hr format (i.e. 23:59 instead of 11:59 PM)

Example Usage

>`deadline Submit Essay /by 11/9/2022-23:59`

Example Outcome
```
 Understood. I have added the following task:
   [D][ ] Submit Essay (by: Sun, 11 September 2022 11:59 PM)
 You now have a total of 1 task(s).  
```

### Add an Event Task - `event`

Adds an Event, that takes place between certain dates or time, to the TaskList.

Format: `event DESCRIPTION /at DD/MM/YY-HH:MM ~ DD/MM/YY-HH:MM`
* Time must be in 24hr format (i.e. 23:59 instead of 11:59 PM)

Example Usage

>`event Recess Week /at 17/9/2022-00:00 ~ 25/9/2022-23:59`

Example Outcome
```
 Understood. I have added the following task:
   [E][ ] Recess Week (at: Sat, 17 September 2022 12:00 AM - Sun, 25 September 2022 11:59 PM)
 You now have a total of 1 task(s).  
```

### Mark a Task - `mark`

Sets a Task, in the TaskList, as completed.

Format: `mark INDEX`
* Use index of task from the list of tasks

Example Usage

>`mark 1`

Example Outcome
```
 Great job! I will mark the task as completed.
   [T][X] Watch recorded lecture videos
```

### Unmark a Task - `unmark`

Sets a Task, in the TaskList, to uncompleted.

Format: `unmark INDEX`
* Use index of task from the list of tasks

Example Usage

>`unmark 1`

Example Outcome
```
 Understood. I will mark the task as uncompleted.
   [T][ ] Watch recorded lecture videos
```

### Lists all Tasks - `list`

Displays all Tasks in the TaskList.

Format: `list`

Example Usage

>`list`

Example Outcome
```
 Here are your current tasks:
 1. [T][ ] Watch recorded lecture videos
 2. [T][X] Submit Essay
```

### Sort Tasks - `sort`

Sorts all Tasks in the TaskList.

Format: `sort ATTRIBUTE`
* Attribute 1. `type` Sorts in the order of Todo > Deadline > Event
* Attribute 2. `status` Sorts uncompleted Tasks before completed Tasks
* Attribute 3. `description` Sorts by description, in lexicographical order
* Attribute 4. `date` Sorts by date, in chronological order (Todos are last)

Example Usage

>`sort status`

Example Outcome
```
 I have finished sorting your list of tasks!

 Here are your current tasks:
 1. [T][ ] Watch recorded lecture videos
 2. [T][X] Submit Essay 
```

### Find a Task - `find`

Finds a Task from the TaskList.

Format: `find SEARCH_TERM`

Example Usage

>`find video`

Example Outcome
```
 I have found these matching tasks:
 1. [T][ ] Watch recorded lecture videos
```

### Delete a Task - `delete`

Deletes a Task from the TaskList.

Format: `delete INDEX`
* Use index of task from the list of tasks

Example Usage

>`delete 1`

Example Outcome
```
 Understood. I have removed the following task:
   [T][ ] Watch recorded lecture videos
 You have a total of 1 task(s) left.  
```

### Empty the TaskList - `empty`

Deletes all Tasks from the TaskList.

Format: `empty`

Example Usage

>`empty`

Example Outcome
```
 Understood. I have emptied your list of tasks. 
```

### Exit Ren - `bye`

Exits and closes the Ren program.

Format: `bye`
