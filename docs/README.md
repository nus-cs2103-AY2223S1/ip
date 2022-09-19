# User Guide

## Features

Doemon is a friendly chatbot that functions as a task manager, allowing you to add and delete tasks!

### Add tasks to your list

You can add 3 different types of tasks: Todo, Deadline, and Event tasks!

### Mark/Unmark your recorded tasks

Mark your tasks when you're done. Unmark them if there was something you missed.

### Delete tasks

Delete any tasks that you do not need anymore.

### Find tasks

Find tasks that you have recorded by using keyword phrases.

### Come back to your tasks later

Your tasks will be recorded after every action, and will persist across multiple usages!

## Usage

### `list` - List all tasks

List all the tasks that you have recorded.

Format: `list`

```
expected output
```

### `list` - List all tasks

List all the tasks that you have recorded.

Format: `list`
* Short-form: `l`

```
Here is what's on my bread:
1.[D][X] Finish CS2103T Individual Project (by: Sep 16 2022 11:59PM)
2.[E][ ] Go out and party! (at: Sep 17 2022 8:00PM)
3.[T][ ] Buy milk
```

### `todo` - Add a todo task

Adds a todo task to your list of tasks.

Format: `todo DESCRIPTION`
* Adds a todo item described by `DESCRIPTION`.
* `DESCRIPTION` field is compulsory and must be provided.
* Short-form: `t`.

Example usage:
* `todo Finish cake`
* `t Feed the dog`

```
Alright! I have recorded this task on my bread:
  [T][ ] Feed the dog
You now have 4 tasks recorded on my bread.
```

### `deadline` - Add a deadline task

Adds a deadline to your list of tasks, with a specified due date and/or time.

Format: `deadline DESCRIPTION [/by DATE_TIME]`
* Adds a deadline item described by `DESCRIPTION`, with due `DATE_TIME`.
* Both `DESCRIPTION` and `DATE_TIME` fields are compulsory and must be provided.
* If `DATE_TIME` has a valid format of `yyyy-mm-dd`, it will be reformatted as a proper date.
* If `DATE_TIME` has a valid date format *and* valid time format of `hhmm`, given in 24-hour format,
  both date and time will be reformatted.
* Short-form: `d`.

Example usage:
* `deadline Project submission /by tonight`
* `d Internship application /by 2022-09-25 2359`

```
Alright! I have recorded this task on my bread:
  [D][ ] Internship application (by: Sep 25 2022 11:59PM)
You now have 5 tasks recorded on my bread.
```

### `event` - Add an event task

Adds an event to your list of tasks, with a specified date and/or time.

Format: `event DESCRIPTION [/at DATE_TIME]`
* Adds a deadline item described by `DESCRIPTION`, with due `DATE_TIME`.
* Both `DESCRIPTION` and `DATE_TIME` fields are compulsory and must be provided.
* If `DATE_TIME` has a valid format of `yyyy-mm-dd`, it will be reformatted as a proper date.
* If `DATE_TIME` has a valid date format *and* valid time format of `hhmm`, given in 24-hour format,
  both date and time will be reformatted.
* Short-form: `e`.

Example usage:
* `event Dance concert /at tomorrow night`
* `e Chalet at Changi Civil Service Club /at 2022-12-27 1300`

```
Alright! I have recorded this task on my bread:
  [E][ ] Chalet at Changi Civil Service Slub (at: Dec 27 2022 1:00PM)
You now have 5 tasks recorded on my bread.
```

### `mark` - Mark a task as completed

Marks a recorded task as completed.

Format: `mark INDEX`
* Marks the task at the specified `INDEX`.
* `INDEX` is a compulsory field, and must be a valid number between 1 and the total number of tasks.
* Short-form: `m`.

Example usage:
* `mark 3`
* `m 2`

```
Yay! This task is now marked as done:
  [T][X] Feed the dog
```

### `unmark` - Unmark a task

Marks a task as in-progress.

Format: `unmark INDEX`
* Unmarks the task at the specified `INDEX`.
* `INDEX` is a compulsory field, and must be a valid number between 1 and the total number of tasks.
* Short-form: `u`.

Example usage:
* `unmark 3`
* `u 2`

```
I guess you weren't done with that one:
  [T][ ] Feed the dog
```

### `delete` - Delete a task

Removes a recorded task from your list of tasks.

Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* `INDEX` is a compulsory field, and must be a valid number between 1 and the total number of tasks.
* Short-form: `del`.

Example usage:
* `delete 3`
* `del 2`

```
I used a knife to slice off this task from my bread:
  [T][ ] Feed the dog
There's 5 items left on my bread.
```

### `find` - Find a task

Searches and returns any matching tasks given a specified input phrase.

Format: `find KEYPHRASE`
* Returns any tasks that contain the specified `KEYPHRASE`.
* `KEYPHRASE` is an optional field. Providing an empty `KEYPHRASE` will return all tasks.
* Search is *case-sensitive*.
* Short-form: `f`.

Example usage:
* `find and party`
* `f Internship`

```
Here are the matches I found on my bread:
1.[D][ ] Internship application (by: Sep 25 2022 11:59PM)
```

### `help` - Help

Provides some help text on all available commands.

Format: `help`
* Short-form: `h`.

Example usage:
* `help`
* `h`

```
These are the commands my bread can understand:
* list/l - Lists all your recorded tasks
* todo/t [desc] - Adds a todo with a specified description
* deadline/d [desc] /by [date/time] - Adds a deadline with specified description and date/time
* event/e [desc] /at [date/time] - Adds an event with specified description and date/time
* mark/m [num] - Marks the task at the specified number
* unmark/u [num] - Unmarks the task at the specified number
* delete/del [num] - Deletes the task at the specified number
* bye/b - Exits the chatbot
```

### `bye` - Closes the application

Say bye to Doemon and closes the window. 

Format: `bye`
* Short-form: `b`.

Example usage:
* `bye`
* `b`

```
I'm going to sleep now...See you again soon!
```

### Saving data

Doemon automatically saves all data on recorded tasks in the file `./data/doemon.txt`.

### Loading data

Doemon will try to load tasks from the file `./data/doemon.txt`. If loading of the file fails,
resulting from a corrupted file or otherwise, Doemon will create a new empty file.