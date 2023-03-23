package com.fullstackduck.boxes.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Mapeamento JPA e Lombok
@Entity
@Table(name="tb_movimentacao_estoque")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class MovimentacaoEstoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	
	//Relacionamento com a entidade de Produtos 
	@ManyToOne
	@JoinColumn(name = "produto_id")
	@Getter @Setter private Produto produto;

	//Relacionamento com a entidade de Produtos 
	@ManyToOne
	@JoinColumn(name = "estoque_id")
	@Getter @Setter private Estoque estoque;
}
