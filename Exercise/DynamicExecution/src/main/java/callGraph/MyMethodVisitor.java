package callGraph;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MyMethodVisitor extends MethodVisitor{
	
	
  private String callerClass;
private String callerMethod;
private MethodVisitor delegate;

MyMethodVisitor(int access, MethodVisitor delegate, String callerClass, String callerMethod) {
    super(Opcodes.ASM5, delegate);
    this.callerClass = callerClass;
    this.callerMethod = callerMethod;
    this.delegate = delegate;
  }
  
  @Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
	  Label l0 = new Label();
      delegate.visitLabel(l0);
      delegate.visitLineNumber(7, l0);
      delegate.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
      delegate.visitLdcInsn(String.format("%s.%s -> %s.%s",callerClass,callerMethod,owner,name));
      delegate.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println","(Ljava/lang/String;)V",false);
      delegate.visitMethodInsn(opcode, owner,  name, desc, itf);
  }
}
