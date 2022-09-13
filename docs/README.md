# User Guide for Duke

Welcome to Dukity-Duke! This bot will help you manage your
tasks so that you can stay on track towards your goals!

## Features 

### Create a Todo

This feature creates a Todo Task.

```
input: todo return book

expected output: 
Got it. I've added this task:
[T][] return book
```


### Create an Event

This feature creates an Event Task.
```
input: event project meeting /at Mon 2-4pm

expected output: 
Got it. I've added this task:
[E][] project meeting(at:Mon 2-4pm)
```

### Create a Deadline

This feature creates a Deadline Task.
```
input: deadline return book /by 2/12/2019 1800

expected output: 
Got it. I've added this task:
[D][] return book (by: 2019-12-02 1800)
```

### Mark a Task as Done
This feature marks a task as done.
```
Assuming is the task is the first 1, and is called
todo return book

input: mark 1

expected output: 
Nice! I've marked this task as done:
[T][X] return book
```

### Mark a Task a not Done
This feature removes a mark on a task that was marked done.
```
Assuming is the marked task is the first 1, and is called
todo return book

input: unmark 1

expected output: 
OK, I've marked this task as not done yet:
[T][] return book
```

### List all Tasks
This feature lists all the tasks and their status.
```
Assuming we have 2 tasks, one is called todo return book,
the other is called event project meeting /at Mon 2-4pm

input: list

expected output: 
Here are the tasks in your list:
1.[T][] return book
2.[E][] project meeting (at:Mon 2-4pm)
```
### Delete a Task
This feature deletes a task from the list.
```
Assuming is the task is the first 1, and is called
todo return book

input: delete 1

expected output: 
Noted. I've removed this task:
[T][] return book
```



[//]: # (## Usage)

[//]: # ()
[//]: # (### `Keyword` - Describe action)

[//]: # ()
[//]: # (Describe the action and its outcome.)

[//]: # ()
[//]: # (Example of usage: )

[//]: # ()
[//]: # (`keyword &#40;optional arguments&#41;`)

[//]: # ()
[//]: # (Expected outcome:)

[//]: # ()
[//]: # (Description of the outcome.)

[//]: # ()
[//]: # (```)

[//]: # (expected output)

[//]: # (```)
