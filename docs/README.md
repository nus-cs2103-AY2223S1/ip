# JARVIS User Guide

<b> JARVIS </b>is a task manager chatbot that helps you keep track of your tasks through a command line style interface. Get stuff done the way Iron Man does!

## Features

### Three task types
- `todo`: A task with a description
- `event`: An event with a description of what and when it is
- `deadline`: A deadline with a description and due date

### Search
Search for tasks using the `find` command

### Mark and unmark
Mark tasks as done or undone using the `mark` and `unmark` commands

## Usage
Parameters for commands (such as descriptions and due dates) are indicated using the `\` symbol e.g `\d`. In effect, you can indicate parameters in any order you like!

When there are issues such as missing parameters, **JARVIS** will inform you appropriately. An example of such a message is as follows (when trying to add an event with no information provided):
```
The following issues occured with your command:
1. This command needs a non-empty description.
2. This command needs an at date
```
Upon exiting, **JARVIS** saves tasks to a folder called `data` which is created wherever you ran the JAR file. The next time you run the JAR file, your tasks are loaded from this folder. **Do not delete this folder or the files inside unless you want to delete all your tasks!**

##  `list` -  Lists all tasks added

#### Example:

`list`

####  Explanation:
An appropriate message is displayed upon sending `list` to the bot - either a list of tasks created or an indication that no tasks have been added, e.g:
```
1. [T] [] Study for midterms
2. [D] [X] Submit iP (by: Sep 19 2022 2359)
```
Each task is prefaced by one letter indicating its type: `T` for todo, `D` for deadline and `E` for event.

Next, there is a `[X]` for tasks that have been marked as done, followed by the task description.

The numbering of the tasks from `list` is used for commands that need a `/id` parameter to specify a task.

##  `todo` -  Adds a todo task

#### Example:

`todo /d Get groceries`

#### Expected outcome:
```
Sure! I’ve added this task:
[T] [] Get groceries
You now have 1 task to do.
```
The number shown may vary depending on how many tasks you already created.

**Parameters:**

`/d` - The description of the task


##  `event` -  Adds an event task

#### Example:

`event /d Calculus midterm /at next Monday`

#### Expected outcome:
```
Sure! I’ve added this task:
[E] [] Calculus midterm (at: next Monday)
You now have 1 task to do.
```
The number shown may vary depending on how many tasks you already created.


**Parameters:**

`/d` - The description of the event
`/at` - Description of when the event is


##  `deadline` -  Adds a deadline task

#### Example:

`deadline /d Submit lab /by 26-08-2022 2359`

#### Expected outcome:
```
Sure! I’ve added this task:
[D] [] Submit lab (by: Aug 26 2022 2359)
You now have 1 task to do.
```
The number shown may vary depending on how many tasks you already created.


**Parameters:**

`/d` - The description of the deadline
`/by` - The date and time for the deadline formatted as dd-MM-yyyy hhmm where hhmm is in 24 hour time

##  `mark` - Marks a specified task as done

#### Example:

`mark /id 1`

#### Expected outcome:
```
Nice! I’ve marked this task as done:
<description of task>
```

**Parameters:**

`/id` - The id of the task as it appears from the `list` command

##  `unmark` - Unmarks the specified task to not done

#### Example:

`unmark /id 1`

#### Expected outcome:
```
Alright, I’ve marked this task as not done yet:
<description of task>
```

**Parameters:**

`/id` - The id of the task as it appears from the `list` command

##  `delete` - Deletes the specified task

#### Example:

`delete /id 1`

#### Expected outcome:
```
Noted, I’ve removed this task:
<description of task>
You now have 0 tasks to do
```
The number shown may vary depending on how many tasks you already created.

**Parameters:**

`/id` - The id of the task as it appears from the `list` command

##  `find` - Finds tasks that contain the specified text in their descriptions

#### Example:

`find /s book`

#### Expected outcome:
If there are tasks created matching your query, a similar format to `list` is shown for the matching tasks. If there are no matching tasks, the bot informs you like this:
```
No matching tasks found for query: 'book'
```

**Parameters:**

`/s` - The query to filter tasks based on

##  `help` - Provides help

#### Example:

`help` - Simply sending help lists all the commands that the bot supports
```
>> help
todo,help,find,list,deadline,event,delete,mark,unmark,bye
```
`help <command name>` - Shows help for the specified command, e.g
```
>> help todo
Adds a todo task
Usage: todo /d Get groceries
```

**Parameters:**
(Optional)
`<command name>` - The command name you want help for

##  `bye` - Exits the chatbot

#### Example:
`bye`