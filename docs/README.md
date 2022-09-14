# Duke User Guide

## Features 

### 1. Adding tasks

Description: Adds a task. There are 3 different types of tasks you could add: Todo, Deadline and Events.

#### - Add a Todo

Command: `todo {task}`

Example: `todo borrow book`

Expected outcome:
```
Swee Chai Butterfly! limpeh added this task:
[T][]borrow book
Now you have 1 tasks in the list.
```

#### - Add a Deadline

Command: `deadline {task} /by date and time in any format`

Example: `deadline return book /by 19-9-2022`

Expected outcome:
```
Swee Chai Butterfly! limpeh added this task:
[D][]return book (by: 19-9-2022)
Now you have 2 tasks in the list.
```

#### - Add an Event

Command: `event {task} /at date and time in any format`

Example: `event kayaking at kallang /at 19-9-2022 1300`

Expected outcome:
```
Swee Chai Butterfly! limpeh added this task:
[E][]return book (at: 19-9-2022 1300)
Now you have 3 tasks in the list.
```

### 2. Deleting tasks

Description: Deletes a task at the input task number

Command: `delete {task number}`

Example: `delete 1`

Expected outcome:
```
Ok boss! I've removed this task liao:
[T][] borrow book
You have 2 tasks left boss!
```

### 3. Listing your tasks

Description: Lists out all the tasks in the task list.

Command: `list`

Expected outcome:
```
Stop slacking liao, see got so much stuff to do:
1.[D][]return book (by: 19-9-2022)
2.[E][]return book (at: 19-9-2022 1300)
```

### 4. Marking your tasks as done

Description: Marks the task of the input task number as done.

Command: `mark {task number}`

Example: `mark 1`

Expected outcome:
```
Okay Boss! Limpeh marked this task as done:
[D][X]return book (by: 19-9-2022)
```


### 5. Unmarking your tasks

Description: Unmarks the task of the input task number as not done.

Command: `unmark {task number}`

Example: `unmark 1`

Expected outcome:
```
Okay Boss! Limpeh marked this task as not done yet:
[D][]return book (by: 19-9-2022)
```

### 6. Finding tasks

Description: Finds all the task that contains the keyword and list them out.

Command: `find {keyword}`

Example: `find book`

Expected outcome:
```
Okay Boss! Limpeh searched everywhere and found these tasks:
1.[D][]return book (by: 19-9-2022)
2.[E][]return book (at: 19-9-2022 1300)
```

