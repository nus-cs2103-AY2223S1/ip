# Doomba Task Manager
🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧  
 **Doomba** hasn't reached it's _final form yet_!!!  
🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧 🚧  
What it can do
* add tasks
* delete tasks
* find tasks
* display list

How to use?
1. download latest release
2. run on your pc

An excerpt:
```java
public ArrayList<Task> searchFor(String keyword) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task t : this.store) {
            if (t.getDescription().contains(keyword)) {
                output.add(t);
            }
        }
        return output;
    }
```

Things to do:
- [x] Update PR description with GFMD elements 
- [ ] Week 4 iP task
- [ ] package new release

> uhhh, i have no idea what to do with blockquotes
>>me too :(
>> count me in

You can see more of my projects [here](github.com/nopehax)!  
`System.out.print("Hello, World!")`
