package CallGraph.CallGraph;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class CallGraphClassVisitor extends ClassVisitor{
	
	String classNameStartingNode;
	String classNameArrivalNode;
	String methodNameStartingNode;
	String methodNameArrivalNode;
	
    public CallGraphClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    
    public CallGraphClassVisitor(int api) {
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
    	
        //System.out.println("\n- "+name+"-"+desc+"-"+signature);
        
        MethodVisitor oriMv= new CallGraphMethodVisitor(this, api);
       
        return oriMv;
        
    }
	
}