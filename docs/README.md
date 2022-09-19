# User Guide

This user guide is adapted from [AddressBook Level 3](https://nus-cs2103-ay2223s1.github.io/tp/UserGuide.html).

Yem Chatbot is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

--------------------------------------------------------------------------------------------------------------------

## Quick Start
1. Ensure you have Java `11` or above installed on your computer.

2. Download the latest `yem.jar` from [here](https://github.com/sugiyem/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Yem Chatbot.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
<br /> <br />
   ![Ui](Ui.png)

--------------------------------------------------------------------------------------------------------------------
## Features

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words inside the `<>` are the parameters to be supplied by the user.<br>
  e.g. in `todo <task>`, `<task>` is a parameter which can be used as `todo eat`.

* Items in square brackets are optional.<br>
  e.g `todo <task> [#<tag>]` can be used as `todo eat` or `todo eat #lunch`.

* Parameters with `…` after them can be used multiple times, with `,` as the separator.<br>
  e.g. `find <keyword>...` can be used as `find cs2103t`, `find cs2103t, cs2101` etc.

* Parameter `<date>` must be supplied in `YYYY-MM-DD` format.<br>
e.g. `at <date>` can be used as `at 2022-10-10`

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>

### Listing all tasks: `list`

Shows a list of all your tasks, including other information such as status (whether it's done or not), deadline or time (if any), and tags (if any).
```
Here are the tasks in your list
1. [E][X] cs2102 project meeting (at: Sep 11 2022) [uni]
2. [D][ ] cs2103 ip (by: Sep 16 2022) [uni, assignment]
3. [T][X] wash clothes
```
Format: `list`

### Adding a todo task: `todo`

Adds a todo task to the task list. Use the `#` sign to add tags to the task.

Format: `todo <task> [#<tag>...]` <br />

Example:
- `todo wash clothes`
- `todo Do ST2131 quiz #assignment`
- `todo grade CS1101S mission #TA, uni, work`

### Adding a deadline task: `deadline`
Adds a deadline task to the task list. Use the `#` sign to add tags to the task.

Format: `deadline <task> /by <date> [#<tag>...]`

Example:
- `deadline ST2131 quiz 1 /by 2022-09-11`
- `deadline tiktok OA /by 2022-09-18 #intern`
- `deadline CS2102 Assignment /by 2022-09-20 #uni, hw`

### Adding an event task: `event`
Adds an event task to the task list. Use the `#` sign to add tags to the task.

Format: `event <task> /at <date> [#<tag>...]`

Example:
- `event cs2102 project meeting /at 2022-09-11`
- `event cs2103t tutorial /at 2022-09-14 #class`
- `event cs1101s studio 6 /at 2022-09-12 #TA, univ, work`


### Marking a task as done: `mark`

Marks the unique task given the index as done. 1-based indexing is used here.

Format: `mark <index>`

Sample: `mark 2`

### Unmarking a task as not done: `unmark`

Unmarks the unique task given the index as not done. 1-based indexing is used here.

Format: `unmark <index>`

Sample: `unmark 1`

### Deleting a task: `delete`

Deletes the unique task given the index. 1-based indexing is used here.

Format: `delete <index>`

Sample: `delete 2`

### Finding tasks by name: `find`

List out all tasks where it's description includes any of the keywords given.

```
Here are the tasks in your list that match the keyword
1. [D][ ] cs2103 ip (by: Sep 16 2022) [uni]
```

Format: `find <keyword>...`

Sample:
- `find cs2103t`
- `find cs2103, assignment, project`

### Finding tasks by time or deadline: `at`
List out all tasks where it's deadline or time includes any of the given dates.

Format: `at <date>...`

Sample:
- `at 2022-09-20`
- `at 2022-09-10, 2022-08-08, 2022-12-12`

### Finding tasks by any of the tags: `any`
List out all tasks that has any of the given tags.

```
Here are the tasks in your list that match any of the given tags
1. [E][X] cs2102 project meeting (at: Sep 11 2022) [uni]
2. [D][ ] cs2103 ip (by: Sep 16 2022) [uni]
```

Format: `any <tag>...`

Sample:
- `any uni`
- `any assignment, TA, univ`

### Finding tasks by all of the tags: `all`
List out all tasks that has all of the given tags.

Format: `all <tag>...`

Sample:
- `all uni`
- `all assignment, quiz, project`
### Exiting the program: `bye`
Exits the Yem Chatbot.

Format: `bye`

## Command Summary

| Action               | Format, Examples                                                                                 |
|----------------------|--------------------------------------------------------------------------------------------------|
  | **List Tasks**       | `list`                                                                                           |
| **Add Todo**         | `todo <task> [#<tag>...]` <br /> e.g., `todo Do ST2131 quiz #assignment`                         |
| **Add Deadline**     | `deadline <task> /by <date> [#<tag>...]` <br /> e.g. `deadline tiktok OA /by 2022-09-18 #intern` |
| **Add Event**        | `event <task> /at <date> [#<tag>...]` <br /> e.g. `event cs2103t tutorial /at 2022-09-14 #class` |
| **Mark Task**        | `mark <index>` <br /> e.g., `mark 2`                                                             |
| **Unmark Task**      | `unmark <index>` <br /> e.g., `unmark 1`                                                         |
| **Delete Task**      | `delete <index>` <br /> e.g., `delete 2`                                                         |
| **Find by Name**     | `find <keyword>...` <br /> e.g., `find cs2103t`                                                  |
| **Find by Date**     | `at <date>...` <br /> e.g., `at 2022-09-20`                                                      |
| **Find by Any Tags** | `any <tag>...` <br /> e.g., `any assignment, TA, univ`                                           |
| **Find by All Tags** | `all <tag>...` <br /> e.g., `all assignment, quiz, project`                                      |
| **Exit**             | `bye`                                                                                            |





