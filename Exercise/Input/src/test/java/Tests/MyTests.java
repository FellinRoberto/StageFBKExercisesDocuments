package Tests;

import input.A;
import input.B;
import input.C;
import input.D;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
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
