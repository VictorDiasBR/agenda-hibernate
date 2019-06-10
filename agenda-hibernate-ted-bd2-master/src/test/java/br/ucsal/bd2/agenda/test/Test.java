package br.ucsal.bd2.agenda.test;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.ucsal.bd2.agenda.dao.CidadeDao;
import br.ucsal.bd2.agenda.dao.ContatoDao;
import br.ucsal.bd2.agenda.dao.EnderecoDao;
import br.ucsal.bd2.agenda.dao.EstadoDao;
import br.ucsal.bd2.agenda.domain.Cidade;
import br.ucsal.bd2.agenda.domain.Contato;
import br.ucsal.bd2.agenda.domain.Endereco;
import br.ucsal.bd2.agenda.domain.Estado;
import br.ucsal.bd2.agenda.enums.TipoEnderecoEnum;

public class Test {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;

		emf = Persistence.createEntityManagerFactory("agenda");
		EntityManager em = emf.createEntityManager();

		try {

			ContatoDao contatoDao = new ContatoDao();
			EstadoDao estadoDao = new EstadoDao();
			CidadeDao cidadeDao = new CidadeDao();
			EnderecoDao enderecoDao = new EnderecoDao();
			Estado bahia = new Estado("BA", "Bahia");
			Cidade salvador = new Cidade("SSA", "Salvador", bahia);

			List<String> telVictor = new ArrayList<>();
			telVictor.add("71993864688");
			Endereco endVictor = new Endereco("lul lul lul", salvador, TipoEnderecoEnum.RESIDENCIAL);
			//Contato victor = new Contato("Victor Dias", telVictor, endVictor);

			// Only state Persist
			estadoDao.persist(bahia);
			// Only city Persist
			cidadeDao.persist(salvador);
			// Only Addresses Persist
			enderecoDao.persist(endVictor);
			// Only Contact Persist
		//	contatoDao.persist(victor);

			// Consults
			cidadesEstado(em);
			contatosDeUmaCidade(em, "SSA");
			enderecoDoContato(em, "Victor Dias");

		} catch (Exception e) {
			logger.error("Erro: ", e);
		}

	}

	private static final Logger logger = Logger.getLogger(Test.class);

	public static void cidadesEstado(EntityManager em) {

		String hql = "select new br.ucsal.bd2.agenda.test.CidadesEstadoBDto(e,c) from tab_cidade c "
				+ "inner join c.estado e where e.sigla = :estado ";
		TypedQuery<CidadesEstadoBDto> query = em.createQuery(hql, CidadesEstadoBDto.class);
		query.setParameter("estado", "BA");
		System.out.println("______________________________________________");
		System.out.println();
		System.out.println("Cidades do estado Bahia");
		System.out.println();
		List<CidadesEstadoBDto> cidadesDto = query.getResultList();
		for (CidadesEstadoBDto cidadeDto : cidadesDto) {
			System.out.println(cidadeDto.getEstado().getNome() + " | " + cidadeDto.getCidade().getNome() + " | ");
		}

	}

	public static void contatosDeUmaCidade(EntityManager em, String siglaCity) {

		String hql = "select new br.ucsal.bd2.agenda.test.ContatosCidadeBDto(c, t) from tab_contato c "
				+ "inner join c.telefones t " + "inner join c.endereco e " + "inner join e.cidade ci "
				+ "where ci.sigla = :cidade";

		TypedQuery<ContatosCidadeBDto> query = em.createQuery(hql, ContatosCidadeBDto.class);

		query.setParameter("cidade", siglaCity);
		System.out.println("______________________________________________");
		System.out.println();
		System.out.println("Contatos da cidade " + siglaCity + "");
		System.out.println();
		List<ContatosCidadeBDto> contatos = query.getResultList();

		for (ContatosCidadeBDto contato : contatos) {

			System.out.println(contato.getContato().getNome() + " | " + contato.getTelefones());
		}
	}

	public static void enderecoDoContato(EntityManager em, String nome) {

		String hql = "select new br.ucsal.bd2.agenda.test.EnderecoContatoBDto (e.logradouro, ci.estado, e.cidade) "
				+ "from tab_contato c " + "inner join c.endereco e " + "inner join e.cidade ci "
				+ "inner join ci.estado es " + "where c.nome = :nomeContato ";

		TypedQuery<EnderecoContatoBDto> query = em.createQuery(hql, EnderecoContatoBDto.class);
		query.setParameter("nomeContato", nome);
		System.out.println("______________________________________________");
		System.out.println();
		System.out.println(" Endereço do contato " + nome + "");
		System.out.println();

		List<EnderecoContatoBDto> endereco = query.getResultList();
		for (EnderecoContatoBDto e : endereco) {
			System.out.println(e.getLogradouro() + " | " + e.getCidade().getNome() + " | " + e.getEstado().getNome() + " | ");
		}

	}
}
