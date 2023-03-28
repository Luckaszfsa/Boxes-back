package com.fullstackduck.boxes.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstackduck.boxes.entities.enums.Categoria;

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
@Table(name="tb_despesa")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Despesa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private Long id;
	@Getter @Setter private String nome;
	private Integer categoria;
	@Getter @Setter private Double valor;
	@Getter @Setter private String observacao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Getter @Setter private Instant dataDespesa;
	
	//Relacionamento com a entidade de Usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
    @Getter @Setter private Usuario usuario;
	
	public Despesa(Long id, String nome, Categoria categoria, Double valor, String observacao, Instant dataDespesa, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		setCategoria(categoria);
		this.valor = valor;
		this.observacao = observacao;
		this.dataDespesa = dataDespesa;
		this.usuario = usuario;
	}
	
	public Categoria getCategoria() {
	    if (this.categoria != null) {
	        return Categoria.valueOf(this.categoria);
	    }
	    return null;
	}
	
	public void setCategoria(Categoria categoria) {
		if(categoria != null) {
			this.categoria = categoria.getCode();
		}
	}

	
	
	

	
}
