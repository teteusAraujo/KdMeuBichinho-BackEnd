package com.kdmeubichinho.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.kdmeubichinho.converters.AnuncioStatusConverter;
import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.enums.AnimalClassificacaoEtaria;
import com.kdmeubichinho.enums.AnimalPorte;
import com.kdmeubichinho.enums.AnimalSexo;
import com.kdmeubichinho.enums.AnuncioStatus;
import com.kdmeubichinho.repositories.AnuncioRepository;
import com.kdmeubichinho.repositories.PessoaRepository;
import com.kdmeubichinho.specification.AnuncioSpecification;

public class AnuncioService {
	

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	private AnuncioStatus ATIVO = AnuncioStatus.ATIVO;

	public Iterable<Anuncio> getFilteredAnnounce(Pageable pageable, String cep, AnuncioStatus status, AnimalSexo sexo,
			AnimalPorte porte, AnimalClassificacaoEtaria classificacaoEtaria, Integer idCategoria, Integer idEspecie,
			Boolean castrado, Boolean vacinado) {
		return anuncioRepository
				.findAll(Specification.where(cep == null ? null : AnuncioSpecification.AnimalCepFilter(cep))
						.and(status == null ? null : AnuncioSpecification.statusFilter(status))
						.and(sexo == null ? null : AnuncioSpecification.AnimalSexoFilter(sexo))
						.and(idCategoria == null ? null : AnuncioSpecification.AnuncioCategoriaFilter(idCategoria))
						.and(idEspecie == null ? null : AnuncioSpecification.AnimalEspecieFilter(idEspecie))
						.and(classificacaoEtaria == null ? null
								: AnuncioSpecification.AnimalClassificacaoEtariaFilter(classificacaoEtaria))
						.and(vacinado == null ? null : AnuncioSpecification.AnimalVacinadoFilter(vacinado))
						.and(castrado == null ? null : AnuncioSpecification.AnimalCastradoFilter(castrado))
						.and(porte == null ? null : AnuncioSpecification.AnimalPorteFilter(porte)), pageable);
	}

	public Optional<Anuncio> getAnnounceById(Integer id) {
		return anuncioRepository.findById(id);
	}

	public Page<Anuncio> getAnnounceByEmailPessoa(String email, Pageable pageable) {
		return anuncioRepository.findByidPessoa_Email(email, pageable);
	}

	public Anuncio addAnnounce(Anuncio anuncio) {

		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(anuncio.getIdPessoa().getEmail());
		if (pessoa.isPresent()) {
			Integer pessoaId = pessoa.get().getIdPessoa();
			anuncio.getIdPessoa().setIdPessoa(pessoaId);
		}
		if (anuncio.getIdAnimal().getNome().isEmpty()) {
			anuncio.getIdAnimal().setNome("Desconhecido");
		}
		anuncio.setStatus(ATIVO);

		anuncioRepository.save(anuncio);
		return anuncio;
	}

	public Anuncio updateStatusAnnounce(Integer idAnuncio) throws Exception {

		AnuncioStatusConverter statusConverter = new AnuncioStatusConverter();
		Anuncio meuAnuncio = anuncioRepository.findById(idAnuncio).orElseThrow(() -> new IllegalAccessException());

		Date date = new Date();
		date.setHours((date.getHours()) - 3);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(formatter.format(date));

		if (meuAnuncio.getStatus().equals("Ativo")) {
			meuAnuncio.setStatus(statusConverter.convertToEntityAttribute("Inativo"));
			meuAnuncio.setDataEncerramento(date);

		} else if (meuAnuncio.getStatus().equals("Inativo")) {
			meuAnuncio.setStatus(statusConverter.convertToEntityAttribute("Ativo"));
			meuAnuncio.setDataEncerramento(null);
		}

		anuncioRepository.save(meuAnuncio);
		return meuAnuncio;
	}

	public void deleteAnnounce(Integer id) {
		anuncioRepository.deleteById(id);
	}


}
