# Duke User Guide

![Ui](Ui.png)

## Setup
1. Ensure you have Java 11 or above installed.
2. Download the file from here. (To add the link)
3. Copy the jar file to an empty folder in your computer
4. Double click it to run or  run from the terminal using `java -jar {filename}.jar`

## Features 

### Feature - Task Creation

Command words `todo, deadline, event`
allows you to create new events to be added into your agenda.
Command wor

### Feature - Task Management

Duke has the ability to `mark`, `unmark` and `delete` task from your task list. This allows
you to clear tasks that has been completed.

Description of the feature.

### Feature - Friendlier Syntax

By popular demand, we have created friendlier syntax which allows you to type shorter
commands to produce the same intended result. `todo -> t`, `deadline -> d`, 
`event -> e`, `delete -> del/rm`, `list -> ls`

## Usage

### `ToDo` - Creates a ToDo task and adds it to task list.

Command Format: `todo <description>`

Expected Outcome:

```
Got it. I've added this task:
[T][] <description>
```

### `Event` - Creates an Event task and adds it to task list.

Command Format: `event <description> /at YYYY-MM-DD`

Expected Outcome:

```
Got it. I've added this task:
[E][] <description> (at: dd MMM yyyy)
```

### `Deadline` - Creates a Deadline task and adds it to task list.

Command Format: `deadline <description> /by YYYY-MM-DD`

Expected Outcome:

```
Got it. I've added this task:
[D][] <description> (by: dd MMM yyyy)
```

### `List` - Displays all the tasks.

Command Format: `list` or `ls`

Expected Outcome:

```
<Displays tasks in a numbered list>
1. task 1
2. task 2
...
```

### `Find` - Find the task in the task list with matching keyword.

Command Format: `find <keyword>`

Expected Outcome:

```
Here are the matching tasks in your list!
1. task 1
2. task 2
...
```
### `Mark` - Adds indication that a task is done.

Command Format: `mark <task_index>`

Expected Outcome:

```
Nice! I've marked this task as done:
<Displays task that is marked>
```

### `Unmark` - Adds indication that a task is not done.

Command Format: `unmark <task_index>`

Expected Outcome:

```
OK, I've marked this task as not done yet:
<Displays task that is unmarked>
```

### `Delete` - Deletes a task.

Command Format: `delete <task_number>` or `del <task_number>` 
or `rm <task_number>`

Expected Outcome:

```
Noted. I've removed this task:
<Displays task that is removed>
Now you have <number> tasks in the list.
```

### `Bye` - Exits the program.

Command Format: `bye`