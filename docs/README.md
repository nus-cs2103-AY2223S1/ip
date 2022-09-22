# Duke User Guide
Duke is a simple application that helps you keep track of your tasks.

## Features 

### `list`

Lists all recorded tasks.
>##### Example Usage
>```
>list
>```

### `todo`

Adds a generic task.
>##### Example Usage
>```
>todo have a fruit
>```

### `deadline`

Adds a task with a deadline.

Use the keyword `/by`.

Specify the date in the format yyyy-mm-dd.
>##### Example Usage
>```
>deadline submit homework /by 2022-09-22
>```

### `event`

Adds a task occurring at a later date.

Use the keyword `/at`.

Specify the date in the format yyyy-mm-dd.
>##### Example Usage
>```
>event breakfast /at 2022-09-23
>```

### `delete`

Deletes a recorded task.

Specify the index of the task to be deleted.
>##### Example Usage
>```
>delete 1
>```

### `mark`

Marks a task as done.

Specify the index of the task to be marked.
>##### Example Usage
>```
>mark 1
>```

### `unmark`

Marks a task as not done.

Specify the index of the task to be unmarked.
>##### Example Usage
>```
>unmark 1
>```

### `find`

Finds all the tasks containing a specific term.
>##### Example Usage
>```
>find breakfast
>```

### `archive`

Archives all tasks into a file.
>##### Example Usage
>```
>archive
>```
