# User Guide

## Features 

### Manage Tasks

There are 3 types of tasks:

1. Todos
2. Deadlines (due date)
3. Events (date of event)

### Mark Tasks

Mark Tasks as completed.

### Search for tasks

Find a task efficiently using the search function.

### Automatic Sorting

Deadlines and events have a time field.  
Tasks are sorted automatically based on their dates.

## Usage

`[parameter]` denotes required parameters.

### `list` - Displays the user's tasks.

Displays all tasks, with their type, completion status and date.

Example of usage: 

`list`

Expected outcome:

```
>> 1.[D][] complete ip(by: 16/9/2022 2359)
>> 2.[E][] midterm celebration(at: 17/9/2022 0000)
>> 3.[T][] revise for midterms
```

### `todo [description]` - Add a Todo task.

Adds a todo task with a description.

Example of usage:

`todo revise for midterms`

Expected outcome:

```
>> Got it. I've added this task:
>>     [T][] revise for midterms
>> Now you have 1 tasks in the list.
```

### `deadline [description] /by [deadline] [time]` - Add a Deadline.

Adds a deadline with a description with a due date and time.

Example of usage:

`deadline complete ip /by 16/09/2022 2359`

Expected outcome:

```
>> Got it. I've added this task:
>>     [D][] complete ip(by: 16/9/2022 2359)
>> Now you have 2 tasks in the list.
```

### `event [description] /at [date] [time]` - Add a Deadline.

Adds an event with a description with a date and time.

Example of usage:

`event midterm celebration /at 17/09/2022 0000`

Expected outcome:

```
>> Got it. I've added this task:
>>     [E][] midterm celebration(at: 17/9/2022 0000)
>> Now you have 3 tasks in the list.
```

### `mark [task number]` - Marks a task as done.

Indicates that the task with this task number is complete.

Example of usage:

`mark 1`

Expected outcome:

```
>> Nice. I've marked this task as done:
>>     [D][X] complete ip(by: 16/9/2022 2359)
```

### `unmark [task number]` - Marks a task as incomplete.

Indicates that the task with this task number is incomplete.

Example of usage:

`unmark 1`

Expected outcome:

```
>> Ok, I've marked this task as not done yet:
>>     [D][] complete ip(by: 16/9/2022 2359)
```

### `delete [task number]` - Deletes a task

Deletes the task with this task number is incomplete.

Example of usage:

`delete 3`

Expected outcome:

```
>> Noted. I've removed this task:
>>     [T][] revise for midterms
>> Now you have 2 tasks in the list.
```

### `find [key string]` - Finds tasks. 

Displays all task which contains the key string in the task description.

Example of usage:

`find midterm`

Expected outcome:

```
>> [E][] midterm celebration(at: 17/9/2022 0000)
>> [T][] revise for midterms
```

### `bye` Exits the application.

Closes duke, and saves the tasks.  Will be loaded when duke is opened again.
