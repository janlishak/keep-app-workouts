
## Motivation behind the project
Everyone at least once in life, decided to start a healthier lifestyle by going out to the gym or doing exercise regularly. And probably everyone later found out that fulfilling such a goal is nothing easy. It takes a lot of time, commitment and willpower. On top of that it is hard to keep track of all the exercises and how to do them properly. 
**KeepAPP** is an android app that help users to keep up with their workouts by organizing exercise into workout plans. Workout plans can be created from templates and later personalized so that they fit the user needs. Workout plans can be built up from a predefined list of exercise each containing instructions customized counters or timers specific to the exercise. When the user start exercising the App helps the user count the exercises by showing timers and playing sounds. KeepApp also takes care about planning and reminding the user to exercise. 

## Video
[video demonstration](https://youtu.be/kxfAmkFgdNU "youtube")

## Terminolgy and Domain model
For easier reading and full understanding of  the the documentation, it is crucial to first set the terminology in the project. Those are the mainly used  terms in the project and their definition in the scope of this project
* **Exercise** is a single exercise movement with defined named and description how the exercise is performed. Each exercise should also have a video or picture assigned to it and belong to one exercise collection. 
* **Exercise Collection** is a set of exercises with common properties. An example of Exercise Collection can be *Gymnastic Exercises* witch can only be performed with gymnastic rings. 
* **Session** is also a set of exercises but can only contain *session exercises* which have addition properties than just exercises. Session has a name for example *upper body exercises*  and contain set of exercises that should be performed in one session. In the project sessions are sometimes also called workouts or workouts sessions.
* **Session Exercise** has all the properties as exercise but in addition also have the number of repetitions, sets and time to rest. This is a very common pattern in many exercise plans called **sets, reps and rest**. 
*  **Plan** is a set of sessions.
## Requirements
moscow prioritized requirements, requirements in *italics* are non-functional
#### Must have
✅ add own exercises with relevant information  
✅ mange exercises and store them in exercise library  
✅ order similar types of exercises into exercise collections  
✅ create and manage plans containing sessions  
✅ mange session exercises in the session  
✅ *use fragments and mvvm architecture*  

### Should have
✅ add video or image for an exercise  
✅ show details about the user profile  
🟨 show exercises in the session in way the user can check the completed  
✅ *store data remotely*  
✅ *support for multiple users*  

### Could have
🟧 reminding the user to exercise by sending notifications  
🟧 keeping track of completed exercises  
🟧 sharing workout plans with friends  
🟧 show graphs and statistics  
🟧 tracking persons weight and other relevant information  

### Won't have
⬛️ using sensor to determine activity  
⬛️ tracking activities such as cycling and running with GPS  

## Preview

![Recordit GIF](http://g.recordit.co/9WPfzKcmOS.gif)

---
*KeepAPP Workouts is a part of university course examination and therefore does not allow third party contributions.*
