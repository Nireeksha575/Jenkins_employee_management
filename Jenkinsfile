pipeline {
    agent any
    environment {
        NEW_VERSION = '1.3.0'
    }
    stages {
        stage("build") {
            steps {
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

    post {
        failure {
            script {
                if (env.CHANGE_ID) {
                    withCredentials([usernamePassword(credentialsId: 'github-app-employee-mgmt', usernameVariable: 'GH_APP', passwordVariable: 'GH_TOKEN')]) {
                        bat """
                    curl -s -X POST ^
                      -H "Authorization: token %GH_TOKEN%" ^
                      -H "Accept: application/vnd.github+json" ^
                      https://api.github.com/repos/Nireeksha575/Jenkins_employee_management/issues/${env.CHANGE_ID}/comments ^
                      -d "{\\"body\\": \\"Build failed for commit ${env.GIT_COMMIT}. View logs: ${env.BUILD_URL}\\"}"
                    """
                    }
                }
            }
        }
        always {
            script {
                def conclusion = (currentBuild.currentResult == 'SUCCESS') ? 'SUCCESS' : 'FAILURE'
                publishChecks name: 'Jenkins Build',
                        summary: 'Build result',
                        conclusion: conclusion
            }
        }
    }
}