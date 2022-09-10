# Uncle Cheong User Guide

## Features 

### 1. Adding tasks

#### - Add a Todo

Command: `todo {task details}`

Example: `todo make the bed`

Expected outcome:
```
Swee lah! I added this task liao:
[T][ ] make the bed
Boss, you got 1 task now
```

#### - Add an Event

Command: `event {task details} /at {date and time in MMM d yyyy hh:mm a}`

Example: `event JB trip /at Sep 15 2022 08:00 AM`

Expected outcome:
```
Swee lah! I added this task liao:
[E][ ] JB trip (at: 15-Sep-2022 08:00 AM)
Boss, you got 2 tasks now
```

#### - Add a Deadline

Command: `deadline {task details} /by {date and time in MMM d yyyy hh:mm a}`

Example: `deadline tutorial submission /by Sep 16 2022 11:59 PM`

Expected outcome:
```
Swee lah! I added this task liao:
[D][ ] tutorial submission (by: 16-Sep-2022 11:59 PM)
Boss, you got 3 tasks now
```

### 2. List tasks

Description: lists out all the tasks in the list according to the order at which the task was added

Command: `list`

Expected outcome:
```
Boss ah, this one your tasks:
1. [T][ ] make the bed
2. [E][ ] JB trip (at: 15-Sep-2022 08:00 AM)
3. [D][ ] tutorial submission (by: 16-Sep-2022 11:59 PM)
```

### 3. Mark tasks

Description: marks the task at the input task number as completed

Command: `mark {task number}`

Example: `mark 2`

Expected outcome:
```
Swee lah! I marked this task as done liao:
[E][X] JB trip (at: 15-Sep-2022 08:00 AM)
```

### 4. Unmark tasks

Description: marks the task at the input task number as completed

Command: `unmark {task number}`

Example: `unmark 2`

Expected outcome:
```
Eh? Not done yet? Okay I change liao:
[E][] JB trip (at: 15-Sep-2022 08:00 AM)
```

### 5. Delete tasks

Description: delete the task at the input task number

Command: `delete {task number}`

Example: `delete 3`

Expected outcome:
```
Okay boss, this task I delete le:
[D][ ] tutorial submission (by: 16-Sep-2022 11:59 PM)
```

### 6. Find tasks

Description: View all the tasks in your list that contain the keyword

Command: `find {keyword}`

Example: `find CS2102`

Expected outcome:
```
Boss ah, this one all your tasks matching 'CS2102':
1. [T][ ] CS2102 Lecture Recording
2. [D][ ] CS2102 Tutorial (by: 12-Sep-2022 05:00 PM)
```

### 7. View schedule

Description: View all the events in your list at the input date

Command: `schedule /at {date in MMM d yyyy}`

Example: `schedule /at Sep 15 2022`

Expected outcome:
```
Here are your tasks at 2022-09-15
[E][] JB trip (at: 15-Sep-2022 08:00 AM)
```
