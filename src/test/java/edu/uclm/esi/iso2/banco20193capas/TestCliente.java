package edu.uclm.esi.iso2.banco20193capas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.model.Cliente;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCliente extends TestCase{
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testsetid() {
		Cliente c = new Cliente();
		long l = 123;
		c.setId(l);
		assertTrue(c.getId()== 123);
	}
	
	@Test
	public void testsetnif() {
		Cliente c = new Cliente();
		c.setNif("123L");
		assertTrue(c.getNif()=="123L");
	}
	
	@Test
	public void testsetnombre() {
		Cliente c = new Cliente();
		c.setNombre("Valentin");
		assertTrue(c.getNombre()=="Valentin");
	}
	
	@Test
	public void testsetapellidos() {
		Cliente c = new Cliente();
		c.setApellidos("Stoyanov Tsvetanov");
		assertTrue(c.getApellidos()=="Stoyanov Tsvetanov");
	}
	
	

}