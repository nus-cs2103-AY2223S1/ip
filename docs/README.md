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
- Tag task ```tag```
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

<img width="399" alt="feature 2" src="https://user-images.githubusercontent.com/62635032/189613027-c1cfacab-d4dd-46e2-afb1-1b4dd5bd10f9.png">

### Feature 3: Add deadline
Adds a deadline task

Example usage:
```
deadline <description> /by <date in dd/MM/yyyy format>
Eg: deadline assignment 1 /by 20/10/2022
```

Expected outcome:
<img width="403" alt="feature 3" src="https://user-images.githubusercontent.com/62635032/189613211-b8c7c7ba-3680-486e-9231-a6dcc5ad9aaf.png">


### Feature 4: Adds event
Adds an event task

Example usage:
```
event <description> /at <date in dd/MM/yyyy format>
Eg: event Hackarthon /at 05/10/2022
```

Expected outcome:
<img width="397" alt="feature 4" src="https://user-images.githubusercontent.com/62635032/189613233-2fa3ee8a-0cef-46b2-bf4d-5150c3333eea.png">


### Feature 5: Marks task as done
Mark the specified task in the list as done.

Example usage:
```
mark <index of item starting from 0>
Eg: mark 2
```

Expected outcome:
<img width="399" alt="feature 5" src="https://user-images.githubusercontent.com/62635032/189613264-096c1ea4-e511-40bd-bc5f-b7b97d6844b6.png">



### Feature 6: List all tasks
List all the existing tasks.

Example usage:
```
list
```
Expected outcome:
<img width="385" alt="feature 6" src="https://user-images.githubusercontent.com/62635032/189613292-1e26004b-1837-4675-9411-b3264e33cd26.png">


### Feature 7: Delete task
Delete the specified task in the list using an index
Example usage:
```
delete <Given index of the item>
Eg: delete 0
```
Expected outcome:
<img width="379" alt="feature 7" src="https://user-images.githubusercontent.com/62635032/189613312-e9db8c6b-8987-4845-9805-94d8d561975b.png">


### Feature 8: Search keyword in list
Search tasks by a given keyword. The returned tasks would be all the tasks that contains the keyword.
Example usage:
```
find <description>
Eg: find Homework
```
Expected outcome:
<img width="385" alt="feature 8" src="https://user-images.githubusercontent.com/62635032/189613356-22b1523e-d90f-48de-a969-3b61b6870fe1.png">


### Feature 9: Tag task in list
Add tags to the task so that you can better categorise your task 
Example usage:
```
tag <taskName> <tagName>
Eg: tag homework1 hard
```
Expected outcome:
<img width="378" alt="feature 9_1" src="https://user-images.githubusercontent.com/62635032/189613392-80db54af-b38a-47fa-8557-a6dea8a24d16.png">
<img width="382" alt="feature 9_2" src="https://user-images.githubusercontent.com/62635032/189613411-1dd0f456-3f99-4611-ba14-6188000845c9.png">


### Feature 10: Exit application
Type bye to exit the application 
```
bye
```

Expected outcome:
<img width="389" alt="feature 10" src="https://user-images.githubusercontent.com/62635032/189613435-60288a17-03e9-4293-a39d-00a6d954b817.png">
