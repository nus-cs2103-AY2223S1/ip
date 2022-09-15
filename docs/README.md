# User Guide

## Features 

### Add and save tasks

The chatbot allows you to add and save 3 different types of tasks, **todo**, **event** and **deadline**. Date and/or time has to be specified for **event** and **deadline** tasks.

### Indicate whether tasks are done or not

The chatbot allows you to mark your tasks as either **done** or **undone**.

### Update tasks

The chatbot allows you to update any previously saved tasks with new description, date and/or time.

### Delete tasks

The chatbot allows you to delete any previously saved tasks.

### View saved tasks

The chatbot allows you to view all your saved tasks in list form.

### Find saved tasks using keywords

The chatbot allows you to find saved tasks that contains a keyword/phrase.

## Usage

### `todo` - Adds and saves a **todo** task

To add and save a todo task, type the command as such:

"todo _description of task_"

Example of usage: 

`todo return book`

Expected outcome:

The todo task together with its description will be saved in the chatbot.

```
Ok! I have added the following Todo task!:
       T | O | return book
You now have a total of 1 tasks!
```

### `event` - Adds and saves an **event** task

To add and save a event task, type the command as such:

"event _description of task_ /at _date in YYYY-MM-DD format_ _time in HH:MM format_"

For date and time of event, at least one of them must be stated when saving the event task.

Example of usage: 

`event attend wedding /at 2022-11-10 18:00`

Expected outcome:

The event task together with its description, date and/or time will be saved in the chatbot.

```
Ok! I have added the following Event task!:
       E | O | attend wedding | Nov 10 2022 | 18:00
You now have a total of 1 tasks!
```

### `deadline` - Adds and saves an **deadline** task

To add and save a deadline task, type the command as such:

"deadline _description of task_ /by _date in YYYY-MM-DD format_ _time in HH:MM format_"

For date and time of deadline task, at least one of them must be stated when saving the deadline task.

Example of usage: 

`deadline return book /at 2022-07-06 08:05`

Expected outcome:

The deadline task together with its description, date and/or time will be saved in the chatbot.

```
Ok! I have added the following Deadline task!:
       D | O | return book | Jul 06 2022 | 08:05
You now have a total of 1 tasks!
```

### `list` - Lists down all saved tasks and numbers them

To view all saved tasks, type the command as such:

"list"

Example of usage: 

`list`

Expected outcome:

All saved tasks will be listed and numbered.

```
The following are your saved tasks:
       1. T | O | return book
       2. E | O | attend wedding | Nov 10 2022 | 18:00
       3. D | O | return book | Jul 06 2022 | 08:05 
```

### `mark` - Indicates that a task is done

To indicate that a task has been completed, type the command as such:

"mark _the number of the task as indicated in the list displayed after typing the 'list' command_"

Example of usage: 

`mark 2`

Expected outcome:

The second task in the list will be indicated as done, and its description in the list will have the "O" changed to "X"

```
Task has been marked as done!:
       E | X | attend wedding | Nov 10 2022 | 18:00
```

### `unmark` - Indicates that a task is not done

To indicate that a task has not been completed, type the command as such:

"unmark _the number of the task as indicated in the list displayed after typing the 'list' command_"

Example of usage: 

`unmark 2`

Expected outcome:

The second task in the list will be indicated as not done, and its description in the list will have the "X" changed to "O"

```
Task has been marked as NOT done!:
       E | O | attend wedding | Nov 10 2022 | 18:00
```

### `update` - Updates a task with a new description and/or date and/or time

To update an event task, type the command as such:

"update _the number of the task in the list_ _new description of the task_ /at _new date_ _new time_"

To update a deadline task, type the command as such:

"update _the number of the task in the list_ _new description of the task_ /by _new date_ _new time_"

You must include at least a new description/date/time for an update. 

If you are updating a date/time for an event task, you have to add "/at" before the date/time. If you are updating a date/time for an deadline task, you have to add "/by" before the date/time.

Example of usage: 

`update 2 /at 2022-12-10 17:00`

Expected outcome:

The second task in the list will be updated with the new date and time.

```
Task has been updated!:
       E | O | attend wedding | Dec 10 2022 | 17:00
```

### `delete` - Deletes a saved task in the chatbot

To delete a saved task, type the command as such:

"delete _the number of the task in the list_"

Example of usage: 

`delete 2`

Expected outcome:

The second task in the list will be deleted.

```
Ok! I have removed the following task!:
       E | O | attend wedding | Dec 10 2022 | 17:00
You now have a total of 2 tasks!
```

### `find` - Finds any saved tasks that matches a given keyword/phrase

To find a task based on a keyword/phrase, type the command as such:

"find _word/phrase that you want in the saved tasks_"

Example of usage: 

`find book`

Expected outcome:

All saved tasks whose description contains the word 'book' will be displayed.

```
The following are tasks in your list that contain the given keyword!:
       1. T | O | return book
       2. D | O | return book | Jul 06 2022 | 08:05 
```

### `bye` - Closes the chatbot and saves all changes to the saved tasks

To save all changes made to the saved tasks, such as new tasks added, tasks deleted and updates to tasks, type the command as such:

"bye"

Example of usage: 

`bye`

Expected outcome:

All changes made to the tasks during this session of the chat will be saved.

```
Sad to see you go! Visit me again soon!
```