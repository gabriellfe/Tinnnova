package br.com.tinnova.domain.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.tinnova.domain.model.Veiculo;

public interface VeiculoRepositoryQueries {
	
	List<Veiculo> find(Integer ano, 
			LocalDate created, String descricao, String marca, LocalDate updated,String veiculo, boolean vendido);
	
	
	

}
