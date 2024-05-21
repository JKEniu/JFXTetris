pipeline {
    agent any

    stages {
        stage('Get Project Version') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'git_tok', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh "chmod -R * 777"
                        sh "./gradlew release -Prelease.customUsername='$USERNAME' -Prelease.customPassword='$PASSWORD' -Prelease.disableChecks -Prelease.pushTagsOnly"
                        def gradleOutput = sh(script: './gradlew cV', returnStdout: true).trim()
                        def versionLine = gradleOutput.readLines().find { it.startsWith('Project version: ') }
                        def projectVersion = versionLine - 'Project version: '
                        env.PROJECT_VERSION = projectVersion.trim()
                    }
                }
            }
        }    
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
                    sh "docker tag lab6 jkeniu/tetris:$PROJECT_VERSION"
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
                    sh "docker push jkeniu/tetris:$PROJECT_VERSION"
                    }
                }
            }
    }
}
