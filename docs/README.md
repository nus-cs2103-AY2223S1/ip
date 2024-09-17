# About Doris
Doris is a chat bot with an *attitude* and is not afraid to tell you. 
Doris can help you manage your task and get your s**t together!

<div align="center"><i> Meet Doris today! </i></div>

![image](Ui.png)
<br/><br/>

# Get started with Doris ️
1. Download `doris-v0.2.jar` from this [page](https://github.com/marcuslowhuiyu/ip/releases/tag/A-Release)
2. Navigate to the directory containing the `jar` file in terminal
3. Run `java -jar doris-v0.2.jar`
4. Tell Doris what you need to do
5. Say `bye` to Doris, and she'll remember what you need to do!

**Only close Doris using the bye command or else she might forget what you need to do!**
<br/><br/><br/>

# User Guide ️

### 1. `todo` 

Create a new 'todo'.

Tell Doris your command in the format:

`todo <task description>`

Example:
```
Input: 
    todo task
Output:
    Eh remember to do this ah:
    task
    You have 1 tasks leh better hurry up
```
---
### 2. `deadline` 
Create a new `deadline` task with a date to complete the task by.

Tell Doris your command in the format:

`deadline <task description> /by <yyyy-MM-dd hh:mm a>`

Example:
```
Input: 
    deadline finish assignment /by 2022-09-10 12:30 PM
Output: 
    Eh this one due soon stop wasting time go do now:
    finish assignment 
    You have 2 tasks leh better hurry up
```
---
### 3. `event` 
Create a new `event` with when it is happening.

Tell Doris your command in the format:

`event <event description> /at <yyyy-MM-dd hh:mm a>`

Example:
```
Input: 
    event meet Doris /at 2022-09-10 12:30 PM
Output: 
    Oi remember to attend this ah:
    meet Doris
    You have 3 tasks leh better hurry up
```
---
### 4. `list` 

View all your tasks.

Tell Doris your command in the format:

`list`

Example:
```
Input: 
    list
Output: 
    Eh faster go do these
    1. [T][ ] task
    2. [D][ ] finish assignment (by 2022-09-10 12:30 PM)
    3. [E][ ] meet Doris (at 2022-09-10 12:30 PM)
```
---
### 5. `mark` 
Mark a task as completed.

Tell Doris your command in the format:

`mark <task number>`

Example:
```
Input: 
    mark 2
Output: 
    Huh you sure you do finish assignment already or not?
    Okay la I trust you I trust you
```
### 6. `unmark` 
Mark a task as not completed.

Tell Doris your command in the format:

`unmark <task number>`

Example:
```
Input: 
    unmark 2
Output: 
    Eh don't laze leh go do go do finish assignment
```
---
### 7. `find` 
Search for specific tasks in your list by a keyword (partial match supported).

Tell Doris your command in the format:

`find <keyword>`

Example:

```
Input:
    find assign
Output:
    Eh I managed to find these tasks hurry go do
    1. [D][ ] finish assignment (by 2022-09-10 12:30 PM)
```
---
### 8. `delete`
Deletes a task from your list.

Tell Doris your command in the format:

`delete <task number>`

Example:

```
Input:
    delete 2
Output:
    Eh you don't want do this just say la:
    finish assignment
    You have 2 tasks leh better hurry up
```
---
### 9. `bye` 
Say goodbye to Doris and exit the chat bot.

Tell Doris your command in the format:

`bye`
