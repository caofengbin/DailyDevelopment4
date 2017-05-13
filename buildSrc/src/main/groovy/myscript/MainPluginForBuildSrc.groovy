package myscript

import org.gradle.api.Plugin
import org.gradle.api.Project

public class MainPluginForBuildSrc implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create('pluginsrc', MyExtension)

        project.task('testPlugin')<<{
            println project.pluginsrc.message
        }
    }
}