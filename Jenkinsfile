pipeline {
    agent any

    tools {
        maven 'Maven'  // Nom de l'installation Maven configurée dans Jenkins
        jdk 'JDK21'    // Nom de l'installation JDK configurée dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // Utilise la configuration SCM de Jenkins plutôt qu'une URL en dur
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {  // Nom de l'installation SonarQube configurée dans Jenkins
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'  // Publication des résultats de test
            recordCoverage(tools: [[parser: 'JACOCO']])  // Publication des rapports de couverture JaCoCo
        }
        success {
            echo 'Pipeline exécutée avec succès'
        }
        failure {
            echo 'La pipeline a échoué'
        }
    }
}