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
                sh 'mvn clean verify'
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

        stage('Create Pull Request') {
            when {
                expression {
                    return env.BRANCH_NAME != 'main' && currentBuild.resultIsBetterOrEqualTo('SUCCESS')
                }
            }
            steps {
                echo 'Création automatique d\'une Pull Request vers main...'
                withCredentials([usernamePassword(credentialsId: 'b440a0dc-537d-489f-a303-d00dad66fbba',
                                                   usernameVariable: 'GITHUB_USER',
                                                   passwordVariable: 'GITHUB_PASS')]) {
                    sh '''
                        PR_RESPONSE=$(curl -s -X POST \
                        -u "${GITHUB_USER}:${GITHUB_PASS}" \
                        -H "Accept: application/vnd.github.v3+json" \
                        https://api.github.com/repos/d022025filsrouge/backend/pulls \
                        -d '{
                            "title": "Merge automatique de la branche '"${BRANCH_NAME}"' vers main",
                            "body": "Cette PR a été créée automatiquement par Jenkins après validation des tests et de la Quality Gate SonarQube.",
                            "head": "'"${BRANCH_NAME}"'",
                            "base": "main"
                        }')

                        PR_URL=$(echo $PR_RESPONSE | grep -o '"html_url": "[^"]*"' | grep -o 'https://[^"]*' | head -1)
                        if [ -n "$PR_URL" ]; then
                            echo "Pull Request créée avec succès: $PR_URL"
                        else
                            echo "Erreur lors de la création de la PR: $PR_RESPONSE"
                        fi
                    '''
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