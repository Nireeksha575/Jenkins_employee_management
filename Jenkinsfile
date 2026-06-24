pipeline{
    agent any

    stages{
        stage("build"){
            steps {
              echo 'Building the application....'
              bat 'mvn clean package -DskipTests=false'
            }
        }
         stage("test"){
                    steps {
                 echo 'Deploying the application....'
                  }
         }
    }
}