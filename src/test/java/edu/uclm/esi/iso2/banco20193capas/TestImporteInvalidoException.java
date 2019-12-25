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
import edu.uclm.esi.iso2.banco20193capas.exceptions.ImporteInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.SaldoInsuficienteException;
import edu.uclm.esi.iso2.banco20193capas.model.Cliente;
import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.Tarjeta;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaDebito;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestImporteInvalidoException extends TestCase {

	
	@Before
	public void setUp() {
		Cliente pepe= new Cliente("123","Pepe","Tomates");
		Cliente eva= new Cliente("1234","Eva","Pepinos");
		//Cuenta c1= new Cuenta(333);
		Cuenta c2 = new Cuenta(444);
	}
	
	@Test
	public void retirarMenorCero() {
		Cuenta c1= new Cuenta(333);
		Cliente pepe= new Cliente("123","Pepe","Tomates");
		pepe.insert();
		try {
			c1.addTitular(pepe);
			c1.insert();
			
			c1.ingresar(100);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("una excepcion inesperada " + e);
		}
		try {
			c1.retirar(-1);
			fail("error esperaba que lanzase el error");
		} catch (ImporteInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
