# User Guide

## Features 

### List

Lists all tasks in task list with task index. 

### Add

Adds a task to your task list.

Types of tasks:
1. Todo 
  - command:
    `todo <taskname>`
2. Event
  - command:
    `event <taskname> /at <loaction> <yyyy-mm-dd> <hhmm> `
    - date and time are all in integers, time should be in 24 hour clock. eg 2007-07-28 1400
3. Deadline
  - command:
    `deadline <taskname> /by <yyyy-mm-dd> <hhmm>`
    - date and time are all in integers, time should be in 24 hour clock. eg 2007-07-28 1400
 
### Delete

Removes a task from task list.
- command:delete 'index'
  - index has to be a positive integer staring from 1. Index of task can be check using list feature. 

### Tag

Tag tasks with tags such as #fun
- command:
  `tag <index> #<tag>`
- command:
  `print tag <index>`
  - prints task that has already been tagged 
- command: 
  `remove tag <index>`
  - removes the tag for the task 

### Search

1. Search name
  - command:
    `find <taskname>`
    - returns sepcific task of the given task name
2. Search date
  - command: 
    `datefilter <yyyy-mm-dd>`
    - returns all tasks on the same date
3. Search tag
  - command: 
    `#<tag> `
    - returns all tasks with the same tag

### Mark

Marks a task as done or undone.
- command:
  `mark <index>`
- command:
  `unmark <index>`
  - index has to be a positive integer staring from 1. Index of task can be check using list feature.

## Usage

### Todo Task

Example of usage: 

`todo shopping`

Expected outcome:

```
Got it, this task is added in your list:
[T][ ] shopping
Now you have 1 task in your list.
```

### Event Task

Example of usage: 

`event meeting /at school 2007-04-07 1400`

Expected outcome:

```
Got it, this task is added in your list:
[E][ ] meeting (at: school, SATURDAY, Apr 07 2007, 02:00 pm)
Now you have 2 task in your list.
```

### Deadline Task

Example of usage: 

`deadline essay /by 2007-07-07 1900`

Expected outcome:

```
Got it, this task is added in your list:
[D][ ] essay (by: SATURDAY, Jul 07 2007, 07:00 pm)
Now you have 3 task in your list.
```

### List

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] shopping
2.[E][ ] meeting (at: school, SATURDAY, Apr 07 2007, 02:00 pm)
3.[D][ ] essay (by: SATURDAY, Jul 07 2007, 07:00 pm)
```

### Mark Task

Example of usage: 

`mark 1`

Expected outcome:

```
Nice! I have marked this task as done:
[T][X] shopping
```

### Unmark Task

Example of usage: 

`unmark 1`

Expected outcome:

```
This task is marked as not done:
[T][ ] shopping
```


### Delete Task

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've deleted this task:
[E][ ] meeting (at: school, SATURDAY, Apr 07 2007, 02:00 pm)
Now you have 2 tasks in your list.
```

### Tag Task

Example of usage: 

`tag 1 #fun`

Expected outcome:

```
Your task: [T][ ] shopping is tagged as:
#fun
```

### Find Task

## by task name 

Example of usage: 

`find shopping`

Expected outcome:

```
Here are the matching tasks:
1.[T][ ] shopping
```

## by task date

Example of usage: 

`dateFilter 2007-07-07`

Expected outcome:

```
Here are the matching tasks:
1.[D][ ] essay (by: SATURDAY, Jul 07 2007, 07:00 pm)
```


## by tag

Example of usage: 

`#fun`

Expected outcome:

```
Here are the matching tasks:
1.[T][ ] shopping
```

### Bye


Example of usage: 

`bye`

Expected outcome:

```
Bye! Hope to see you again soon!
```
