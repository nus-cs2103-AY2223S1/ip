# User Guide

## Features 

### Feature-1: Adding and deleting Tasks to the chatbot

Tasks can be added to the chatbot, and there are 3 different types of tasks: Todo, Event and Deadline.
1. Todo: a generic task type which only has a description of the task.
2. Event: A more specific task type about an upcoming event that happens at a certain date. Has both a description and a date field.
3. Deadline: Another more specific task about an upcoming deadline that is set at a certain date. Had both a description and a date field.

These tasks can also be deleted by the user.

### Feature-2: Listing out all tasks in the task list

Tasks can be listed out for the user to see. Users will see a full description of every task. Listing is also related both to deleting and marking tasks as the list index is used to specify which task to delete and mark.

### Feature-3: Finding and listing out all tasks which have a specific keyword

Lists out all tasks which contains a user specified keyword.

### Feature-4: Marking completion of tasks

Tasks can be marked as "done" or "not done". This will show when the user lists out all the tasks as a marking next to the task with an "x" marking indicating a task that is done while as absence of said marking indicating it is not yet done.

### Feature-5: Saving and loading tasks to a log file

Tasks are saved to a log file when the user enters the bye command, these tasks can later be loaded again.

### Feature-6: Archiving tasks

Tasks can be archived by the user to different archives, allowing users to save task configurations and load them at will. These archives are named by the user so that they may track them easier. Users may also list out all archive names so they don't lose track.

## Usage

### 'todo [description]'

Adds a todo task with the description into the list.

Example of usage: 

```
todo Buy the groceries
```

Expected outcome:

Adds a todo task into the list with description "Buy the groceries"

```
Task added:
[T][]Buy the groceries
```

### 'event [description] [at date in format DD/MM/YYYY]'

Adds a event task with the description and at date into the list

Example of usage: 

```
event Uncle's bday /at 2/2/2023
```

Expected outcome:

Adds a event task into the list with description "Uncle's bday" and at date of "2/2/2023"

```
Task added:
[E][]Uncle's bday (at: Thu, 2 Feb 2023)
```

### 'deadline [description] [by date]'

Adds a deadline task with description and by date into the list

Example of usage: 

```
deadline homework /by 4/6/2023
```

Expected outcome:

Adds a deadline task into the list with description "homework" and by date of "4/6/2023"

```
Task added:
[D][]homework (by: Sun, 4 Jun 2023)
```
### 'delete [index]'

Deletes task at the given index of the tasklist

Example of usage: 

```
todo run
delete 1
```

Expected outcome:

Deletes the todo task which is the only task in the list

```
Task deleted:
[T][] run
```

### 'list'

Lists out all tasks in the task list

Example of usage: 

```
todo run
event hide /at 2/4/2025
list
```

Expected outcome:

lists out both the todo task and the event task

```
Tasks:
1. [T][]run
2. [E][]hide (at: Wed, 2 Apr 2025)
```
### 'find [keyword]'

Lists out all tasks in the task list which contains the keyword

Example of usage: 

```
todo run
todo away we run
todo cycle
find run
```

Expected outcome:

lists out both the todo task and the event task

```
Tasks found:
Tasks:
1. [T][]run
2. [T][]away we run
```
### 'mark [index]'

marks the task in the task list at the given index as done

Example of usage: 

```
todo run
mark 1
```

Expected outcome:

marks the todo task as done

```
Tasks:
1. [T][]run
2. [E][]hide (at: Wed, 2 Apr 2025)
```
### 'unmark [index]'

marks the task in the task list at the given index as not done

Example of usage: 

```
todo run
mark 1
unmark 1
```

Expected outcome:
marks the todo task as not done

```
Task has been marked not done: [T][]run
```
### 'bye [-optional: log file name]'

saves the current tasks to the log file, optional argument to specify the log file's name in case the user does not want the default name

Example of usage: 

```
todo run
bye
```

Expected outcome:
saves the todo task to the log file

```
Saved 1 tasks.
Bye bye! :D
```
### 'load [-optional log file name]'

saves the current tasks to the log file, optional argument to specify the log file's name in case it is not the default one

Example of usage: 

```
load
```

Expected outcome:
loads the tasks in the log file into the program.

```
Loaded 1 tasks.
Have a productive day!
```
### 'archive [archive file name]'

saves the current tasks to the archive file with the name specified, then clears the task list.

Example of usage: 

```
todo run
archive MyArchive
```

Expected outcome:
the todo task is archived in the "MyArchive.txt" archive file.

```
Archived 1 tasks
```
### 'loadA [archive file name]'

Loads the tasks in the archive file with the specified name into the current programs task list, while clearing the previous tasks in it.

Example of usage: 

```
loadA MyArchive
```

Expected outcome:
the tasks archived in the "MyArchive.txt" file are loaded into the program, with previous tasks being cleared.

```
Loaded 1 tasks from archive!
```
### 'listA'

Lists all the archives the program has created in that session.

Example of usage: 

```
archive MyArchive
listA
```

Expected outcome:
lists all the archives the program is tracking, that being the "MyArchive" archive

```
Archive list:
1. MyArchive
```
