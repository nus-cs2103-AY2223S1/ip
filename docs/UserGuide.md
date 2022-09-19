#Naruto Chatbot

Part-time Hokage Wannabe full-time virtual assistant, Naruto is here to help you organise your day.
This is a text based task scheduler which helps streamline your day.
---
## Quick Start
1. Ensure you have a minimum of Java `11` installed on your device.
2. Download the latest `naruto.jar` from [here](https://github.com/Charles1026/ip/releases/tag/A-Release).
3. Move the file to your desired directory and double click it to open.
4. Tada, the app is now running, the GUI should look something like this:
![Naruto Chatbot Ui](./Ui.png)
---

## Commands

### Notes on Command
- Please input all Date and Time inputs in this format (The time section can be omitted if not required).
    >YYYY-MM-DD HH:SS
- Any part of the command in `[]` should be replaced with your own text.
- Commands should follow the format given as Naruto will not understand what you mean otherwise.
- Duplicate items cannot be added to Naruto and he will warn you if you make such an attempt.

### View all Commands: `help`
View all the commands in case you need help remembering.
```bash
help
``` 

### Adding a Todo Item: `todo`
Add an item you need to do into the task scheduler.

```bash
todo [Description]
``` 

### Adding a Deadline Item: `deadline`
Add an item you need to complete by a certain deadline to the task scheduler.
```bash
deadline [Description] /by [Deadline]
``` 

### Adding an Event Item: `event`
Add an item you need to attend to at a certain time to the task scheduler.
```bash
event [Description] /at [EventTime]
``` 

### List all Items: `list`
List all the items currently in the task scheduler by index.
```bash
list
``` 

### Complete an Item: `mark`
Mark an item as completed by index.
```bash
mark [index]
``` 

### Uncheck an Item: `unmark`
Mark an item as not completed by index.
```bash
unmark [index]
``` 

### Delete an Item: `delete`
Remove an item by index.
```bash
delete [index]
``` 

### Find an Item: `find`
Search for an item by name
```bash
find [Name]
``` 

### Close the Program: `bye`
Save the tasks in the scheduler and close the app.
```bash
bye
``` 
---
## Commands Summary
| Commands                       |
|--------------------------------|
| help                           |
| todo [Item]                    |
| deadline [Item] /by [Deadline] |
| event [Item] /by [EventTime]   |
| list                           |
| mark [Index]                   |
| unmark [Index]                 |
| delete [Index]                 |
| find [Name]                    |
| bye                            |