pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install package -DskipTests -Pprod'
      }
    }

    stage('Ansible CI') {
      steps {
        sh 'sudo ansible-playbook -i /home/odix/Devops/ansible/hosts /home/odix/Devops/ansible/opportunites-MS/opportunites-MS-playbook-ci.yml;'
      }
    }

    stage('Ansible CD') {
      steps {
        sh 'sudo ansible-playbook -i /home/odix/Devops/ansible/hosts /home/odix/Devops/ansible/opportunites-MS/opportunites-MS-playbook-cd.yml;'
      }
    }

  }
}