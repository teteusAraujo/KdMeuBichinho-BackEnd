package com.kdmeubichinho.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.kdmeubichinho.enums.*;
import com.kdmeubichinho.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.kdmeubichinho.converters.AnuncioStatusConverter;
import com.kdmeubichinho.entities.Anuncio;
import com.kdmeubichinho.entities.Pessoa;
import com.kdmeubichinho.repositories.AnuncioRepository;
import com.kdmeubichinho.repositories.PessoaRepository;
import com.kdmeubichinho.specification.AnuncioSpecification;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	private AnuncioSpecification specification = new AnuncioSpecification();

	public Iterable<Anuncio> getFilteredAnnounce(Pageable pageable, String cep, AnuncioStatus status, AnimalSexo sexo,
			AnimalPorte porte, AnimalClassificacaoEtaria classificacaoEtaria, Integer idCategoria, Integer idEspecie,
			Boolean castrado, Boolean vacinado) {
		return anuncioRepository
				.findAll(Specification.where(cep == null ? null : specification.animalCepFilter(cep))
						.and(status == null ? null : specification.statusFilter(status))
						.and(sexo == null ? null : specification.animalSexoFilter(sexo))
						.and(idCategoria == null ? null : specification.anuncioCategoriaFilter(idCategoria))
						.and(idEspecie == null ? null : specification.animalEspecieFilter(idEspecie))
						.and(classificacaoEtaria == null ? null
								: specification.animalClassificacaoEtariaFilter(classificacaoEtaria))
						.and(vacinado == null ? null : specification.animalVacinadoFilter(vacinado))
						.and(castrado == null ? null : specification.animalCastradoFilter(castrado))
						.and(porte == null ? null : specification.animalPorteFilter(porte)), pageable);
	}

	public Optional<Anuncio> getAnnounceById(Integer id) {
		return anuncioRepository.findById(id);
	}

	public Page<Anuncio> getAnnounceByEmailPessoa(String email, Pageable pageable) {
		return anuncioRepository.findByidPessoaEmail(email, pageable);
	}

	public Anuncio save(Anuncio anuncio) {

		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(anuncio.getIdPessoa().getEmail());
		if (pessoa.isPresent()) {
			Integer pessoaId = pessoa.get().getIdPessoa();
			anuncio.getIdPessoa().setIdPessoa(pessoaId);
		}

		if (anuncio.getIdAnimal().getNome().isEmpty()) {
			anuncio.getIdAnimal().setNome("Desconhecido");
		}
		anuncio.setStatus(AnuncioStatus.ATIVO);

		anuncioRepository.save(anuncio);
		return anuncio;
	}

	public Anuncio updateStatusAnnounce(Integer idAnuncio) {

		AnuncioStatusConverter statusConverter = new AnuncioStatusConverter();
		Anuncio meuAnuncio = anuncioRepository.findById(idAnuncio).orElseThrow(() -> new ValidationException(EnumException.ITEM_NAO_ENCONTRADO));

		Date date = new Date();
		date.setHours((date.getHours()) - 3);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		if (meuAnuncio.getStatus() == AnuncioStatus.ATIVO) {
			meuAnuncio.setStatus(statusConverter.convertToEntityAttribute("Inativo"));
			meuAnuncio.setDataEncerramento(date);

		} else if (meuAnuncio.getStatus() == AnuncioStatus.INATIVO) {
			meuAnuncio.setStatus(statusConverter.convertToEntityAttribute("Ativo"));
			meuAnuncio.setDataEncerramento(null);
		}

		anuncioRepository.save(meuAnuncio);
		return meuAnuncio;
	}

	public void deleteAnnounce(Integer id) {
		anuncioRepository.deleteById(id);
	}

	public List<Anuncio> getAll(){
		return this.anuncioRepository.findAll();
	}
}
