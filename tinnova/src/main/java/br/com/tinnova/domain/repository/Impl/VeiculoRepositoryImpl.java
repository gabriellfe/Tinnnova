package br.com.tinnova.domain.repository.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.tinnova.domain.model.Veiculo;
import br.com.tinnova.domain.repository.VeiculoRepositoryQueries;

public class VeiculoRepositoryImpl implements VeiculoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> find(Integer ano, LocalDate created, String descricao, String marca, LocalDate updated,
			String veiculo, boolean vendido) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Veiculo.class);
		Root root = criteria.from(Veiculo.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		

		if (StringUtils.hasText(veiculo)) {
			String veiculoUpper = veiculo.toUpperCase();
			predicates.add(builder.equal(root.get("veiculo"),  veiculoUpper )); 
		}
		if (StringUtils.hasText(marca)) {
			String marcaUpper = marca.toUpperCase();
			predicates.add(builder.like(root.get("marca"), "%" + marcaUpper + "%"));
		}
		if (ano != null) {
			predicates.add(builder.equal(root.get("ano"),ano));
		}
		if (created != null) {
			predicates.add(builder.equal(root.get("created"),  created ));
		}
		if (StringUtils.hasText(descricao)) {
			String descricaoUpper = descricao.toUpperCase();
			predicates.add(builder.like(root.get("descricao"), "%" + descricaoUpper + "%"));
		}
		if (updated != null) {
			predicates.add(builder.equal(root.get("updated"),updated));
		}	
		if (vendido == true) {
			predicates.add(builder.equal(root.get("vendido"),vendido));
		}	
		
		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Veiculo> query = manager.createQuery(criteria);

		return query.getResultList();
	}

}
