# User Guide
## Turtle
>“Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Turtle frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ *SUPER* FAST to use

## Features

### Listing all tasks: `list`

Lists all tasks currently stored in Turtle task manager.

Format: `list`

### Greeting the user: `hello`

Displays Turtle's greeting to greet the user.

Format: `hello`

### Exiting the application: `bye`

Exits the program and saves the user's tasks to memory.

Format `bye`

### Marking a task as complete: `mark`

Marks the specified task in Turtle task manager as completed.

Format: `mark INDEX`

* Marks the task specified by the `INDEX` as completed.
* The index refers to the index number of the task as displayed in the task list.
* The index **must be a positive integer 1, 2, 3,...**

Examples:

* `list` followed by `mark 2` marks the task at index 2 as done.

### Un-marking a task as incomplete: `unmark`

Un-marks the specified task in Turtle task manager as incomplete.

Format: `unmark INDEX`

* Un-marks the task specified by the `INDEX` as not completed.
* The index refers to the index number of the task as displayed in the task list.
* The index **must be a positive integer 1, 2, 3,...**

Examples:

* `list` followed by `unmark 2` marks the task at index 2 as not done.

### Deleting a task: `delete`

Delete the specified task in Turtle task manager.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number of the task as displayed in the task list.
* The index **must be a positive integer 1, 2, 3,...**

Examples:

* `list` followed by `delete 2` deletes the task at index 2.

### Locating a task by keywords: `find`

Find tasks that have descriptions that contain a keyword.

Format: `find KEYWORD`

* Search is case-sensitive. e.g. `BOOK` will not match `book`.
* Only the description of a task is searched.
* Partial words are searched as well. e.g. `boo` with match `book`.
* Will return all tasks that contain the specified keyword.

Examples:

* `find book` returns all tasks that contain the keyword `book` as part of
  the description of the task.

### Reminders for unmarked deadline: `remind`

Finds umarked `deadline` tasks for
reminder purposes.

Format: `remind`

* Returns the `deadline` task which is incomplete.

### Adding a todo task: `todo`

Adds a todo task to the Turtle task manager.

Format: `todo DESCRIPTION`

* The `DESCRIPTION` is any sentence describing the task and
  includes the spaces used.

Examples:

* `todo read book` adds the `todo` task `read book` to the Turtle task
  manager.

### Adding a deadline task: `deadline`

Adds a deadline task to the Turtle task manager.

Format: `deadline DESCRIPTION /by BY`

* The `DESCRIPTION` is any sentence describing the task and
  includes the spaces used.
* The `BY` time specifies the deadline, it is a time. e.g. 2022-10-15 06:00.
* The `BY` time must be in the format `yyyy-MM-dd HH:mm`.

Examples:

* `deadline return book /by 2022-10-15 06:00` adds the `deadline` task
  `return book` to the Turtle task manager, and it sets the time by which
  to complete the task, i.e. the deadline, to be `2022-10-15 06:00`.

### Adding an event task: `event`

Adds an event task to the Turtle task manager.

Format: `event DESCRIPTION /from START /to END`

* The `DESCRIPTION` is any sentence describing the task and
  includes the spaces used.
* The `START` time specifies the start of the event.
  The `END` time specifies the end of the event. Both are
  times. e.g. `2022-10-15 06:00`.
* The `START` and `END` times must be in the format `yyyy-MM-dd HH:mm`.


Examples:

* `event project meeting /from 2022-10-15 06:00 /to 2022-10-15 07:00` adds
  the `event` task `project meeting` to the Turtle task manager. It sets
  the start time to `2022-10-15 06:00` and the end time to `2022-10-15 07:00`.

## Command Summary

| **Command**  | **Format, Examples**                                                                                              |
|--------------|-------------------------------------------------------------------------------------------------------------------|
| **List**     | `list`                                                                                                            |
| **Hello**    | `hello`                                                                                                           |
| **Exit**     | `bye`                                                                                                             |
| **Mark**     | `mark INDEX`, e.g. `mark 1`                                                                                       |
| **Un-mark**  | `unmark INDEX`, e.g. `unmark 1`                                                                                   |
| **Delete**   | `delete INDEX`, e.g. `delete 1`                                                                                   |
| **Find**     | `find KEYWORD`, e.g. `find book`                                                                                  |
| **Reminder** | `remind`                                                                                                          |
| **Todo**     | `todo DESCRIPTION`, e.g. `todo read book`                                                                         |
| **Deadline** | `deadline DESCRIPTION /by BY`, e.g. `deadline return book /by 2022-10-15 06:00`                                   |
| **Event**    | `event DESCRIPTION /from START /to END`, e.g. `event project meeting /from 2022-10-15 06:00 /to 2022-10-15 07:00` |