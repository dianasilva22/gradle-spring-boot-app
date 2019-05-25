
// For plantuml support
import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.apache.commons.io.FilenameUtils
// For gradle
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
// For File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import groovy.io.FileType

/**
 *
 */
class RenderPlantUmlTask extends DefaultTask {

    def Path assetsPathInput = project.projectDir.toPath().resolve('src/main/puml/')
    def Path assetsPathOutput = project.projectDir.toPath().resolve('build/docs/javadoc')

    RenderPlantUmlTask() {
        Path projectPath = project.projectDir.toPath()

        new File(assetsPathInput.toString()).eachFileRecurse( FileType.FILES,
                { pumlFile ->
                    if (pumlFile.name.endsWith('.puml')) {
                        inputs.file pumlFile

                        def outFile=getDestination(pumlFile, '.png')
                        outputs.file outFile
                    }
                }
        )
    }

    File getDestination(File puml, String extension) {
        // eg: puml = /workspaces/project/src/main/puml/application_start_image1.puml
        String baseName = FilenameUtils.getBaseName(puml.name)    // eg: application_start_image1
        String destName = baseName
        String basePath = FilenameUtils.getFullPath(puml.path)    // eg: /workspaces/project/src/main/puml/

        String assetsPathInputString=assetsPathInput.toString()

        String assetsPathOutputString=assetsPathOutput.toString()

        String relativePath=basePath.substring(assetsPathInputString.length())

        String newRelativePathString = relativePath + destName + extension

        String newAbsolutePathString = assetsPathOutputString+newRelativePathString

        Path destPathPng=Paths.get(newAbsolutePathString)

        destPathPng.toFile()
    }

    @TaskAction
    def render() {
        Path projectPath = project.projectDir.toPath()
        for (int i=0; i<inputs.files.size(); i=i+1) {
            File puml = inputs.files[i]
            File png=getDestination(puml, '.png')

            String relPumlPath = projectPath.relativize(puml.toPath()).toString()
            String pumlContent = new String(Files.readAllBytes(puml.toPath()), 'UTF-8')

            // Now, generate the file
            SourceStringReader reader = new SourceStringReader(pumlContent)

            // IMPORTANT:
            // It is necessary to create the directory if it does not exist yet!
            if (!png.exists())
            {
                def dir=FilenameUtils.getFullPath(png.path)
                def subdir = new File(dir)
                subdir.mkdirs()
            }

            reader.generateImage(new FileOutputStream(png), new FileFormatOption(FileFormat.PNG))
        }
    }
}
