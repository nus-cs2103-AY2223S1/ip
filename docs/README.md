# chatBOB

Bob is a **task manager chatbot** that works with a Graphical User Interface (GUI). 
It is optimized for **todos, deadlines, and events.** 
With Bob's help, you no longer have to remember your own tasks, it's all just one type away!
## Features 


+ "9.    EDIT TASK: edit <task number> <name-to-be-edited OR /date-to-be-edited>\n"
+ "10.   DISPLAY COMMANDS: help\n"
+ "11.   TO END THE PROGRAM: bye\n";

### Feature 1: Help

#### Usage:
`help` - shows list of all current commands

####Expected outcome

Bob will send a list of all the possible commands.
```
-------------------------------------
here's what you can do|

1. ADD A TODO TASK: todo <task>
...
...
...
-------------------------------------
```

### Feature 2: List

####Usage:
`list` - list all tasks added by user

####Expected Outcome:
Bob will reply with a list of all tasks.
```aidl
-------------------------------------
here are your tasks!

1. [D][X] business proposal (by: Dec 12 2019)
2. [T][] read book 
3. [E][] school conference (at: Oct 20 2022)
...
...
-------------------------------------
```

### Feature 3: ToDo

#### Usage:
`todo <task description>` - creates a new ToDo task

####Expected outcome

Bob will create a new ToDo task with the inputted task description and add it to the list.
```
-------------------------------------
okay! new task:
 [T][] sleep
just <number of tasks> tasks left! 
-------------------------------------
```

### Feature 4: Deadline

#### Usage:
`deadline <task description> /by <yyyy-mm-dd>` - creates a new Deadline task

####Expected outcome

Bob will create a new Dedline task with the inputted task description and date, then add it to the list.
```
-------------------------------------
okay! new task:
 [D][] school project (by: Dec 15 1992)
just <number of tasks> tasks left! 
-------------------------------------
```

### Feature 5: Event

#### Usage:
`event <task description> /at <yyyy-mm-dd>` - creates a new Event task

####Expected outcome

Bob will create a new Event task with the inputted task description and date, then add it to the list.
```
-------------------------------------
okay! new task:
 [E][] press conference (at May 17 2021)
just <number of tasks> tasks left! 
-------------------------------------
```

### Feature 6: Mark

#### Usage:
`mark <task index>` - masks task as done based on the specified task index in the list

####Expected outcome

Bob will mark task with specified index in the list as done.
```
-------------------------------------
yay! you've completed a task!
[T][] sleep
-------------------------------------
```

### Feature 7: Unmark

#### Usage:
`unmark <task index>` - masks task as undone based on the specified task index in the list

####Expected outcome

Bob will unmark task with specified index in the list as done (if task is initially marked as done) .
```
-------------------------------------
yay! you've completed a task!
[T][] sleep
-------------------------------------
```

### Feature 8: Remove

#### Usage:
`remove <task index>` - removes task of specified task index from the list

####Expected outcome

Bob will remove task with specified index from the list.
```
-------------------------------------
there's one less task for you! removed: 
 [T][] sleep
just <number of tasks> left!
-------------------------------------
```

### Feature 9: Filter

#### Usage:
`filter <yyyy-mm-dd>` - filters out all tasks with specified event/deadline date

####Expected outcome

Bob will search the list for all tasks with specified event and deadline dates, then showcase a list of just the filtered out tasks.
```
-------------------------------------
here are your tasks on 'Dec 12 2019'
 
 1. [D][] business proposal (by: Dec 12 2019)
 2. [E][] business meeting (at: Dec 12 2019)
-------------------------------------
```

### Feature 10: Find

#### Usage:
`find <searchword>` - search for all tasks which contain inputted searchword in task description

####Expected outcome

Bob will search for all tasks that contain the specified keyword, then show a list of all the found tasks
```
-------------------------------------
here are all tasks with 'business'

1. [D][] business proposal (by: Dec 12 2019)
2. [E][] buiness meeting (at: Dec 21 2019)
3. [T][] business submission
-------------------------------------
```

### Feature 11: Edit 

#### Usage:
Edits the task description or date.
```
update <task index>  <description to be updated> 
OR
update <task index> /<event or deadline date to be updated>
```
####Expected outcome

Bob will find and edit the task description/date of the task with the pecified task index in the list 
```
-------------------------------------
okay! task updated!
initital: [D][] business proposal (by: Dec 12 2019)

modified: [D][] business subscription (by Dec 12, 2019)
-------------------------------------
```

### Feature 12: End

#### Usage:
`bye` - To end the programmme

####Expected outcome

Bob will close and stop the entire programnme.
