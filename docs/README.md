# User Guide
AIlfred is a **desktop application** for managing tasks in your everyday life, via a **Command Line Interface**.
Pronounced A-I-lfred(_ay-aye-el-ferud_), he is here to aid in your task management and can be easily setup by anyone.

## Table of contents
1. [Quick Start](#quick-start-using-intellij)
2. [Features](#features)
   1. [Deadline command](#add-a-deadline-deadline)
   2. [Event command](#add-an-event-event)
   3. [Todo command](#add-a-todo-todo)
   4. [Print list command](#print-all-your-tasks-list)
   5. [Delete task command](#delete-a-task-delete)
   6. [Find task command](#find-a-task-find)
   7. [Mark task command](#mark-a-task-mark)
   8. [Unmark task command](#unmark-a-task-unmark)
   9. [Sort task command](#sort-all-your-tasks-sort)
   10. [Saving your data](#saving-the-data)
   11. [Editing your save file](#editing-the-data-file)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

## Quick Start: Using IntelliJ
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    2. Select the project directory, and click `OK`.
    3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Creating a directory to store save file
   Creating a new save file...
   
   Hello! I am AILfred, your personal assistant. 
   What can I do for you?
   ```

## Features 
<div markdown="block">

**Notes about the command format:**<br>

* Words in `[]` are the parameters to be supplied by the user. <br>
  e.g. in `deadline [description] /by [date]`, `[description]` and `[date]` can be used as `deadline CS2103T Quiz /by 2022-09-15`. <br><br/>

* All dates are formatted in this order `YYYY-MM-DD`. <br>
  e.g `2022-10-31` would stand for **31st of October 2022**. <br><br/>

* Parameters should follow the syntax strictly, otherwise AIlfred will not be able to understand you!<br><br/>

* Extraneous parameters for commands that do not take in parameters (such as `list`, `bye` and `sort`) are not allowed.<br>
  e.g. if the command specifies `sort 123`, it will be interpreted as `sort`. <br><br/>

</div>

### Add a Deadline: `deadline`

Creates a deadline task that tracks when the task needs to be completed by. <br>
* Description can be as descriptive as you want.
* Date should strictly follow the format `YYYY-MM-DD`.
* `/by` tag should be used between the `[description]` and `[date]`. <br>

Format: `deadline [description] /by [date]`
Examples:
* `deadline CS2103T Quiz /by 2022-09-15`
* `deadline CS2101 Team Meetings Reflection /by 2022-09-01`

### Add an Event: `event`

Creates an event task that tracks when the date the event is happening.
* Description can be as descriptive as you want.
* Date should strictly follow the format `YYYY-MM-DD`.
* `/at` tag should be used between the `[description]` and `[date]`. <br>

Format: `event [description] /at [date]`
Examples:
* `event My Birthday! /at 2022-09-15`
* `event CS2101 OP1 Presentation /at 2022-09-01`

### Add a Todo: `todo`

Creates a todo task that tracks something you may need to do.
* Description can be as descriptive as you want.
* There is no need for a date in yout todo tasks.

Format: `todo [description]`
Examples:
* `todo CS2100 Lecture Recordings`
* `todo Finish ip for CS2103T`

### Print all your tasks: `list`

Prints out all your current tasks saved.
* This command does not take in additional parameters, but it can still be used with them as additional parameters are ignored.
* The format of printing is as such:
  * `[Type][Status] Description of task (at/by: Date of event/deadline)`

Format: `list`
Examples:
* Input: `list`
* Expected output:
```
1. [T][ ] CS2100 Lecture Recordings
2. [E][X] Interview with AWS (at: Sep 28 2022)
```

### Delete a task: `delete`

Delete a task that you currently have saved.
* This command takes in the index of the task you want to delete as the additional parameter.

Format: `delete [index]`
Examples:
* Input: `delete 2` <br>
* Expected output:
```
Noted. I've removed this task:
  [T][ ] CS2100 Lecture Recordings
Now you have 1 tasks in the list.
```

### Find a task: `find`

Finds a task that you currently have saved.
* This command takes in a **singular** keyword that you are looking for.

Format: `find [keyword]`
Examples:
* Input: `find CS2100` <br>
* Expected output:
```
1. [T}[ ] CS2100 Lecture Recordings
  3. [D][X] CS2100 Lab 4 (by: Sep 15 2022)
```

### Mark a task: `mark`

Marks a task that you currently have saved.
* This command takes in the index of the task you want to mark as the additional parameter.

Format: `mark [index]`
Examples:
* Input: `mark 1` <br>
* Expected output:
```
Nice!. I've marked this task as done:
  [T][X] CS2100 Lecture Recordings
```

### Unmark a task: `unmark`

Unmarks a task that you currently have saved.
* This command takes in the index of the task you want to ummark as the additional parameter.

Format: `mark [index]`
Examples:
* Input: `mark 1` <br>
* Expected output:
``` 
OK. I've marked this task as not done yet:
  [T][ ] CS2100 Lecture Recordings
```

### Sort all your tasks: `sort`

Sorts out all your current tasks saved according to the date.
* This command does not take in additional parameters, but it can still be used with them as additional parameters are ignored.
    * The tasks are sorted by earliest (top) to latest (bottom) based on **dates**.
    * Todos are automatically placed at the _bottom_ as they do not have any dates.

Format: `sort`
Examples:
* Input: `sort`
* Expected output:
```
Your Tasks has been sorted!
``` 

If you type `list` again, all tasks should be sorted in accordance to their dates.

### Exit the application: `bye`

Exits the application
* This command does not take in additional parameters, but it can still be used with them as additional parameters are ignored.
* On running this command, the application will automatically close and save your data.

Format: `bye`
Examples:
* Input: `bye`
* Expected outcome: The application closes.

### Saving the data
AIlfred data are saved in the hard disk automatically after any command that changes the data and on exiting the application. There is no need to save manually.

### Editing the data file
AIlfred data are saved as a `.txt` file in `[Application location]/src/data/duke.txt`. Advanced users are welcomed to update data directly by editing this file <br>
The data is parsed as the following:
```
TYPE | STATUS | DESCRIPTION | DATE
```

| Symbol | Tag           | Meaning                     |
|--------|---------------|-----------------------------|
| **D**  | `TYPE`        | Deadline task               |
| **E**  | `TYPE`        | Event task                  |
| **T**  | `TYPE`        | Todo task                   |
| **0**  | `STATUS`      | Task is undone              |
| **1**  | `STATUS`      | Task is done                |
| -      | `DESCRIPTION` | Description of task         |
| -      | `DATE`        | Date of task (if necessary) |

## FAQ
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AIlfred home folder.

## Command summary

| Action           | Format, Examples                                                                     |
|------------------|--------------------------------------------------------------------------------------|
| **Add Deadline** | `deadline [description] /by [date]` <br> e.g., `deadline CS2100 Quiz /by 2022-09-15` |
| **Add Event**    | `event [description] /at [date]` <br> e.g., `event My Birthday! /at 2000-10-20`      |
| **Add Todo**     | `todo [description]`<br> e.g., `todo CS2103T tp tasks`                               |
| **Print List**   | `list`                                                                               |
| **Delete Task**  | `delete [index]`<br> e.g., `delete 3`                                                |
| **Find Task**    | `find [keyword]` <br> e.g., `find CS2103T`                                           |
| **Mark Task**    | `mark [index]`<br> e.g., `mark 3`                                                    |
| **Unmark Task**  | `unmark [index]`<br> e.g., `unmark 3`                                                |
| **Sort Task**    | `sort`                                                                               |