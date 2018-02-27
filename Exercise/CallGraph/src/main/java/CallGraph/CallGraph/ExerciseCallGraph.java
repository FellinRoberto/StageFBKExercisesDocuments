package CallGraph.CallGraph;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class ExerciseCallGraph {

    public static void main(String[] args) throws Exception{

        File file=new File("../input.jar");
       
        try {
            JarFile jar = new JarFile(file);

            for (ZipEntry entry : Collections.list(jar.entries())) {
                if (!entry.getName().matches("(.+).(class)")) {
                    continue;
                }
                System.out.println("\n- " + entry);
                ClassReader classReader = new ClassReader(jar.getInputStream(entry));
                classReader.accept(new CallGraphClassVisitor(Opcodes.ASM5), 0);
            }
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


/*

-due progetti distinti: 1 per trasformatore e uno per trasmorare
-aggiungere javadoc di asm

*/