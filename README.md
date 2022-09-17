# Michael Todo-list Bot
Michael is a F1-themed todo list bot following the open source Duke project template. Michael only heeds your command if your first name is Max and last name is Verstappen

![masi](https://user-images.githubusercontent.com/46512542/190847321-dd88dac3-afc7-45d8-a044-e909748aadb4.jpeg)

# Michael User Guide
### Features
Adding and Deleting Tasks

Add 3 types of tasks to your list. The 3 types include: `event`, `todo` and `deadline`

### Listing Your Tasks
List out all the tasks that you have added.

### Find a Task
Find tasks that you have added containing a keyword.

### Mark as done or undone
Mark your tasks as done or undone.

## Usage
### `todo` - Add a todo task
Format: `todo <task description>`

Example of usage:

`todo homework`

Expected outcome:
```
Got it my lord. I've added this task:
  [T][] homework
Now you have 2 tasks in the list.
```

### `event` - Add an event task
Format: `event <task description> /at <time>`

Example of usage:

`event F1 /at Friday`

Expected outcome:
```
Got it my lord. I've added this task:
  [E][] F1 (at: Friday)
Now you have 3 tasks in the list.
```

### `deadline` - Add a deadline task
Format: `deadline <task description> /by dd/mm/yyyy HHmm`

Example of usage:

`deadline CS2103T /by 16/09/2022 2359`

Expected outcome:
```
Got it my lord. I've added this task:
  [D][] CS2103T (by: Sep 16 2022 23:59:00)
Now you have 4 tasks in the list.
```

### `list` - List all current tasks
Format: `list`

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list, my lord:
1.[T][] task
2.[T][] homework
3.[E][] F1 (at: Friday)
4.[D][] CS2103T (by: Sep 16 2022 23:59:00)
```

### `find` - Search tasks with a keyword
Format: `find <keyword>`

Example of usage:

`find home`

Expected outcome:
```
Here are the matching tasks in your list, my lord:
2.[T][] homework
```

### `mark` - Mark task as done
Format: `mark <index>`

Example of usage:

`mark  2`

Expected outcome:
```
Well done, my lord. I've marked this task as done:
 [T][X] homework
```

### `unmark` - Mark task as not done
Format: `unmark <index>`

Example of usage:

`unmark  2`

Expected outcome:
```
OK, my lord. I've marked this task as not done yet:
 [T][] homework
```

### `delete` - Delete a task
Format: `delete <index>`

Example of usage:

`delete  2`

Expected outcome:
```
Noted my lord. I've removed this task:
 [T][] homework
Now you have 3 tasks in the list.
```

### Easter Eggs
Supported commands: `lewis`, `max`, `toto`, `king`

Format: `<easter egg>`

Example of usage:

`max`

Expected outcome:
```
MAX MAX SUPER MAX
```
