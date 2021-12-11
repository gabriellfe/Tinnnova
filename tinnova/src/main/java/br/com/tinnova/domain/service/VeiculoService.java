package br.com.tinnova.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.tinnova.domain.exception.EntidadeEmUsoException;
import br.com.tinnova.domain.exception.EntidadeNaoEncontradaException;
import br.com.tinnova.domain.model.Veiculo;
import br.com.tinnova.domain.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public Veiculo salvar(Veiculo veiculo) {
		veiculo.setDescricao(veiculo.getDescricao().toUpperCase());
		veiculo.setMarca(veiculo.getMarca().toUpperCase());
		veiculo.setVeiculo(veiculo.getVeiculo().toUpperCase());
		if(validaMarca(veiculo.getMarca()).isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Marca inválida"));
		}
		
		return veiculoRepository.save(veiculo);
	}

	public void excluir(Long veiculoId) {
		try {
			veiculoRepository.deleteById(veiculoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro com código %d", veiculoId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Veiculo de código %d não pode ser removida, pois está em uso", veiculoId));
		}
	}

	public List<Veiculo> find(Veiculo veiculo) {
		return veiculoRepository.find(veiculo.getAno(), veiculo.getCreated(), veiculo.getDescricao(),
				veiculo.getMarca(), veiculo.getUpdated(), veiculo.getVeiculo(), veiculo.isVendido());
	}
	
	public List<Veiculo> validaMarca(String marca) {
		return veiculoRepository.findMarca(marca);
	}
}
