# User Guide

## Features 

### Feature- Manage Tasks

Tasks are categorized between  `Deadline`, `Event` and `Todo`.

Users are able to: 

1. **Add** and **delete** Tasks
2. **Mark** and **unmark** Tasks when done 
3. **Find** Tasks using keywords
4. **Filter** for `Deadline` and `Event` using dates
5. **List** to see all tasks
6. **Tag** tasks 

### Feature- Save 

After every command, every Task is saved automatically to a save file. 
This save file is read when the application is opened at a later date.

## Usage
Here is the list of all the commands that can be used within the 
Blink application. 

The information of each command is arranged in: 
1) Input format 
2) Parameters (if applicable for the command)
3) Sample Input
4) Expected outcome

### `todo` - Adds a _Todo_ task 

Input format: `todo DESCRIPTION`

Parameters:
- `DESCRIPTION: content of the task`

Sample input: 
```
todo Team meeting
```

Expected outcome: 

Output the information of task added and
current number of tasks recorded by the application.

```
Alright this task has been successfully added!
[T][ ] Team meeting
Total of 1 tasks now
```

### `deadline` - Adds a _Deadline_ task

Input format: `deadline DESCRIPTION /by DATE`

Parameters:
- `DESCRIPTION: content of the task`
- `DATE: date of the deadline in the format YYYY-MM-DD`

Sample input: 
```
deadline 2103T ip /by 2022-09-16
```

Expected outcome:

Output the information of task added and
current number of tasks recorded by the application.

```
Alright this task has been successfully added!
[D][ ] 2103T ip (by: SEPTEMBER 16 2022 FRIDAY)
Total of 2 tasks now
```

### `event` - Adds a _Event_ task

Input format: `event DESCRIPTION /at DATE`

Parameters:
- `DESCRIPTION: content of the task`
- `DATE: date of the event in the format YYYY-MM-DD`

Sample input: 
```
event Studying day /at 2022-09-22
```

Expected outcome:

Output information of task added and
current number of tasks recorded by the application.

```
Alright this task has been successfully added!
[T][ ] Studying day (at: SEPTEMBER 22 2022
Thurssday)
Total of 3 tasks now
```

### `list` - Displays all the tasks 

Input format: `list`

Sample input: 
```
list
```

Expected output:

Shows all tasks currently stored in the application.

```
There is a total of 3 tasks currently: 
1: [T][ ] Team meeting
2: [D][ ] 2103T ip (by: SEPTEMBER 16 2022 
FRIDAY)
3: [E][ ] Studying day (at: SEPTEMBER 22 2022
THURSDAY)
``` 
If there is no tasks stored or added, it will show: 
```
No task currently
```

### `mark` - Marks a task as done

Input format: `mark NUMBER`

Parameters:
- `NUMBER: task at the position of the number to be marked`

Sample input:
```
mark 1
```

Expected output:

Task is marked as done as the [ ] is
changed to [X].

```
This task has been marked as done
[T][X] Team meeting
```

But if a mark task is marked the following output is displayed.

```
Error found: You do already still wanna do 
again? :|
```


### `unmark` - Mark task as undone 

Input format: `unmark NUMBER`

Parameters
- `NUMBER: task at the position of the number to be unmarked`

Sample input:
```
unmark 1
```

Expected output:

Task is unmarked as done as the [X] is
changed to [ ].

```
This task has been marked as done
[T][ ] Team meeting
```

But if a unmark task is unmarked the following output is displayed.

```
Error found: How to unmark? You haven't even 
do yet...
```

### `delete` - Delete a task

Input format: `delete NUMBER`

Parameters:
- NUMBER: `task at the position of the number is deleted`

Sample input:
```
delete 1
```

Expected output:

Shows the info of the task deleted and the
number of remaining tasks.

```
Task has been successfully deleted.
[T][ ] Team meeting
2 tasks remaining
```

### `tag` - Tags a task with some info

Input format: `tag NUMBER INFO`

Parameters:
- `NUMBER: position of task to tag`
- `INFO: info to tag task with`

Sample input:
```
tag 1 important work
```

Expected out:
Tags the back of a task with the INFO behind a #. It 
will wrap to the next line if the task info is too long.
```
Tag added successfully
[D][ ] 2103T ip (by: SEPTEMBER 16 2022 FRIDAY)
#important work
```

### `find` - Find task using a keyword 

Input format: `find KEYWORD`

Parameters:
- `KEYWORD: find tasks that has this keyword in their desciption or tags`

Sample input:
```
find 2103T
```

Expected output:

Finds and list all tasks with the keyword provided.

```
1 task found 
[D][ ] 2103T ip (by: SEPTEMBER 16 2022 FRIDAY
#important work
```

If no tasks found with the keyword Blink will output:

```
No tasks found with the keyword: 2103t
```

### `filter` - Filter to get all tasks on a specified date

Input format: `filter DATE`

Parameters:
- `DATE: searches for all tasks of this inputted date in the
   date format YYYY-MM-DD`

Sample input:
```
filter 2022-09-16
```

Expected output:
Finds and list all tasks with the specified date. So will only
work with  `Deadline` and `Event` tasks.

```
1 task found
[D][ ] 2103T ip (by: SEPTEMBER 16 2022 FRIDAY
#important work
```

If there is no task on that specified date the output will be:

```
No task found on 2022-09-16
```

### `bye` - Ends the program 

Exits the program and application shuts down.

Input format: `bye` 

