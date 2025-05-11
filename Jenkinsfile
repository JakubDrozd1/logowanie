pipeline {
    agent any

    tools {
        maven 'Maven_3.8.1'
        sonarQubeScanner 'SonarScanner' // Ustaw zgodnie z nazwą z Global Tool Configuration
    }

    environment {
        SONAR_TOKEN = credentials('sonarqube-token') // ID tokena z Jenkins Credentials
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
                withSonarQubeEnv('SonarQube') {
                    bat '''
                        sonar-scanner ^
                        -Dsonar.projectKey=logowanie ^
                        -Dsonar.sources=src ^
                        -Dsonar.java.binaries=target/classes ^
                        -Dsonar.login=%SONAR_TOKEN%
                    '''
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
