# User Guide

## Features 

### Add and delete tasks

Types of tasks supported include:
- Todos
- Deadlines
- Events

Tasks can be added with their respective keywords, and deleted by their index in the tasklist.

### Mark and unmark

Tasks can be marked as done once they are completed, or unmarked if they have not been completed yet.

### Tag tasks

Tasks can be tagged by tags to group them under a common category.

### Search for tasks

Search for tasks in 3 ways:
1. The date the task occurs on
2. The task's name
3. The tag the task is tagged with

## Usage

### `todo` - Adds a ToDo task

**Example of usage:** 

`todo NAME`

**Expected outcome:** A message to confirm that the ToDo task was added successfully.

```
added:
  [T][ ] NAME
now you have x tasks in the list. type list to view them.
```

### `deadline` - Adds a Deadline task

**Example of usage:**

`deadline NAME /by DD/MM/YYYY (HHMM)`

**Expected outcome:** A message to confirm that the Deadline task was added successfully.

```
added:
  [D][ ] NAME (by: DATE)
now you have x tasks in the list. type list to view them.
```

### `event` - Adds an Event task

**Example of usage:**

`event NAME /PREPOSITION DD/MM/YYYY HHMM - (DD/MM/YYYY) HHMM`

**Expected outcome:** A message to confirm that the Deadline task was added successfully.

```
added:
  [T][ ] NAME (PREPOSITION: START DATE - END DATE)
now you have x tasks in the list. type list to view them.
```

### `list` - Lists out all the tasks

Tasks are arranged in the order they were added.

**Example of usage:**

`list`

**Expected outcome:** A list of all the tasks added.

```
these are the tasks in your list:
1. ...
```

### `mark` - Marks a task as done

**Example of usage:**

`mark INDEX`

**Expected outcome:** A message to confirm that the task was marked as done successfully.

```
good job! this task has been marked as done:
  [T][X] DESCRIPTION
```

### `unmark` - Marks a task as not done yet

**Example of usage:**

`unmark INDEX`

**Expected outcome:** A message to confirm that the task was unmarked successfully.

```
ok, i've marked this task as not done yet:
  [D][] DESCRIPTION (by: DATE)
```

### `delete` - Deletes a task

Deletes the specified task from the task list.

**Example of usage:**

`delete INDEX`

**Expected outcome:** A message to confirm that the task was deleted successfully.

```
okay! i have deleted the following task from your list:
  [T][X] DESCRIPTION
now you have x tasks in the list. type list to view them.
```

### `search` - Searches for tasks by date

Searches for all the tasks that occurs on the specified date. This applies only to deadlines and events.

**Example of usage:**

`search DD/MM/YYYY`

**Expected outcome:** A list of tasks matching the date, or a message that no tasks occur on the specified date.

```
these are the tasks occurring on YYYY-MM-YY:
  1. ...
```

### `find` - Searches for tasks by keywords

Searches for all the tasks that contains the specified keywords.

**Example of usage:**

`find KEYWORDS`

**Expected outcome:** A list of tasks containing the keywords, or a message that no tasks match the search term.

```
these are the matching tasks in your list:
  1. ...
```

### `tag` - Tags a task

**Example of usage:**

`tag INDEX #TAG`

**Expected outcome:** A message to confirm that the task was tagged with the specified tag successfully.

```
Okay! The task has been tagged as'#TAG'.
```

### `untag` - Untags a task

**Example of usage:**

`untag INDEX #TAG`

**Expected outcome:** A message to confirm that the tag was removed from the task successfully.

```
okay! the task is no longer tagged as '#TAG'.
```

### `alltags` - List of all the tags added

**Example of usage:**

`alltags`

**Expected outcome:** A list of all the tags added, or a message that no tags have been added yet.

```
these are the tags you have added:
  ...
```

### `tasktags` - List out a task's tags

**Example of usage:**

`tasktags INDEX`

**Expected outcome:** A list of tags under the task, or a message that the task has not been tagged with any tags.

```
here's a list of the tags you have added so far to this task:
  ...
```

### `taggedby` - Searches for tasks by the tag

**Example of usage:**

`tagged by #TAG`

**Expected outcome:** A list of tasks tagged by the specified tag, or a message that there are no tasks under the tag.

```
these are the tasks tagged under #TAG:
  1. ...
```

### `bye` - Saves the task list and exits Duke.

The tasklist, along with all the tags used, will be saved to `/data/list.txt` if possible.

**Example of usage:**

`bye`

**Expected outcome:** Duke is exited.

