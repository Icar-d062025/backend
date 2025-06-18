pipeline {
    agent any

    tools {
        maven 'maven'       // Nom exact défini dans "Global Tool Configuration"
        jdk 'JDK21'         // Nom du JDK 21 dans Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('Sonarqube')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN"
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    // Attend la QualityGate et interrompt le pipeline en cas d'échec
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo 'Le build, les tests et l\'analyse SonarQube se sont déroulés avec succès.'
        }
        failure {
            echo 'Une erreur est survenue durant le pipeline.'
        }
    }
}