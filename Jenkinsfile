pipeline {
    agent any
    
    stages {
        stage("Git_Checkout") {
            steps {
                git branch: 'main', url: 'https://github.com/ManaasGupta/jenkins_project'
            }
        }
        stage("Build") {
            steps {
                // Execute Maven build
                bat './mvnw clean package'
            }
        }
        stage("Archive_JARs") {
            steps {
                // Archive JAR files
                archiveArtifacts '**/target/*.jar'
            }
        }
        stage("JUnit_Results") {
            steps {
                // Publish JUnit test results
                junit testResults: '**/target/surefire-reports/TEST*.xml'
            }
        }
        stage("Jacoco_Results") {
            steps {
                // Generate Jacoco code coverage report
                jacoco(execPattern: '**/target/**.exec')
                // Replace '**/target/**.exec' with the correct location of Jacoco execution files if needed
            }
        }
    }
}
