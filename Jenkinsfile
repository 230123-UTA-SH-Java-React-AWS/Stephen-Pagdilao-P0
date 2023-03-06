pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building'
                // Checking mvn version
                sh 'mvn -version'

                // Compiling and generating the .jar file
                sh 'mvn clean package'

                // Cleans the image repository of docker
                sh 'docker prune image -f'
                //Builds the image of our docker
                sh 'docker build -t scifiler/api:latest .'
            }
        }
    }
}