package br.org.cremesp.datas.feriados.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "PUB_FERIADOS")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Feriado implements Serializable {

	private static final long serialVersionUID = 1L;

	public Feriado(Date data, String nome) {
		super();
		this.data = data;
		this.nome = nome;
	}

	@Id
	@Column(name = "FERI_DT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date data;

	@Column(name = "FERI_NOME")
	private String nome;

}
