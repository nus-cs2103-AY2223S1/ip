# Duke Bot

## Your New Personal Task Assistant!

##Tasks
Duke supports three tasks: `Todo`, `Deadline` and `Event`. Each of these tasks supports users to mark as completed.

## Functionality
###Starting Out
Use `java -jar ip.jar` to run. 

###Exiting
Format:`bye`

Exits the application.

### Help
Format: `help`
 
Prints out the list of functions for Duke.

Example of usage:
```
help
```
```
help: prints the help menu
list: prints out the todo list            
bye: closes the program
mark: marks task as done
unmark: marks task as not done
find: finds all tasks containing given keyword
todo: adds a todo task
deadline: adds a task with a deadline (use /by)
event: adds an event (use /at)
```


###List
Format:`list` 

Prints out all the tasks in the list.

Example of usage:
```
list
```
```
Here are the tasks in your list:
 1. [D][X]  buy food (by: 2022-08-25 2300)
 2. [T][ ]  eat food
 3. [T][ ]  do work
 4. [T][ ]  breathe
```

### Mark
Format:`mark i`

Marks the `i`-th task in the list as done.

Example of usage:
```
mark 1
```
```
Nice! I've marked this task as done:
     1. [D][X]  buy food (by: 2022-08-25 2300)
```
### Unmark
Format: `unmark i`
 
Marks the `i`-th task in the list as not done.
Example of usage:
```
mark 1
```
```
OK, I've marked this task as not done yet:
     1. [D][ ]  buy food (by: 2022-08-25 2300)
```

### Find
Format: `find <Name>`

Finds all task that contains `<Name>` in the Task description.

Example of usage:
```
find food
```
```
Here are the matching tasks in your list:
    
    [T][ ] eat food
```
### Delete
Format: `delete i`

Deletes the task at index `i` in the list.

Example of usage:
```
delete 2
```
```
Noted. I've removed this task:
    [T][ ] eat food
Now you have 2 tasks in the list.
```
### Todo
Format: `todo <Task>`

Creates a Todo task with description `<Task>`.

Example of usage:
```
todo buy drinks
```
```
Got it. I've added to the list
    [T][ ] buy drinks
Now you have 3 tasks in the list.
```
### Event
Format: `event <Task> /at <Date> <Time>`

Creates an event at `<Date> <Time>` with task description `<Task>`. `<Date>` must be of format `YYYY-MM-DD`.

Example of usage:
```
event sleep /at 2022-08-24 2359
```
```
Got it. I've added this task
    [E][ ] sleep (at: 2022-08-24 2359)
Now you have 4 tasks in the list.
```
### Deadline
Format: `deadline <Task> /by <Date> <Time>`

Creates a deadline for `<Date> <Time>` with task description `<Task>`. `<Date>` must be of format `YYYY-MM-DD`.

Example of usage:
```
deadline CS2103T ip /by 2022-09-16 2359
```
```
Got it. I've added this task
    [D][ ] CS2103T ip (by: 2022-09-16 2359)
Now you have 5 tasks in the list.
```


##Saving
Saving is done automatically in Duke, tasks are stored in a `.txt` file on the user's local storage.
