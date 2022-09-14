# User Guide
- User Guide
  - [Overview](#overview)
  - [Features](#features)
    - [Addition of tasks](#addition-of-tasks)
    - [Deleting of tasks](#deleting-of-tasks)
    - [Marking and unmarking of tasks](#marking-and-unmarking-of-tasks)
    - [Finding of tasks or tags](#finding-of-tasks-or-tags)
    - [Viewing of tasks](#viewing-of-tasks)
  - [Usage](#usage)
    - [list](#list---an-overview-of-all-your-tasks)
    - [todo](#todo---add-a-todo-task)
    - [event](#event---add-a-event-task)
    - [deadline](#deadline---add-a-deadline-task)
    - [find](#find---find-a-task)
    - [delete](#delete---delete-a-task)
    - [cdf(changedateformat)](#cdf---changing-of-the-date-format)
    - [findtag](#findtag---find-a-tag)
    - [mark](#mark---mark-a-task)
    - [unmark](#unmark---unmark-a-task)
    - [bye](#bye---exits-the-program)

## Overview
This is Seaward, your friendly neighborhood chatbot! 
Seaward helps you to take charge of organizing the tasks that you have to complete by allowing you to add, tag, mark and list them all out in one convenient place.
Potential users will be glad to have trusty Seaward bt their side!

## Features 

### Addition of tasks

Seaward categorizes your tasks into three main tasks:
- ```Todo```
- ```Deadline```
- ```Event```

A ```Task``` has a description can it can have a associated tag included.
A ```Todo``` is like a normal task, it just has a description.
A ```Deadline``` has a description, but it also has an associated date which represents its deadline.
A ``` Event``` is similar to a ```Deadline```, but its associated date represents when it will happen instead.

### Deleting of tasks

Seaward allows you to delete your tasks after you no longer want to keep track of them.
Or you simply want to edit your tasks.

### Marking and unmarking of tasks

Seaward allows you to keep track of your progress with your tasks by marking or unmarking your tasks .

### Finding of tasks or tags

Seaward can easily help you to find a keyword in the description or the tag of a task in a pinch. Thus, you can easily retrieve the information that you want with this feature.

### Viewing of tasks

Seaward allows you can see all of your tasks in one place so that you can see how many pending tasks you have.

### Storing of your tasks in your computer

Seaward will create a text file so that it will __remember__ what your tasks were! When you exit and start Seaward again, you will see that your tasks were right where you left off.

## Usage

### `list` - An overview of all your tasks

Displays the current tasks that you have. The description, tag and type of task will be shown, along with its completion and date _(if applicable)_

Format: 
`list`

Example of usage: `list`

Expected outcome:
```
1. [D][X] Watch lecture #urgent (by: 2022-09-12)
2. [E][ ] TF2 i69 lan #hype (at: 2022-08-25)
3. [T][ ] Borrow a broom
```

### `todo` - Add a Todo task

Creates a ```Todo``` task with the provided associated description and/or tag.

Format:

`todo {DESCRIPTION}`

`todo {DESCRIPTION} /tag {TAG}`

Example of usage: 

`todo read book`

`todo read book /tag Hunger Games`

Expected outcome:
```
Noted. I have added:
[T][ ] read book
Now you have 1 task(s) in your list.
```
```
Noted. I have added:
[T][ ] read book #Hunger Games
Now you have 1 task(s) in your list.
```

### `event` - Add a Event task

Creates an ```Event``` task with its associated description, tag and date. Date must be in YYYY-MM-DD format.

Format:

`event {DESCRIPTION} /at {YYYY-MM-DD}`

`event {DESCRIPTION} /tag {TAG} /at {YYYY-MM-DD}`

Example of usage: 

`event TF2 i69 lan /at 2022-08-25`

`event TF2 i69 lan /tag hype /at 2022-08-25`

Expected outcome:
```
Noted. I have added:
[E][ ] TF2 i69 lan (at: 2022-08-25)
Now you have 1 task(s) in your list.
```
```
Noted. I have added:
[E][ ] TF2 i69 lan #hype (at: 2022-08-25)
Now you have 1 task(s) in your list.
```

### `deadline` - Add a Deadline task

Creates an ```Deadline``` task with its associated description, tag and date. Date must be in YYYY-MM-DD format.

Format:

`deadline {DESCRIPTION} /by {YYYY-MM-DD}`

`deadline {DESCRIPTION} /tag {TAG} /by {YYYY-MM-DD}`

Example of usage: 

`deadline functional expressionism /by 2022-08-24`

`deadline functional expressionism /tag no pls /by 2022-08-24`

Expected outcome:
```
Noted. I have added:
[D][ ] functional expressionism (by: 2022-08-24)
Now you have 1 task(s) in your list.
```
```
Noted. I have added:
[D][ ] functional expressionism #no pls (by: 2022-08-24)
Now you have 1 task(s) in your list.
```

### `find` - Find a task

Finds the tasks that contain a key word provided by the user. Note that the find feature is case sensitive.

Format:

`find {KEYWORD}`

Example of usage: 

`find book`

Expected outcome:

If your list consists of
```
1. [T][ ] return book #urgent
```
then
```
Here are the matching tasks in your list:
1. [T][ ] return book #urgent
```

If your list consists of
```
1. [T][ ] return Book #urgent
```
then
```
Sorry! There are no matching tasks that contains
book
```

### `delete` - Delete a task

Delete a task in your list. The task index that you provide will delete the task at that index.

Format:

`delete {INDEX}`

Example of usage: 

`delete 2`

Expected outcome:

If your list consists of
```
1. [T][ ] return book #urgent
2. [T][ ] Borrow a broom
```
then
```
Noted. I have removed this task:
[T][ ] Borrow a broom
Now you have 1 task(s) in your list.
```

### `cdf` - Changing of the date format

Changes the date format for an ```Event``` or a ```Deadline```. However, the changes are temporary. The index is provided by the user.

Format:

`cdf {INDEX}`

Example of usage: 

`cdf 1`

Expected outcome:

If your list consists of
```
1. [D][ ] functional expressionism (by: 2022-08-24)
```
then
```
Here is a different date format for this task!
[D][ ] functional expressionism (by: 24 Aug 2022)
```

### `findtag` - Find a tag

Finds the tasks with tags that contain a key word provided by the user. Note that the findtag feature is case sensitive.

Format:

`findtag {KEYWORD}`

Example of usage: 

`findtag urgent`

Expected outcome:

If your list consists of
```
1. [T][ ] return book #urgent
```
then
```
Here are the tasks with the matching tags in your list:
1. [T][ ] return book #urgent
```

If your list consists of
```
1. [T][ ] return book #Urgent
```
then
```
Sorry! There are no matching tasks with a tag that contains
urgent
```

### `mark` - Mark a task

Marks a task as complete. The index of the task will be provided by the user.

Format:

`mark {INDEX}`

Example of usage: 

`mark 2`

Expected outcome:

If your list consists of
```
1. [T][ ] return book #urgent
2. [T][ ] Borrow a broom
```
then
```
I have marked this task as done:
[T][X] Borrow a broom
```

### `unmark` - Unmark a task

Marks a task as incomplete. The index of the task will be provided by the user.

Format:

`unmark {INDEX}`

Example of usage: 

`unmark 1`

Expected outcome:

If your list consists of
```
1. [T][X] return book #urgent
2. [T][ ] Borrow a broom
```
then
```
I have marked this task as undone:
[T][ ] return book #urgent
```

### `bye` - Exits the program

When exiting, your tasks will be saved into a file on your computer so that the next time you boot up Seaward, you will have your tasks there.

Format:

`bye`

Example of usage: 

`bye`

Expected outcome:
```
Seaward out! See you next time buddy!
```
