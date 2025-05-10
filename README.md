💪 FitTracker – Full Stack Fitness App

FitTracker is a powerful, real-time fitness tracking web application built with Spring Boot, React, and PostgreSQL, deployed on AWS EC2. It offers tailored workouts, nutrition tracking, and dynamic progress monitoring for both men and women, including daily classes and coach/mentor management.

🌐 Live Demo

🚀 Your EC2 Public URL or Domain

🧱 Tech Stack

🔧 Backend
Java + Spring Boot

Spring Security – User authentication/authorization

JWT – Token-based login system

PostgreSQL – Relational database

RESTful APIs

🎨 Frontend

React.js (with React Router)

Dynamic UI with:

Real-time timers

Animated progress bars

Calories burned counters

Daily/weekly goal tracking

Music player and rotating background images

☁️ Deployment

Hosted on AWS EC2

Backend and frontend served via Spring Boot's static folder

🔐 Features
✅ Authentication
Register/Login using Spring Security

Role-based access control (Admin/User)

🏋️‍♀️ Girls’ Gym & 🏋️ Boys’ Gym

Exercise categories (e.g., Yoga, Zumba, Weight Lifting, CrossFit)

Each category includes:

Full exercise list

Benefits, calories, duration

Realtime timer and goal tracking

Confetti celebration on goal completion

📆 Daily Classes & Coaching
Schedule-based exercise sessions

Track completion via checkboxes

Progress saved per user

📊 Progress & Goals
Daily goals with completion status

Weekly performance summary

Nutrition completion tracking

🎵 Bonus UI Features
Music player (track1.mp3)

Rotating background images

Neon-style buttons

⚙️ Installation & Setup
Clone the repository:

bash
Copy
Edit
git clone https://github.com/yourusername/FitTracker.git
cd FitTracker
Backend (Spring Boot):

Open in Spring Tool Suite (STS) or IntelliJ

Configure application.properties for PostgreSQL:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/fittracker
spring.datasource.username=your_username
spring.datasource.password=your_password
Run the application (FitTrackerApplication.java)

Frontend (React):

Build your React app:

bash
Copy
Edit
npm install
npm run build
Copy the build/ files into src/main/resources/static/ of Spring Boot

Access the app:

Visit http://<your-ec2-public-ip>:8080 in browser

🚀 Deployment on EC2
Set up a PostgreSQL instance (local or on RDS)

Launch a Linux EC2 instance

Install Java, PostgreSQL client, and copy Spring Boot .jar

Set up Nginx (optional) for reverse proxy

Use systemd or a script to run the Spring Boot app in background

📁 Directory Structure

swift
Copy
Edit
backend/
├── src/main/java/com/fittracker
│   ├── controller/
│   ├── config/
│   ├── model/
│   └── service/
├── static/
│   ├── index.html
│   ├── images1/
│   ├── music/
│   ├── static/css, js/
│   └── manifest, favicon, logo

📸 Screenshots

![Screenshot (640)](https://github.com/user-attachments/assets/96edee53-81ef-4b03-bfb3-62e589704260)
![Screenshot (641)](https://github.com/user-attachments/assets/0868de2b-9ac9-46ff-a7db-21cb4e65b15c)
![Screenshot (642)](https://github.com/user-attachments/assets/fb5c1e5b-40ff-4787-9c75-9a028d9e2918)
![Screenshot (644)](https://github.com/user-attachments/assets/45752126-c0e4-48bc-948f-02f5d043431a)
![Screenshot (646)](https://github.com/user-attachments/assets/29517ab5-3033-43ba-84c8-f7c304096955)






🙋‍♂️ Author
Veeru
📧 Email: veerupenudhota6009@gmail.com

📝 License
This project is licensed under the MIT License.
