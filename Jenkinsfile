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
                echo 'Compilation, tests unitaires et génération rapports...'
                sh 'mvn clean verify'  // verify déclenche aussi les tests et JaCoCo
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('JaCoCo Report') {
            steps {
                echo 'Génération du rapport JaCoCo...'
                sh 'mvn jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            options {
                timeout(time: 5, unit: 'MINUTES')
            }
            steps {
                echo 'Analyse SonarQube en cours...'
                withSonarQubeEnv(installationName: 'SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                            -Dsonar.host.url=https://sonarqube.dedsecm.xyz \
                            -Dsonar.projectKey=vroomvroomcar \
                            -Dsonar.projectName="VroomVroomCar" \
                            -Dsonar.java.source=21 \
                            -Dproject.build.sourceEncoding=UTF-8 \
                            -Dfile.encoding=UTF-8 \
                            -Dsonar.exclusions=**/*Dto.java,**/config/**,**/*Application.java
                    '''
                }
            }
        }

        stage('Quality Gate') {
            options {
                timeout(time: 5, unit: 'MINUTES')
            }
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

