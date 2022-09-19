# User Guide for Piggy

Piggy is a chat bot that can manage a list of tasks for you.

![Piggy Screenshot](/docs/Ui.png)

## Features

- Add tasks
- List all tasks
- Delete task
- Search tasks
- Mark task as done or not done
- View schedule for a day

## Tasks

There are 3 types of tasks: Todo, Deadline and Event.

### Todo

Todos have no deadline and only consist of a description.

### Deadline

Deadlines have a deadline datetime attached to them.

### Event

Events have an at datetime attached to them.

## Usage

### Add Todo

```
todo [description]
```

Add a todo task.

#### Example

```
todo Clean my room
```

### Add Deadline

```
deadline [description] /by [yyyy-MM-dd HH:mm]
```

Add a deadline task.

#### Example

```
deadline CS2103T Individual Project /by 2022-09-19 23:59
```

### Add Event

```
event [description] /at [yyyy-MM-dd HH:mm]
```

Add an event task.

#### Example

```
event Housewarming party /at 2022-09-19 18:00
```

### List all Tasks

```
list
```

Shows a list of all tasks.

### Delete Task

```
delete [index]
```

Delete the task with the corresponding `index`. The `index` can be found from the list of tasks.

### Search Tasks

```
find [keyword]
```

Searches all the task descriptions for one that contains `keyword`.

#### Example

```
find homework
```

### Mark Task as Done

```
mark [index]
```

Marks the task with the specified `index` as done.

### Mark Task as Not Done

```
unmark [index]
```

Marks the task with the specified `index` as not done.

### View Schedule

```
schedule [yyyy-MM-dd]
```

Views the schedule for the specified date.
