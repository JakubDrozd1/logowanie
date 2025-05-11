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
                git 'https://github.com/RafHon/logowanie.git'
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
    }
}
