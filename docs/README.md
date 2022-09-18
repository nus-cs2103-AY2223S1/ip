# User Guide for Peter's Duke

## Features for tasks
- Adding Tasks
- Deleting Tasks
- Marking/UnMarking Tasks
- Listing Tasks
- Finding Tasks
- Saving Tasks

## Features for clients
- Adding Clients
- Deleting Clients
- Listing Clients
- Saving Clients

## Other Features
- Exiting program

## Features for tasks

### Adding Tasks `todo` `deadline` `event`:

Adds task to task list.

Format for todo: `todo TASKNAME` <br />
Format for deadline: `deadline TASKNAME /by dd/MM/yyyy HHmm` <br />
Format for event: `event TASKNAME /at dd/MM/yyyy HHmm`

Examples:
- `todo read book`
- `deadline go school /by 11/11/2022 1000`
- `event club /at 11/11/2022 2300`

### Deleting Tasks `delete`:

Deletes task from task list.

Format: `delete INDEX`

Example: `delete 1`

### Marking/UnMarking Tasks `mark` `unmark`:

Marks/UnMark task from task list as done/undone.

Format for mark: `mark INDEX` <br />
Format for unmark: `unmark INDEX`

Examples: 
- `mark 1`
- `unmark 2`

### Listing Tasks `list task`:

Lists tasks in task list.

### Finding Tasks `find`:

Find tasks from task list that contain keyword and lists them out.

Format: `find KEYWORD`

Example: `find READ BOOK`

### Saving Tasks:

Tasks are automatically saved in "Tasks.txt"

## Features for clients

### Adding clients `client`:

Adds client to client list.

Format: `client NAME PHONENUMBER ADDRESS`

Example: `client tom 92245669 Woodleigh Avenue`

### Deleting clients `delete client`:

Deletes client from client list.

Format: `delete client PHONENUMBER`

Example: `delete client 92245669`

### Listing clients `list client`:

Lists clients from client list.

### Saving Tasks:

Tasks are automatically saved in "Clients.txt"

## Other Features:

### Exiting program `bye`:

Exits the program.
