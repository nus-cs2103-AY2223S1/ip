# User Guide

Froggy is a chat-bot like desktop app for managing your tasks, optimized for use via a
Command Line Interface**(CLI).

## Features 

### Listing all tasks: `list`
Shows all the tasks that you have on your Todo List.

Format: `list`

Examples:
- `list` will list all the tasks
<img src="https://user-images.githubusercontent.com/50956270/190833404-9faaa5f0-3f65-405c-8431-9f2875fd8c04.png" width="300" height="500" />


### Adding a ToDo task: `todo`
Adds a Todo task to your tasks.

Format: `todo TASK_NAME`

Examples: 
- `todo borrow book` creates a task called `borrow book`
<img src="https://user-images.githubusercontent.com/50956270/190833492-bc5b3c03-3c7a-40e8-bb74-5866e0439007.png" width="300" height="500" />

### Adding a Deadline task: `deadline`
Adds a Deadline task to your tasks which is a task that needs to be done before a specific date.

Format: `deadline TASK_NAME /by DATE`
- TASK_NAME is the name of the task
- DATE is the date in this format format `yyyy-mm-dd` e.g. (`2019-10-15`)

Examples:
- `deadline return book /by 2019-10-15` creates a task called return book with a deadline of `2019-10-15`. 
<img src="https://user-images.githubusercontent.com/50956270/190833525-495922a6-1a90-41af-8509-31647507855c.png" width="300" height="500" />

### Adding a Event task: `event`
Adds a Event task to your tasks which is a task that ends at a specific date.

Format: `event TASK_NAME /at DATE`
- TASK_NAME is the name of the task
- DATE is the date in this format format `yyyy-mm-dd` e.g. (`2019-10-15`)

Examples:
- `event project meeting /at 2019-10-15` creates a task called project meeting which ends at `2019-10-15`.
<img src="https://user-images.githubusercontent.com/50956270/190833559-daa6be97-c24b-47c6-8607-d6114e8f8231.png" width="300" height="500" />

### Marking a task as done: `mark`
Marks a task as done.

Format: `mark TASK_NUMBER`
- TASK_NUMBER can be found when you type the `list` command

Examples: 
- `mark 1` marks the first task in the list as done
<img src="https://user-images.githubusercontent.com/50956270/190833577-1581bfc3-d08d-490e-9e28-fa8d06080684.png" width="300" height="500" />

### Unmarking task: `unmark`
Unmarks a task, setting it as incomplete.

Format: `unmark TASK_NUMBER`
- TASK_NUMBER can be found when you type the `list` command

Examples:
- `unmark 1` marks the first task in the list as not done
<img src="https://user-images.githubusercontent.com/50956270/190833600-266199ba-90fa-43d2-b440-e317ba14470b.png" width="300" height="500" />

### Deleting a task: `delete`
Deletes a task.

Format: `delete TASK_NUMBER`
- TASK_NUMBER can be found when you type the `list` command

Examples:
- `delete 1` deletes the first task in the list
<img src="https://user-images.githubusercontent.com/50956270/190833622-19cdc0e5-b768-4863-a537-8d17a47f4492.png" width="300" height="500" />

### Updating a task: `update`
Updates a task

#### Format 1: `update TASK_NUMBER /description NEW_DESCRIPTION`
- TASK_NUMBER can be found when you type the `list` command
- NEW_DESCRIPTION is the new description that you want the task to have

Examples:
- `update 1 /description borrow book` updates the description of the first task to be `borrow book`
<img src="https://user-images.githubusercontent.com/50956270/190833732-c70f9001-2d14-4568-a142-82b89acf3764.png" width="300" height="500" />

#### Format 2: `update TASK_NUMBER /date DATE`
- TASK_NUMBER can be found when you type the `list` command
- DATE is the date in this format format `yyyy-mm-dd` e.g. (`2022-10-15`)

Examples: 
- `update 1 /date 2022-10-15` updates the date time of the first task to be `2022-10-15`
<img src="https://user-images.githubusercontent.com/50956270/190834429-e4a6adc6-50bb-4787-a877-b765c1e2b4a6.png" width="300" height="500" />

### Finding a task: `find`
Finds a task based on the matching keyword

Examples:
- `find borr` finds a task with the description containing the description `borr` 
<img src="https://user-images.githubusercontent.com/50956270/190329792-36a40877-b6e1-4f86-b795-8043c23dc4f2.png" width="300" height="500" />

### Goodbye: `bye`
Exits the program
