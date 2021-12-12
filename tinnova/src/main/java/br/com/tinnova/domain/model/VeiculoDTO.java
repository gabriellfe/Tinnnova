package br.com.tinnova.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VeiculoDTO {
	
		private Long id;
		private String veiculo;
		private String marca;
		private Integer ano;
		private String descricao;
		private String vendido;
		private LocalDate created;
		private LocalDate updated;
}
