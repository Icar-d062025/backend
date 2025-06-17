pipeline {
    agent any

    tools {
        maven 'maven'   // Assure-toi que ce nom correspond à ta config Maven dans Jenkins
        jdk 'JDK21'     // Nom de l'installation JDK 21 configurée dans Jenkins
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
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            recordCoverage(tools: [[parser: 'JACOCO']])
        }
        success {
            echo 'Pipeline exécutée avec succès'
        }
        failure {
            echo 'La pipeline a échoué'
        }
    }
}
