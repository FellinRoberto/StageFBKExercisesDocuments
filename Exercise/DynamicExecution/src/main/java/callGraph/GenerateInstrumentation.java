package callGraph;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import com.google.common.io.ByteStreams;
import  java.lang.ClassLoader;

public class GenerateInstrumentation {
	
    public static class DynamicClassLoader extends ClassLoader {
        public Class<?> define(String className, byte[] bytecode) {
            return super.defineClass(className, bytecode, 0, bytecode.length);
        }
    };

    public static void main(String[] args) throws Exception{

    	File file=new File(args[0]);
    	 FileOutputStream jarName = new FileOutputStream("../output.jar");
    	 JarOutputStream jarOut = new JarOutputStream(jarName);
        try {
            JarFile jar = new JarFile(file);
            
                        
            for (ZipEntry entry : Collections.list(jar.entries())) {
                if (!entry.getName().matches("(.+).(class)") || entry.getName().contains("Test")) {
                	byte[] otherFile =ByteStreams.toByteArray(jar.getInputStream(entry));
                	jarOut.putNextEntry(new ZipEntry(entry.getName()));
                    jarOut.write(otherFile);
                    jarOut.closeEntry();
                }else {
                System.out.println("\n- " + entry.getName());                
                byte[] newClass=MyClassVisitor.transform(ByteStreams.toByteArray(jar.getInputStream(entry)));                
                //byte[] newClass=ByteStreams.toByteArray(jar.getInputStream(entry));
           
               /* 
                WRITE ON FOLDER
                Path filePath = Paths.get("../Output/"+entry.getName());
                Files.createDirectories(filePath.getParent());
                Files.write(new File("../Output/"+entry.getName()).toPath(), newClass);
              */
                
                //WRITE ON JAR  how to execute: java -cp output.jar input.A 1
                jarOut.putNextEntry(new ZipEntry(entry.getName()));
                jarOut.write(newClass);
                jarOut.closeEntry();
                }
            }
            jarOut.close();
            jarName.close();
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}