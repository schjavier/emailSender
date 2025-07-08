pipeline{
    agent {
        label 'emailSenderBuilder'

        }

    environment{
        DOCKER_COMPOSE_STAGING = "/home/javier/apps/staging/docker-compose-staging.yml"
        DOCKER_COMPOSE_PROD = "/home/javier/apps/production/docker-compose-prod.yml"
        STAGING_URL = "https://staging-emailsender.lotorojo.com.ar"
        PROD_URL = "https://emailsender.lotorojo.com.ar"
    }
    stages {

        stage('Checkout'){
            steps {
                git branch: 'main',
                url: 'https://github.com/schjavier/emailSender.git',
                poll: false
            }
        }

        stage('Build & Test'){
            steps {
                sh 'mvn clean package -DskipTests=false'
            }
            post {
                success{
                    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
                }
            }
        }

        stage('Deploy to Staging'){
            steps{
                script{
                    echo "Desplegando en Staging (${STAGING_URL})..."
                    sh """
                        docker-compose -f ${DOCKER_COMPOSE_STAGING} down --remove-orphans
                        docker-compose -f ${DOCKER_COMPOSE_STAGING} up -d --build
                     """
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
                    sh """
                        docker-compose -f ${DOCKER_COMPOSE_PROD} down --remove-orphans
                        docker-compose -f ${DOCKER_COMPOSE_PROD} up -d --build
                       """
                }
            }
        }
    }
    post {
        always {
            emailext (
                subject: "[Jenkins] ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                body: """
                    <p>Estado: ${currentBuild.currentResult}</p>
                    <p>Job: ${env.JOB_NAME}</p>
                    <p>Build: ${env.BUILD_NUMBER}</p>
                    <p>URL Staging: <a href="${env.STAGING_URL}">${STAGING_URL}</a></p>
                    <p>URL Producción: <a href="${env.PROD_URL}">${PROD_URL}</a></p>
                """,
                to: 'schjavier@gmail.com',
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}