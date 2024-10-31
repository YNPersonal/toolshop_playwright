// Ensure GITHUB_URL is set to a default value if not already provided
env.GITHUB_URL = env.GITHUB_URL ?: "https://github.com/YNPersonal/toolshop_playwright"

// Ensure GITHUB_CREDENTIALS is set to a default value if not already provided
env.GITHUB_CREDENTIALS = env.GITHUB_CREDENTIALS ?: "8b9784e2-1822-474a-9a0f-0fe06381b3b6"

checkout([
    $class: "GitSCM",
    branches: scm.branches,
    extensions: scm.extensions + [[$class: "LocalBranch"], [$class: "WipeWorkspace"]],
    userRemoteConfigs: [[credentialsId: env.GITHUB_CREDENTIALS, url: env.GITHUB_URL]],
    doGenerateSubmoduleConfigurations: false
])
//sh "git archive --format=tar ${env.GIT_BRANCH}:jenkins/Common_libs/ > Common_libs.tar"
//sh "git archive --format=tar ${env.GIT_BRANCH}:jenkins/Common_scripts/ > Common_scripts.tar"
sh "git checkout ${params.branch}"
//sh "rm -rf jenkins/Common_libs/ && mkdir -p jenkins/Common_libs/ && tar -xf Common_libs.tar -C jenkins/Common_libs/"
s//h "rm -rf jenkins/Common_scripts/ && mkdir -p jenkins/Common_scripts/ && tar -xf Common_scripts.tar -C jenkins/Common_scripts/"
