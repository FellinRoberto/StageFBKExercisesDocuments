package CallGraph.CallGraph;

import org.objectweb.asm.MethodVisitor;

final class CallGraphMethodVisitor extends MethodVisitor {

	private final CallGraphClassVisitor callGraphClassVisitor;

	CallGraphMethodVisitor(CallGraphClassVisitor callGraphClassVisitor, int arg0) {
		super(arg0);
		this.callGraphClassVisitor = callGraphClassVisitor;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		this.callGraphClassVisitor.classNameArrivalNode=owner;
		this.callGraphClassVisitor.methodNameArrivalNode=name;
		System.out.println(this.callGraphClassVisitor.classNameStartingNode+"."+this.callGraphClassVisitor.methodNameStartingNode+"->"+this.callGraphClassVisitor.classNameArrivalNode+"."+this.callGraphClassVisitor.methodNameArrivalNode);
	}
}