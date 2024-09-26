# User Guide

Duke is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Duke can get your contact management tasks done faster than traditional GUI apps.

-------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest duke.jar from [here]().
3. Copy the file to the folder you want to use as the _home folder_ for your Duke.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   Note how the app contains some sample data.<br>
   ![Ui](Ui.png)
5. Refer to the <<Features>> below for details of each command.

-------------------------------------------------------------------------------------------------------------

[[Feature]]
## Features 
- Add todo task: `todo`
- Add deadline task: `deadline`
- Add event task: `event`
- List tasks: `list`
- Delete task: `delete`
- Mark task: `mark`
- Unmark task: `unmark`
- Find task: `find`
- Tag task: `tag`
- Exit Duke: `bye`
- Save upon exiting

### Manage Tasks
Add, delete, mark, unmark or tag any of the todo, deadline or event task.

### View Tasks
List all tasks.
Find tasks based on specific words.

### Save data
Data is saved upon exiting the program.

------------------------------------------------------------------------------------------------------------

## Usage

### Add a Todo Task - `todo`

Adds a todo task to the list.

Note: | symbol should not be used.

Format:

`todo [description]`

Example of todo:

`todo homework`

Expected outcome:
```
Got it. I've added this task:
[T][ ] homework
Now you have 2 tasks in the list.
```

### Adds a Deadline Task - `deadline`

Adds a deadline task to the list.

Ensure that Date is set in YYYY-MM-DD format. Time in HHMM 24h format.

Note: | symbol should not be used.

Format:

`deadline [description] /by [YYYY-MM-DD HHMM]`

Example of deadline:

`deadline homework /by 2022-12-20 1200`

Expected outcome:
```
Got it. I've added this task.
[D][ ] homework(by: Dec 20 2022 12:00 PM)
Now you have 3 tasks in the list.
```

### Adds an Event Task - `event`

Adds an event task to the list.

Ensure that Date is set in YYYY-MM-DD format. Time in HHMM 24h format.

Note: | symbol should not be used.

Format:

`event [description] /at [YYYY-MM-DD HHMM]`

Example of event:

`event birthday /at 2022-02-01 1800`

Expected outcome:
```
Got it. I've added this task:
[E][ ] birthday (at: Feb 01 2022 6:00 PM)
Now you have 4 tasks in the list.
```

### list all Tasks - `list`

Display all the tasks.

Example of list:

`list`

Expected outcome:
```
1.[T][ ] pack bag [urgent]
2.[T][ ] homework
3.[D][ ] homework(by: Dec 20 2022 12:00 PM)
4.[E][ ] birthday (at: Feb 01 2022 6:00 PM)
```
### Deletes a Task -  `delete`

Delete a task from the list based on the task index in the list.

Example of delete:

`delete 1`

Expected outcome:
```
Noted. I've removed tis task:
[T] ] pack bag [urgent]
Now you have 3 tasks in the list.
```

### Marks a Task - `mark`

Marks a task with a X. If task has been marked before, task printed remains the same.

Example of mark: 

`mark 1`

Expected outcomeL
```
Nice! I've marked this task as done:
[T][X] homework
```

### Unmarks a Task -`unmark`

Unmarks a Task by removing the X. If task has not been marked before, task printed remains the same.

Example of unmark:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][ ] homework
```

### finds the relevant task - `find`

Finds the task which its description contains the keywords.

Example of find:

`find homework`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][ ] homework
2.[D][ ] homework (by: Dec 2022 12:00 PM)
```

### tags a Task - `tag`

Tags a task in the list based on the index declared and the name declared.\
Subsequent tags are followed after a comma.
All tags lies inside the [] brackets.

Example of tag:

`tag 1 urgent`

Expected outcome:
```
Tags added: urgent
[T][ ] homework [urgent]
```

Exiting Duke - `bye`

Exits the program.

Example of bye:

`bye`

Expected outcome:

The application close.


