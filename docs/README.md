# Phil User Guide

## <ins>Features</ins>

- **Help** - Get a list of all the available commands.

- **Add/Delete task** - Ability to add a todo, deadline and event task.

- **Mark/Unmark task** - Ability to mark or unmark as done.

- **List tasks** - Ability to generate a list of all the tasks in progress

- **Find task** - Ability to find a task based on keywords.

- **Clear List** - Ability to clear the entire list.

---

## <ins>Usage</ins>

### `help` - Gets the list of available commands.

Enter `help`.

Example of usage: 

`help`

Expected outcome:

A help list.

```
todo - Add a todo task - todo {description}
.
.
.
```
---

### `todo` - Add a todo task

Enter `todo` followed by a description to create a todo task.

Example of usage: 

`todo borrow book`

Expected outcome:

A todo task with the desciption of "borrow book" will be added to the list.

```
added: [T][ ] borrow book
```
---

### `deadline` - Add a `deadline` task

Enter `deadline` followed by a description, ` \by ` and a date and/or time to create a deadline task.

Example of usage: 

`deadline return book /by 2022-9-16 2359`

Expected outcome:

A deadline task with the desciption of "return book" and the date/time deadline as "16 September 2022 11:59 PM" will be added to the list.

```
added: [D][ ] return book (by: Sep 16 2022 11:59 PM)
```
---

### `event` - Add a `event` task

Enter `event` followed by a description, ` \at ` and a date and/or time to create a event task.

Example of usage: 

`event book festival /at Sunday 2-4pm`

Expected outcome:

A event task with the desciption of "book festival" and the date/time as "Sunday 2-4pm" will be added to the list.

```
added: [E][ ] book festival (at: Sunday 2-4pm)
```
---

### `mark` - Mark a task as done

Enter `mark` followed by the task number.

Example of usage: 

`mark 1`

Expected outcome:

If the task of that number exists, it will mark it as done.

```
Roger sir the task has been marked!
```
The task will then look like this.
```
1.[D][X] borrow book (by: Sep 16 2022 11:59 PM)
```
---

### `unmark` - Unmark a task as done

Enter `unmark` followed by the task number.

Example of usage: 

`unmark 1`

Expected outcome:

If the task of that number exists, it will unmark it.

```
Aww okay the task has been unmarked.
```
The task will then look like this.
```
1.[D][] borrow book (by: Sep 16 2022 11:59 PM)
```
---

### `list` - List of tasks in progress

Enter `list`.

Example of usage: 

`list`

Expected outcome:

If there are tasks in the list, it will generate the list.

```
1.[D][] borrow book (by: Sep 16 2022 11:59 PM)
.
.
.
```
---

### `find` - Find tasks in the list based on keywords

Enter `find` followed by keywords to search for.

Example of usage: 

`list borrow book`

Expected outcome:

If there are tasks fitting all the keywords, it will return those tasks.

```
Woohoo here are some matches found!
1.[D][] borrow book (by: Sep 16 2022 11:59 PM)
.
.
.
```
---

### `clear` - Clears the entire task list

Enter `clear`.

Example of usage: 

`clear`

Expected outcome:

The task list will now be empty

```
The list has been successfully cleared!
```
The command `list` will then show this
```
Woohoo! You are all out of tasks!
```
---

### `bye` - Exit the program

Enter `bye`.

Example of usage: 

`bye`

Expected outcome:

Phil will exit in 3 seconds.

```
I shall take my leave in 3 seconds! See you late alligator!
```