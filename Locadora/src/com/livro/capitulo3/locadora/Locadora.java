/*
 * Código-fonte do livro "Programando Java para Web"
 * Autores: Décio Heinzelmann Luckow <decioluckow@gmail.com>
 *          Alexandre Altair de Melo <alexandremelo.br@gmail.com>
 *
 * ISBN: 978-85-7522-238-6
 * http://www.novatec.com.br/livros/javaparaweb
 * Editora Novatec, 2010 - todos os direitos reservados
 *
 * LICENÇA: Este arquivo-fonte está sujeito a Atribuição 2.5 Brasil, da licença Creative Commons,
 * que encontra-se disponível no seguinte endereço URI: http://creativecommons.org/licenses/by/2.5/br/
 * Se você não recebeu uma cópia desta licença, e não conseguiu obtê-la pela internet, por favor,
 * envie uma notificação aos seus autores para que eles possam enviá-la para você imediatamente.
 *
 *
 * Source-code of "Programando Java para Web" book
 * Authors: Décio Heinzelmann Luckow <decioluckow@gmail.com>
 *          Alexandre Altair de Melo <alexandremelo.br@gmail.com>
 *
 * ISBN: 978-85-7522-238-6
 * http://www.novatec.com.br/livros/javaparaweb
 * Editora Novatec, 2010 - all rights reserved
 *
 * LICENSE: This source file is subject to Attribution version 2.5 Brazil of the Creative Commons
 * license that is available through the following URI:  http://creativecommons.org/licenses/by/2.5/br/
 * If you did not receive a copy of this license and are unable to obtain it through the web, please
 * send a note to the authors so they can mail you a copy immediately.
 *
 */
package com.livro.capitulo3.locadora;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
import com.livro.capitulo3.cliente.Cliente;
import com.livro.capitulo3.cliente.ClienteDAO;
import com.livro.capitulo3.endereco.Endereco;
import com.livro.capitulo3.endereco.EnderecoDAO;
import com.livro.capitulo3.filme.Filme;
import com.livro.capitulo3.filme.FilmeDAO;
import com.livro.capitulo3.locacao.Locacao;
import com.livro.capitulo3.locacao.LocacaoDAO;
import com.livro.capitulo3.midia.Midia;
import com.livro.capitulo3.midia.MidiaDAO;
import com.livro.capitulo3.util.HibernateUtil;

/*
 * Classe para testes do sistema da locadora.
 */
public class Locadora {

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory().openSession();
		Locadora locadora = new Locadora();
		locadora.cadastraCategorias();
		locadora.cadastraFilmes();
		locadora.cadastraMidias();

		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = new Endereco();
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente.setCelular("(47) 1111-2222");
		cliente.setEmail("solaris@javapro.com.br");
		cliente.setNome("Fulano Solaris");
		cliente.setTelefone("(47) 3333-7777");
		cliente.setEndereco(endereco);
		endereco.setBairro("Centro");
		endereco.setCep("89000-000");
		endereco.setCidade("Joinville");
		endereco.setComplemento("casa");
		endereco.setNumero(new Integer(1));
		endereco.setRua("Av. Principal");
		endereco.setUf("SC");
		endereco.setCliente(cliente);
		clienteDAO.salvar(cliente);
		enderecoDAO.salvar(endereco);

		LocacaoDAO locacaoDAO = new LocacaoDAO();
		Locacao locacao = new Locacao();
		locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
		locacao.setDataEmprestimo(new Date(System.currentTimeMillis()));
		locacao.setObservacao("Devolução final de semana");
		locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));

		locacao.setCliente(cliente);

		MidiaDAO midiaDAO = new MidiaDAO();
		Midia midia = (Midia) midiaDAO.buscaMidia(new Integer(1));
		locacao.setMidia(midia);

		locacaoDAO.salvar(locacao);
		System.out.println("Cadastros gerados com sucesso!");
	}

	public void cadastraCategorias() {
		//Criando as categorias dos filmes
		String categorias[] = {"Aventura", "Ação", "Comédia"};
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();

		for (int i = 0; i < 3; i++) {
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoriaDAO.salvar(categoria);
		}
	}

	public void cadastraFilmes() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String[] filmesDescricao = {"Senhor dos Anéis", "Transformers", "Ghostbusters"};
    //Aqui subtraímos o ano de lançamento do filme de 1900, para gravarmos o ano correto no banco.
		Date[] filmesAnoProducao = {new Date(2001-1900, 11, 19), new Date(2007-1900, 6, 20), new Date(1985-1900, 1, 1)};
		FilmeDAO filmeDAO = new FilmeDAO();
		Filme filme = null;

		for (int i = 0; i < 3; i++) {
			filme = new Filme();
			filme.setDescricao(filmesDescricao[i]);
			filme.setAno(filmesAnoProducao[i]);
			filme.setCategoria(categoriaDAO.buscaCategoria(i + 1));
			filmeDAO.salvar(filme);
		}
	}

	public void cadastraMidias() {
		Midia midia = null;
		Filme filme = null;
		MidiaDAO midiaDAO = new MidiaDAO();
		FilmeDAO filmeDAO = new FilmeDAO();
		List<Filme> resultado = filmeDAO.listar();

		for (int i = 0; i < 3; i++) {
			midia = new Midia();
			filme = (Filme) resultado.get(i);
			midia.setFilme(filme);
			midia.setInutilizada("N");
			midiaDAO.salvar(midia);
		}
	}
}
