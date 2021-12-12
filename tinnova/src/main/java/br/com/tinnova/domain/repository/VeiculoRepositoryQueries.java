package br.com.tinnova.domain.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.tinnova.domain.model.Veiculo;

public interface VeiculoRepositoryQueries {
	
	/**
	 * Metodo responsável por buscar um veiculo de acordo com os atributos recebidos
	 * @author Gabriell Marques de Felipe {11/12/2021}
	 */
	List<Veiculo> find(Integer ano, LocalDate created, String descricao, String marca, LocalDate updated,
			String veiculo, Boolean vendido);
	
	
	

}
