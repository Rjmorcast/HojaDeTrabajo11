/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hojatrabajo11.GuatemalaGraph;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alber
 */
public class PruebaUnitaria11 {
    
    public PruebaUnitaria11() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetRelationShip() throws IOException {
		GuatemalaGraph n = new GuatemalaGraph();
                int i = n.getRelationShip("Jocotillo","Antigua");	
		assertEquals(i,80);
	}

   
    
}
