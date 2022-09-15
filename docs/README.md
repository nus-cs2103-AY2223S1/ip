# User Guide

## Features 
- List all tasks (`list`)
- Add tasks (`todo`, `deadline`, `event`)
- Delete tasks (`delete`)
- Mark tasks (`mark`)
- Unmark tasks (`unmark`)
- Save tasks and exit (`bye`)

## Usage
### `list` - List all tasks
Example of usage: 
`list`
Expected outcome:
These are example tasks that I saved
```
1: [T][X] Build a family in Oceana
2: [D][] Retrieve a microfilm from a penguin | 2022-06-22
```

### `todo (Task description)` - add a Todo task
Example of usage: 
`todo save the world`
Expected outcome:
```
Task added: [T][] save the world
```

### `deadline (Task description) /by (YYYY-MM-DD)` - add a deadline task
Example of usage: 
`deadline submit ip /by 2022-09-16`
Expected outcome:
```
Task added: [D][] submit ip (by: 2022-09-16)
```

### `event (Task description) /at (YYYY-MM-DD)` - add an Event task
Example of usage: 
`event 2022-09-16`
Expected outcome:
```
Task added: [D][] submit ip (by: 2022-09-16)
```

### `find (case-insensitive substring)` - finds all tasks with the given substring
Example of usage: 
`find ip`
Expected outcome:
```
[D][] submit ip (by: 2022-09-16)
```

### `delete (index)` - delete a task by its 1-based index from the top
Example of usage: 
`delete 1`
Expected outcome:
```
Task deleted: [D][] submit ip (by: 2022-09-16)
```

### `mark (index)` - mark the task by its 1-based index from the top
Example of usage: 
`mark 1`
Expected outcome:
```
Task marked: [D][X] submit ip (by: 2022-09-16)
```

### `unmark (index)` - unmark the task by its 1-based index from the top
Example of usage: 
`unmark 1`
Expected outcome:
```
Task unmarked: [D][X] submit ip (by: 2022-09-16)
```

### `bye` - saves changes and exits the program
Example of usage: 
`bye`
Expected outcome:
```
Changes saved, exiting in 5s
```
