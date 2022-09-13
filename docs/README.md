# User Guide
Duke is a user-friendly reminder bot that helps you keep track of your tasks and things to do.

## Features 
1. Adding a Todo task
2. Adding a Deadline task
3. Adding an Event task
4. Adding a Duration task
5. Mark a task as done
6. Unmark a done task
7. Delete a task
8. List all tasks
9. Find a task by description

## QuickStart
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar from the release section.
3. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
4. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.

### Feature-Todo

Adds a todo task to the task list

#### Usage
Will add a task that is due in the indefinite future to your list

Example of usage:
todo <description of the task>

Expected outcome
The task will be added to the list

```
Got it. I've added this task
[T][] <description of task>
Now you have 1 tasks in the list
```
### Feature-Deadline

Adds a Deadline task to the task list

#### Usage
Will add a task that is due at a specific time to your list

Example of usage:
deadline <description of the deadline> /by <time of the deadline>

Expected outcome
The task will be added to the list

```
Got it. I've added this task
[D][] <description of task> (by <time of the deadline>)
Now you have 1 tasks in the list
```
### Feature-Event

Adds a Event task to the task list

#### Usage
Will add a task that happens at a specific time to your list

Example of usage:
event <description of the event> /at <time of the event>

Expected outcome
The task will be added to the list

```
Got it. I've added this task
[E][] <description of task> (at <time of the event>)
Now you have 1 tasks in the list
```

### Feature-Task

Adds a Duration task to the task list

#### Usage
Will add a task that take s a specific amount of time to finish to your list

Example of usage:
task <description> /takes <time to complete task>

Expected outcome
The task will be added to the list

```
Got it. I've added this task:
[DT][] <description of task> (takes <time to complete task>)
Now you have 1 tasks in the list
```
### Feature-Mark

Marks a task on the task list

#### Usage
Will mark a task indicated by the input index

Example of usage:
mark <task number>

Expected outcome
The task will be marked as done

```
Nice, I've marked this task as done
[DT][X] <description of task> (takes <time to complete task>)
Now you have 1 tasks in the list
```
### Feature-Unmark

Unmarks a task on the task list

#### Usage
Will unmark a task indicated by the input index

Example of usage:
unmark <task number>

Expected outcome
The task will be marked as not done

```
Ok, I've marked this task as not done yet
[DT][] <description of task> (takes <time to complete task>)
Now you have 1 tasks in the list
```
### Feature-Delete
 
Delete task on the task list

#### Usage
Will delete a task indicated by the input index

Example of usage:
delete <task number>

Expected outcome
The task will be deleted

```
Noted, I have removed this task:
[DT][] <description of task> (takes <time to complete task>)
Now you have 0 tasks in the list
```

### Feature-List

Shows all tasks on the task list

#### Usage
Shows all the tasks by order of the index

Example of usage:
list

Expected outcome:
All the tasks will be shown

```
Here are the tasks in your list:
1. [DT][] <description of task> (takes <time to complete task>)
2. [E][] <description of task> (at <time of the event>)
```

### Feature-Find

Finds a task by the description

#### Usage
Shows all the tasks that match the search words

Example of usage:
Find <search string>

Expected outcome:
All the tasks that match the search criteria will be shown

```
Here are the matching tasks in your list:
1. [DT][] <description of task> (takes <time to complete task>)
```



