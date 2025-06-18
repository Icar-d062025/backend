pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout du code source...'
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Compilation et tests unitaires...'
                sh 'mvn clean test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Vérification et configuration du projet SonarQube...'
                withSonarQubeEnv(installationName: 'SonarQube') {
                    script {
                        def sonarUrl = 'https://sonarqube.dedsecm.xyz'
                        def projectKey = 'vroomvroomcar'
                        def projectName = 'VroomVroomCar'

                        withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_TOKEN')]) {
                            // Vérifier si le projet existe déjà
                            def projectExists = sh(
                                script: "curl -s -o /dev/null -w '%{http_code}' -u ${SONAR_TOKEN}: ${sonarUrl}/api/components/show?component=${projectKey}",
                                returnStdout: true
                            ).trim() == '200'

                            // Si le projet n'existe pas, le créer
                            if (!projectExists) {
                                echo "Le projet ${projectName} n'existe pas dans SonarQube. Création en cours..."
                                sh "curl -s -X POST -u ${SONAR_TOKEN}: '${sonarUrl}/api/projects/create?name=${projectName}&project=${projectKey}'"
                                echo "Projet ${projectName} créé avec succès."
                            } else {
                                echo "Le projet ${projectName} existe déjà dans SonarQube."
                            }
                        }

                        // Exécuter l'analyse SonarQube
                        sh 'mvn sonar:sonar -Dsonar.host.url=https://sonarqube.dedsecm.xyz -Dsonar.projectKey=vroomvroomcar -Dsonar.projectName="VroomVroomCar" -Dsonar.java.source=21'
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Vérification de la Quality Gate...'
                waitForQualityGate(abortPipeline: true)
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
        always {
            echo 'Fin du pipeline Jenkins.'
        }
    }
}