package br.com.tinnova.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tinnova.domain.exception.EntidadeEmUsoException;
import br.com.tinnova.domain.exception.EntidadeNaoEncontradaException;
import br.com.tinnova.domain.model.Veiculo;
import br.com.tinnova.domain.model.VeiculoDTO;
import br.com.tinnova.domain.repository.VeiculoRepository;
import br.com.tinnova.domain.service.VeiculoService;

/**
 * Classe responsável pelos endpoints para alteração, cadastro e busca de Veículos
 * @author Gabriell Marques de Felipe {11/12/2021}
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private VeiculoService veiculoService;

	@GetMapping
	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}

	@GetMapping("/{veiculoId}")
	public ResponseEntity<Veiculo> buscar(@PathVariable Long veiculoId) {
		try {
			Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoId);
			return ResponseEntity.ok(veiculo.get());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/find")
	public List<Veiculo> buscarVeiculo(@RequestBody VeiculoDTO veiculo) {
		return veiculoService.find(veiculo);
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Veiculo veiculo) {
		try {
			veiculo.setUpdated(LocalDate.now());
			veiculo.setCreated(LocalDate.now());

			veiculo = veiculoService.salvar(veiculo);

			return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{veiculoId}")
	public ResponseEntity<Veiculo> remover(@PathVariable Long veiculoId) {

		try {
			veiculoService.excluir(veiculoId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PutMapping("/{veiculoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long veiculoId, @RequestBody Veiculo veiculo) {
		try {
			Veiculo veiculoAtual = veiculoRepository.findById(veiculoId).orElse(null);

			if (veiculoAtual != null) {
				BeanUtils.copyProperties(veiculo, veiculoAtual, "id");
				veiculoAtual.setUpdated(LocalDate.now());
				veiculoAtual = veiculoService.salvar(veiculoAtual);
				return ResponseEntity.ok(veiculoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/{veiculoId}")
	public ResponseEntity<?> atualizarVeiculo(@PathVariable Long veiculoId, @RequestBody Veiculo veiculo) {
		try {
			Veiculo veiculoAtual = veiculoRepository.findById(veiculoId).orElse(null);

			if (veiculoAtual != null) {

				if (veiculo.getAno() != null) {
					veiculoAtual.setAno(veiculo.getAno());
				}
				if (veiculo.getDescricao() != null && !veiculo.getDescricao().isEmpty()) {
					veiculoAtual.setDescricao(veiculo.getDescricao());
				}
				if (veiculo.getMarca() != null && !veiculo.getMarca().isEmpty()) {
					veiculoAtual.setMarca(veiculo.getMarca());
				}
				if (veiculo.getVeiculo() != null && !veiculo.getVeiculo().isEmpty()) {
					veiculoAtual.setVeiculo(veiculo.getVeiculo());
				}
				if (veiculo.isVendido() != !veiculoAtual.isVendido()) {
					veiculoAtual.setVendido(veiculo.isVendido());
				}

				veiculoAtual.setUpdated(LocalDate.now());
				veiculoAtual = veiculoService.salvar(veiculoAtual);
				return ResponseEntity.ok(veiculoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
