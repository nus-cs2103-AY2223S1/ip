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

## Usage

### `todo` - create a ToDo task (a task with no associated time)

Format:

```
todo {task description}
```

example usage:

```
todo do homework
```

LishBot's response:

```
Got it! I will add that task now
[T][ ] do homework
Now, the number of tasks you have is 1
```

### `deadline` - create a Deadline task (a task which must be done by certain time)

Format:

```
deadline {task description} /{time modifier} YYYY-MM-DD
```

example usage:

```
deadline CS2103T IP /by 2022-09-16
```

LishBot's response:

```
Got it! I will add that task now
[D][ ] CS2103T IP (by: Sep 16 2022)
Now, the number of tasks you have is 2
```

### `event` - create an Event task (a task which must is done during certain time)

Format:

```
event {task description} /{time modifier} YYYY-MM-DD
```

example usage:

```
deadline CS2103T Tutorial /at 2022-09-16
```

LishBot's response:

```
Got it! I will add that task now
[E][ ] CS2103T Tutorial (at: Sep 16 2022)
Now, the number of tasks you have is 3
```

### `mark` - mark a task as done

Format:

```
mark {task number (1-based indexing)}
```

example usage:

```
mark 1
```

LishBot's response:

```
Great! You have finished a task. I will mark this as done
[T][X] do homework
```

### `unmark` - mark a task as not done

Format:

```
unmark {task number (1-based indexing)}
```

example usage:

```
unmark 1
```

LishBot's response:

```
Okay, I will mark this task as not done yet
[T][ ] do homework
```
### `list` - list all of your current tasks and status

### 
