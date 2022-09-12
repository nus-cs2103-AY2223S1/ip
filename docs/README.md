# User Guide
Duke is a desktop application which is primarily used for Graphical user interface(GUI). It is a task management system which allows users to 
easily add their tasks into the system

## Features

#### The three types of tasks you can input are ```todo```, ```deadline``` and ```event```.
- Load existing tasks when application launches, if any. 
- Add todo: ```todo```
- Add deadline: ```deadline```
- Add event: ```event```
- Mark task as done: ```done```
- List all tasks: ```list```
- Delete task: ```delete```
- Search keyword in list: ```find```
- Show statistics: ```stats```
- Exit the application: ```bye```

### Feature 1: Load existing tasks
The previous tasks that you have added would be saved automatically to 
your computer. If you quit the app and log back in, the tasks would still be there

### Feature 2: Add todo event 
Adds a todo task, storing it in the application 
Type ```todo <description>``` in the textbox and press enter or click the send button.

Example usage:
```
todo Do homework
```


### Feature 3: Add deadline
Adds a deadline task

Example usage:
```
deadline <description> /by <date in dd/MM/yyyy format>
Eg: deadline assignment 1 /by 20/10/2022
```

Expected outcome:


### Feature 4: Adds event
Adds an event task

Example usage:
```
event <description> /at <date in dd/MM/yyyy format>
Eg: event Hackarthon /at 05/10/2022
```

Expected outcome:


### Feature 5: Marks task as done
Mark the specified task in the list as done.

Example usage:
```
mark <index of item starting from 0>
Eg: mark 2
```

Expected outcome:


### Feature 6: List all tasks
List all the existing tasks.

Example usage:
```
list
```
Expected outcome:


### Feature 7: Delete task
Delete the specified task in the list using an index
Example usage:
```
delete <Given index of the item>
Eg: delete 0
```
Expected outcome:


### Feature 8: Search keyword in list
Search tasks by a given keyword. The returned tasks would be all the tasks that contains the keyword.
Example usage:
```
find <description>
Eg: find Homework
```
Expected outcome:


### Feature 8: Tag task in list
Add tags to the task so that you can better categorise your task 
Example usage:
```
tag <taskName> <tagName>
Eg: tag homework1 hard
```
Expected outcome:

### Feature 10: Exit application
Type bye to exit the application 
```
bye
```

Expected outcome:
