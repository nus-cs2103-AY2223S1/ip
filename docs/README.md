# `DukePro` User Guide
![Photo](Ui.png)

####`DukePro` is a powerful and reliable chatbot that allows user to keep track of tasks, events and deadlines.

## Features 

### Type of tasks

- `todo`: A task that has to be done
- `event`: An event with a date and time
- `deadline`: A task with a deadline

### Feature 1 - Welcome and Display Instructions

Greet DukePro and DukePro will display all commands/instructions available.

#### Usage/Command: 
`hi`

####Expected outcome:

A welcome message from DukePro will be displayed, together with the list of available instructions and command that DukePro consist of.

####Description of the outcome:

```
Hello! I'm Duke! What can I do for you?
The commands I currently have are as follows:
1) hi
2) bye  (to exit and close the program)
3) list
4) mark <index of task in list to mark>
5) unmark <index of task in list to unmark>
6) todo <description of task>
7) deadline <description of task> /by <date in DD/MM/YYYY format> 
8) event <description of task> /at <date in DD/MM/YYYY format> 
9) delete <index of task in list to delete> 
10) find <keywords of task in list to find> 
11) priority <priority level> /for <index of task in list to update> (default priority level is 0)
```

### Feature 2 - Bye
Terminate DukePro and Exit Program after all operations ceased.

#### Usage/Command:
`bye`

####Expected outcome:

DukePro will be terminated, list will be cleared and program will after all operations ceased.

####Description of the outcome:

```
<Exit the program>
```

### Feature 3 - List
List out all Tasks that was input by user.

#### Usage/Command:
### `list`

####Expected outcome:

DukePro will list out all tasks that have been input by user.

####Description of the outcome:

```
Here are the tasks in your list:
1. <Task>
...

```
### Feature 4 - Mark
Mark the Task as done based on index of task in list input by user.

#### Usage/Command:
### `mark <index of task in list to mark>`

####Expected outcome:

DukePro will mark the task based on index of task in list by user as done.

####Description of the outcome:

```
Nice! I've marked this task as done :)
[T][X] read (Priority: 0)
```
### Feature 5 - Unmark
Mark the Task as not done based on index of task in list input by user.

#### Usage/Command:
### `unmark <index of task in list to unmark>`

####Expected outcome:

DukePro will unmark (if it is initially marked) the task based on index of task in list by user as not done.

####Description of the outcome:

```
ok I mark this task as not done yet...
[T][ ] read (Priority: 0)
```
### Feature 6 - Todo
Create a Todo task - a task that has to be done by the user.

#### Usage/Command:
### `todo <description of task>`

####Expected outcome:

DukePro will create a task, one that has to be done by the user. DukePro will add the task into the list of tasks the user already has.
####Description of the outcome:

```
Got it, I've added this task:
[T][ ] read (Priority: 0)
Now you have 1 task in the list.
```
### Feature 7 - Deadline
Create a Deadline task - a task that has a deadline which needs to be followed by the user.

#### Usage/Command:
### `deadline <description of task> /by <date in DD/MM/YYYY format>`

####Expected outcome:

DukePro will create a Deadline task, one that has a deadline which the user would like to meet. DukePro will add the task into the list of tasks the user already has.
####Description of the outcome:

```
Got it, I've added this task:
[D][ ] assignment (Priority: 0) (by: 2022-09-22)
Now you have 1 task in the list.
```
### Feature 8 - Event
Create an Event task - a task that has a date where the event will be happening on.

#### Usage/Command:
### `event <description of task> /at <date in DD/MM/YYYY format>`

####Expected outcome:

DukePro will create an Event task, one that has a date which the event will be happening on. DukePro will add the task into the list of tasks the user already has.
####Description of the outcome:

```
Got it, I've added this task:
[E][ ] party (Priority: 0) (at: 2022-09-22)
Now you have 1 task in the list.
```
### Feature 9 - Delete
Delete the task based on index of the task in list input by the user.

#### Usage/Command:
### `delete <index of task in list to delete>`

####Expected outcome:

DukePro will delete the task in the list based on index that user indicate.
####Description of the outcome:

```
Noted. I've deleted this task:
[E][ ] party (Priority: 0) (at: 2022-09-22)
Now you have 1 tasks in the list.
```
### Feature 10 - Find
Find the task(s) in the list based on keyword(s) of the task input by the user.

#### Usage/Command:
### `find <keyword(s) of task in list to find>`

####Expected outcome:

DukePro will find and list out the task(s) in the list that matches the keyword(s) of the task input by the user.
####Description of the outcome:

```
Here are the matching tasks in your list:
[D][ ] assignment (Priority: 0) (by: 2022-09-22)
```
### Feature 11 - Priority
Update the priority level of the tasks based on input by user. Default priority level is 0. Priority level indicated with integers.

#### Usage/Command:
### `priority <priority level> /for <index of task in list to update>`

####Expected outcome:

DukePro will update the priority to the priority level as specified by user, for the specific task based on the index of task input by the user. Priority level of tasks is indicated with integers.
####Description of the outcome:

```
Nice! I've marked this task to your specific priority level :)
Default priority level is 0!
[D][ ] assignment (Priority: 3) (by: 2022-09-22)
```



