package vip.cared.devops.pipelinelib.scm

class Git {
    def pipeline
    def credential

    Git(pipeline, credential) {
        this.pipeline = pipeline
        this.credential = credential
    }

    def checkout(url, branch) {
        def ref = branch
        // Use shallow clone for git fetch
        def shallow_depth = 1

        // Don't fetch tags
        def no_tags = true

        def result = this.pipeline.checkout([
                $class           : 'GitSCM',
                branches         : [[name: ref]],
                userRemoteConfigs: [[credentialsId:  this.credential, url: url]],
                extensions       : [[$class: 'CloneOption', depth: shallow_depth, noTags: no_tags, shallow: true]]
        ])
        return result.GIT_COMMIT
    }
}
