pipeline {
    agent any
    tools {
        maven 'Maven_3.8.1'
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
                    script {
                        def scannerHome = tool 'sonar'
                        bat "\"${scannerHome}\\sonar-scanner.bat\" -Dsonar.projectKey=logowanie -Dsonar.sources=src -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
