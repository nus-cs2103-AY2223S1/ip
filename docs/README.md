# User Guide
Duke Scheduler is a useful tool for keeping track of todos, events and deadlines.

## Features 
-[x] Adding a task
-[x] Listing all tasks
-[x] Finding task based on keyword
-[x] Finding task based on date
-[x] Cloning a task
-[x] Deleting a task
-[x] Marking a task as done
-[x] Marking a task as undone
-[x] Exiting the program

### Adding a task 
Adds a todo, event or deadline task to the scheduler.
#### format:
```
To add a todo: 
todo description

To add an event: 
event description / at YYYY-MM-DD

To add a deadline: 
deadline description / by YYYY-MM-DD
```
#### Examples:
```
todo Do CS2103 Assignment
event Graduation / at 2022-08-08
deadline CS2103 Assignment / by 2022-09-16
```

<br/><br/>
### List all tasks
Shows all the tasks by completed and uncompleted status.
#### format:
```
list
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)

<br/><br/>
### Finding task based on keyword
Finds all the tasks matching the given keyword.
#### format:
```
find keyword
```
#### example:
```
find assignment
```
#### outcome:
![picture alt](https://i.postimg.cc/x8K5RK0j/find-Command.png)

<br/><br/>
### Finding task based on date
Finds all the tasks fall on the same date.
#### format:
```
date YYYY-MM-DD
```
#### example:
```
date 2022-08-08
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)

<br/><br/>
### Clone a task
Clones an existing task using the task index, this provides an easy way to add a new task without having to type all the similar information again.
#### format:
```
clone INDEX
```
#### example:
```
clone 1
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)


<br/><br/>
### Mark a task
Marks a task as completed.
#### format:
```
mark INDEX
```
#### example:
```
mark 1
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)



<br/><br/>
### UnMark a task
Marks a task as uncompleted.
#### format:*
```
unmark INDEX
```
#### example:
```
unmark 1
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)


<br/><br/>
### Delete a task
Deletes a task from the scheduler.
#### format:
```
delete INDEX
```
#### example:
```
delete 1
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)


<br/><br/>
### Exiting the program
Exits the program.
#### format:
```
bye
```
#### outcome:
[//]: # (![picture alt]&#40;https://i.postimg.cc/x8K5RK0j/find-Command.png&#41;)



<br/><br/>
### Saving data
The program will automatically save the task data, so no need to worry about data lost.




### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
