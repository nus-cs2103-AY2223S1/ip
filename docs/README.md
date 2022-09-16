# User Guide(Koba)

Koba is a desktop app for managing tasks and deadlines via a Graphical User Interface (GUI) in the form of a chat bot.

* [Summary of commands](https://github.com/therealdaofu/ip/blob/master/docs/README.md#summary-of-commands)
* Features:
  * [add a todo](https://github.com/therealdaofu/ip/blob/master/docs/README.md#add-a-todo-todo)
  * [add a deadline](https://github.com/therealdaofu/ip/blob/master/docs/README.md#add-a-deadline-deadline)
  * [add a event](https://github.com/therealdaofu/ip/blob/master/docs/README.md#add-a-event-event)
  * [mark a task as complete](https://github.com/therealdaofu/ip/blob/master/docs/README.md#mark-a-task-mark)
  * [mark a task as incomplete](https://github.com/therealdaofu/ip/blob/master/docs/README.md#unmark-a-task-unmark)
  * [delete a task](https://github.com/therealdaofu/ip/blob/master/docs/README.md#delete-a-task-delete)
  * [list all stored tasks](https://github.com/therealdaofu/ip/blob/master/docs/README.md#list-all-stored-tasks-list)
  * [find tasks with a keyword](https://github.com/therealdaofu/ip/blob/master/docs/README.md#find-task-by-a-keyword-find)
  * [display help page](https://github.com/therealdaofu/ip/blob/master/docs/README.md#get-help-page-help)
  * [exit the app](https://github.com/therealdaofu/ip/blob/master/docs/README.md#exit-from-the-app-bye)
  * [saving](https://github.com/therealdaofu/ip/blob/master/docs/README.md#saving-the-data)

## Summary of Commands
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

Format: `todo <DESCRIPTION>`

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

Format: `deadline <DESCRIPTION> /by <DATE_AND_TIME>`
Note: 
- DATE_AND_TIME should be in ISO 8601 format.
- Date should be given in year, month, day order, each seperated by `-`
- Date and time should be seperated by a `T`
- Time should be given in 24hrs format, with hour and time being seperated by a `:`

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

Format: `event <DESCRIPTION> /at <DURATION>`

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

Format: `mark <INDEX>`

Note: 
- The index refers to the index number shown in the displayed list.
- The index **must be a positive integer**, 1, 2, 3...
- The index **must be a valid index**, ie. calling mark 4 on a list with only 3 tasks will return an error.
- The task should not be already marked as complete.

Example of usage:
```
mark 2
```
Mark the task at index 2 as complete.



### Unmark a task: `unmark`
Marks the task at given index as incomplete.

Format: `unmark <INDEX>`

Note: 
- The index refers to the index number shown in the displayed list.
- The index **must be a positive integer**, 1, 2, 3...
- The index **must be a valid index**, ie. calling unmark 4 on a list with only 3 tasks will return an error.
- The task should not be already marked as incomplete

Example of usage:
```
unmark 1
```
Marks the task at index 1 as incomplete.



### Delete a task: `delete`
Deletes the task at given index.

Format: `delete <INDEX>`

Note: 
- The index refers to the index number shown in the displayed list.
- The index **must be a positive integer**, 1, 2, 3...
- The index **must be a valid index**, ie. calling delete 4 on a list with only 3 tasks will return an error.

Example of usage:
```
delete 3
```
Deletes the task at index 3.



### List all stored tasks: `list`
Shows a list of all stored tasks in the app.

Format: `list`



### Find tasks by a keyword: `find`
Returns a list of all tasks that contains the given keyword.

Format: `find <KEYWORD>`

Note: 
- The search is case_sensitive, ie. `Read` will return a different result from `read`.
- The keyword does not need to be proper word, ie. `re` will return both `read book` and `return book`.

Example of usage:
```
find assignment
```
This will return all tasks containing 'assignment' in their description.



### Get help page: `help`
Returns a list of all valid commands and their format in the app.

Format: `help`



### Exit from the app: `bye`
Exits the app.

Format: `bye`

### Saving the data:
The program saves the data to file on every operation, manual saving is **not** required.
