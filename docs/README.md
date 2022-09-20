# User Guide

If you're someone wishing to improve your productivity by managing all your tasks in a single location using the command
line, this app is meant for you!

## Getting Started

1. Download the JAR file from the GitHub release
2. Run the app
3. You're ready to use the chatbot to improve your productivity!

## Features

Here are some handy features you can use to track your tasks, including events and deadlines.

1. list
2. todo
3. deadline
4. event
5. find
6. mark
7. unmark
8. delete

### `list`

Lists all the tasks that you have to complete.

For example,

```
list 
```

could possibly return:

```
1. [T] [ ] Work on Resume
2. [D] [ ] Submit application (by: 31 October 2022, 23:59) 
```

### `todo`

Add a todo item to your task list.

For example,

```
todo Work on Resume
```

would return

```
Gotcha. I have added this task:
    [T] [ ] Work on Resume
Now you have 3 tasks in the list
```

Note that the first box with the `[T]` tells us that the task is a `todo`. The second box `[ ]` tells us that the task
has yet to be completed. You will see similar notation used for deadlines and events.

### `deadline`

Add a task with a deadline to your list

For example, say you wanted to add a deadline to submit your application by the 31st of October 2359. You could easily
achieve it with this:

```
deadline submit application /by 2022-10-31 23:59
```

would return

```
Gotcha. I have added this task:
    [D] [ ] submit application (by: 31 Oct 2022 23:59)
Now you have 4 tasks in the list
```

**Some things to keep in mind**:

1. Don't forget to put the `/at` to indicate the deadline itself.
2. Make sure that the deadline is entered in `YYYY-MM-DD HH:MM` format

### `event`

Adding an event is identical to adding a deadline but instead of using `/by`, we use `/at`.

For example,

```
event team meeting /at 2022-12-12 10:00
```

would return

```
Gotcha. I have added this task:
    [E] [ ] team meeting (at: 12 Dec 2022 10:00)
Now you have 5 tasks in the list
```

### `find`

To find all tasks with a certain keyword in the description, you can use the `find`
command.

For example,

```
find iP
```

would return

```
Here are your results:
    [T] [ ] submit iP
```

If no results are found, the output would be:

```
Sorry, I could not find any tasks with your keyword :(
```

### `mark` and `unmark`

You can mark tasks as done and unmark tasks as not done by passing the position of the task in the list.

For example, if you want to mark the `submit iP` task as done, you'd do it like this:

1. First we use the `list` command to find the position of our task.

```
list
```

Let's say that the task is at position 4 in the list.

Then, we can do:

```
mark 4
```

to mark the task as done!

You can do the same to unmark a task as well :) Try it out for yourself!

### `delete`

Deleting a task works the same way as marking/unmarking a task as done/not done. You just have to specify the task
number and you're good to go.

For example,

```
delete 3
```

will delete the 3rd task in the list.

### `bye`

Once you're done using the chatbot, simply send the `bye` command to exit. The program will terminate itself after 3
seconds.

### Other stuff

*For more advanced users*, you can directly edit the `data.txt` file to add, delete, and mark tasks as
completed/incomplete if you prefer to as well.
