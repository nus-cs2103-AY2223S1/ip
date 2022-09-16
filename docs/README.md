# User Guide

## Meowmeow (=^-ω-^=) 
Meowmeow is a bot that can help you to keep track of your tasks. 


## Commands

### `hi` - Say hi to meowmeow
Meowmeow will introduce itself.

### `list` - Get a list of all tasks
Meowmeow will list all tasks and their completion status.

### `find` - Search for tasks by keyword
Meowmeow will list all tasks that contain the keyword.

### `todo` - Add a Todo task
Meowmeow will add a Todo task to the list.

Example of usage:

`todo Buy fish for Meowmeow`

Expected outcome:

```
(=^-w-^=) [T] [ ] Buy fish for Meowmeow has been added to your task list!

You now have 1 tasks >w<
```


### `deadline` - Add a Deadline task
Meowmeow will add a Deadline task to the list.

Example of usage:

`deadline Buy fish for Meowmeow /by 2022-10-22T23:59:59`

Expected outcome:

```
(=^-w-^=) [D] [ ] Feed Meowmeow the fish (by: 11:59 PM on 22/10/2022) has been added to your task list!

You now have 2 tasks >w<
```

### `event` - Add an Event task
Meowmeow will add an Event task to the list.

Example of usage:

`event Meowmeow eats fish /at 5pm today`

Expected outcome:

```
(=^-w-^=) [E] [ ] Meowmeow eats fish (at: 5pm today) has been added to your task list!

You now have 3 tasks >w<
```

### `delete` - Delete a task
Meowmeow will delete the task with the specified number.

Example of usage:

`delete 3`

Expected outcome:

```
(=^-w-^=) [E] [ ] Meowmeow eats fish (at: 5pm today) has been deleted from your task list!

You now have 2 tasks >w<
```

### `undo` - Undo the last command
Meowmeow will undo your last command.

Example of usage:

`undo` after `delete 3`

Expected outcome:

```
Meowmeow has restored the task you deleted! (=^>w<^=)
```

### `mark` - Mark a task as done
Meowmeow will mark the task with the specified number as done.

Example of usage:

`mark 1`

Expected outcome:

```
Good job(=OwO=) You finished this task! 
[T] [X] Buy fish for Meowmeow
```

### `unmark` - Mark a finished task as undone
Meowmeow will mark the task with the specified number as undone.

Example of usage:

`unmark 1`

Expected outcome:

```
uwu this task has been marked as not done...
[T] [ ] Buy fish for Meowmeow
```

### `bye` - Quit the program
Meowmeow will close the program.

