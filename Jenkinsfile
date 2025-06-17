pipeline {
    agent any

    tools {
        maven 'maven'   // Nom de ta config Maven dans Jenkins
        jdk 'JDK21'     // Nom de ta config JDK dans Jenkins
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
                    withCredentials([string(credentialsId: '152c5835-37c9-429e-b089-5d05551fbd03', variable: 'SONAR_TOKEN')]) {
                        sh 'mvn clean verify sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                    }
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
