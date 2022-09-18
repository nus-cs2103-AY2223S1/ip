# User Guide
DuckWifaKnife, a friendly duck that helps you manage your tasks

### Feature - `todo`

Adds a Todo type task to your tasklist.

### Feature - `deadline`

Adds a Deadline type task to your tasklist.

### Feature - `event`

Adds an Event type task to your tasklist.

### Feature - `notes`

Adds a note to your noteslist.

### Feature - `list`

Lists out all the tasks and notes in your tasklist and noteslist.

### Feature - `mark`

Marks the specific task in the index given as done in the tasklist.

### Feature - `unmark`

Marks the specific task in the index given as not done in the tasklist.

### Feature - `find`

Find any matching task in the tasklist with the given keyword.

### Feature - `delete`

Deletes the specific task in the index given.

### Feature - `bye`

Closes the program.

## Usage

### `todo` - The todo feature will be activated.

A Todo type task will be created and added to the tasklist.

Example of usage: 

`todo finalise ip`

Expected outcome:

"finalise ip" will be added to the tasklist.

```
Got it. I've added this todo task:
 [T][ ] finalise ip
Now you have 1 tasks in the list.
```

### `deadline` - The dealine feature will be activated.

A Deadline type task will be created and added to the tasklist.

Example of usage:

`deadline commit ip /by 20/09/2022 2359`

Expected outcome:

"commit ip (by: Sept 20 2022 23:59:00)" will be added to the tasklist.

```
Got it. I've added this deadline task:
 [D][ ] commit ip (by: Sep 20 2022 23:59:00)
Now you have 2 tasks in the list.
```

### `event` - The event feature will be activated.

A Event type task will be created and added to the tasklist.

Example of usage:

`event done with ip celebration /at 21/09/2022 0000`

Expected outcome:

"done with ip celebration (at: Sep 21 2022 00:00:00)" will be added to the tasklist.

```
Got it. I've added this event task:
 [E][ ] done with ip celebration (at: Sep 21 2022 00:00:00)
Now you have 3 tasks in the list.
```

### `notes` - The notes feature will be activated.

A Note will be created and added to the noteslist.

Example of usage:

`notes i'm bad at coding`

Expected outcome:

"i'm bad at coding" will be added to the noteslist.

```
Got it. I've added this note:
 [N][ ] i'm bad at coding
Now you have 1 notes in the list.
```

### `list` - The list feature will be activated.

Lists all the tasks and notes in the tasklist and noteslist.

Example of usage:

`list`

Expected outcome:

The tasklist and noteslist will be displayed.

```
Here are the tasks in your list:
1.[T][ ] finalise ip
2.[D][ ] commit ip (by: Sep 20 2022 23:59:00)
3.[E][ ] done with ip celebration (at: Sep 21 2022 00:00:00)

Here are the notes in your list:
1.[N][ ] i'm bad at coding
```

### `mark` - The mark feature will be activated.

The task at the index given will be marked as done.

Example of usage:

`mark 2`

Expected outcome:

"commit ip" will be marked as done.

```
Nice! I've marked this task as done:
 [D][X] commit ip (by: Sep 20 2022 23:59:00)
```

### `unmark` - The mark feature will be activated.

The task at the index given will be marked as done.

Example of usage:

`unmark 2`

Expected outcome:

"commit ip" will be marked as not done.

```
OK, I've marked this task as not done yet:
 [D][ ] commit ip (by: Sep 20 2022 23:59:00)
```

### `find` - The find feature will be activated.

Finds any tasks or notes with the given keyword.

Example of usage:

`find ip`

Expected outcome:

Any task with ip will be returned.

```
Here are the matching tasks in your list:
1.[T][ ] finalise ip
2.[D][ ] commit ip (by: Sep 20 2022 23:59:00)
3.[E][ ] done with ip celebration (at: Sep 21 2022 00:00:00)
```

### `delete` - The delete feature will be activated.

The task at the index given will deleted.

Example of usage:

`delete 2`

Expected outcome:

"commit ip" will be deleted.

```
Noted. I've removed this note:
 [D][ ] commit ip (by: Sep 20 2022 23:59:00)
Now you have 2 notes in the list.
```

### `bye` - The bye feature will be activated.

Closes the program.

Example of usage:

`bye`

Expected outcome:

The program will close.

```
Bye. Hope to see you again soon!
```