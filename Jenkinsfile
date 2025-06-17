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
