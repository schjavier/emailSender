pipeline{
    agent {
        label 'emailSenderBuilder'

        }

    environment{
        CODE_BASE_DIR = "/home/apps/emailSender"
        DOCKER_COMPOSE_STAGING_DIR = "/home/javier/apps/staging/"
        DOCKER_COMPOSE_STAGING_FILE = "/home/javier/apps/staging/docker-compose-staging.yml"
        DOCKER_COMPOSE_PROD_DIR = "/home/javier/apps/production/"
        DOCKER_COMPOSE_PROD_FILE = "/home/javier/apps/production/docker-compose-prod.yml"
        STAGING_URL = "https://staging-emailsender.lotorojo.com.ar"
        PROD_URL = "https://emailsender.lotorojo.com.ar"
    }
    stages {

        stage('Checkout'){
            steps {
                cleanWs()

                sh 'git clone https://github.com/schjavier/emailSender.git'
                dir(CODE_BASE_DIR){
                    sh 'git checkout main'
                }

//                 git branch: 'main',
//                 url: 'https://github.com/schjavier/emailSender.git',
//                 poll: false
            }
        }

        stage('Build & Test'){
            steps {
                dir(CODE_BASE_DIR){
                    sh 'mvn clean package -DskipTests=false'
                }
            }
            post {
                success{
                    archiveArtifacts artifacts: 'emailSender/target/*.jar', onlyIfSuccessful: true
                }
            }
        }

        stage('Deploy to Staging'){
            steps{
                script{
                    echo "Desplegando en Staging (${STAGING_URL})..."
                    dir("${CODE_BASE_DIR}"){
                        sh """
                            docker compose -f ${DOCKER_COMPOSE_STAGING_FILE} down --remove-orphans
                            docker compose -f ${DOCKER_COMPOSE_STAGING_FILE} build --no-cache
                            docker compose -f ${DOCKER_COMPOSE_STAGING_FILE} up -d
                         """
                    }
                }
            }
        }

        stage('Approve Production'){
            steps {
                timeout(time:1, unit: 'HOURS'){
                    input(
                        message: "Staging OK. Desplegamos en Producción? (${PROD_URL})",
                        ok: "Deploy to Prod"
                    )
                }
            }
        }
        stage('Deploy to Production') {
            steps {
                script {
                    echo "Desplegando en Producción (${PROD_URL})"

                    dir("${CODE_BASE_DIR}"){
                        sh """
                            docker compose -f ${DOCKER_COMPOSE_PROD_FILE} down --remove-orphans
                            docker compose -f ${DOCKER_COMPOSE_PROD_FILE} build --no-cache
                            docker compose -f ${DOCKER_COMPOSE_PROD_FILE} up -d
                           """
                    }
                }
            }
        }
    }
    post {
        always {
            emailext (
                subject: "[Jenkins] ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                body: """
                    Estado: ${currentBuild.currentResult}
                    Job: ${env.JOB_NAME}
                    Build: ${env.BUILD_NUMBER}
                    URL Staging: <a href="${env.STAGING_URL}">${env.STAGING_URL}</a>
                    URL Producción: <a href="${env.PROD_URL}">${env.PROD_URL}</a>
                """,
                to: 'schjavier@gmail.com',
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}