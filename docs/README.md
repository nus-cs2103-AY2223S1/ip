# User Guide - SotongBoi

## Features

SotongBoi allows you to create and delete Tasks. There are three kinds of tasks:

* ToDos are simple todo items (e.g. homework)
* Deadlines are todo items with a deadline associated with it (e.g. submit assignment by 2/2/2012, 23:59)
* Events are situations that occur between a given timerange, e.g. (Holiday from 2/2/2012, 00:00 to 4/2/2012, 23:59)

In addition, there are other features such as searching, marking/unmarking Tasks, and obtaining statistics available for
SotongBoi.

Here is a full list:

* Add the three types of Tasks mentioned above
* Mark/unmark Tasks as complete/incomplete
* Delete Tasks
* Find Tasks by name
* List all Tasks in the list
* Find statistics of tasks in list: How many of each type of Tasks are available

## Todo List

Your Todo List stores the list of Tasks, which are saved to a text file in ```data/storage.txt```. You can call
the ```list``` command to see your list of Tasks _(refer to the ```list``` command below). Here is how to intepret it:
``` [A][B]<TASK NAME>```

* ```A```: Represents the type of Task.
    * ```T```: ToDo Task
    * ```D```: Deadline Task
    * ```E```: Event Task
* ```B```: Represents if the Task is marked as done or not. If the task is done, will be marked as ```X```.
* ```<TASK NAME>```: Name of the Task.

## Add Task

Added tasks will appear in your todo list. Call the ```list``` function to see your list of Tasks.

### Adding a ToDo Task

Adds a ```ToDo``` object to the task list.\
```ToDos``` are tasks without any associated date times, e.g. Buy tomatoes.

**Usage**: ```todo <TASK_NAME>```\
**Example**: ```todo Buy Tomatoes>``` \
**Appears in Todo List as**: ```[T][ ] Buy Tomatoes```

### Adding a Deadline Task

Adds a ```Deadline``` object to the task list.\
```Deadlines``` are tasks with a deadline involved. e.g. Finish homework by 2/2/2012 23:59.

**Usage**: ```deadline <TASK_NAME> /by <DAY>/<MONTH>/<YEAR> <24-HOUR TIME FORMAT>```\
**Example**: ```deadline Homework /by 1/1/2000 1200``` \
**Appears in Todo List as**: ```[D][ ] Homework (by: Jan 1 2000, 1200)```

### Adding a Event Task

Adds a ```Event``` object to the task list.\
```Events``` are tasks with a time range for the event. e.g. Concert from 2/2/2012 22:00 to 2/2/2012 2359. \

**
Usage**: ```event <TASK_NAME> /at <DAY>/<MONTH>/<YEAR> <24-HOUR TIME FORMAT> - <DAY>/<MONTH>/<YEAR> <24-HOUR TIME FORMAT> "```\
**Example**: ```event Concert /at 1/1/2000 1200 - 1/1/2000 1900``` \
**Appears in Todo List as**: ```[E][ ] Concert (at: Jan 1 2000, 1200 to Jan 1 2000, 1900)```

## Marking and Unmarking Tasks

### Mark a Task as Done

Marks a Task as complete.

**Usage**: `mark <INDEX>`\
**Example**: `mark 1`\
**Marks Todo List as**: `[T][X] Sample Todo`

### Unmark a Task as Incomplete

Marks a Task as incomplete.

**Usage**: `unmark <INDEX>`\
**Example**: `unmark 1`\
**Marks Todo List as**: `[T][ ] Sample Todo`

## Statistics

Statistics provided include:

* the frequency of a type of Task

### Frequency of a Task

Find the frequency of a Task in the Todo List. There are four targets available:

* `ToDos`: Count of ToDos
* `Deadlines`: Count of Deadlines
* `Events`: Count of Events
* `All`: Count of all Tasks

**Usage**: `statistic count <TARGET>`\
**Example**: `statistic count Events`\
**Output**: `The count of Events is 3.`

## Other Commands

### Deleting Tasks

Deletes a Task in the Todo List.

**Usage**: `delete <INDEX>`\
**Example**: `delete 1`\

### Find Task by Name

Finds a Task by its name. Searches by substring.

**Usage**: `find <TASK NAME>`\
**Example**: `find Homework`\
**Output**: List of Tasks that matches the string.

### List All Tasks

Lists all tasks in the Todo List.

**Usage**: `list`\
**Output**: List of all Tasks in the Todo List.
