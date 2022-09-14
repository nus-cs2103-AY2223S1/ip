# User Guide for DUKE

## Features 

### Display task list

View your task list

### Add Tasks

Add up to 3 types of tasks 


**[Todo]**

A todo task denoted by [T]

_Input format: **"todo"** + task description_


**[Deadline]**

A deadline task with a deadline to be done by, denoted by [D]

_Input format: **"deadline"** + task description + **"/by"** + deadline_


**[Event]**

An event task with a time period for the event to be carried out, denoted by [E]

_Input format: **"event"** + task description + **"/at"** + time period_


### Mark / Unmark tasks as done

Marks tasks as done with an "X" 
or undone without an "X" 
to keep track of completion progression


### Undo

Undoes the previous command




## Usage

### list - Displays current task list

Example of usage:
> list

Expected outcome:
```
[T][X] Read book
[D][ ] Return Book /by Monday 2pm
[E][ ] Meeting /at Monday 2pm-4pm
```

### todo - Adds todo task to task list

Example of usage:
> todo Read Book

Expected outcome:
```
[T][ ] Read book
```

### deadline - Adds Deadline task to task list

Example of usage:
> deadline Return Book /by Monday 2pm

Expected outcome:
```
[D][ ] Return Book /by Monday 2pm
```

### event - Adds Event task to task list

Example of usage:
> event Meeting /at Monday 2pm-4pm

Expected outcome:
```
[E][ ] Meeting /at Monday 2pm-4pm
```

### mark - Marks task as done

Example of usage:
> mark 1

Expected outcome:
```
[T][X] Read book
```

### unmark - Marks task as undone

Example of usage:
> unmark 1

Expected outcome:
```
[T][ ] Read book
```

### undo - Undoes the previous command

Example of usage:
> undo

Expected outcome:
```
[T][X] Read book
[D][ ] Return Book /by Monday 2pm
[E][ ] Meeting /at Monday 2pm-4pm

unmark 1

[T][ ] Read book
[D][ ] Return Book /by Monday 2pm
[E][ ] Meeting /at Monday 2pm-4pm

undo 

[T][X] Read book
[D][ ] Return Book /by Monday 2pm
[E][ ] Meeting /at Monday 2pm-4pm
```