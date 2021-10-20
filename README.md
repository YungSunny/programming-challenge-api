# Programming challenge

## How to start app

### First method
- Open project with favorite IDE and start application  

### Second method
1. In main folder run command `mvn clean install`  
2. Go to `target` directory and run command `java -jar game-0.0.1-SNAPSHOT.jar`


## Endpoints

Endpoint to get all tasks:
- host:port/challenge/getAll

Endpoint to submit task: 
- host:port/challenge/submitTask

Endpoint to get players who completed at least one challenge task
- host:port/player/getAll

## Payload for submit task

| Input      | Definition |
| --------- | -----:|
| solution  | Code that is prepared for task evaluation |
| name     |   Solved task name |
| playerName      |    Name of the player that completed the task |

Example
```
{
    "solution": "public class A { public static void main(String args[]) { int num=5,i=1;long factorial=1;while(i<=num){factorial*=i;i++;}System.out.println(factorial);}}",
    "name": "First",
    "playerName": "Sam"
}
```
