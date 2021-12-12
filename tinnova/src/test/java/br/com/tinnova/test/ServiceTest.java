package br.com.tinnova.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.tinnova.domain.model.Veiculo;
import br.com.tinnova.domain.service.VeiculoService;

public class ServiceTest {
	
	@Autowired
	private VeiculoService veiculoService = new VeiculoService();;
	
	@Test
	public void listarTodosOsVeiculosTest(){
		assertEquals(13, veiculoService.find(new Veiculo()).size());
	}

}
