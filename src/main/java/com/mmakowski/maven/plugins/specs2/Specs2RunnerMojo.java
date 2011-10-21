package com.mmakowski.maven.plugins.specs2;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Executes specs2 runner for all specifications in the current project. A thing
 * wrapper for {@link Specs2Runner}, which is implemented in Scala.
 * 
 * @author Maciek Makowski
 * @requiresDependencyResolution test
 * @goal run-specs
 * @phase verify
 * @since 1.0.0
 */
public class Specs2RunnerMojo extends AbstractMojo {
    /** @parameter default-value="${project}" */
    private MavenProject mavenProject;
    /** @parameter default-value="${project.build.testOutputDirectory}" */
    private File testClassesDirectory;
    /** @parameter default-value="${project.build.outputDirectory}" */
    private File classesDirectory;
    /** @parameter expression="${run-specs.suffix}" default-value="Spec" */
    private String suffix;
    /** @parameter expression="${skipTests}" default-value=false */
    private Boolean skipTests;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!skipTests) {
            if (!(new Specs2Runner().runSpecs(getLog(), mavenProject, classesDirectory, testClassesDirectory, suffix).booleanValue()))
                throw new MojoFailureException("there have been errors/failures");
        } else {
            System.out.println("SKIPPING SPECS"); // eh, should probably be using a logger
        }
    }
}
