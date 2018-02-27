package callGraph;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import com.google.common.io.ByteStreams;
import java.lang.Object;
import  java.lang.ClassLoader;

public class GenerateAndRun {
	
    public static class DynamicClassLoader extends ClassLoader {
        public Class<?> define(String className, byte[] bytecode) {
            return super.defineClass(className, bytecode, 0, bytecode.length);
        }
    };

    public static void main(String[] args) throws Exception{

    	File file=new File(args[0]);
        URL u = file.toURI().toURL();
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method m = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        m.setAccessible(true);
        m.invoke(urlClassLoader, new Object[]{u});
       
        
        
        try {
            JarFile jar = new JarFile(file);
            
            for (ZipEntry entry : Collections.list(jar.entries())) {
               	System.out.println(entry.getName());
            }
                        
            for (ZipEntry entry : Collections.list(jar.entries())) {
                if (!entry.getName().matches("(.+).(class)")) {
                    continue;
                }
                System.out.println("\n- " + entry.getName());                
                DynamicClassLoader loader = new DynamicClassLoader();                
                String s=entry.getName().replace(".class", "").replace("/", ".");
                
                byte[] newClass=MyClassVisitor.transform(ByteStreams.toByteArray(jar.getInputStream(entry)));                
                //byte[] newClass=ByteStreams.toByteArray(jar.getInputStream(entry));
             
                Class<?> c =  loader.define(s, newClass);
                Method [] methods= c.getMethods();
                
                for (Method method : methods) {
                	if(method.getName().equals("main")) {
                		method.invoke(null, (Object) new String[] {"0"});//;
                	}
                }
               
            }
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}