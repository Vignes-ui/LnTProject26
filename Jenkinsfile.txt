pipeline {

    agent any

    tools {
        jdk 'JAVA21'
        maven 'Maven-3.9.16'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Vignes-ui/LnTProject26.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn verify sonar:sonar -Dsonar.projectKey=LnTProject'
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t lntproject:latest .'
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                    docker rm -f lntproject 2>NUL
                    docker run -d --name lntproject -p 8085:8080 lntproject:latest
                '''
            }
        }
    }
}