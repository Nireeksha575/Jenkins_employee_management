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
                echo 'Testing the application.. SUCCESS'
                echo 'SUCCESS...'
            }
        }
    }

    post {
        failure {
            script {
                if (env.CHANGE_ID) {
                    pullRequest.comment("❌ Build failed for commit ${env.GIT_COMMIT}.\n\n[View Jenkins logs](${env.BUILD_URL})")
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