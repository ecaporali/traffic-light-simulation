###### Project: Traffic Light Simulation <br/> Author: Enrico Caporali <br/> Date Submitted: 3 October, 2017

# Traffic Light Simulation

## Application Design
The application consists of four main packages which depicts application design as well:

- controller: the TrafficLightController is the core class of the application. 
              It accepts the SimulationConfig and it runs the simulation; it also contains the observable class needed to notify
              the observers lights at any given time as per sequence GREEN->YELLOW->RED. In addition, The TrafficLightsObservable builds the
              output from each pair of Logbooks.
- model: there are four classes in the model package each one of them with different responsibility.
         For example, the Logbook is used to register all the lights changes that occur during its life time.
         The TrafficLights wrapper implements the Observer interface and reacts to changes by updating each respective TrafficLight.
- config: the SimulationConfig class is responsible to initiate the domain objects to run this particular simulation which consists of 
          four traffic lights in an intersection where each pair location is: (N, S) and (E, W).
- utils: the AssertUtils class is responsible to deal with null references and empty collections 

## Assumptions
The main assumptions taken while developing this application are as follow:

- Every lights switch color simultaneously so there are not cases where all the traffic lights have RED light.
- The output is built around locations and it always shows pairs of lights ({N, S} is a pair) and not each individual traffic light.
- The output does not include time for when each light changes
- At the beginning, the state of the traffic light pairs is: (N,S): Green and (E,W): Red

## Development
- The application is written in Java 8 and it leverages its functional approach. No additional external libraries are used throughout the implementation 
  of this application. The chosen build and dependency management tool is Gradle.
- Testing is done by using two standard libraries such as jUnit and Mockito.

### How to build the project and run unit tests
```
./gradlew clean build
```

### How to run the application
```
./gradlew run
```
