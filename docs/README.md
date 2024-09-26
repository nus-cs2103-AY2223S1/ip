# User Guide for JARVIS

### 1. Adding tasks

Description: Adds a task. There are 3 different types of tasks you can add: Todo, Deadline and Events.

#### - Add a Todo

Command: `todo {task}`

Example: `todo read book`

Expected outcome:
```
Got it. I've added this task:
[T][] read book
Now you have 1 tasks in the list.
```

#### - Add a Deadline

Command: `deadline {task} /by date in yyyy-mm-dd format`

Example: `deadline return book /by 2022-10-10`

Expected outcome:
```
Got it. I've added this task:
[D][] return book (by: Oct 10 2022)
Now you have 2 tasks in the list.
```

#### - Add an Event

Command: `event {task} /at date in yyyy-mm-dd format`

Example: `event project meeting /at 2022-10-10`

Expected outcome:
```
Got it. I've added this task:
[E][] project meeting (at: Oct 10 2022)
Now you have 3 tasks in the list.
```

### 2. Deleting tasks

Description: Deletes a task at the input task number

Command: `delete {task number}`

Example: `delete 1`

Expected outcome:
```
Noted. I've removed this task:
[T][] read
Now you have 2 tasks in the list.
```

### 3. Listing your tasks

Description: Lists out all the tasks in the task list.

Command: `list`

Expected outcome:
```
Here are the tasks in your task list:
1.[D][] return book (by: Oct 10 2022)
2.[E][] project meeting (at: Oct 10 2022)
```

### 4. Marking your tasks as done

Description: Marks the task of the input task number as done.

Command: `mark {task number}`

Example: `mark 1`

Expected outcome:
```
Great! I've marked this task as done:
[D][X]return book (by: Oct 10 2022)
```


### 5. Unmarking your tasks

Description: Unmarks the task of the input task number as not done.

Command: `unmark {task number}`

Example: `unmark 1`

Expected outcome:
```
Ok, I have marked this task as not done yet:
[D][]return book (by: Oct 10 2022)
```

### 6. Finding tasks

Description: Finds all the task that contains the keyword and list them out.

Command: `find {keyword}`

Example: `find book`

Expected outcome:
```
Here are the matching tasks in your list:
1.[D][]return book (by: Oct 10 2022)
```
