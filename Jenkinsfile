pipeline {
    agent any

    tools {
        maven 'Maven_3.8.1'
    }

    environment {
        SONAR_TOKEN = credentials('sonar')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/JakubDrozd1/logowanie.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    bat """
                        mvn sonar:sonar ^
                        -Dsonar.projectKey=logowanie ^
                        -Dsonar.host.url=%SONAR_HOST_URL% ^
                        -Dsonar.login=%SONAR_TOKEN%
                    """
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
