
package com.livro.capitulo3.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import com.livro.capitulo3.cliente.Cliente;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1280791770249284855L;

	@Id
	@GeneratedValue(generator = "fk_endereco_cod_cliente")
	@org.hibernate.annotations.GenericGenerator(name = "fk_endereco_cod_cliente", strategy = "foreign", parameters = @Parameter(name = "property", value = "cliente") )
	@Column(name = "cod_cliente")
	private Integer endereco;

	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;

	private String rua;

	private Integer numero;

	private String bairro;

	private String cidade;

	@Column(name = "estado")
	private String uf;

	private String cep;

	private String complemento;

	public Integer getEndereco() {
		return endereco;
	}

	public void setEndereco(Integer endereco) {
		this.endereco = endereco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
