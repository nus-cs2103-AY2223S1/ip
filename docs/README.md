# ZELK
ZELK is your friendly task tracker: add, delete, and manage tasks on a todo-list.

---
## Features

:memo: ### Add a task: `deadline` / `event` / `todo`

Add a task and categorize it into one of the three task types.

Example: `todo buy bread` adds a task of type `todo`

Example: `deadline homework /by 2022-09-20` adds a task of type `deadline`, and stores the deadline into the todo-list.

:memo: ### Delete a task: `delete` or `remove`

Removes a task from your todo-list.

Example: `delete 3` deletes the 3rd task from the todo-list.

:memo: ### Mark a task: `mark` or `unmark`

Checks a task in the todo-list as completed, or incomplete.

Example: `mark 2` marks the 2nd task as completed.

:memo: ### Update a task: `update`

Updates a task in the todo-list

Example: `update 3 todo buy bread` updates the 3rd task in the todo list, with a task of type todo.

:memo: ### View all tasks: `list`

View all the tasks currently in the todo-list.

:memo: ### Find tasks: `find`

Searches through todo-list and returns tasks that matches the given keyword.

Example: `find bread` finds and returns all tasks in the todo-list containing the word 'bread'.

:memo: ### Close the todo-list: `bye`

Closes the bot and exits the todo-list app.

---
## Command Summary

|        Action        | Output                                                                  |
|:--------------------:|-------------------------------------------------------------------------|
|      `deadline`      | Adds a task of type deadline to the todo-list                           |
|       `event`        | Adds a task of type event to the todo-list                              |
|        `todo`        | Adds a task of type todo to the todo-list                               |
| `delete` or `remove` | Deletes the specified task from the todo-list                           |
|        `mark`        | Marks the specified task as completed                                   |
|       `unmark`       | Marks the specified task as incomplete                                  |
|       `update`       | Updates the specified task in the todo-list                             |
|        `list`        | Lists all the tasks in the todo-list                                    |
|        `find`        | Returns all the tasks in the todo-list containing the specified keyword |
|        `bye`         | Closes the todo-list                                                    |



