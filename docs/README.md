# User Guide

## Meet chatNUS!

chatNUS is a messaging app built to store and manage your todo lists. Interact with the chatNUS bot 
with the following commands to create, delete, update and display your tasks!

## Features 

### list

A Standalone command with no trailing arguments. Lists all the tasks currently on your list, task type
and the task status. For example:

```
1. [T][ ] Do My Laundry
2. [D][X] Finish my essay (By: 16 SEPTEMBER 2022 3 PM)
3. [E][ ] Some Event (At: 9 PM)
```

The above indicates:

1. Type: Todo, Status: Not Completed, Name: Do My Laundry
2. Type: Deadline, Status: Completed, Name: Finish my essay (By: 16 SEPTEMBER 2022 3 PM)
3. Type: Event, Status: Not Completed, Name Some Event (At: 9 PM)

Thus, the general format:

SN. [Type][Status] Task Name

### Adding a Todo
To add a todo (Task without a time limitation), type the following command into the input bar:

```
todo Do My Laundry
```

### Adding a Deadline
Deadlines have a time limit, which are indicated using the /by command. 
To add a deadline, use the following command:

```
deadline Finish My Essay /by 2022-03-04 1800
```

Both, the date and time have to be specified. The format for the same is YYYY-MM-DD HHmm

### Adding an event
Events have a time limit too, but they are indicated using the /at command. 
To add an event, use the following command:

```
event My Event /at 9 PM
```

## Edit Task Status

### Mark a Task as Done
To mark a task as done, you need to know the serial number of the task. 
Use the following command:

```
mark 2
```

### Unmark a Previously marked Task
To unmark a task you have previously marked, you need to know the serial number again!
Use the following command:

```
unmark 2
```

### End Session

To end the session, simply use the bye command! chatNUS will store your list and
load it the next time you run chatNUS!

```
bye
```

