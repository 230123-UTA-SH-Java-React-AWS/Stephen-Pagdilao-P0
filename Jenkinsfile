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
                sh 'docker build -t scifiler/api:latest .'
            }
        }
    }
}