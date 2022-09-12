# Duke: Your Friendly Task Manager

## Features 

### Todo Task

A task with a description and a status icon which indicates whether it has been completed

### Deadline Task

A task with a description, deadline time and a status icon which indicates 
whether it has been completed

### Event Task
A task with a description, event time and a status icon which indicates
whether it has been completed

## Usage

### `todo` - adds a todo task to the list of tasks

Example of usage: 

`todo buy bread`

Expected outcome: 

```
[T][ ] buy bread
```

### `deadline` - adds a deadline task to the list of tasks

Example of usage:

`deadline math homework /by 2022-09-25 1800`

Expected outcome:

```
[D][ ] math homework (by: 25 Sep 2022, 6:00PM)
```

### `event` - adds an event task to the list of tasks

Example of usage:

`event meeting /at 2022-09-25 1800`

Expected outcome:

```
[E][ ] meeting (by: 25 Sep 2022, 6:00PM)
```

### `mark/unmark` - mark/unmark a task in the list of tasks as completed 

Example of usage:

`mark 1` : Marks the first task in the list as completed

Expected outcome:

```
[T][X] buy bread
```

### `list` - shows the list of tasks

Example of usage:

`list`

Expected outcome:

```
[T][X] buy bread
[D][ ] math homework (by: 25 Sep 2022, 6:00PM)
[E][ ] meeting (by: 25 Sep 2022, 6:00PM)
```

### `find` - search for tasks using a keyword

Example of usage:

`find homework`: Search for tasks containing "homework"

Expected outcome:

```
[D][ ] math homework (by: 25 Sep 2022, 6:00PM)
```

### `editd` - edit a task's description

Example of usage:

`editd 3 /d project meeting`: Change description of task 3 to "project meeting"

Expected outcome:

```
[E][ ] project meeting (by: 25 Sep 2022, 6:00PM)
```

### `delete` - delete a task from the list of tasks

Example of usage:

`delete 3`: delete task 3 from the list

Expected outcome(list now contains):

```
[T][X] buy bread
[D][ ] math homework (by: 25 Sep 2022, 6:00PM)
```