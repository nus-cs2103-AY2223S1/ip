# Ah Duke User Guide

Welcome to Ah Duke, the Singaporean Task Managing Bot!
To start download the jar file and run the AhDukeApp.

## Adding tasks:

Let's say I would like to add task of a certain `taskName`. 
### There are **3** types of tasks that can be added:

### Todo

Adds a task of type todo to the tasklist.

Example of usage:

`todo Homework`

Expected outcome:

Added a todo `Homework`:

```
Ok I add your task already:
[T][ ] Homework
Now 1 tasks already
```
### Deadline

Adds a task of type deadline to the tasklist, with an additional due **by** date and time in the format `dd/mm/yyyy HHmm`.

Example of usage:

`deadline Homework /by 07/11/2022 1500`

Expected outcome:

Added a deadline `Homework` due by 07/11/2022 1500hrs:

```
Ok I add your task already:
[D][ ] Homework (by: 07 11 2022 15:00)
Now 2 tasks already
```

### Event

Adds a task of type event to the tasklist, with an additional due **at** date and time in the format `dd/mm/yyyy HHmm`.

Example of usage:

`event Class /at 07/11/2022 1500`

Expected outcome:

Added a event `Class` at 07/11/2022 1500hrs:

```
Ok I add your task already:
[E][ ] Class (at: 07 11 2022 15:00)
Now 3 tasks already
```

### Marking Tasks

### `mark` - Mark a task at an index as completed.

Example of usage:

`mark 1`

Expected outcome:

Marks as completed:

```
Ok ticked this already
[T][X] Homework
```

### `unmark` - Mark a task at an index as uncompleted.

Example of usage:

`unmark 1`

Expected outcome:

Marks as uncompleted:

```
Ok not done yet ah
[T][ ] Homework
```

## Other Commands

### `list` - List all tasks

Example output:
```
Here are your tasks la:
1.[T][ ] Homework
2.[D][ ] Homework (by: 07 11 2022 15:00)
3.[E][ ] Class (at: 07 11 2022 15:00)
```

### `delete` - Delete a task at an index.

Example of usage:

`delete 2`

Expected outcome:

Deleted a deadline `Homework` at 07/11/2022 1500hrs:

```
I remove this ah:
[D][ ] Homework (by: 07 11 2022 15:00)
Now 2 tasks only
```

### `find` - Finds a task with matching name.

Example of usage to find my homework task (Case sensitive):

`find Home`

Expected outcome:

Found a task `Homework`:

```
Matching one:
1:[T][ ] Homework
```

### `bye` - Closes the task bot.

To view these commands anytime within the app use `help`.
Happy task managing with Ah Duke!

Made by `snigloo` :)