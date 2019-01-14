package io.ciera.maven;

import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import org.apache.maven.project.MavenProject;

import io.ciera.runtime.summit.application.IApplication;
import io.ciera.tool.CoreTool;

import java.io.File;

/**
 * Goal which runs the Core builder.
 */
@Mojo(name="core", defaultPhase=LifecyclePhase.GENERATE_SOURCES)
public class CoreMojo extends AbstractCieraMojo {

    @Parameter
    private String input;

    @Parameter
    private String output;

    @Parameter(defaultValue="${project.basedir}/src-gen")
    private String genDir;

    @Parameter(readonly=true, defaultValue="${project}")
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        String projectDir = getProject().getBasedir().getPath();
        String inFile = null == input ? "" : new File(projectDir).toURI().relativize(new File(input).toURI()).getPath();
        String outFile = null == output ? "" : new File(projectDir).toURI().relativize(new File(output).toURI()).getPath();
        String genDirPath = null == genDir ? "" : new File(projectDir).toURI().relativize(new File(genDir).toURI()).getPath();
        IApplication compiler = new CoreTool();
        compiler.setup(new String[]{"-i", inFile, "-o", outFile, "--cwd", projectDir, "--gendir", genDirPath, "--use-version", getProject().getVersion()}, new CieraMavenLogger(getLog()));
        compiler.initialize();
        compiler.start();
        copyCustomCode();
        addSrcGen();
        refreshFiles();
    }

    public MavenProject getProject() {
        return project;
    }

}
