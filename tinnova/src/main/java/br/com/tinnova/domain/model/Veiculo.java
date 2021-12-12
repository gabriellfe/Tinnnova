package br.com.tinnova.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "TB_VEICULO")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VEICULO")
	private Long id;
	@Column(nullable = false, name = "TX_VEICULO")
	private String veiculo;
	@Column(nullable = false, name = "TX_MARCA")
	private String marca;
	@Column(nullable = false, name = "DT_ANO")
	private Integer ano;
	@Column(nullable = false, name = "DS_DESCRICAO")
	private String descricao;
	@Column(nullable = false, name = "DS_VENDIDO")
	private Boolean vendido;
	@Column(name = "DT_CREATED")
	private LocalDate created;
	@Column(name = "DT_UPDATED")
	private LocalDate updated;

}
