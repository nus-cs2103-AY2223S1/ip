# User Guide

## Dobby

> Dobby is a chat-bot that aims to help users keep track of their daily tasks.

## Quick Start

1. Ensure you have Java `11` installed in your Computer.
2. Download the latest from [here](https://github.com/avock/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Dobby.
4. Double-click the file to start Dobby.

## Features

### Adding 3 types of tasks: Todo, Event and Deadline.

Three different task types to keep track off.

### Mark and Unmark tasks as done.

Status of tasks can be marked and unmarked by the user.

### Find tasks.

Tasks with descriptions which contain a certain keyword can be found and printed out.

### Delete tasks.

Unwanted tasks can be deleted by the user.

### Customizable commands.

Users can customize their own preferred command for each function.

### List out tasks.

Tasks can be listed out, both done and not-done for the user to refer to. The status of tasks will be shown as well.

###                                                                                           

## Usage

### `Todo` - Add a todo

Adds a todo task into the task list. A todo is a general task.

Format of usage:
`todo todoDescription`

Example of usage:

`todo find Dobby's sock`

Expected outcome:

The newly added todo task, as well as the number of tasks in the task list.

```
Yes master, Dobby will add the following task to the list:
 
        [T][ ] find Dobby's sock
        
Master has 1 task(s) left.
```

###

### `Deadline` - Add a deadline

Adds a deadline task into the task list. A deadline is a task with a due date.

Format of usage:

`deadline deadlineDescription /by YYY-MM-DD HHMM`

Example of usage:

`deadline save Harry Potter /by 2022-09-16 2359`

Expected outcome:

The newly added description task, as well as the number of tasks in the task list.

```
Yes master, Dobby will add the following task to the list: 

        [D][ ] save Harry Potter (by: Sep 16 2022 23:59)
        
Master has 1 task(s) left.
```

###

### `Event` - Add an event

Adds a event task into the task list. An event is a task with a date of the event.

Format of usage:

`event deadlineDescription /at YYY-MM-DD HHMM`

Example of usage:

`event escape Hogwarts /at 2022-09-16 2359`

Expected outcome:

The newly added description task, as well as the number of tasks in the task list.

```
Yes master, Dobby will add the following task to the list: 

        [E][ ] escape Hogwarts (by: Sep 16 2022 23:59)
        
Master has 1 task(s) left.
```

###

### `list` - Lists all tasks

Lists out all tasks, both done and not-done for the user to refer to. The status of tasks will be shown as well.

Format of usage:

`list`

Expected outcome:

```
"Here are the tasks Dobby has found:"

    1. [T][ ] TestTodo
    2. [D][X] TestDeadline (by: Feb 02 2022 02:02)
    3. [E][ ] TestEvent (by: Feb 02 2022 02:02)
```

###

### `mark` - Mark a task as done

Updates the status of a task to done.

Format of usage:

`mark taskIndex`

Example of usage:

`mark 1`

Expected outcome for tasks that were not marked as done:

```
Well done master! Dobby has marked the following task as done:
        
        [T][X] TestTodo
```

Expected outcome for tasks that were marked as done:

```
Dobby has already marked this task done!
```

###

### `unmark` - Mark a task as not done

Updates the status of a task to not done.

Format of usage:

`unmark taskIndex`

Example of usage:

`unmark 1`

Expected outcome for tasks that were marked as done:

```
OK, Dobby will take care of
        
        [T][ ] TestTodo
```

Expected outcome for tasks that were not marked as done:

```
Master has never marked this task done before...
```

###

### `delete` - Delete a task

Removes a task from the task list permanently.

Format of usage:

`delete taskIndex`

Example of usage:

`delete 1`

Expected outcome:

```
Task deleted! Less work for master! Dobby is HAAAAAPPY!
Dobby has removed this task: 
    
        [T][ ] TestTodo

Master has only 1 task(s) left.
```

###

### `find` - Find tasks by keyword

Lists out tasks whose description contain the given keyword or phrase.

Format of usage:

`find keyword/phrase`

Example of usage:

`find dobby's sock`

Expected outcome:

```
Dobby has found the following relevant tasks:

        1.[T][ ] dobby's sock
        2.[T][ ] find dobby's sock
```

###

### `simplify` - Customizes commands for a function

Customizes the command to call each function.

Format of usage:

`simplify initialCommand newCommand`

Example of usage:

`simplify todo dobbyTodo`

Expected outcome:

```
Yes master, from now on dobby will do [todo] when dobby sees [dobbyTodo].
```

###

### `bye` - Exit the program

Format of usage:

`bye`

Expected outcome:

```
Byebye. Dobby will miss you!
```

###