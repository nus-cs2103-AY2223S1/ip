# User Guide

## Features

### Managing Todos

Never forget to do something ever again!
- Create Todos
- Delete Todos

### Managing Events

Never forget another important day of your life!
- Create Events
- Delete Events
- List Events before and after a specific time

### Managing Deadlines

Never forget another deadline!
- Create Deadline
- Delete Deadline
- List Deadline before and after a specific time

### Sorting Tasks

Sort your tasks chronologically

### Marking Tasks

Mark tasks to show that you've done it

### Unmarking Tasks

Unmark tasks to show that you've not done it

### Delete Tasks

Delete Tasks that are irrelevant

### `Help` - Get help with the commands of Aladdin

Example of usage:

`help`

Expected outcome:

Description of the outcome.

```
Here are some wishes you can try:
deadline
delete
event
exit
find
help
list
mark
sort
todo
unmark
```

### `deadline` - Add a new Deadline
Example of Usage:
`deadline return book by 02-12-2019 18:00`
Expected Outcome:
A new deadline is created
```
This deadline has been successfully added!
[D] [ ] return book (by: 02-12-2019 18:00)
```

### `delete` - Delete a Task
Example of Usage:
`delete 1`
Expected Outcome:
A task will be deleted
```
This deadline has been successfully deleted!
[D] [ ] return book (by: 02-12-2019 18:00)
```

### `event` - Add a new Event
Example of Usage:
`event project meeting at 05-12-2019 12:00`
Expected Outcome:
A new event is created
```
This event has been successfully created!
[E] [ ] project meeting (at: 05-12-2019 12:00)
```

### `exit` - Exit the programme
Example of Usage:
`exit`
Expected Outcome:
The Aladdin Service programme will close
```
Thank you for using aladdin services!
```

### `find` - Find a specific task
Example of Usage:
`find book`
Expected Outcome:
All tasks related to books will be displayed
```
Here are the matching tasks:
1. [T] [X] buy book
2. [T] [ ] read book
3. [D] [ ] return book (by: 06-06-2019 00:00)
```

### `help` - Get help with aladdin commands
Example of Usage:
`help`
Expected Outcome:
The aladdin help string will be shown
```
Here are some wishes you can try:
todo
help
exit
find
sort
deadline
event
list
delete
mark
unmark
```

### `list` - List all Tasks
Example of Usage:
`list`
Expected Outcome:
All tasks stored will be displayed
```
Here are your tasks:
1: [T] [X] buy book
2: [T] [ ] read book
3: [T] [ ] buy milk
4: [E] [ ] project meeting (at: 05-12-2019 12:00)
5: [D] [ ] return book (by: 06-06-2019 00:00)
6: [T] [ ] buy milk
7: [E] [ ] project meeting (at: 05-12-2019 12:00)
```

`list before 05-12-2019 11:00`
Expected Outcome:
All tasks before the specified date time stored will be displayed
```
Here are your tasks:
1: [T] [X] buy book
2: [T] [ ] read book
3: [T] [ ] buy milk
4: [E] [ ] project meeting (at: 05-12-2019 12:00)
5: [T] [ ] buy milk
```

`list after 05-12-2019 11:00`
Expected Outcome:
All tasks after the specified date time stored will be displayed
```
Here are your tasks:
Here are your tasks:
1: [T] [X] buy book
2: [T] [ ] read book
3: [T] [ ] buy milk
4: [E] [ ] project meeting (at: 05-12-2019 12:00)
5: [T] [ ] buy milk
6: [E] [ ] project meeting (at: 05-12-2019 12:00)
```

### `mark` - Mark a task done
Example of Usage:
`mark 1`
Expected Outcome:
The task will be marked as done
```
This task has been successfully marked!
[T] [X] buy book
```

### `sort` - Sort tasks
Example of Usage:
`sort`
Expected Outcome:
The tasks will be sorted chronologically
```
Tasks have been successfully sorted!
1: [T] [X]  buy book
2: [T] [ ]  buy milk
3: [T] [ ]  buy milk
4: [T] [ ]  read book
5: [D] [ ]  return book (by: 06-06-2019 00:00)
6: [E] [ ]  project meeting (at: 05-12-2019 12:00)
7: [E] [ ] project meeting (at: 05-12-2019 12:00)
```

Example of Usage:
`sort reverse`
Expected Outcome:
The tasks will be sorted chronologically in the reversed order
```
Tasks have been successfully sorted!
1: [E] [ ] project meeting (at: 05-12-2019 12:00)
2: [E] [ ] project meeting (at: 05-12-2019 12:00)
3: [D] [ ] return book (by: 06-06-2019 00:00)
4: [T] [ ] read book
5: [T] [ ] buy milk
6: [T] [ ] buy milk
7: [T] [X] buy book
```

### `todo` - Add a new Todo
Example of Usage:
`todo revise for exams`
Expected Outcome:
A new Todo is created
```
This task has been successfully added!
[T] [ ] revise for exams
```

### `unmark` - Unmark a task to being undone
Example of Usage:
`deadline`
Expected Outcome:
The task will be unmark as being not done
```
This task has been successfully unmarked!
[T] [ ] buy book
```