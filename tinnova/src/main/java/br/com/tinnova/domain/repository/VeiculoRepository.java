package br.com.tinnova.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tinnova.domain.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryQueries {
	
	@Query(value = "SELECT * FROM TB_VEICULO c WHERE c.tx_marca = ?1", nativeQuery = true)
	List<Veiculo> findMarca(String marca);
	
}
