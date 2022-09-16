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

Format: `todo DESCRIPTION`

Example of usage:
```
todo Read the Hobbit
```
Adds a new todo, 'read the hobbit' to the app.

```
todo Sign up for gym
```
Adds a new todo, 'Sign up for gym' to the app.


### Add a deadline: `deadline`
Adds a new deadline to the app.

Format: `deadline DESCRIPTION /by DATE_AND_TIME`
<Note: DATE_AND_TIME should be in ISO 8601 format>

Example of usage:
```
deadline assignment 1 /by 2022-08-15T23:59
```
Adds a new deadline, assignment 1, due on Aug 8th, 2022 at 23:59hrs.

```
deadline critical reflection 1A /by 2022-09-01T08:00
```
Adds a new deadline, critical reflection 1A, due on Sep 1th, 2022, at 08:00hrs.


### Add a event: `event`
Adds a new event to the app.

Format: `event DESCRIPTION /at DURATION`

Example of usage:
```
event birthday party /at Friday 5-8pm
```
Adds a new event, birthday party at Friday, 5-8pm

```
event team meeting /at 27/8/22 at 8-10am
```
Adds a new event, team meeting on 27th Aug 2022, at 8-10am.


### Mark a task: `mark`
Marks the task at given index as complete.

Format: `mark INDEX`
<sub>
 Note: 
 - The index refers to the index number shown in the displayed list.
 - The index **must be a positive integer**, 1, 2, 3...
</sub>

