pipeline{
    agent any
    environment {
     NEW_VERSION = '1.3.0'
    }
    stages{
        stage("build"){
            steps {
              echo 'Building the application....'
              echo "Building version ${NEW_VERSION}"
              bat 'mvn clean package -DskipTests=false'
            }
        }
         stage("test") {
             steps {
                 echo 'Testing the application..'
             }
         }
    }
}