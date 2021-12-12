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

/**
 * Classe Service para Controller de Veiculos
 * 
 * @author Gabriell Marques de Felipe {11/12/2021}
 * @version 1.0
 *
 */


@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	
	/**
	 * Metodo para salvar Veículo e validar a marca
	 */

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
	
	/**
	 * Metodo para excluir Veículo pelo ID
	 */

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
	
	/**
	 * Metodo para buscar veículos por qualquer atributo
	 */

	public List<Veiculo> find(Veiculo veiculo) {
		return veiculoRepository.find(veiculo.getAno(), veiculo.getCreated(), veiculo.getDescricao(),
				veiculo.getMarca(), veiculo.getUpdated(), veiculo.getVeiculo(), veiculo.isVendido());
	}
	
	/**
	 * Metodo para validar marcas
	 */
	
	public List<Veiculo> validaMarca(String marca) {
		return veiculoRepository.findMarca(marca);
	}
}
