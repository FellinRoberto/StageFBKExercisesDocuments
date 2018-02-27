package callGraph;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
	
	String classNameStartingNode;
	String methodNameStartingNode;


	public static byte[] transform(byte[] b) {
	    final ClassReader classReader = new ClassReader(b);
	    final ClassWriter cw = new ClassWriter(classReader,
	    ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS);
	    classReader.accept(new MyClassVisitor(cw), ClassReader.EXPAND_FRAMES);
	    return cw.toByteArray();
	 }
	
	public MyClassVisitor(ClassVisitor cv) {
	    super(Opcodes.ASM5, cv);
	  }
	
	public MyClassVisitor(int api) {
	    super(api);
	  }
	
	
	@Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		//System.out.println(name);
		classNameStartingNode=name;
		super.visit(version, access, name, signature, superName, interfaces);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
    	methodNameStartingNode=name;
    	MethodVisitor v=super.visitMethod(access, name, desc, signature, exceptions);
        v= new MyMethodVisitor(api, v, classNameStartingNode, methodNameStartingNode);
        return v;
    }
    
    
}