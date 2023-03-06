pipeline {
    agent any

    stages {
        stage('Building and creating .jar file') {
            steps {
                echo 'Building'
                // Checking mvn version
                sh 'mvn -version'

                // Compiling and generating the .jar file
                sh 'mvn clean package'
            }
        }
        stage('Creating docker image and running') {
            steps {
                // Cleans the image repository of docker
                sh 'sudo docker image prune -f'

                //Builds the image of our docker
                sh 'sudo docker build -t scifiler/api:latest .'
            }
        }

        stage('Deploying into docker container') {
            steps {
                //Stop all running containers
                sh 'sudo docker rm $(sudo docker ps -aq)'

                // Run image into container
                sh 'sudo docker run -e url=$url -e secret=$secret -d -p 80:5050 -t scifiler/api:latest'
            }
        }
    }
}