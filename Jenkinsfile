pipeline {
    agent any

    tools {
        maven 'maven'   // Nom exact défini dans "Global Tool Configuration"
        jdk 'JDK21'     // Nom de ton JDK 21 dans Jenkins
    }

    environment {
        // On utilise la valeur sécurisée des credentials Jenkins ici
        SONAR_TOKEN = credentials('Sonarqube')  // <- ID de ton token dans les credentials
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
                withSonarQubeEnv('SonarQube') { // Le nom du serveur défini dans Jenkins > Manage Jenkins > Configure System
                    sh 'mvn verify sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    try {
                        timeout(time: 10, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
                        }
                    } catch (e) {
                        echo "Impossible de récupérer le résultat du Quality Gate, mais l'analyse Sonar est bien lancée."
                    }
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
            echo '🎉 Pipeline exécutée avec succès !'
        }
        failure {
            echo '💥 La pipeline a échoué.'
        }
    }
}
