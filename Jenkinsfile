pipeline {
    agent any

    tools {
        maven 'maven'      // Nom défini dans "Global Tool Configuration"
        jdk 'JDK21'        // Nom exact de ton JDK dans Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('Sonarqube') // ID du credential sécurisé (type: Secret text)
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Unit Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // Le nom défini dans Jenkins > Manage Jenkins > Configure System
                    sh """
                        mvn verify sonar:sonar \
                        -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    try {
                        // Patiente jusqu'à 2 minutes que le Quality Gate soit terminé
                        timeout(time: 2, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
                        }
                    } catch (e) {
                        echo "⚠️ Quality Gate non récupéré à temps. Vérifie manuellement dans SonarQube si nécessaire."
                        error("Quality Gate timeout")
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
