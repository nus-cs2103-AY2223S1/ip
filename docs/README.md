# User Guide
Duke Scheduler is a useful tool for keeping track of todos, events and deadlines, making scheduling easy, never get late anymore!

## Features 
1. [X] Adding a task
2. [X] Listing all tasks
3. [X] Finding task based on keyword
4. [X] Finding task based on date
5. [X] Cloning a task
6. [X] Deleting a task
7. [X] Marking a task as done
8. [X] Marking a task as undone
9. [X] Exiting the program

<br/><br/>
### 1.Adding a task 
Adds a todo, event or deadline task to the scheduler.
#### format:
```
To add a todo: 
todo description

To add a deadline: 
deadline description / by YYYY-MM-DD

To add an event: 
event description / at YYYY-MM-DD
```
#### Examples:
```
todo read book Assignment
deadline finsih cs2103 assignment / by 2022-09-16
event open house / at 2022-08-01
```
#### outcome:
![picture alt](https://i.postimg.cc/0jyCBxjn/add.png)

<br/><br/>
### 2.List all tasks
Shows all the tasks by completed and uncompleted status.
#### format:
```
list
```
#### outcome:
![picture alt](https://i.postimg.cc/66fLx7NQ/List.png)

<br/><br/>
### 3.Finding task based on keyword
Finds all the tasks matching the given keyword.
#### format:
```
find keyword
```
#### example:
```
find book
```
#### outcome:
![picture alt](https://i.postimg.cc/W1HwYQc6/find-Keyword.png)

<br/><br/>
### 4.Finding task based on date
Finds all the tasks fall on the same date.
#### format:
```
date YYYY-MM-DD
```
#### example:
```
date 2022-09-16
```
#### outcome:
![picture alt](https://i.postimg.cc/brc6C4bF/date.png)

<br/><br/>
### 5.Clone a task
Clones an existing task using the task index, this provides an easy way to add a new task without having to type all the similar information again.
#### format:
```
clone INDEX
```
#### example:
```
clone 2
```
#### outcome:
![picture alt](https://i.postimg.cc/449q6QtS/clone.png)


<br/><br/>
### 6.Mark a task
Marks a task as completed.
#### format:
```
mark INDEX
```
#### example:
```
mark 2
```
#### outcome:
![picture alt](https://i.postimg.cc/FzTLdL9g/mark.png)



<br/><br/>
### 7.UnMark a task
Marks a task as uncompleted.
#### format:*
```
unmark INDEX
```
#### example:
```
unmark 2
```
#### outcome:
![picture alt](https://i.postimg.cc/hjpjpRHK/unmark.png)


<br/><br/>
### 8.Delete a task
Deletes a task from the scheduler.
#### format:
```
delete INDEX
```
#### example:
```
delete 4
```
#### outcome:
![picture alt](https://i.postimg.cc/C1D7qJtC/delete.png)


<br/><br/>
### 9.Exiting the program
Exits the program.
#### format:
```
bye
```
#### outcome:
![picture alt](https://i.postimg.cc/TwF8Rkbr/bye.png)



<br/><br/>
### Saving data
The program will automatically save the task data, so no need to worry about data lost.

