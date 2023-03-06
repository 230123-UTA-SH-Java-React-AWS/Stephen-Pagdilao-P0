pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building'
                sh 'mvn -version'
                sh 'mvn clean package'

                // Cleans the image repository of docker
                sh 'sudo docker prune image -f'

                //Builds the image of our docker
                sh 'docker build -t scifiler/api:latest .'
            }
        }
    }
}