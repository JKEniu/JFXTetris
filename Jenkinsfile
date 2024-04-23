pipeline {
    agent any

    stages {
        stage("Build docker image"){
            steps{
                script {
                    sh """
                        docker build -t Tetris -f DockerfileBuild . > logs_${env.BUILD_NUMBER}.log 2>&1
                    """
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: "logs_${env.BUILD_NUMBER}.log", fingerprint: true
                }
            }
        }
        stage("Test"){
            steps{
                script {
                    sh """
                        docker build -t test -f DockerfileTest . > logs_${env.BUILD_NUMBER}.log 2>&1
                    """
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: "logs_${env.BUILD_NUMBER}.log", fingerprint: true
                }
            }
        }
    }
}
