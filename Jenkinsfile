pipeline {
    agent {
        node {
            label 'node0'
        }
    }
    options {
        ansiColor('xterm')
    }
    tools {
        jdk 'java11'
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
    }
    post {
        always {
            junit testResults: '**/build/test-results/test/*.xml', allowEmptyResults: true
        }
    }
}
