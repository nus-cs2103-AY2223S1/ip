# Commands

## Add a todo task: `todo DESCRIPTION`
Adds a task with the given `DESCRIPTION`.

**Example of usage:**
 ```
 todo return book
 ```

## Add a deadline task: `deadline DESCRIPTION /by yyyy-MM-dd`
Adds a task with the given `DESCRIPTION` and a specified deadline.

**Example of usage:**
```
deadline read book /by 17-09-2022
```

 ## Add an event: `event DESCRIPTION /at yyyy-MM-dd`
 Adds an event with the given `DESCRIPTION` and a specified date.

 Date and time format is the same as the deadline command.

**Example of usage:**
```
event CS2103T lecture /at 17-09-2022
```

## List all tasks: `list`
Lists all tasks in the task list.


 ## Marking a task as done: `mark INDEX` —
 Marks the task at the given `INDEX` as done.

You may want to use the `list` command to find the index of the task you want to mark as done.

**Example of usage:**
```
mark 1
 ```

## Marking a task as not done: `unmark INDEX` —
Marks the task at the given `INDEX` as not done.

**Example of usage:**
 ```
 unmark 1
 ```

## Delete a task - `delete INDEX` —
Deletes the task at the given `INDEX`.

**Example of usage:**
 ```
delete 1
 ```

## Find tasks by keyword: `find KEYWORD` —
Finds all tasks whose description contains the given `KEYWORD`.
The search is case-insensitive.

**Example of usage:**

```
find book 
```

 ## Exit the program: `exit`
 Exits the program with a goodbye message.

 # Advanced
 ## Data Storage
 All the data is saved in the file `./data/duke.txt`. You can modify task list by directly editing
 this file. Each line of the file describes one task. The format is the following:
 ```
<TASK TYPE>,<COMPLETION STATUS>,<DESCRIPTION>,<ARGS...>
 ```
 - `TASK Type` is `T` for todo task, `D` for deadline, and `E` for event tasks.
 - `COMPLETION STATUS` is 0 or 1 depending on whether the task is completed or not.
 - `DESCRIPTION` is the description of the task.
 - Currently, `ARGS...` only take in a date for deadline and events. The date must be in the
 format `yyyy-MM-dd`. Specifically, day and month number should not have leading zeros.

 Example data file:
 ```
T,1,return book  
E,0,project meeting ,2022-10-11
D,0,finish project,2022-09-15
 ```