package com.github.nuguya21.jobs

import com.github.nuguya21.jobs.api.Job

class JobManager {
    internal companion object {

        private val id: MutableSet<String> = mutableSetOf()
        private val job: MutableMap<String, Class<Job>> = mutableMapOf()

        fun register(job: Job) {
            if (!exists(job)) {
                this.id.plus(job.id)
                this.job[job.id] = job.javaClass
            }
        }

        fun unregister(job: Job) {
            if (exists(job)) {
                this.job.remove(job.id)
                this.job.remove(job.id)
            }
        }

        fun getJob(id: String): Job? {
            return this.job[id]?.newInstance()
        }

        fun getJobs(): Set<Class<Job>> {
            return this.job.values.toSet()
        }

        private fun exists(id: String): Boolean {
            return id in this.id
        }

        private fun exists(job: Job): Boolean {
            return exists(job.id)
        }
    }
}