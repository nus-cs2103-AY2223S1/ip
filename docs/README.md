# DUKELIST USER GUIDE
The latest and greatest command line interface bot to help you keep track of your life!
## Features 

### Feature - `Todo`

Allows you to add a task that you have to complete

Example of usage:

`todo football`

Expected outcome:

```
Got it! I'v added this task:
[T][]football
Now you have one tasks in the list.
```
### Feature - `Deadline`

Allows you to add a deadline for something that you have to complete by then

Example of usage:

`deadline assignment1 /by 2022-09-08 7pm`

Expected outcome:

```
Got it! I'v added this task:
[D][]assignment1 (by 8th September 2022 7pm)
Now you have 2 tasks in the list.
```

### Feature - `Event`

Allows you to add a event that is upcoming for you

Example of usage:

`event tournament /at 2022-09-08 7pm`

Expected outcome:

```
Got it! I'v added this task:
[E][]tournament (at 8th September 2022 7pm)
Now you have 3 tasks in the list.
```

### Feature - `mark`

marks the first task in your list as complete

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I'v marked this task as done!
[T][X] football
```

### Feature - `unmark`

unmarks the  task in your list as incomplete

Example of usage:

`unmark 1`

Expected outcome:

```
Okay, I'v marked this task as not done yet:
[T][X] football
```

### Feature - `delete`

deletes that task in your list

Example of usage:

`delete 1`

Expected outcome:

```
Noted, I have removed this task:
[T][] football
```

### Feature - `list`

shows all the current tasks that you have in your list

Example of usage:

`list`

Expected outcome:

```
1. [T][ ] Football
2. [D][X] Assignment (by: Sep 08 2022 7pm)
```

### Feature - `find`

finds task with the keyword you are looking for

Example of usage:

`find football`

Expected outcome:

```
1. [T][ ] Football
```


