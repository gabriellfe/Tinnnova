package br.com.tinnova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tinnova.domain.model.Veiculo;
import br.com.tinnova.domain.repository.VeiculoRepository;
import br.com.tinnova.domain.service.VeiculoService;

@SpringBootTest
class TinnovaTestDevApplicationTests {

	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Test
	public void listarTodosOsVeiculosTest(){
		assertEquals(12, veiculoRepository.findAll().size());
	}
	
	@Test
	public void buscarVeiculosPorIDTest(){
		Optional<Veiculo> veiculo = veiculoRepository.findById(1L);
		assertEquals(1, veiculo.get().getId());
}
	
	@Test
	public void buscarVeiculosPorNomeTest(){
		Veiculo veiculoBusca = new Veiculo();
		veiculoBusca.setVeiculo("FOX");
		List<Veiculo> listaRetorno = veiculoService.find(veiculoBusca);
		Veiculo veiculoRetorno = new Veiculo();
		for(Veiculo item: listaRetorno) {
			veiculoRetorno.setVeiculo(item.getVeiculo());
		}
		assertEquals("FOX", veiculoRetorno.getVeiculo());
}
	
	@Test
	public void salvarVeiculoDeletarPorIDTest(){
		Veiculo veiculoBusca = new Veiculo();
		veiculoBusca.setVeiculo("PUNTO");
		veiculoBusca.setAno(2015);
		veiculoBusca.setDescricao("Descricao teste");
		veiculoBusca.setMarca("Fiat");
		veiculoBusca.setVendido(true);
		veiculoBusca.setUpdated(LocalDate.now());
		veiculoBusca.setCreated(LocalDate.now());
		veiculoBusca = veiculoService.salvar(veiculoBusca);
		assertNotEquals(null, veiculoBusca.getId());
		veiculoRepository.deleteById(veiculoBusca.getId());
}
	
}