# User Guide


## Command Format

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `todo <TODO_NAME>`, `<TODO NAME>` is a parameter which can be used as `todo sleep`.

* Items in square brackets are optional.<br>
  e.g `<TODO_NAME> [/tag <TAG_NAME>]` can be used as `swim /tag cardio` or as `swim`.

* `<DEADLINE_TIME>` and `<EVENT_TIME>` can take any date time in the format of `year-month-date hour:minute` or `date-month-year hour:minute`.<br>
  The separator of date, month and year can be changed to `/` or a space character.
  e.g `2022/09/8 20:55` or `7 Nov 1945 00:00`

* Only `<TAG_NAME>` with no spaces can be given to a task.<br>
  e.g `exam` but not `CS exam`


## Features 

### `todo`
Adds a tagged or untagged todo task to the task list

Format: `todo <TODO_NAME> [/tag <TAG>]`

Expected outcome:  
Description of todo task being added, alongside the current number of tasks in the list

Example of usage:  
`todo clean my room`  
`todo run 10K marathon /tag goals`

### `deadline`
Adds a tagged or untagged deadline task to the task list

Format: `deadline <DEADLINE_NAME> /by <DEADLINE_TIME> [/tag <TAG>]`

Expected outcome:  
Description of deadline task being added, alongside the current number of tasks in the list

Example of usage:  
`deadline submit IP /by 16/9/2022 23:59`  
`deadline get married /by 2030-01-01 00:00 /tag love`

### `event` 
Adds a tagged or untagged event task to the task list

Format: `event <EVENT_NAME> /at <EVENT_TIME> [/tag <TAG>]`

Expected outcome:  
Description of event task being added, alongside the current number of tasks in the list

Example of usage:  
`event celebrate birthday /at 15 Sep 2022 15:00`  
`event puppy death /at 2010 January 14 17.30 /tag memory`

### `delete`
Deletes a task from the task list

Format: `delete <TASK_NUMBER>`

Expected outcome:  
Description of task being deleted

Example of usage:  
`delete 2`

### `mark`
Marks a task in the task list as done

Format: `mark <TASK_NUMBER>`

Expected outcome:  
Description of task being marked

Example of usage:  
`mark 3`

### `unmark`
Unmarks a task in the task list as not done

Format: `unmark <TASK_NUMBER>`

Expected outcome:  
Description of task being unmarked

Example of usage:  
`unmark 1`

### `tag`
Tags or changes a tag of a task in the task list with a new tag name

Format: `tag <TASK_NUMBER> <TAG_NAME>`

Expected outcome:  
Description of task being tagged

Example of usage:  
`tag 5 education`

### `untag`
Untags a task in the task list

Format: `untag <TASK_NUMBER>`

Expected outcome:  
Description of task being untagged

Example of usage:  
`untag 9`

### `view`
Finds the tasks in the task list with the given tag

Format: `view <TAG_NAME>`

Expected outcome:  
List of tasks with the given tag

Example of usage:  
`view sports`

### `find`
Finds the tasks in the task list with the matching name

Format: `find <KEYWORD>`

Expected outcome:  
List of tasks with the matching name

Example of usage:  
`find exam CS`

### `list`
Shows all tasks in the task list

Format: `list`

Expected outcome:  
List of all tasks in the task list

Example of usage:  
`list`

### `bye`
Ends the program and saves the file to the storage

Format: `bye`

Expected outcome:  
Program is closed

Example of usage:  
`bye`


