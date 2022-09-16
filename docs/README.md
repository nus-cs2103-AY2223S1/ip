# User Guide(Koba)

Koba is a desktop app for managing tasks and deadlines via a Graphical User Interface (GUI) in the form of a chat bot.

* [Summary of commands](https://github.com/therealdaofu/ip/blob/master/docs/README.md#summary-of-commands)
* Features:
  * [adding a todo](https://github.com/therealdaofu/ip/blob/master/docs/README.md#add-a-todo-todo)
  * [adding a deadline]

## Summary of Commands.
|Function                 |Command                                                    |
|-------------------------|-----------------------------------------------------------|
|add a todo               |**todo**     DESCRIPTION                                   |
|add a deadline           |**deadline** DESCRIPTION **/by** DATE_AND_TIME_IN_ISO_FORMAT|
|add a event              |**event**    DESCRIPTION **/at** DURATION                  |
|mark a task as completed |**mark**     INDEX                                         |
|mark a task as incomplete|**unmark**   INDEX                                         |
|deletes a task           |**delete**   INDEX                                         |
|list all tasks           |**list**                                                   |
|find tasks with a keyword|**find**     KEYWORD                                       |
|display help page        |**help**                                                   |
|exit the app             |**bye**                                                    |


## Features


### Add a todo: `todo`
Adds a new todo to the app.

Format: `todo **DESCRIPTION**`

Example of usage:
```
todo Read the Hobbit
```
Adds a new todo, 'read the hobbit' to the app.

```
todo Sign up for gym
```
Adds a new todo, 'Sign up for gym' to the app.

### Add a deadline: `deadline DESCRIPTION /by DATE_AND_TIME`
Adds a new deadline to the app.

Format: `deadline **DESCRIPTION** /by **DATE_AND_TIME`
<Note: DATE_AND_TIME should be in ISO 8601 format>

Example of usage:
- deadline assignment 1 /by 2022-08-15T23:59
- deadline critical reflection 1A /by 2022-09-01T08:00

### Add a event: `event DESCRIPTION /at DURATION`
Adds a new event to the app.

Format: `event **DESCRIPTION** /at **DURATION**`

Example of usage:
-
## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
