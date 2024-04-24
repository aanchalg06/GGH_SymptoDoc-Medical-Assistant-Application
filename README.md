# Medical Assistant Application(SymptoDoc)

## Problem Statement
The evolution of AI has been nothing short of extraordinary. From humble beginnings, machines now learn, adapt, and even create with startling sophistication. Let's focus on using this power to address the challenges we face. Problem statement: Develop a healthcare recommendation system that analyzes user symptoms leveraging symptom data (using mock data), healthcare provider databases, and user ratings, recommends doctors with matching specialties and aligned schedules.

## Solution
To utilize the prompt provided by the user, the application's chat functionality is designed to capture user input effectively. When the user enters their symptoms into the chat interface, the application processes this prompt through a ViewModel, specifically the chatViewModel. Upon receiving the prompt, the ViewModel triggers an event, ChatUIEvent.SendPrompt, which encapsulates the prompt string. For example, the prompt might be structured like "For these symptoms [user's symptoms], give me specialists from this list: Cardiologist, Dermatologist, Endocrinologist, etc.". This event is then sent to the recommendation system, where the prompt is parsed and analyzed. The system identifies key symptom keywords and matches them with a database of specialists' expertise. Leveraging this data, the system generates recommendations for specialists best suited to address the user's symptoms. These recommendations are then presented back to the user through the application's interface, facilitating informed decision-making regarding healthcare provider selection. This seamless integration of user prompts and recommendation processing ensures a personalized and efficient healthcare recommendation experience for the user.

### Recommendation System
Utilizes symptom data provided by users, processes it through a recommendation engine, leveraging Gemini for additional insights. The system matches user symptoms with pre-existing data of specialists and doctors, sorting them based on ratings and expertise.

### Jetpack Compose UI Kits
Employs Jetpack Compose UI kits for modern and flexible user interface design, ensuring a visually appealing and intuitive user experience.

### Network Error Handling
Incorporates robust network error handling mechanisms to gracefully manage and recover from network failures, ensuring uninterrupted service.

### User Input Handling
Efficiently handles user inputs, validating and processing them securely to enhance user experience and application reliability.

### Guest Login Facilities
Facilitates users to log in as guests, allowing them to access basic functionalities without requiring full authentication.

### Doctor Listing by Specialist
Provides a comprehensive list of doctors categorized by their respective specialties, enabling users to find relevant healthcare providers easily.

### Email Facility
Includes email functionality for users to communicate with healthcare providers, facilitating seamless interaction and appointment scheduling.

### Calendar Date Picker for Appointment Booking
Integrates a calendar date picker feature to streamline the appointment booking process, offering users convenience and flexibility in scheduling appointments.

## Setup

1. Clone this repository.
2. Install dependencies.
3. Run the application on an Android device or emulator.

## Demo
[Link to demo video](demo_video_url)

This application presents a comprehensive solution to healthcare recommendation, leveraging AI and modern mobile development technologies to address the evolving needs of users in the medical domain.
