package Tests;

import input.A;
import input.B;
import input.C;
import input.D;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * java -cp input.jar:/home/rfellin/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar junit.textui.TestRunner Tests.MyTests
 */
public class MyTests extends TestCase{
	
	

		public void testApp()
	    {
			A testerA = new A(); 
	        testerA.main(new String[] {"0"});
	        
	        B testerB = new B(); 
	        testerB.f("ciao");
	       
	        C testerC = new C();
	        testerC.f("ciao");
	        
	        D testerD = new D(); 
	        testerD.main(new String[] {"0"});
	    }
    

}
