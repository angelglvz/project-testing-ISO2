package edu.uclm.esi.iso2.banco20193capas;

import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.exceptions.ClienteNoAutorizadoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaSinTitularesException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaYaCreadaException;
import edu.uclm.esi.iso2.banco20193capas.model.Cliente;
import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.Tarjeta;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaDebito;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestClienteNoAutorizadoException extends TestCase{

	@Before
	public void setUp() {
		Cliente pepe= new Cliente("123","Pepe","Tomates");
		Cliente eva= new Cliente("1234","Eva","Pepinos");
		Cuenta c= new Cuenta(333);
	}
	
	@Test
	public void testCuentaNoTitulares() {
		Cliente pepe= new Cliente("123","Pepe","Tomates");
		Cliente eva= new Cliente("1234","Eva","Pepinos");
		Cuenta c= new Cuenta(333);
		boolean encontrado = false;
		try {
			pepe.insert();
			c.addTitular(pepe);
			c.insert();
			
		} catch (CuentaSinTitularesException | CuentaYaCreadaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Cliente titular : c.getTitulares())
			if (titular.getNif().equals(eva.getNif())) {
				encontrado = true;
				break;
			}

		if (!encontrado)
			try {
				throw new ClienteNoAutorizadoException(eva.getNif(), c.getId());
				
			} catch (ClienteNoAutorizadoException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
	}
	@Test
	public void testEncuentraTitular() {
		Cliente pepe= new Cliente("123","Pepe","Tomates");
		Cliente eva= new Cliente("1234","Eva","Pepinos");
		Cuenta c= new Cuenta(333);
		try {
			c.addTitular(eva);
			c.addTitular(pepe);
		} catch (CuentaYaCreadaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			fail("cuenta ya creada");
		}
		
		boolean encontrado = false;
		for (Cliente titular : c.getTitulares())
			if (titular.getNif().equals(eva.getNif())) {
				encontrado = true;
				break;
			}

		if (!encontrado)
			try {
				throw new ClienteNoAutorizadoException(eva.getNif(), c.getId());
			} catch (ClienteNoAutorizadoException e) {
				// TODO Auto-generated catch block
				
				fail("cliente no autorizado " + e);
			}
	}
}
