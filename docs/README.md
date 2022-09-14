# User Guide
Ted is a chatbot for managing tasks.

In order to use Ted, you will need Java 11 installed in your computer.

## Features 

### Add todo task: `todo`

Adds a task with a basic task description to Ted.

Format: `todo [TASK]`

Example of usage:`todo read book`

Expected outcome:

Ted will indicate successful addition to task list, as well as a count of all tasks currently in task list.

e.g.
```
added to tasklist:
[T][ ] read book
task count: 1
```

### Add deadline task: `deadline`

Adds a task with a task description and a deadline to Ted.

Format: `deadline [TASK] /by [YYYY-MM-DD HH:mm]`

Example of usage: `deadline submit homework /by 2022-12-12 23:59`

Expected outcome:

Ted will indicate successful addition to task list, as well as a count of all tasks currently in task list.


e.g.
```
added to tasklist:
[D][ ] submit homework (by: 12 Dec 2022 11:59PM)
task count: 2
```

### Add event task: `event`

Adds a task with a task description and an event timing to Ted.

Format: `event [TASK] /at [TIME]`

Example of usage: `event go for school /at friday 2-6pm`

Expected outcome:

Ted will indicate successful addition to task list, as well as a count of all tasks currently in task list.

e.g.
```
added to tasklist:
[E][ ] go for school (at: friday 2-6pm)
task count: 3
```

### Mark task: `mark`

Marks a task as completed.

Format: `mark [INDEX]`

Example of usage: `mark 1`

Expected outcome:

Ted will indicate task completion. 

e.g.
```
Great! Task done:
[T][X] read book
```

### Unmark task: `unmark`

Marks a task as uncompleted.

Format: `unmark [INDEX]`

Example of usage: `unmark 1`

Expected outcome:

Ted will indicate that task is not completed.

e.g.
```
Aw :( Task undone:
[T][ ] read book
```

### Delete task: `delete`

Deletes a task.

Format: `delete [INDEX]`

Example of usage: `delete 1`

Expected outcome:

Ted will indicate task deletion, and display remaining task count.

e.g.
```
Done! Task deleted:
[T][ ] read book 
remaining task count: 2
```

### List all tasks: `list`

Lists all tasks added to Ted.

Format: `list`

Expected outcome:

Ted will display all tasks in task list.

e.g.
```
Your tasklist:
1. [D][ ] submit homework (by: 12 Dec 2022 11:59PM)
2. [E][ ] go for school (at: friday 2-6pm)
```

### Find tasks: `find`

Finds all tasks that contain the keyword entered by user.

Format: `find [KEYWORD]`

Example of usage: `find school`

Expected outcome:

Ted will display all tasks that contain the search keyword entered.

e.g.
```
Here are the matching tasks in your list:
1. [E][ ] go for school (at: friday 2-6pm)
```
### Remind about deadline tasks: `remind`

Reminds about upcoming deadlines.

Format: `remind`

Expected outcome:

Ted will display tasks with upcoming deadlines.

e.g.
```
!Upcoming Deadlines!
1. [D][ ] submit homework (by: 12 Dec 2022 11:59PM)
```

### Exit program: `bye`

Ends the program.

Format: `bye`

## Command summary
| Action       | Format, Examples                                                                                   |
|--------------|----------------------------------------------------------------------------------------------------|
| add todo     | `todo [TASK]`<br/>e.g., `todo read book`                                                           |
| add deadline | `deadline [TASK] /by [YYYY-MM-DD HH:mm]`<br/>e.g., `deadline submit homework /by 2022-12-12 23:59` |
| add event    | `event [TASK] /at [TIME]`<br/>e.g., `event go for school /at friday 2-6pm`                         |
| mark task    | `mark [INDEX]`<br/>e.g., `mark 1`                                                                  |
| unmark task  | `unmark [INDEX]`<br/>e.g., `unmark 1`                                                              |
| delete task  | `delete [INDEX]`<br/>e.g., `delete 1`                                                              |
| list         | `list`                                                                                             |
| find         | `find [KEYWORD]`<br/>e.g., `find book`                                                             |
| remind       | `remind`                                                                                           |
| bye          | `bye`                                                                                              |
