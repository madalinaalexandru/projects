package tema1;

import static org.junit.Assert.*;
import org.junit.*;

public class Test {

	private static Polinom p1;
	private static Polinom p2;
	private static Operatii op;
	private static int nrTesteExecutate = 0;
	private static int nrTesteCuSucces = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		p1 = new Polinom("3x^4-5x^3+2x^2-x+1");
		p2 = new Polinom("x+1");
		op = new Operatii();
	}
		
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
			
		System.out.println("S-au executat " + nrTesteExecutate + " teste, din care "+ nrTesteCuSucces + " au avut succes!");
	}

	@Before
	public void setUp() throws Exception {
		nrTesteExecutate++;
	}
		
	@After
	public void tearDown() throws Exception {
	}
	
	@org.junit.Test
	public void testPoli() {
		Polinom p = new Polinom("13x-12x^2+3x^2+5x-5-2x+8-0");
		
		String rez = new String(p.toString());
		
		assertEquals(rez,"-9x^2+16x+3");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testDerivare() {
		String rezDerivare = new String(op.derivare(p1).toString());
		
		assertEquals(rezDerivare,"12x^3-15x^2+4x-1");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testIntegrare() {
		String rezIntegrare = new String(op.integrare(p2).toString());
			
		assertEquals(rezIntegrare,"0.5x^2+x");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testSuma() {
		String rezSuma = new String(op.adunare(p1, p2).toString());
		
		assertEquals(rezSuma,"3x^4-5x^3+2x^2+2");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testScadere() {
		String rezScadere = new String(op.scadere(p1, p2).toString());
		
		assertEquals(rezScadere,"3x^4-5x^3+2x^2-2x");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testInmultire() {
		String rezInmultire = new String(op.inmultire(p1, p2).toString());
		
		assertEquals(rezInmultire,"3x^5-2x^4-3x^3+x^2+1");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testImpatire() {
		String rezImpartire = new String(op.impartire(p1, p2));
		
		assertEquals(rezImpartire,"cat = 3x^3-8x^2+10x-11, rest = 12");
		nrTesteCuSucces++;
	}
	
	@org.junit.Test
	public void testValInX() {
		String rez = new String(String.valueOf(op.valoareInX(p1, -2)));
		
		assertEquals(rez,"99.0");
		nrTesteCuSucces++;
	}		
}
