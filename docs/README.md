# User Guide
**Neo** is a _GUI based application_ for managing all your tasks!

## Index
- [GUI](#GUI)
- [Getting Started](#GettingStarted)
- [Features](#Features)
- [Command summary](#CommandSummary)

##GUI
<img src= Ui.png>

## GettingStarted
All you need to do is,

1. download it from [here](https://github.com/richavm14/ip/releases/tag/A-Jar).
2. download neo.jar
3. clock it to add your tasks
4. let it manage your tasks for you 😉

## Features 

### Feature-List

The `features` are listed below:

- [Hi](#hi): Greeting from bot
- [Help](#help): Get list of command formats
- [List](#list): To get list of all tasks
- [Deadline](#deadline): Add deadline task
- [Event](#event): Add event task
- [Todo](#todo): Add todo task
- [Mark](#mark): Mark a task as completed
- [Un mark](#unmark): Remove mark task as uncompleted
- [Delete](#delete): Delete a task
- [Find](#find): Filter tasks with given keyword
- [low](#low): Set task priority to low
- [medium](#medium): Set task priority to medium
- [high](#high): Set task priority to high
- [Bye](#bye): Close task bot
### Feature-Description

### hi
Greet the bot!

### help
A list of all the commands and their formats will be displayed.

Format: `help`

Expected outcome:
```
Hello! I'm Neo Enter help for more details
```

### list
List of all tasks that have been stored by user will be displayed. 
Other details like date, priority and task done or not will be displayed too.
A list of all the commands and their formats will be displayed.

Format: `list`

Expected outcome:
```
1. [D][ ] read book  (by: Feb 02 2022) priority: high

2. [D][X] return book  (by: Apr 04 2022) priority: low

3. [E][ ] book fest (at: Mar 03 2022) 
```

### deadline
Add deadline task to list of tasks. It will be represented by D.

Format: `deadline <description> /by <yyyy-mm-dd>`

Example Usage: `deadline read book /by 2022-02-02`

Expected outcome:
```
[D][ ] read book  (by: Feb 02 2022) 
```


### event
Add event task to list of tasks. It will be represented by E.

Format: `event <description> /at <yyyy-mm-dd>`

Example Usage: `event book fest /at 2022-03-03`

Expected outcome:
```
[E][ ] book fest (at: Mar 03 2022) 
```

### todo
Add todo task to list of tasks. It will be represented by T.

Format: `todo <description>`

Example Usage: `todo sort library`

Expected outcome:
```
[T][ ] sort library
```

### mark
Use this command to mark a task as done. It will be represented by an X.

Format: `mark <task number>`

Example Usage: `mark 1`

Expected outcome:
```
[D][X] read book  (by: Feb 02 2022)
```


### unmark
Use this command to unmark a task as not done. It will be represented by a blank.

Format: `unmark <task number>`

Example Usage: `unmark 1`

Expected outcome:
```
[D][ ] read book  (by: Feb 02 2022)
```

### delete
delete task from task list.

Format: `delete <task number>`

Example Usage: `delete 3`

### find
Add todo task to list of tasks. It will be represented by T.

Format: `find <description>`

Example Usage: `find book`

Expected outcome:
```
1. [D][ ] read book  (by: Feb 02 2022) priority: high

2. [D][X] return book  (by: Apr 04 2022) priority: low
```

### low
Use this command to add low priority to a task. 

Format: `low <task number>`

Example Usage: `low 2`

Expected outcome:
```
[D][X] return book  (by: Apr 04 2022) priority: low
```

### medium
Use this command to add medium priority to a task.

Format: `medium <task number>`

Example Usage: `medium 2`

Expected outcome:
```
[D][X] return book  (by: Apr 04 2022) priority: medium
```

### high
Use this command to add high priority to a task.

Format: `high <task number>`

Example Usage: `high 2`

Expected outcome:
```
[D][X] return book  (by: Apr 04 2022) priority: high
```

## CommandSummary
| COMMAND  | FORMAT                                  |
|----------|-----------------------------------------|
| Help     | help                                    |
| Event    | event _description_ /at _yyyy-mm-dd_    |
| Deadline | deadline _description_ /by _yyyy-mm-dd_ |
| Todo     | todo _description_                      |
| Mark     | mark _task number_                      |
| Unmark   | unmark _task number_                    |
| Hi       | hi                                      |
| Find     | find _keyword_                          |
| Low      | priority _tag_name_                     |
| Medium   | medium _task number_                    |
| High     | high _task number_                      |
| Delete   | delete _task number_                    |
| List     | list                                    |
| Bye      | bye                                     |

