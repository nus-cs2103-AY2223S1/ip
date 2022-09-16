# User Guide

## Features 

### Task Management

You can use LishBot's Task Management feature to: 
1. add and delete task
2. categorize task into ToDo, Deadline and Event
3. mark task as done or not done
4. list all your task
5. find task by related keyword

### NEW FEATURE!!! Trivia

You can use LishBot's Trivia feature to make LishBot memorize new commands and responses

## Commands

Here, U denotes user and B denotes bot
List of commands: todo, event, deadline, delete, mark, unmark, list, find, teach, bye

### `todo` - create a ToDo task (a task with no associated time)

Format:

```
todo {task description}
```

Example usage:

```
U: todo do homework
B: Got it! I will add that task now
   [T][] do homework
   Now, the number of tasks you have is 1
```

### `deadline` - create a Deadline task (a task which must be done by certain time)

Format:

```
deadline {task description} /{time modifier} YYYY-MM-DD
```

example usage:

```
U: deadline CS2103T IP /by 2022-09-16
B: Got it! I will add that task now
   [D][] CS2103T IP (by: Sep 16 2022)
   Now, the number of tasks you have is 2
```

### `event` - create an Event task (a task which must is done during certain time)

Format:

```
event {task description} /{time modifier} YYYY-MM-DD
```

Example usage:

```
U: deadline CS2103T Tutorial /at 2022-09-16
B: Got it! I will add that task now
   [E][] CS2103T Tutorial (at: Sep 16 2022)
   Now, the number of tasks you have is 3
```

### `delete` - delete a task

Format:

```
delete {task number (1-based indexing)}
```

Example usage:

```
U: delete 1
B: Roger that! I will remove this task
   [T][X] do homework
   Now, the number of tasks you have is 3
```

### `mark` - mark a task as done

Format:

```
mark {task number (1-based indexing)}
```

Example usage:

```
U: mark 1
B: Great! You have finished a task. I will mark this as done
   [T][X] do homework
```

### `unmark` - mark a task as not done

Format:

```
unmark {task number (1-based indexing)}
```

Example usage:

```
U: unmark 1
B: Okay, I will mark this task as not done yet
   [T][ ] do homework
```

### `list` - list all of your current tasks and status

Format:

```
list
```

Example usage:

```
U: list
B: Finding your task list...
   Found it! Here are what you have to do:
   1. [T][] do homework
   2. [D][] CS2103T IP (by: Sep 16 2022)
   3. [E][] CS2103T Tutorial (at: Sep 16 2022)
```

### `find` - find tasks related to your keyword

Format:

```
find {keyword}
```

Example usage:

```
U: find homework
B: Let me find tasks that match your description...
   1. [T][] do homework
```

### `teach` - teach LishBot to memorize commands and responses

Format:

```
teach {command}/{response}
```

Example usage:

```
U: teach what is your name?/LishBot
B: Okay, I have memorized that command!
U: what is your name?
B: LishBot
```

### `bye` - close the app and save tasks and memory (trivia)

Format:

```
bye
```

Example usage:

```
U: bye
B: Glad to be of help! I have saved all your task progress and the new commands you taught me :) See you later~
```
