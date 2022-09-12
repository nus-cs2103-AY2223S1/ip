# User Guide: Kirby Deluxe Bot
Bored of the traditional to-do task list applications? <br><br> Kirby is a desktop task management app that provides a refreshing way of keeping track of your ongoing tasks! While incorporating the interactive element offered by chat bots, it improvises the conventional features of a task manager. Through its intuitive interface and input system, Kirby provides unparalleled convenience to its users and is easy to learn. 

- [Quick Start (Installation Guide)](#quick-start)
- [Features](#features) 
- [Command Summary](#command-summary)
- [FAQ](#faq)

## Quick Start (Installation Guide)
To download and use Kirby: <br>
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `kirby.jar` from [here](https://github.com/sltsheryl/ip/releases/tag/A-Release).
3. Open a command window in that folder.
4. Run the command `java -jar kirby.jar` (i.e., run the command in the same folder as the jar file). 

## Features
### Create a Task 
Create one of the three types of task supported by Kirby.

| Types of Task  | Purpose | Command |
| ------------- | ------------- |------------- |
| Todo  | A simple to-do task with no time frame can be added.| `todo <task name>`|
| Event | A special occasion happening at a time.  | `event <task name> /at <time>`|
| Deadline |A task with a designated time frame to be completed.  | `deadline <task name> /by <time>`|

For tasks with a `<time>` component, users can input valid date format `yyyy-mm-dd` and the tasks would be ordered in a chronological order. <br>
The `<task name>` and `<time>` are required fields if they are shown in the command syntax above.

### Delete a Task: `delete <task index>`
Delete an existing task in the list of tasks using the index position. <br> 
The index is one-based (starts from 1). 

### Mark a Task: `mark <task index>` 
If a task is completed, mark it so that it is shown as marked in the list of tasks when `show` is called. <br> 
The index is one-based (starts from 1). <br>
<p align="center">
<img width="596" alt="marked" src="https://user-images.githubusercontent.com/96589109/189592390-72b776bb-3349-4ea9-a2f7-209c5921057f.png"> <br>
Marked tasks are represented with checkboxes
</p>

### Unmark a Task: `unmark <task index>`
If a task is incomplete, unmark it so that it is shown as pending in the list of tasks when `show` is called. <br>
The index is one-based (starts from 1). <br>

### Show All Tasks: `show`
Display the list of all tasks that are currently in the list. <br> 
<p align="center">
<img width="591" align="center" alt="show" src="https://user-images.githubusercontent.com/96589109/189591911-e6247836-80bc-4a0d-b4c6-451a109400a7.png"> <br>
List of tasks displayed
</p>

### Find Tasks Corresponding to a Keyword: `find <keyword>`
List down all the tasks that contain a specified keyword. <br>

### Get Tasks Occurring on a Date: `get <yyyy-mm-dd>`
List down all the tasks that happened on a specified date. <br>

### Help: `help`
View all the commands with proper command syntax. <br>

### Exit: `bye`
Exit the program once you have finished using Kirby. <br>
Your existing tasks would be stored locally and saved when you open Kirby again. <br>


## Command Summary

| Action  | Format| Examples |
| ------------- | ------------- |------------- |
| **Add Todo**  | `todo <task name>`| `todo Clear the laundry`|
| **Add Event** | `event <task name> /at <time>`  | `event CS2100 exam /at 2022-09-13`, `event d1NNer dAt3 /at 5pm`|
| **Add Deadline** | `deadline <task name> /by <time>`  | `Finish marking duty /by 2022-12-01`, `deadline st2334 assignment /by tomorrow`|
| **Help**  | `help`| `help`|
| **Delete Task**  | `delete <task index>`| `delete 2`|
| **Mark Task**  | `mark <task index>`| `mark 1`, `mark 2`|
| **Unmark Task**  | `unmark <task index>`| `unmark 1`, `unmark 2`|
| **Get Tasks**  | `get <yyyy-mm-dd>`| `get 2012-12-23`, `get 1990-01-31`|
| **Find Tasks**  | `find <keyword>`| `find math`, `find 2012`, `find T`|
| **Exit**  | `bye`| `bye`|


## FAQ
**Q:** Will my existing tasks be saved after I close the program? <br>
**A:** Rest assured, your tasks will be stored and you can view them again using `show` once you open Kirby again. <br> <br>
**Q:** How do I transfer my data to another Computer? <br>
**A:** Install the app in the other computer and overwrite the empty data file `kirby.txt` it creates with the file that contains the data of your previous Kirby folder (default would be `kirby.txt`). <br><br>
**Q:** What if I added a task that has an invalid date format for `<time>`? <br>
**A:** The `<time>` field need not be in the form of a date. If it is not in a standard form `yyyy-mm-dd`, it will be saved as a raw input. <br>
If it is in the `yyyy-mm-dd` format, it will then be displayed in the Date - Month - Year syntax.

