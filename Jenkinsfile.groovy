pipeline {
    agent any

    stages {
        stage('Info') {
            steps {
                sh 'ls -la'
                sh 'git log -1 --oneline'
                sh 'second build'
            }
        }
        stage('Validate files') {
            steps {
                sh 'test -f Dockerfile && test -f missing.yaml && test -f service.yaml'
                echo 'Required files are present'
            }
        }
    }

    post {
        success { echo 'Pipeline succeeded' }
        failure { echo 'Pipeline failed' }
    }
}