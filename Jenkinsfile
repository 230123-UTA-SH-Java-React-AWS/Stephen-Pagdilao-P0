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
                sh 'sudo docker build -t scifiler/api:latest .'
            }
        }

        stage('Deploying into docker container') {
            steps {

                //Stop all running containers
                sh 'sudo docker rm -f $(sudo docker ps -aq)'

                //Run latest version of image into my container
                sh 'sudo docker run -e url=$url -e secret=$secret -d -p 80:5050 -t scifiler/api:latest'
            }
        }
    }
}