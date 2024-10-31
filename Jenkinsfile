pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'playwright-test-image' // Name for the Docker image
        APP_URL = 'http://host.docker.internal:4200/' // Application running locally
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull the specific branch from GitHub
                git branch: 'main', url: 'https://github.com/YNPersonal/toolshop_playwright'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image from the Dockerfile in the repository
                    sh 'docker build -t ${DOCKER_IMAGE} .'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run a container from the built image and pass the APP_URL
                    sh '''
                    docker run --rm \
                    -e APP_URL=${APP_URL} \
                    ${DOCKER_IMAGE} npm run test
                    '''
                }
            }
        }
    }

    post {
        always {
            // Cleanup after the test run
            sh 'docker system prune -f'
        }
    }
}