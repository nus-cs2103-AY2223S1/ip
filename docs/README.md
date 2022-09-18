# User Guide

## About

Duke is a Personal Assistant Chatbot that helps a person to keep track of various tasks.

## Quick Start

1. Ensure that you have Java 11 installed.
2. Download the latest release [here](https://github.com/totsukatomofumi/ip/releases/tag/A-Release).
3. Double-click .jar file to start managing your tasks.

## Features 

### Chat-style Interface

Input commands using a chat-style interface.

### Task Organisation

Add or delete todos (not by any specified date), deadlines (due by a date) and events (happening at a date).

### Task Marking

Mark or unmark any task as done.

### Task Listing

List out all tasks.

### Task Finding

Find tasks which descriptions contain the word/ phrase you are looking for.

## Usage

### `todo` - Add todo

Adds a todo (task with no specific date).

Example of usage: 

`todo Read the textbook`

Expected outcome:

Duke will add the todo to the list and reply:

```
Got it. I've added this task:
[T][ ] Read the textbook
Now you have 1 tasks in the list.
```

### `deadline` - Add deadline

Adds a deadline (task due by a specific date). 
If the year is omitted, Duke will assume it to be the current year.

Example of usage:

`deadline Attempt the quiz /by 16 Sep 22`

Expected outcome:

Duke will add the deadline to the list and reply:

```
Got it. I've added this task:
[D][ ] Attempt the quiz (by: Sep 16 2022)
Now you have 2 tasks in the list.
```

### `event` - Add event

Adds an event (task happening at a specific date).
If the year is omitted, Duke will assume it to be the current year.

Example of usage:

`event Sit for finals /at 16 Sep 22`

Expected outcome:

Duke will add the event to the list and reply:

```
Got it. I've added this task:
[E][ ] Sit for finals (at: Sep 16 2022)
Now you have 3 tasks in the list.
```

### `delete` - Delete task

Deletes a task of the specified task number.

Example of usage:

`delete 2`

Expected outcome:

Duke will delete the specified task from the list and reply:

```
Noted. I've removed this task:
[D][ ] Attempt the quiz (by: Sep 16 2022)
Now you have 2 tasks in the list.
```

### `mark` - Mark task

Marks task of the specified task number as done.

Example of usage:

`mark 1`

Expected outcome:

Duke will mark the specified task as done and reply:

```
Nice! I've marked this task as done:
[T][X] Read the textbook
```

### `unmark` - Unmark task

Unmarks task of the specified task number as done.

Example of usage:

`unmark 1`

Expected outcome:

Duke will unmark the specified task as done and reply:

```
OK, I've marked this task as not done yet:
[T][ ] Read the textbook
```

### `list` - List tasks

Lists all tasks.

Example of usage:

`list`

Expected outcome:

Duke will list all tasks, replying:

```
Here are the tasks in your list:
1.[T][ ] Read the textbook
2.[E][ ] Sit for finals (at: Sep 16 2022)
```

### `find` - Find tasks

Finds all tasks which descriptions contain the specified word/ phrase (case insensitive).

Example of usage:

`find read`

Expected outcome:

Duke will list all matching tasks, replying:

```
Here are the matching tasks in your list:
1.[T][ ] Read the textbook
```

### `bye` - End session

Ends the current Duke session.

Example of usage:

`bye`

Expected outcome:

Session will end in 2-3 seconds after Duke replies:

```
Bye. Hope to see you again soon!
```