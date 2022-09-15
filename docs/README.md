# User Guide
This user guide is adapted from [AddressBook Level 3](https://nus-cs2103-ay2223s1.github.io/tp/UserGuide.html).

Botto is a **desktop application** to manage your tasks. Botto is 
optimized to be used through the **Command Line Interface (CLI)**.

## QuickStart
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the home folder for Botto.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
<br />

![Ui](Ui.png)

***
# Features
Notes about the command format:
- Words in `UPPER_CASE` are the **required** paramaters to be supplied by the user.
<br />
e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo read this`.
<br /> <br />
- All `DATES` parameter must be formatted in `YYYY-MM-DD` e.g. `2022-12-30`
  <br /> <br />
- For all `INDEX` parameter, the following applies:
  - INDEX must be an `integer`
  - INDEX must be a valid index. In other words, it's value
    is between `1` and `the number of tasks in the list` **inclusive**
    <br /><br />
- Extraneous parameters for commands that do not take parameters (such as `bye`) will be ignored.
  <br />
e.g. the command `bye [some random stuff]` will be interpreted as `bye`

***
# Adding a todo: todo
Adds a task of type todo to the list of tasks.
<br /> <br />
Format: `todo TASK`
<br /> <br />
Examples:
1. todo create user guide
2. todo do some tasks

***
## Adding an event: event
Adds a task of type event to the list of tasks.
<br /> <br />
Format: `event DESCRIPTION /at DATE`
<br /> <br />
Examples:
1. `event anime watchalong /at 2022-01-01`
2. `event eat /at 2022-02-03`

***
## Adding a deadline: deadline
Adds a task of type deadline to the list of tasks.
<br /><br />
Format: `deadline DESCRIPTION /by DATE`
<br /><br />
Examples:
1. `deadline finish IP /by 2022-09-16`
2. `deadline create user guide /by 2022-09-15`

***
## Listing all of the tasks: list
Lists all of the tasks from the list of tasks.
<br /><br />
Format: `list`
<br />

***
## Marking a task as done: mark
Marks a task as done from the list of tasks.
<br /><br />
Format: `mark INDEX`
<br /><br />
Example:
- `mark 1`
- `mark 2`

***
## Unmarking a task from done: unmark
Unmarks a task from being done from the list of tasks.
<br /><br />
Format: `unmark INDEX`
<br /><br />
Examples:
- `unmark 1`
- `unmark 2`

***
## Deleting a task: delete
Deletes a task from being from the list of tasks.
<br /><br />
Format: `delete INDEX`
<br /><br />
Examples:
- `delete 1`
- `delete 2`

***
## Finding tasks: find
Find a task from the list of tasks with the given keyword.
Note that the search is **Case-Sensitive**
<br /><br />
Format: `find KEYWORD`
<br /><br />
Examples:
- `find CS2103T`
- `find ip`

***
## Adding priority to a task: priority
Modify a priority to a selected task.
<br /><br />
Format: `priority INDEX PRIORITY_LEVEL`
<br /><br />
Note: `PRIORITY_LEVEL` must either be `low`, `medium`, `high`, or `none`
<br /><br />
Examples:
- `priority 1 none`
- `priority 2 low`
- `priority 3 medium`
- `priority 4 high`

***
## Exiting the Program: bye
Exits the program after a certain time.
<br /><br />
Format: `bye`
***
#Command Summary
| Action         | Format, Examples                                                                 |
|----------------|----------------------------------------------------------------------------------|
| **Add ToDo**   | `todo DESCRIPTION` <br /> e.g.`todo write a todo list`                           |
| **Add Event**  | `event DESCRIPTION /at DATE` <br /> e.g. `event watchalong /at 2022-01-01`       |
|**Add Deadline**| `deadline DESCRIPTION /by DATE` <br /> e.g. `deadline write this /by 2022-09-16` |           
| **List Tasks** | `list`                                                                           |
|**Mark Task**| `mark INDEX` <br /> e.g. `mark 1`                                                |
| **Unmark Task** | `unmark INDEX` <br /> e.g. `unmark 1`                                            |
| **Delete Task** | `delete INDEX` <br /> e.g. `delete 1`                                             |
| **Find Keyword** | `find KEYWORD` <br /> e.g. `find CS2103T`                                        |
| **Modify Priority** | `priority INDEX PRIORITY_LEVEL` e.g. `priority 1 high` |
|**Exit Program** | `bye`|