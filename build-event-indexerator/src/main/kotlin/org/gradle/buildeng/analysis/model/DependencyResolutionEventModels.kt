package org.gradle.buildeng.analysis.model

import java.time.Duration
import java.time.Instant

/**
 * -- What versions of Dependency X are folks using?
 *
 * select version, count(version)
 * from dependency_resolutions
 * where rootProjectName = 'gradle'
 *  and resolutionType like 'ModuleComponentIdentity_1_%'
 *  and group = 'com.google.cloud'
 *  and module = 'google-cloud-core-http'
 *  and timestamp (in last 7 days)
 *
 * -- Did dependency resolution from Bintray get more reliable after Gradle 5.0? Did it get slower or faster?
 *
 * select
 */

data class DependencyResolution(
        val rootProjectName: String,
        val buildId: String,
        val buildTimestamp: Instant,
        val moduleDependencies: List<ModuleDependency>,
        val projectDependencies: List<ProjectDependency>,
        val unknownTypeDependencies: List<UnknownTypeDependency>,
        val failureIds: List<String>,
        val failures: String?
)

data class ModuleDependency(val group: String?, val module: String?, val version: String?)

data class ProjectDependency(val buildPath: String?, val projectPath: String)

data class UnknownTypeDependency(val className: String?, val displayName: String?)

data class Repository(val id: String, val name: String, val type: String, val properties: String?)

data class NetworkActivity(val id: String, val location: String, val contentLength: Int, val startTimestamp: Instant, val duration: Duration, val failureId: String?, val failure: String?)
