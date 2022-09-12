# User Guide for Duke

![Ui](Ui.png)

## Quick start

1. Ensure you have Java `11` or above installed.


2. Download the latest `duke.jar` from [here](https://github.com/dexter-sim/ip/releases).


3. Copy the file to the folder you want to use as the home folder for Duke.


4. Double-click the file to start the app.


5. Enter a command in the command box to execute it.

## Features 

### Feature-Bye

Exits the program.

Command Format: `bye`

### Feature-ToDo

Creates a ToDo task and adds it to task list.

Command Format: `todo <description>`

Expected Outcome: 

```
Got it. I've added this task:
[T][] <description>
Now you have <number> tasks in the list.
```

### Feature-Deadline

Creates a Deadline task and adds it to task list.

Command Format: `deadline <description> /by YYYY-MM-DD`

Expected Outcome:

```
Got it. I've added this task:
[D][] <description> (by: MMM dd yyyy)
Now you have <number> tasks in the list.
```

### Feature-Event

Creates an Event task and adds it to task list.

Command Format: `event <description> /at YYYY-MM-DD`

Expected Outcome:

```
Got it. I've added this task:
[E][] <description> (at: MMM dd yyyy)
Now you have <number> tasks in the list.
```

### Feature-List

Displays all the tasks.

Command Format: `list`

Expected Outcome:

```
Here are the tasks in your list:
.
.
.
```

### Feature-Find

Searches and displays all the tasks that contain specified task description.

Command Format: `find <description>`

Expected Outcome:

```
Here are the tasks in your list:
.
.
.
```
### Feature-Mark

Marks a task as complete.

Command Format: `mark <task_number>`

Expected Outcome:

```
Nice! I've marked this task as done:
...
```

### Feature-Unmark

Marks a task as incomplete.

Command Format: `unmark <task_number>`

Expected Outcome:

```
OK, I've marked this task as not done yet:
...
```

### Feature-Delete

Deletes a task.

Command Format: `delete <task_number>`

Expected Outcome:

```
Noted. I've removed this task:
...
Now you have <number> tasks in the list.
```
