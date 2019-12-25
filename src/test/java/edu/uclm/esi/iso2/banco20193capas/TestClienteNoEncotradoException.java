package edu.uclm.esi.iso2.banco20193capas;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.exceptions.ClienteNoAutorizadoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.ClienteNoEncontradoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaSinTitularesException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaYaCreadaException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.ImporteInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.SaldoInsuficienteException;
import edu.uclm.esi.iso2.banco20193capas.model.Cliente;
import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.Manager;
import edu.uclm.esi.iso2.banco20193capas.model.Tarjeta;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaDebito;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestClienteNoEncotradoException extends TestCase {

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void clienteEncontrado() {
		Cliente pepe= new Cliente("123","Pepe","Tomates");
		try {
			
			pepe.insert();
			
		}catch(DataIntegrityViolationException e) {
			fail("exception " + e);
		}
		
		Optional<Cliente> optCliente = Manager.getClienteDAO().findByNif("123");
		if (!optCliente.isPresent())
			try {
				throw new ClienteNoEncontradoException("123");
				
			} catch (ClienteNoEncontradoException e) {
				// TODO Auto-generated catch block
				fail("client should have been found");
			}
	}
	
	@Test
	public void clienteNoEncontrado() {
		Optional<Cliente> optCliente = Manager.getClienteDAO().findByNif("123");
		if (!optCliente.isPresent())
			try {
				throw new ClienteNoEncontradoException("123");
			} catch (ClienteNoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
			
	}
}
