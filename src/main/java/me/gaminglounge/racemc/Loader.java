package me.gaminglounge.racemc;

import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;

public class Loader implements PluginLoader {

    @Override
    public void classloader(PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();

        // Add Maven Central repository
        resolver.addRepository(new RemoteRepository.Builder(
            "MavenCentralLoad",
            "default",
            "https://repo1.maven.org/maven2/")
            .build());

        // Add InfernalSuite repository
        resolver.addRepository(new RemoteRepository.Builder(
            "InfernalSuiteRepo",
            "default",
            "https://repo.infernalsuite.com/repository/maven-snapshots/")
            .build());

        // Add the file-loader dependency
        resolver.addDependency(new Dependency(
            new DefaultArtifact("com.infernalsuite.asp:file-loader:4.0.0-SNAPSHOT"),
            null));

        // Add the resolver to the classpath builder
        classpathBuilder.addLibrary(resolver);
    }
}