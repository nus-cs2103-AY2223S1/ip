# User Guide

Duke is a Personal Assistant Chatbot that helps you keep track of your tasks and deadlines. As of now, Duke supports the following commands:

## Commands 

- **List:** list all the tasks stored in the list e.g. `list`
- **Mark:** mark a task as done ✔️ e.g. `mark 3`
- **Unmark:** mark a task as not done yet e.g. `unmark 3`
- **Todo:** add tasks without any date/time attached to it, e.g. `todo borrow book`
- **Deadline:** add tasks that need to be done before a specific date/time, e.g. `deadline return book /by Sunday`
- **Event:** add tasks that start at a specific time and ends at a specific time, e.g. `event project meeting /at Mon 2-4pm`
- **Delete:** delete a task by its index number shown in the list, e.g. `delete 3`
- **Find:** find a task by searching for a keyword, e.g. `find book`
- **Tag:** tag a task, e.g. `tag 2 fun`

## Usage

### `List` - list all the tasks

Example of usage: 

`list`

Expected outcome: Lists out all the tasks stored in the list.

```
≖‿≖✧ Here are the tasks in your list: 
1. [T][X] test # test
2. [E][ ] project meeting (at: Mon 2-4pm) # work
3. [D][X] return book (by: Sunday) # reminder
```

### `Mark` - mark task as done

Example of usage:

`mark 2`

Expected outcome: Marks a task as completed based on its index in the list of tasks.

```
Good job! ʕ•̀ω•́ʔ I've marked this task as done: 
[E][X] project meeting (at: Mon 2-4pm) # work
```

### `Todo` - add a todo

Example of usage:

`todo borrow book`

Expected outcome: Add tasks without any date/time attached to it.

```
Okay! (๑´ڡ`๑) I've added this task:
[T][ ] borrow book # no tag
Now you have 4 tasks in the list!
```

### `Deadline` - add a deadline

Example of usage:

`deadline return book /by Sunday`

Expected outcome: Add tasks that need to be done before a specific date/time.

```
Okay! (๑´ڡ`๑) I've added this task:
[D][ ] return book (by: Sunday) # no tag
Now you have 5 tasks in the list!
```

### `Event` - add an event

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome: Add tasks that that start at a specific time and ends at a specific time.

```
Okay! (๑´ڡ`๑) I've added this task:
[E][ ] project meeting (at: Mon 2-4pm) # no tag
Now you have 6 tasks in the list!
```

### `Delete` - delete a task

Example of usage:

`delete 6`

Expected outcome: Delete a task based on its index in the list of tasks.

```
Okay! (๑´ڡ`๑) I've removed this task:
[E][ ] project meeting (at: Mon 2-4pm) # no tag
Now you have 5 tasks in the list!
```

### `Find` - find a task

Example of usage:

`find book`

Expected outcome: Find task based on keyword search.

```
≖‿≖✧ Here are the matching tasks in your list:
1. [T][ ] borrow book # no tag
2. [D][ ] return book (by: Sunday) # no tag
```

### `Tag` - tag a task

Example of usage:

`tag 1 fun`

Expected outcome: Find task based on keyword search.

```
≖‿≖✧ Successfully tagged task No.1 with # fun
```