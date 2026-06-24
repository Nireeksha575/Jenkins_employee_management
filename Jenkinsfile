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
         stage("test"){
                    steps {
                 echo 'Testing the application....'
                  }
         }
         stage("deploy"){
                        steps {
                          echo 'Deploying the application....'
                          withCredentials([
                           usernamePassword(credentialsId: 'JenkinsAdminNireeksha',
                           usernameVariable: 'USER', passwordVariable: 'PASSWORD')
                          ]) {
                          echo "some script ${USER} ${PASSWORD}"

}
                           }
                  }
    }
}