# Duke User Guide

Here are the features of the Duke, follow the given command formats to use them!

## List all tasks

### Format: `list` 

Lists all the task the user has currently, or informs the user if they have no tasks.

**Example of usage:**

`list`

**Example output:**

```
Here are the duties in your list:
1.[T][ ] return book
2.[D][ ] homework (by: Friday 2359)
```

## Add a Todo task

### Format: `todo <description>` 

Adds a Todo task to the task list with the given description.

**Example of usage:** 

`todo return book`

**Example output:**

```
Understood, my Lord. I have added this duty:
[T][ ] return book
```

## Add a Deadline task

### Format: `deadline <description> /by <time>` 

Adds a Deadline task to the task list with the given description and time as deadline.

**Example of usage:** 

`deadline homework /by Friday 2359`

**Example output:**

```
Understood, my Lord. I have added this duty:
[D][ ] homework (by: Friday 2359)
```

## Add an Event task

### Format: `event <description> /at <time>` 

Adds an Event task to the task list with the given description and time of occurrence.

**Example of usage:** 

`event party /at Sunday night`

**Example output:**

```
Understood, my Lord. I have added this duty:
[E][ ] party (at: Sunday night)
```

## Mark a task as done

### Format: `mark <task ID>` 

Marks the task with the given ID as done.

**Example of usage:** 

`mark 2`

**Example output:**

```
Excellent work, my Lord. You have completed the following duty:
[D][X] homework (by: Friday 2359)
```

## Label a task as not done yet

### Format: `unmark <task ID>` 

Marks the task with the given ID as not done yet.

**Example of usage:** 

`unmark 2`

**Example output:**

```
Alas, there seems to be more work to be done for this duty:
[D][ ] homework (by: Friday 2359)
```

## Delete a task

### Format: `delete <task ID>` 

Deletes the task with the given ID.

**Example of usage:** 

`delete 2`

**Example output:**

```
As you wish my Lord, I have removed this duty:
[D][ ] homework (by: Friday 2359)
```

## Clear the task list

### Format: `clear` 

Removes all tasks from the task list.

**Example of usage:** 

`clear`

**Example output:**

```
Your duty list has been erased.
```

## Find tasks

### Format: `find <keywords>` 

Finds all tasks in the task list whose description contains the given keyword(s).

**Example of usage:** 

`find book`

**Example output:**

```
Here are the duties in your list that matched:
[T][ ] return book
```

## Edit task description

### Format: `changedesc <task ID> <new description>` 

Updates the task with the given ID with the new description.

**Example of usage:** 

`changedesc 1 buy book`

**Example output:**

```
Right away my Lord, here is the amended duty:
[T][ ] buy book
```

## Edit task time

### Format: `changetime <task ID> <new time>` 

Updates the task with the given ID with the new time.

**Example of usage:** 

`changetime 2 Thursday 2359`

**Example output:**

```
Right away my Lord, here is the amended duty:
[D][ ] homework (by: Thursday 2359)
```