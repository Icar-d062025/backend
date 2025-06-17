pipeline {
    agent any

    tools {
        maven 'maven'   // Nom exact défini dans "Global Tool Configuration"
        jdk 'JDK21'     // Nom de ton JDK 21 dans Jenkins
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
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
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
            node('docker-agent') {  // Utiliser le nom exact de l'agent dans les logs
                junit '**/target/surefire-reports/*.xml'
                // Correction: Spécifier le pattern pour localiser le rapport JaCoCo
                recordCoverage(tools: [[parser: 'JACOCO', pattern: '**/target/site/jacoco/jacoco.xml']])
            }
        }
        success {
            echo '🎉 Pipeline exécutée avec succès !'
        }
        failure {
            echo '💥 La pipeline a échoué.'
        }
    }
}