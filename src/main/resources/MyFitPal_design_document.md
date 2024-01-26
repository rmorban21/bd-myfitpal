# **Team Motivated** Design Document

## ****MyFitPal Application**** Design

## 1. Problem Statement

****MyFitPal**** is designed to seamlessly integrate fitness into the busy lives of users, providing a customizable app
that adapts to individual availability and fitness goals, supported by a unique chat-bot acting as a virtual coach.

This design document outlines how MyFitPal tailors workout routines and motivational strategies to each user's specific
fitness objectives and time constraints, ensuring that even those with the most demanding schedules can effectively
pursue and achieve their health and fitness aspirations.


## 2. Top Questions to Resolve in Review

1.   What are the essential features that MyFitPal must have to provide value to users while remaining
     feasible for initial development?

2.   What are the most suitable frameworks and libraries within the Java ecosystem for developing the chat-bot feature
     in MyFitPal? How can these frameworks be leveraged to create an interactive and responsive chat-bot that provides
     motivational support and fitness advice?

3.  What approach should MyFitPal take to gather essential user data for personalization, such as fitness levels, goals,
    and availability?

## 2.1 Chatbot Integration Details

- **Chatbot Interaction**: The chatbot will use a script-based model due to current NLP limitations in Java/ developed
  on the frontend in javascript.
- **Script Management**: Scripts will be structured with decision tree format, allowing for keyword matching and
  predefined responses paths.
- **User Interaction**: The chatbot will guide users through registration, fitness assessments and other inquiries
  through scripted dialogue.
- **User Queries**: The chatbot will respond to user inputs based on keyword recognition, provide relevant information,
  or redirect to appropriate function of the app.
- **Chatbot Implementation**: The chatbot will leverage Java libraries such as AWS SDK and possibly other open source
  libraries that facilitate scripting and AWS service integration. This will allow integration with AWS Lambda and DynamoDB.

## 3. Use Cases


U1. As a MyFitPal customer, I want to create, save and view my personalized workout plan when I log into my fitness
dashboard, so I can easily follow my daily exercise routine.

U2. As a MyFitPal user, I want to easily track and view my workout progress after each session, so I can track my
fitness improvements and stay motivated.

U3. As a MyFitPal user, I want to receive daily motivational messages and workout tips from the chat-bot when I open
the app, so I can stay inspired and informed about my fitness journey.

U4. As a MyFitPal user, I want to receive informed answers and relevant resource links when I ask fitness-related
questions to the chatbot, so I can gain deeper insights and additional information to support my fitness journey

U5. As a MyFitPal user, I want to receive reminders and notifications about upcoming workouts, meals and water intake.

U6. As a MyFitPal user, I want to be able to make changes to my workout plan including adding/removing exercises,
sets/reps, training frequency and training time blocks.

U7. As a MyFitPal user, I want to be able to customize MyFitPal avatar or appearance to fit my preference


## 4. Project Scope

- Creating and updating SMART fitness goals through the chatbot ('with MyFitPal')
- Creating, retrieving and updating personalized workout plans through the chatbot
- Creating and updating a personalized workout schedule through the chatbot
- Customize MyFitPal (chatbot avatar)
- Receive motivating, tailored messages from the chatbot daily
- Ask chatbot fitness related questions
- Receive credible information and resources from the chatbot when prompted with fitness related questions
- Receive notifications and reminders about upcoming workouts from the chatbot
- Record and track workout progress based on fitness goals
- Receive personalized feedback about my workout plan as it relates to my fitness goals from the chatbot
- Creating new user profile with unique username and password

### 4.1. In Scope

- Creating new user profile with unique username and password
- Creating and updating SMART fitness goals through the chatbot ('with MyFitPal')
- Creating, retrieving and updating personalized workout plans through the chatbot
    - based on User profile: goal, experience level, availability
- Creating and updating a personalized workout schedule through the chatbot
- Record and track workout progress based on fitness goals


### 4.2. Out of Scope

- In future versions, given more time, added functionality could include:
    - Ask chatbot fitness related questions
    - More advanced AI/ML integration for more personalized chatbot conversations - current version is limited due to
      current NLP limitations in Java
    - Receive credible information and resources from the chatbot when prompted with fitness related questions
    - Customize MyFitPal (chatbot avatar)
    - Receive personalized feedback about my workout plan as it relates to my fitness goals from the chatbot
    - Receive motivating, tailored messages from the chatbot daily
    - Receive notifications and reminders about upcoming workouts from the chatbot


# 5. Proposed Architecture Overview

- **Chatbot-Enabled User Registration and Profile Setup**:
    - Function: Utilize the chatbot for user registration, profile creation, and initial setup.
    - Implementation: Develop chatbot scripts using AWS Lambda to guide users through the registration process. Store user
      profiles in DynamoDB.

- **Initial Fitness Assessment through Chatbot**:
    - Function: Conduct a chatbot-led initial fitness assessment to understand user goals, fitness levels, and availability.
    - Implementation: Design an interactive questionnaire within the chatbot. Process responses using Lambda functions and
      store assessment data in DynamoDB.

- **Personalized Workout Plan Generation**:
    - Function: Generate customized workout plans based on the initial assessment data collected via the chatbot.
    - Implementation: Use AWS Lambda to analyze assessment data and create tailored workout routines. Plans are accessible
      and manageable through the chatbot interface.

- **Workout Scheduling and Reminders**:
    - Function: Schedule workouts and send timely reminders through the chatbot.
    - Implementation: Implement scheduling logic in AWS Lambda. Use chatbot interactions to inform users about upcoming
      workouts and send reminders.

- **Workout Tracking and Progress Updates**:
    - Function: Allow users to log and track their workouts through the chatbot.
    - Implementation: Develop functionality in the chatbot to track workout sessions. Use Lambda functions to update and
      store workout data in DynamoDB.

MyFitPal Class Diagram
[View Class Diagram](../.idea/project_diagrams/MyFitPal_CD.puml)

MyFitPal Sequence Diagram
[View Sequence Diagram](../.idea/project_diagrams/MyFitPal_SD.puml)


# 5.1 How the architecture satisfies the requirements?
- Most functionality is done through the Chatbot including signing in with a unique username and password, creating,
  updating and viewing personalized fitness plans, and collecting user data for personalization.
- Users are able to schedule workout sessions and track workout progress.
- Users wil receive notifications of upcoming workouts.
- AWS Lambda and DynamoDB tables will be used to store User data including workout plans, fitness goals, and responses
  to fitness assessment.

# 5.2 Data Security

- **Data Encryption** - use AWS's built-in encryption services.
- **User Authentication and Authorization** - implement user authentication and authorization to prevent unauthorized
  access.

# 5.3 User Experience Design

- **Intuitive Chatbot Interaction**: Ensuring the chatbot is easy to interact with, even for users who are new to
  fitness apps.
- **Engaging and Responsive**: The app's interface will be visually appealing and responsive to different device sizes.
- **Personalization**: The app will reflect a high degree of personalization in both content and design, catering to
  user preferences
- **Customization(future designs)** - potential for user customizable MyFitPal avatar.

# 6. API

## 6.1. Public Models

- **UserModel**: Manage user profiles, including username, email, fitness goals, availability, and workout preferences. It
  integrates assessment data for streamlined profile management and interacts with Users table in DynamoDB, handling operations
  like creating new user profiles and updating existing ones.

- **WorkoutPlanModel**: Stores workout plans referencing exercises and maintains a schedule for users. It works in conjunction
  with the WorkoutPlans and WorkoutSessions tables, allowing users to create, retrieve, update and delete their workout plans.

- **ExerciseModel**: Manages a list of available exercises within MyFitPal. Facilitates the management and updating of
  exercises, interacting with the Exercises table in DynamoDB. Essential for maintaining scalability and an updated
  exercise database.

- **WorkoutSessionModel**: Tracks individual workout sessions, including exercises performed, duration, and other session-specific
  details. This model corresponds to the WorkoutSessions table, enabling users to log their workout sessions and view past records.

//- **NotificationModel**: Handles notifications for reminders, motivational messages, and progress updates. Interacts with
Notifications table to store and retrieve user specific notifications. (Out of scope)

## 6.2. Endpoints

**All endpoints interact with the chatbot**

- **User Registration Endpoint**
    - Method: POST /users/register
    - Data Required: Username, email, password.
    - Data Returned:
        - Success: User profile with userID.
        - Errors: **UserAlreadyExistsException** if username/email exists; **InvalidDataException** for invalid input.

    - **User Login Endpoint**
    - Method: POST /users/login
    - Data Required: Username, password.
    - Data Returned:
        - Success: Success message and user details.
        - Errors: **UserNotFoundException** for non-existing user; **InvalidCredentialsException** for wrong credentials.

User Registration and Login Sequence Diagram
[View Sequence Diagram](../.idea/project_diagrams/UserRegistration_UserLogin_SD.puml)

- **Create Workout Plan Endpoint**
    - Method: POST /workoutPlans
    - Data Required: User ID, workout schedule, list of exercises.
    - Data Returned:
        - Success: Workout plan with planID.
        - Errors: **UserNotFoundException** if user doesn't exist; **InvalidDataException** for invalid data.

- **Update Workout Plan Endpoint**
    - Method: PUT /workoutPlans/:planID
    - Data Required: Updated workout schedule, exercises list.
    - Data Returned:
        - Success: Updated workout plan.
        - Errors: **WorkoutPlanNotFoundException** if planID doesn't exist.

Create and Update WorkoutPlan Sequence Diagram
[View Sequence Diagram](../.idea/project_diagrams/Create_UpdateWorkoutPlan_SD.puml)

- **Track Workout Session Endpoint**
    - Method: POST /workoutSessions
    - Data Required: User ID, date, duration, exercises performed.
    - Data Returned:
        - Success: Details of the recorded session.
        - Errors: **UserNotFoundException** if user doesn't exist; **WorkoutPlanNotFoundException** if referenced plan doesn't exist.

Track WorkoutSession Sequence Diagram
[View Sequence Diagram](../.idea/project_diagrams/TrackWorkoutSession_SD.puml)

- **Get Workout Plan Endpoint**
    - Method: GET /workoutPlans/:planID
    - Data Required: Plan ID.
    - Data Returned:
        - Success: Workout plan details.
        - Errors: **WorkoutPlanNotFoundException** if planID doesn't exist.

//- **Send Notifications Endpoint** // Out of scope
//  - Method: POST /notifications/send
//  - Data Required: User ID, message content.
//  - Data Returned:
//    - Success: Confirmation of sent notification.
//    - Errors: **UserNotFoundException** if user doesn't exist.

Send Notifications Sequence Diagram
[View Sequence Diagram](../.idea/project_diagrams/SendNotifications_SD.puml)

# 7. Tables

- Users
    - Partition Key: userID (String) - can never be null
    - Attributes:
        - username (String)
        - password (String)
        - email (String)
        - fitnessGoals (Map)
        - availability (Map)
        - experienceLevel (String)
        - preferences (Map)
    - Functions:
        - Get user by userID
        - Update user details

- WorkoutPlans
    - Primary Key: planID (String)
    - Sort Key: userId (String)
    - Attributes:
        - PlanDetails(List) - high-level overview of the plan
    - Functions:
        - Get all workout plans for a user.
        - Get, update, or delete a specific workout plan.

- WorkoutSessions
    - Primary Key: sessionID (String)
    - Attributes:
        - planID (String) - link to WorkoutPlans table
        - Day (String) - e.g., "Day 1:Legs"
        - Exercises (List) - detailed exercise information for that session
    - Functions:
        - Retrieve sessions linked to a specific workout plan
        - Add a new session record

//- Notifications (Out of scope)
//  - Primary Key: notificationID (String)
//  - Sort Key: userID (String)
//    - Attributes:
//      - message (String)
//      - notificationTime (DateTime)
//    - Functions:
//      - Get notifications for a user
//      - Create a new notification

- FitnessAssessmentData
    - Partition Key: assessmentID (String)
    - Attributes:
        - userID (String)
        - responses (Map) - includes details about fitness goals, exp level and availability
    - Functions:
        - Get, update or delete assessment data for a user.

- ExerciseTypes
    - Partition Key: typeID (String)
    - Attributes:
        - name (String)
    - Functions:
        - Get at least one exercise type

- Exercises
    - Partition Key: exerciseID (String)
    - Attributes:
        - name (String)
        - typeID (String)
        - tutorialURL (String)
    - Functions:
        - Get exercises by type
        - Retrieve specific exercise details


# 8. Pages

[Front Page Mockup](https://wireframe.cc/wv2d6P)

[Chatbot Mockup](https://wireframe.cc/tU9rnu)
 