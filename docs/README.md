# User Guide for `Zuke`
`Zuke` is a task manager that helps you keep track of different types of tasks.
It provides the flexibility to use the CLI :computer: or the GUI :sparkles:.<br><br>
**Note: Release v0.2 will be be the GUI only!**

## CLI or GUI?
There is not much difference between the two, and you can use them interchangeably without
worrying about difference in functionalities. You might find the GUI more 
user-friendly to use though, since it looks like a chat bot's interface.

## Features 

### Types of tasks

* `todo`: a task with a description only
* `deadline`: a task with a description and deadline
* `event`: a task with a description and time of occurrence

### Create and delete tasks :heavy_plus_sign: :heavy_minus_sign:
Add each type of task using the `task`, `deadline` or `event` commands.<br>

Delete a task with the `delete` command.

### List tasks 
List your entire task list with the command `list`.

### Mark a task as completed :white_check_mark:
Mark a task as completed with the `mark` command. You can also unmark a task
using the `unmark` command.

### Tag a task :hash:
While adding tasks, you can specify a tag for the task (e.g. `todo homework #cs2103t`).

### Search for tasks :microscope:
Search for tasks that contains matching words in their descriptions with the `find` command.

## Usage

### `todo` - Create a todo task :heavy_plus_sign:

Creates a todo task and adds it to the task list.

#### Example of usage:
`todo <description> [<tag>]`

#### Required parameters:
* `<description>`: String
  * The description of the task to be added

#### Optional parameters:
* `<tag>`: String
    * The tag to be associated with this task

#### Expected outcome:
`Zuke`'s reply:
```
Got it. I've added this task:
    [T][<tag>][ ] <description>
```


### `deadline` - Create a deadline task :heavy_plus_sign:

Creates a task with a deadline and adds it to the task list.

#### Example of usage:
`deadline <description> /by <time> [<tag>]`

#### Required parameters:
* `<description>`: String
  * The description of the task to be added
* `<time>`: date and time in `dd/MM/yy HHmm` format
  * The deadline of the task

#### Optional parameters:
* `<tag>`: String
    * The tag to be associated with this task

#### Expected outcome:
`Zuke`'s reply:
```
Got it. I've added this task:
    [D][<tag>][ ] <description> (by: <dd MMM yyyy, HHmm)
```

### `event` - Create an event task :heavy_plus_sign:

Creates an event and adds it to the task list.

#### Example of usage:
`event <description> /at <time> [<tag>]`

#### Required parameters:
* `<description>`: String
    * The description of the event to be added
* `<time>`: date and time in `dd/MM/yy HHmm` format
    * The time of occurrence of the event

#### Optional parameters:
* `<tag>`: String
    * The tag to be associated with this event

#### Expected outcome:
`Zuke`'s reply:
```
Got it. I've added this task:
    [E][<tag>][ ] <description> (at: <dd MMM yyyy, HHmm)
```

### `delete` - Delete a task :heavy_minus_sign:

Delete a task from the task list.

#### Example of usage:
`delete <task-id>`

#### Required parameters:
* `<task-id>`: Integer
    * The ID of the task, obtained from the `list` command

#### Expected outcome:
Example of `Zuke`'s reply:
```
Noted. I've removed this task:
    [T] [ ] Cut hair
```

### `list` - List all tasks in the task list

Displays all the tasks in the task list, in the order they were added.

#### Example of usage:
`list`

#### Expected outcome:
An example of `Zuke`'s reply:
```
Here are the tasks in your list:
1. [T] [ ] Cut hair
2. [E] [ ] RVRC Symposum (at: 19 Sep 2022, 0900)
3. [D] [important] [ ] CS2103T Individual Project Submission (by: 16 Sep 2022, 2359)
```


### `mark` - Mark a task as completed :white_check_mark:

Marks a task in the task list as completed.

#### Example of usage:
`mark <task-id>`

#### Required parameters:
* `<task-id>`: Integer
    * The ID of the task, obtained from the `list` command

#### Expected outcome:
Example of `Zuke`'s reply:
```
Nice! I've marked this task as done:
  [T] [X] Cut hair
```

### `unmark` - Mark a task as uncompleted

Unmarks a completed task in the task list.

#### Example of usage:
`unmark <task-id>`

#### Required parameters:
* `<task-id>`: Integer
    * The ID of the task, obtained from the `list` command

#### Expected outcome:
Example of `Zuke`'s reply:
```
OK, I've marked this task as not done yet:
  [T] [ ] Cut hair
```

### `find` - Search for a task :microscope:

Search for tasks in the task list that contains the given keyword in its description. 

#### Example of usage:
`find <keyword>`

#### Required parameters:
* `<keyword>`: String
  * The string that the resultant tasks will contain

#### Expected outcome:
Example of `Zuke`'s reply:
```
Here are the matching tasks in your list:
1. [T] [ ] Cut hair
```

### `bye` - Exits program :wave:

Terminates the program, saving the task list for future use.

#### Example of usage:
`bye`

#### Expected outcome:
Example of `Zuke`'s reply (on CLI only):
```
Bye. Hope to see you again soon!
```
Program exits.