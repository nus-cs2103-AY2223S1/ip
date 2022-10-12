# User Guide

## Features 

### Add Tasks

Add tasks which can be one of three types, **Event**, 
**Deadline**, or **ToDo**.

Format:
1. todo `TASK_DESCRIPTION`
2. event `TASK_DESCRIPTION` /at `DATE_IN_MM/DD/YYYY` `TIME_IN_24_HR_FORMAT` 
3. deadline `TASK_DESCRIPTION` /at `DATE_IN_MM/DD/YYYY` `TIME_IN_24_HR_FORMAT`

Example:  
event Odoro Practice /at 2/12/2019 1800

Expected Outcome:
```
    Looks like you have a new event:
[E][ ] Odoro Practice (at: 12 Feb 2019 6:00PM) 
           2 tasks left, ganbara!
```

### Delete tasks

Delete tasks from task list by specifying index of task.

Format:  
delete `INDEX_NUMBER`

Example:  
delete 1

Expected Outcome:
```
    Task deleted!   
  [T][X] return book
  Now you have 1 left.
```

### List tasks

List out all the tasks currently in the task list.

Format:   
list 

Expected Outcome:  
```
1.[T][X] return book
            #
2.[T][ ] return magazine
            #
```

### Mark tasks as done

Marks a task at a specified index as done

Format:  
mark `INDEX_NUMBER`

Example:  
mark 1

Expected Outcome:  
```
Good Job! You're killing it!
        [X] return book
```

### Unmark tasks as undone 

Unmarks a task at a specified index as undone.

Format:  
unmark `INDEX_NUMBER`

Example:  
unmark 1

Expected outcome:
```
AAaaa please get it done soon...
        [] return book
```

### Tag a task

Adds a tag to task at a specified index. Only one tag per task.

Format:  
tag `INDEX_NUMBER` `TAG_DESCRIPTION`

Example: 
tag 1 important 

Expected outcome: 
```
Your tag important has been added to the task :)
```

### Find tasks 

Find tasks in the task-list containing the keyword given.

Format: 
find `KEYWORD`

Example:  
find return 

Expected outcome:
```
1.[T][] return book
            #
2.[T][] return magazine
            #
```

### Terminate application 

End the application. 

Format:  
`bye`

Expected outcome:  
```
sayonara, goodbye
```

### Automatic saving of data

Kiwi saves your data in a local file automatically after termination. Yay!