# User Guide

This is a user guide for the **Karen task bot**. Karen is an unfriendly task manager
who helps manage different types of tasks, begrudgingly.

Karen task bot is optimised for use via Graphical User Interface (GUI).

## Notations
`{}` means the value encases within the brackets can be anything.

`[]` means the field is optional.

## Command Summary
 Keyword      | Usage                                                       
--------------|-------------------------------------------------------------
 **todo**     | `todo {description}`                                        
 **deadline** | `deadline {description} /by {dd/mm/yyyy}`                   
 **event**    | `event {description} /at {dd/mm/yyyy}`                      
 **list**     | `list`                                                      
 **mark**     | `mark {task number}`                                        
 **unmark**   | `unmark {task number}`                                      
 **delete**   | `delete {task number}`                                      
 **find**     | `find {keyword}`                                            
 **update**   | `update {task number} [{description}] [/at {dd/mm/yyyy}] `  
 **bye**      | `bye`                                                       


## Features 
### Add a todo: `todo`
Adds a task of type todo to your task list.

Format: `todo {description}`

Example: `todo bake a cake`

Expected output:
```
Fine, I'll add this task:
    [T][] bake a cake
Now you have {x} tasks in the list...
```

### Add a deadline: `deadline`
Adds a task of type deadline to your task list.

Format: `deadline {description} /by {dd/mm/yyyy}`

Example: `deadline CS2103T assignment /by 12/12/2022`

Expected output:
```
Fine, I'll add this task:
    [D][] CS2103T assignment (by: Dec 12 2022)
Now you have {x} tasks in the list...
```

### Add an event: `event`
Adds a task of type event to your task list.

Format: `event {description} /at {dd/mm/yyyy}`

Example: `event Joe's birthday /at 10/10/2022`

Expected output:
```
Fine, I'll add this task:
    [E][] Joe's birthday (at: Oct 10 2022)
Now you have {x} tasks in the list...
```

### Mark task as completed: `mark`
Marks a task in your task list as complete.

Format: `mark {task number}`

Example: `mark 2`

Expected output:
```
Took you long enough to complete this task:
[T][X] CS2103T assignment (by: Dec 12 2022)
```

### Mark task as uncomplete: `unmark`
Marks a task in your task list as uncompleted.

Format: `unmark {task number}`

Example: `unmark 1`

Expected output:
```
Another task marked as not done?? Slow indeed
[T][] bake a cake
```

### List tasks: `list`
Returns all the tasks in your task list.

Format: `list`

Example: `list`

Expected output:
```
Here are your dumb tasks in your list:
1.[T][] bake a cake
2.[D][X] CS2103T assignment (by: Dec 12 2022)
3.[E][] Joe's birthday (at: Oct 10 2022)
```

### Delete a task: `delete`
Deletes a task in your task list.

Format: `delete {task number}`

Example: `delete 3`

Expected output:
```
Ughh I'll remove this task:
    [E][] Joe's birthday (at Oct 10 2022)
Now you have {x} tasks in the list...
```

### Find task: `find`
Returns a list of tasks that matches keyword.

Format: `find {keyword}`

Example: `find bake`

Expected output:
```
Tsk! Be grateful I searched through your entire list to find these:
1. [T][] bake a cake
```

### Update a task: `update`
Updates a task in your task list.

Format: `update {task number} [{description}] [/at {dd/mm/yyyy}]`

Example 1: `update 2 bake cupcake`

Example 2: `update 2 /at 09/09/2023`

Expected output 1:
```
Fickle-minded as usual. I've update this task:
[D][X] bake cupcake (by: Dec 12 2022)
```

### Exit task bot: `bye`
Exits the program.

Format: `bye`

Example: `bye`

Expected output:
```
K finally, good riddance!
```




