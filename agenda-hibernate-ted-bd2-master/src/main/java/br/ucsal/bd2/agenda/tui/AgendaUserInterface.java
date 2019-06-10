package br.ucsal.bd2.agenda.tui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ucsal.bd2.agenda.dao.CidadeDao;
import br.ucsal.bd2.agenda.dao.ContatoDao;
import br.ucsal.bd2.agenda.dao.EnderecoDao;
import br.ucsal.bd2.agenda.dao.EstadoDao;
import br.ucsal.bd2.agenda.domain.Cidade;
import br.ucsal.bd2.agenda.domain.Contato;
import br.ucsal.bd2.agenda.domain.Endereco;
import br.ucsal.bd2.agenda.domain.Estado;
import br.ucsal.bd2.agenda.enums.TipoEnderecoEnum;

public class AgendaUserInterface {

	public static void main(String[] args) {
		Menu();
	}

	private static void Menu() {
		int opcao = 0;
		ContatoDao contatoDao = new ContatoDao();
		EstadoDao estadoDao = new EstadoDao();
		CidadeDao cidadeDao = new CidadeDao();
		EnderecoDao enderecoDao = new EnderecoDao();

		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("- LISTA DE CONTATOS -");
			for (Contato contatosInicio : contatoDao.findAll()) {
				System.out.println(contatosInicio.toString());
			}
			System.out.println("//////////AGENDA TELEFONICA//////////");
			System.out.println("\nPara adicionar um contato: (1)");
			System.out.println("Para remover um contato: (2)");
			System.out.println("Para editar um contato: (3)");
			System.out.println("Para listar todos os contatos: (4)");
			System.out.println("Para sair do programa: (0)");
			System.out.println("////////////////////////////////////");

			opcao = sc.nextInt();

			if (opcao > 4 || opcao < 0) {
				Menu();
			}
			// faltaram algumas validações, mas muito pouco tempo pra resolver.
			switch (opcao) {
			case 1:
				Estado estado = null;
				String estadoNomeUtilizavel = null;
				String estadoSigla = null;
				String estadoNome = null;
				List<Estado> lEstados = estadoDao.findAll();
				for (Estado estadoNaLista : lEstados) {
					System.out.println(estadoNaLista.toString());
				}

				System.out.println("Utilizar estado existente? (1) Sim, (2) Não.");
				int opEstado = sc.nextInt();
				if (opEstado == 1) {
					System.out.println("Digite o nome referente ao estado");
					estadoNomeUtilizavel = sc.next();
					estado = estadoDao.getByNome(estadoNomeUtilizavel);
				} else if (opEstado == 2) {
					System.out.println("Digite a Sigla referente ao Estado: LIMITE(2)");
					estadoSigla = sc.next();
					System.out.println("Digite o nome do Estado:");
					estadoNome = sc.next();
					estado = new Estado(estadoSigla, estadoNome);
				} else {
					System.out.println("Opção invalida.");
					opcao = 1;
				}
				System.out.println("Estado selecionado:" + estado.toString());
				Cidade cidade = null;
				String cidadeNomeUtilizavel = null;
				String cidadeSigla = null;
				String cidadeNome = null;

				List<Cidade> lCidades = cidadeDao.findAll();
				for (Cidade cidadeNaLista : lCidades) {
					System.out.println(cidadeNaLista.toString());
				}

				System.out.println("Utilizar Cidade existente? (1) Sim, (2) Não.");
				int opCidade = sc.nextInt();
				if (opCidade == 1) {
					System.out.println("Digite o número referente à cidade");
					cidadeNomeUtilizavel = sc.next();
					cidade = cidadeDao.getByNome(cidadeNomeUtilizavel);
				} else if (opCidade == 2) {
					System.out.println("Digite a Sigla referente à CIDADE: LIMITE(3)");
					cidadeSigla = sc.next();
					System.out.println("Digite o nome da cidade:");
					cidadeNome = sc.next();
					cidade = new Cidade(cidadeSigla, cidadeNome, estado);
				} else {
					System.out.println("Opção invalida. || Retornando ao inicio.");
					opcao = 1;
				}
				System.out.println("Cidade selecionada: " + cidade.toString());
				Endereco endereco = null;
				TipoEnderecoEnum tipo = null;
				String endLogradouro = null;
				System.out.println("- Cadastrar Endereço - ");
				System.out.println("Digite o logradouro completo");
				endLogradouro = sc.next();
				System.out.println("Qual tipo de endereço? (1) Residencial, (2) Comercial");
				int opTipoEnd = sc.nextInt();
				if (opTipoEnd == 1) {
					tipo = TipoEnderecoEnum.RESIDENCIAL;
				} else if (opTipoEnd == 2) {
					tipo = TipoEnderecoEnum.COMERCIAL;
				} else {
					System.out.println("Opção invalida. || Retornando ao inicio.");
					opcao = 1;
				}
				endereco = new Endereco(endLogradouro, cidade, tipo);

				System.out.println("Endereço selecionado: " + endereco.toString());

				Contato contato = null;
				String opTelefone = null;
				String nomeContato = null;
				System.out.println("Digite o nome do contato:");
				nomeContato = sc.next();
				System.out.println("Digite o número telefonico:");
				opTelefone = sc.next();

				List<String> listaTelefonica = new ArrayList<>();
				listaTelefonica.add(opTelefone);
				System.out.println("Adicionar outro número? (1) Sim, (2) Não.");
				int pAddOutroContato = sc.nextInt();
				while (pAddOutroContato != 2) {
					System.out.println("Digite o número telefonico:");
					opTelefone = sc.next();
					listaTelefonica.add(opTelefone);
					System.out.println("Adicionar outro número? (1) Sim, (2) Não.");
					pAddOutroContato = sc.nextInt();
				}
				System.out.println("Numeros cadastrados");
				for (String numeros : listaTelefonica) {
					System.out.println(numeros);
				}
				String opEmail = null;
				System.out.println("Digite o Email:");
				opEmail = sc.next();
				List<String> listaEmails = new ArrayList<>();
				listaEmails.add(opTelefone);
				System.out.println("Adicionar outro Email? (1) Sim, (2) Não.");
				int pAddOutroContatoEmail = sc.nextInt();
				while (pAddOutroContatoEmail != 2) {
					System.out.println("Digite o número telefonico:");
					opTelefone = sc.next();
					listaEmails.add(opEmail);
					System.out.println("Adicionar outro Email? (1) Sim, (2) Não.");
					pAddOutroContatoEmail = sc.nextInt();
				}
				System.out.println("Emails cadastrados");
				for (String emails : listaEmails) {
					System.out.println(emails);
				}

				contato = new Contato(nomeContato, listaTelefonica, listaEmails, endereco);

				System.out.println("Aguarde a persistencia.");

				try {
					estadoDao.persist(estado);
					cidadeDao.persist(cidade);
					enderecoDao.persist(endereco);
					contatoDao.persist(contato);
					System.out.println("Operação realizada com sucesso");

				} catch (Exception e) {
					System.out.println(e.getStackTrace().toString());
				}
				break;

			case 2:
				Contato contatoAExcluir = null;
				List<Contato> listaContatos = contatoDao.findAll();
				for (Contato con : listaContatos) {
					System.out.println(con.toString());
				}
				System.out.println("Selecione um contato à excluir pelo seu nome.");
				String opContato = sc.next();
				contatoAExcluir = contatoDao.getByNome(opContato);

				contatoDao.remove(contatoAExcluir);
				System.out.println("Operação realizada com sucesso.");

				break;

			case 3:
				System.out.println("/// BETA: Ainda precisa editar todo o elemento.");
				Integer OpcaoEditar;
				System.out.println("O que deseja EDITAR? (1) Estado, (2) Cidade, (3) Endereco, (4) Contato");
				OpcaoEditar = sc.nextInt();

				if (OpcaoEditar == 1) {
					System.out.println("Edição de estado existente - Selecione um pelo nome.");

					Estado estadoEditar = null;
					String estadoNomeEd = null;
					String estadoSiglaEd = null;
					String estadoNomeEditavel = null;
					List<Estado> lEstadosEditar = estadoDao.findAll();
					for (Estado estadoNaLista : lEstadosEditar) {
						System.out.println(estadoNaLista.toString());
					}
					System.out.println("Digite o nome referente ao estado");
					estadoNomeEditavel = sc.next();
					estadoEditar = estadoDao.getByNome(estadoNomeEditavel);
					if (estadoEditar != null) {
						System.out.println("Digite a Sigla referente ao Estado: LIMITE(2)");
						estadoSiglaEd = sc.next();
						System.out.println("Digite o nome do Estado:");
						estadoNomeEd = sc.next();
						estadoEditar.setNome(estadoNomeEd);
						estadoEditar.setSigla(estadoSiglaEd);
						estadoDao.merge(estadoEditar);

						System.out.println("Estado editado:" + estadoEditar.toString());
					} else {
						System.out.println("Estado não existente / não encontrado");
					}
				}
				if (OpcaoEditar == 2) {
					Cidade cidadeEditar = null;
					String cidadeNomeEd = null;
					String cidadeSiglaEd = null;
					String cidadeNomeEditavel = null;
					Estado estadoParaEditar = null;
					String estadoNomeParaEditar = null;
					List<Cidade> lCidadesEditar = cidadeDao.findAll();
					for (Cidade cidadeNaLista : lCidadesEditar) {
						System.out.println(cidadeNaLista.toString());
					}
					System.out.println("Digite o nome referente ao estado");
					cidadeNomeEditavel = sc.next();
					cidadeEditar = cidadeDao.getByNome(cidadeNomeEditavel);
					if (cidadeEditar != null) {

						System.out.println("Digite a Sigla referente à Cidade: LIMITE(3)");
						cidadeSiglaEd = sc.next();
						System.out.println("Digite o nome da Cidade:");
						cidadeNomeEd = sc.next();
						System.out.println("Estados disponíveis.");
						for (Estado estadoParaedit : estadoDao.findAll()) {
							System.out.println(estadoParaedit.toString());
						}
						System.out.println("Digite o nome do estado");
						estadoParaEditar = estadoDao.getByNome(estadoNomeParaEditar);

						cidadeEditar.setNome(cidadeNomeEd);
						cidadeEditar.setSigla(cidadeSiglaEd);
						cidadeEditar.setEstado(estadoParaEditar);
						cidadeDao.merge(cidadeEditar);

						System.out.println("Cidade editada:" + cidadeEditar.toString());
					} else {
						System.out.println("Cidade não existente / não encontrada");
					}
				}
				if (OpcaoEditar == 3) {
				}
				if (OpcaoEditar == 4) {
				}
				break;

			case 4:
				List<Contato> listaContatosAtualizada = contatoDao.findAll();
				for (Contato con : listaContatosAtualizada) {
					System.out.println(con.toString());
				}
				break;
			}
		} while (opcao != 0);
		sc.close();
		if (opcao == 0)
			System.exit(opcao);
	}
}
