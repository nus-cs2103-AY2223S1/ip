# User Guide
Sheep is a __Command Line Interface (CLI) Java Desktop Application__, which keeps track of all the tasks. Currently, Sheep supports three types of task:

- _Todo_ task: A task you want to do in the future
- _Deadline_ task: A task you want to finish before a specific day
- _Event_ task: A task you want to do at a specific time
## Features 

### Add new tasks

You can ask Sheep to add a new task to the list of tasks.

### Tag tasks
You can ask Sheep to tag a task with a specific tag.

### Delete tasks

You can ask Sheep to delete a specific from the list.

### View all tasks

You can ask Sheep to show you the entire list of tasks.

### Mark tasks as done

Once you have finish a task, you can ask Sheep to mark a task as done.

### Unmark tasks

Mistakenly mark a task as done. No worries, you can also add Sheep to unmark that task.

### Find tasks with keywords

Cannot look for the tasks you want? You can ask Sheep to find it for you.

### Local save
No need to ask. Sheep you automatically remember all the tasks when you exit and display it to you the next time you open Sheep.

## Usage

### `todo` - Add a new todo

> Adds a new todo task to the list.

Example of usage:

`todo use Sheep`

Expected outcome:
A todo task is added to the list.

Description of outcome:
```
Yay! I added [T][] use Sheep to the list.
You have 1 task in the list.
```

### `deadline` - Add a new deadline

> Adds a new deadline task to the list.

Example of usage:

`deadline add an event in Sheep /by 2022-09-19`

Expected outcome:
A todo task is added to the list.

Description of outcome:
```
Yay! I added [D][] add an event in Sheep (by: Sep 19 2022) to the list.
You have 2 tasks in the list.
```

### `event` - Add a new event

> Adds a new event task to the list.

Example of usage:

`event lecture Software Engineer /at 2022-09-16 16:00`

Expected outcome:
A event task is added to the list.

Description of outcome:
```
Yay! I added [E][] lecture Software Engineer (at: Sep 16 2022 16:00) to the list.
You have 3 tasks in the list.
```

### `/tag` - Tag a task when adding

> Add a tag for the task when adding.

Example of usage:

`todo Follow this user guide /tag Sheep`

Expected outcome:
A tagged todo task is added to the list.

Description of outcome:
```
Yay! I added [T][] Follow this user guide #Sheep
You have 4 tasks in the list.
```


### `mark` - Mark a task as completed

> Marks a task at specific position in the list displayed by Sheep as done.

Example of usage:

`mark 2`

Expected outcome:
The task at the index is marked as done.

Description of outcome:
```
Yay! I have marked this task as done
[D][X] add an event in Sheep (by: Sep 19 2022)
```

### `unmark` - Mark a task as not yet completed

> Unmarks a task at specific position in the list displayed by Sheep.

Example of usage:

`mark 2`

Expected outcome:
The task at the index is marked as done.

Description of outcome:
```
Baa! I have unmarked this task 
[D][] add an event in Sheep (by: Sep 19 2022)
```

### `list` - Display all the tasks

> Shows the whole list of tasks.

Example of usage:

`list`

Expected outcome:
The list of all added tasks.

Description of outcome:
```
This is your task list:
1. [T][] use Sheep
2. [D][] add an event in Sheep (by: Sep 19 2022)
3. [E][] lecture Software Engineer (at: Sep 16 2022 16:00)
4. [T][] Follow this user guide #Sheep
```

### `find` - Find all the tasks containing keywords

> Displays the list of tasks with the descriptions or tags that contains given keywords.

Example of usage:

`find Sheep`

Description of outcome:
```
1. [T][] use Sheep
2. [D][] add an event in Sheep (by: Sep 19 2022)
```


### `delete` - Remove a task from the task list

> Deletes a task at specific position in the list displayed by Sheep.

Example of usage:

`delete 3`

Description of outcome:
```
Yay! I have removed this task from your list
[E][] lecture Software Engineer (at: Sep 16 2022 16:00)
```

### `bye` - Exit the program 

> Say goodbye to Sheep and exit the program.

Example of usage:

`bye`

Description of outcome:
```
Baa! :( See you later.
```