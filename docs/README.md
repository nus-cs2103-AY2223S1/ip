# Duke Bot User Guide

Duke is a bot that will help you keep track of your tasks in a task list. 👍🏻

## Features 

Note: Details within [] are to be inputted by users themselves. 
```
eg.
- User Guide: todo [task]
- User Input: todo study
```

### Todo

Adds a todo task into your task list.

**Usage**

`todo [task]` - Adds a todo task with the task description inputted by the user.
```
eg. 
- User Input: todo study
- Expected Output: Got it! I've added this task: 
		   [T][] study 
		   Now you have 1 tasks in the list.
```

### Deadline

Adds a deadline task with a date deadline to your task list.

**Usage**

`deadline [task] /by [date in yyyy-mm-dd format]` - Adds a task with a deadline and the task description inputted by the user.
```
eg. 
- User Input: `deadline study /by 2022-09-13`
- Expected Output: Got it! I've added this task: 
		   [D][] study (by: 13 September 2022) 
		   Now you have 2 tasks in the list.
```

### Event

Adds a event task with any description of date or time to your task list.

**Usage**

`event [task] /at [date/time/day]` - Adds an event task with the task description inputted by the user.
```
eg. 
- User Input: event study /at Monday 3pm
- Expected Output: Got it! I've added this task: 
                   [E][] study (at: Monday 3pm) 
		   Now you have 3 tasks in the list.
```

### List

Lists out all the tasks in your task list.

**Usage**

`list` - Lists out all the tasks the user has inputted into the Duke bot.
```
eg. 
- User Input: `list`
- Expected Output: Here are the tasks in your list: 
		   1.[T][] study 
		   2.[D][] study (by: 13 September 2022) 
		   3.[E][] study (at: Monday 3pm)
```

### Mark

Marks a task in your task list as complete.

**Usage**

`mark [task number]` - Marks the task specified by the user as complete.
```
eg. 
- User Input: `mark 1`
- Expected Output: Nice! I've marked this task as done:
		   [T][X] study 
```

### Unmark

Unarks a task in your task list.

**Usage**

`unmark [task number]` - Unmarks the task specified by the user.
```
eg. 
- User Input: `unmark 1`
- Expected Output: OK, I've marked this task as not done yet:
		   [T][] study 	   
```

### Find

Finds a task with a specific keyword or kerywords in your task list.

**Usage**

`find [word]` - Finds all the tasks in the task list with the word 'word' in it.
```
eg. 
- User Input: `find study`
- Expected Output: Here are the matching tasks in your list:
		   1.[T][] study 
		   2.[D][] study (by: 13 September 2022) 
		   3.[E][] study (at: Monday 3pm) 	   
```

### Delete

Deletes a specific task in your task list.

`delete[task number]` - Deletes the task with the task number specified by the user from the task list.
```
eg. 
- User Input: `delete 1`
- Expected Output: Noted i've removed this task:
		   [T][] study 
		   Now you have 2 tasks in the list.
```
