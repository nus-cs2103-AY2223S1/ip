# User Guide

Hi! Doomba is your personal chatbot that tracks all the tasks that you plan on doing

## Features 

- Different types of tasks to add
  - ToDos
  - Deadlines
  - Events
  - Recurring Tasks

- Multiple commands
 - add
 - delete
 - mark
 - unmark
 - list
 - help
 - bye

- Wrong command handling

- Save your tasks

- Bot with a personality

### Types of Tasks

- ToDo: Just a simple task.
- Deadline: For tasks that needs to be done by a certain date.
- Event: For tasks happening at a particular time.
- Recurring Tasks: For tasks that occurs daily, weekly, monthly or yearly.

### Saving tasks

Your data won't be gone when you exit the program as you can save your tasks.
No need to key in everything again!

Description of the feature.

## Usage

### `Keyword` - Describe action
\[ \] are for arguments with description/format specified
< > are for optional arguments

##### `todo` - Add ToDo into list:
`todo [description]`

Example: `todo buy groceries`
Doomba will reply with a confirmation that the todo has been added, along with the task type \[T\], whether the task has been marked done or not [ /X], the task description, and how many tasks you have in the list.
```
added: [T][ ] buy groceries
You currently have 1 tasks in the list
```

##### `deadline` - Add deadline into list:
`deadline [ description ] /by [ date ] < time >`
- date is in dd/MM/yy format
- time is in HHmm format

Example: `deadline submit project /by 06/09/22 2359` - with optional time argument
Doomba will reply with a confirmation that the deadline has been added, along with the task type \[D\], whether the task has been marked done or not [ /X], the task description, and how many tasks you have in the list.
```
added: [D][ ] submit project (by: 06 Sep 22 11:59PM)
You currently have 2 tasks in the list
```

##### `event` - Add event into list:
`event [ description ] /at [ dd/MM/yy ] < time >`
- date is in dd/MM/yy format
- time is in HHmm format

Example: `event Alec Benjamin Concert /at 04/12/22` - without optional time argument
Doomba will reply with a confirmation that the event has been added, along with the task type \[E\], whether the task has been marked done or not [ /X], the task description, and how many tasks you have in the list.
```
added: [E][ ] Alec Benjamin Concert (at: 04 Dec 22)
You currently have 3 tasks in your list
```

##### `recurring` - Add recurring task into list:
`recurring [ description ] /every [ frequency ] </at time> *[ number of times ]`
frequency options: format
- Yearly: dd/MM/yy
- Monthly: dd (from 1-31)
- Weekly: 3 letter day of the week, first letter capitalized
- Daily: HHmm (optional time argument not applicable)
If optional time argument is not provided, the default time would be 12:00PM.

Example: `recurring go climbing /every Wed /at 1330 *8` - with optional time argument
Doomba will reply with a confirmation that the recurring task has been added, along with the task type \[R\], whether the task has been marked done or not [ /X], the task description, and how many tasks you have in the list.
```
added: [R][ ] go climbing (next: 14 Sep 22 1:30PM; remaining: 8)
You currently have 4 tasks in the list
```

##### `list` - Shows all tasks currently in list:
`list`
Doomba will display all tasks in the list, sorted by order added. Index for each task and total number of tasks in the list will also be shown.

##### `delete`
##### `mark`
##### `unmark`
##### `remaining`
##### `/?`
##### `bye`










Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
