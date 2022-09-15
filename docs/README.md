# User Guide

## Features 

### Track your todos, events and deadline!
Manage all your todos, events and deadlines in one place.
View their dates and done status at one glance.


### Sort
Sort your todos, events and deadlines by their dates.

## Usage

### `list` - lists the items in the task list 
Lists the items in the task list currently, along with their details 
like marked status and associated date & time if any

Example of usage:
`list`

Expected outcome:
```
1 [T][] Math tutorial
2 [E][X] CS2103T lecture (Sep 16 2022)
3 [D][] CS2103T IP (Sep 16 2022)
```

### `todo` - Adds a todo item
Adds a todo item which is marked undone and echos the description
with the number of tasks in task list

Example of usage:
`todo finish tutorial`

Expected outcome:
```
I've added: [T][] finish tutorial you have 4 tasks left
```

### `deadline` - Adds a deadline item
Adds a deadline item which is marked undone and echos the description
along with its date & time as well as the number of tasks in task list

Example of usage:
`deadline math tutorial /by 2022-09-15 1330`

Expected outcome:
```
I've added: [D][] math tutorial (by: Sep 15 2022 13:30) you have 5 tasks left
```

### `event` - Adds a deadline item
Adds an event item which is marked undone and echos the description
along with its date & time as well as the number of tasks in task list

Example of usage:
`event CS2100 Lab /at 2022-09-16 1400`

Expected outcome:
```
I've added: [E][] CS2100 Lab (at: Sep 16 2022 14:00) you have 6 tasks left 
```

### `mark` - marks the task item as done
Marks the task at the specified index as done

Example of usage:
`mark 3`

Expected outcome:
```
I have marked: [D][X] CS2103T IP
```

### `unmark` - unmarks the task item as undone
Unmarks the task at the specified index as undone

Example of usage:
`unmark 2`

Expected outcome:
```
I have unmarked: [E][] CS2103T lecture
```

### `delete` - deleted the task item
deleted the task at the specified index

Example of usage:
`delete 1`

Expected outcome:
```
I have deleted: [T][] Math tutorial
```

### `sort` - sorts the tasks in task list
sorts the tasks in the task list according to their associated dates,
if no dates are associated then they are automatically given the highest priority

Example of usage:
`sort`

Expected outcome:
```
1 [T][] finish tutorial
2 [D][] math tutorial (by: Sep 15 2022 13:30)
3 [E][] CS2103T lecture (at: Sep 16 2022)
4 [D][X] CS2103T IP (by: Sep 16 2022)
5 [E][] CS2100 Lab (at: Sep 16 2022 14:00)
```
