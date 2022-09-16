# User Guide
Good morning from PUKE bot. A task manager bots who manages your tasks reluctantly  
## Features 

### Manage tasks
Puke keeps track of 3 types of tasks: Todos, Events and Deadlines. You can add a new task or delete an old task. A task also has a status which is complete or not complete. 

### Save tasks 
Puke saves your tasks on your computer local storage, so you can come back to access the tasks you have written after you have closed the app. 

### Find tasks 
Quickly find tasks in the list with a given keyword. 

### List tasks 
Have a quick overview of the lists of saved tasks and their status. Displays in an easy to read list on the graphical UI. 

## Usage

### `help` - Open help menu 

Opens up a helpsheet with all commands and format

### `todo <description>` - Creates a ToDo 

Adds a new "todo" to your task list

Example of usage: `todo play the piano`

Expected outcome:

```
Got it. I've added this task: 
[T][] play the piano
Now you have 1 tasks in the list.
```


### `deadline <description> /by <date> <time>` - Creates a deadline with attached time and date

Adds a new deadline into your task list. 

Example of usage: `deadline do 3000 push-ups /by 2022-10-10 23:59`

Expected outcome:

```
Got it. I've added this task:
[D][] do 3000 push-ups (by: Oct 10 2022 23:59)
Now you have 1 tasks in the list.
```


### `event <description> /at <date> <time>` - Creates an event with attached time and date

Adds a new event into your task list.

Example of usage: `event Eric Chou Concert /at 2022-10-9 15:00`

Expected outcome:

```
Got it. I've added this task:
[E][] Eric Chou Concert (at: Oct 9 2022 15:00)
Now you have 1 tasks in the list.
```


### `list` - Display all saved tasks

Displays all saved tasks in a list format on the graphical UI. 

Expected outcome:


```
Here are the tasks in your list: 
1.[T][] play the piano 
2.[D][] do 3000 push-ups (by: Oct 10 2022 23:59)
3.[E][] Eric Chou Concert (at: Oct 9 2022 15:00)
```


### `mark <index>` - Marks a task as done

Marks a task in your task list as done. 

Example of usage: `mark 1`

Expected outcome:

```
Nice! I've marked this task as done: 
 [T][X] play the piano
```

### `unmark <index>` - Marks a task as not done

Marks a task in your task list as not done. 

Example of usage: `unmark 1`

Expected outcome:

```
OK! I've marked this task as not done yet: 
 [T][] play the piano
```


### `delete <index>` - Deletes a task

Deletes a task in your task list

Example of usage: `delete 2`

Expected outcome:

Description of the outcome.

```
Noted. I've removed this task: 
[D][] do 3000 push-ups(by: Oct 10 2022 23:59)
```


### `find <keyword>` - Finds tasks in the list 

Searches the list for matching tasks with a given keyword

Example of usage: `keyword`

Expected outcome:

```
Here are the matching tasks in your list
1.[E][] Eric Chou Concert (at Oct 9 2022 15:00)
```


### `bye` - Exts the app 

Exits the bot by closing the app.
