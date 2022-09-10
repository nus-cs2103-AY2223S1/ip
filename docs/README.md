# User Guide
Duke CLI Chatbot frees your mind of having to remember things you need to do. It's,

- text-based
- easy to use
- ~~FAST~~ ***SUPER FAST*** to use

Download it from [here](https://github.com/sprintaway/ip) and try now!

## Features 


### `list`, shows the list of tasks stored.

## Usage

Example of usage:
`list`

Expected outcome: 
```
"Here are the tasks in your list:
 1. ...
 2. ...
```

### `todo xxx`, where xxx refers to any task you would like to add to the list

## Usage

Example of usage:

`todo clean the toilet`

Expected outcome:
```
Got it. I've added this task:
1. [T][ ] todo clean the toilet 
Now you have 1 tasks in the list.
```

### `deadline xxx /by yyy`, where xxx refers to any task you would like to add to the list and yyy refers to a date in the yyyy-MM-dd format

## Usage

Example of usage:

`deadline submit finance report /by 2022-10-02`

Expected outcome:
```
Got it. I've added this task:
1. [D][ ] submit finance report (by: Oct 02 2022)
Now you have 1 tasks in the list.
```

### `event xxx /at yyy`, where xxx refers to any task you would like to add to the list and yyy refers to a date in the yyyy-MM-dd format

## Usage

Example of usage:

`event submit finance report /at 2022-10-02`

Expected outcome:
```
Got it. I've added this task:
1. [E][ ] submit finance report (at: Oct 02 2022)
Now you have 1 tasks in the list.
```

### `mark xxx`, where xxx refers to a task number inside your list, marks the task at that task number to be done through "X"

## Usage

Example of usage (assuming you have a `[T][ ] todo clean the toilet` inside your list):

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
1. [T][X] todo clean the toilet
```


### `unmark xxx`, where xxx refers to a task number inside your list, unmarks the task at that task number

## Usage

Example of usage (assuming you have a `[T][X] todo clean the toilet` inside your list):

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
1. [T][ ] todo clean the toilet
```

### `delete xxx`, where xxx refers to a task number inside your list, deletes the task at that task number

## Usage

Example of usage (assuming you have a `[T][ ] todo clean the toilet and [D][ ] submit finance report (by: Oct 02 2022)` inside your list):

`delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][ ] todo clean the toilet
Now you have 1 tasks in the list.
```

### `bye`, exits the application

## Usage

Example of usage:

`bye`

Expected outcome:
```
_______________________________________
Bye. Hope to see you again soon!
_______________________________________
```
and it exits the application

### `sort`, sorts the dates in chronologically order, the earliest dates are at the top, the latest dates at the bottom, and todo will always be at the bottom due to lack of date

## Usage

Example of usage:

`sort` on a list of 
```
1. [D][ ] submit finance report (by: Dec 19 2022)
2. [D][ ] submit chemistry report (by: Oct 01 2022)
3. [T][ ] cut my hair
4. [D][ ] submit physics report (by: Oct 02 2022)
```

Expected outcome:
```
Here is the new sorted list:
1. [D][ ] submit chemistry report (by: Oct 01 2022)
2. [D][ ] submit physics report (by: Oct 02 2022)
3. [D][ ] submit finance report (by: Dec 19 2022)
4. [T][ ] cut my hair
```

### `find xxx`, where xxx is a string (some text) that you would like to find

## Usage

Example of usage:

`find repo` on a list of 
```
1. [D][ ] submit finance report (by: Dec 19 2022)
2. [D][ ] submit chemistry homework (by: Oct 01 2022)
3. [T][ ] cut my hair
4. [D][ ] submit physics report (by: Oct 02 2022)
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][ ] submit finance report (by: Dec 19 2022)
2. [D][ ] submit physics report (by: Oct 02 2022)
```

