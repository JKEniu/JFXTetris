pipeline {
    agent any

    stages {
        
        stage('Build docker image') {
            steps {
                script {
                    sh "docker build -t lab6 -f DockerfileBuild ."                    
                    }
                }
            }
        stage('Tag docker image') {
            steps {
                script {
                    sh "docker tag lab6 jkeniu/tetris:${env.BUILD_NUMBER}"
                    }
                }
            }
        stage('Login into DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker_pass', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh "docker login -u $USERNAME -p $PASSWORD"
                    }
                }
            }
        }

        stage('Push docker image') {
            steps {
                script {
                    sh "docker push jkeniu/tetris:${env.BUILD_NUMBER}"
                    }
                }
            }
    }
}
