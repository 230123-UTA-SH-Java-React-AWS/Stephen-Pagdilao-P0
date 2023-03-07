pipeline {
    agent any

    stages {

        stage('Building and create .jar file'){
            steps {
                echo 'Building the jar file'

                // Checks the latest version of maven
                sh 'mvn -version'

                // Builds and create our .jar file
                sh 'mvn clean package'
            }
        }

        stage('Creating docker image') {
            steps {
                // Removes all extra docker image
                sh 'sudo docker image prune -f'

                // Builds the image of our application
                sh 'sudo docker build -t scifiler/api:latest'
            }
        }
    }
}