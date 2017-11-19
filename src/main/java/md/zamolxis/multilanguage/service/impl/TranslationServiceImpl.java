package md.zamolxis.multilanguage.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.TranslationEntity;
import md.zamolxis.multilanguage.entity.predicate.TranslationPredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.repository.TranslationRepository;
import md.zamolxis.multilanguage.service.CultureService;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.GenericService;
import md.zamolxis.multilanguage.service.ResourceService;
import md.zamolxis.multilanguage.service.TranslationService;

@Service
public class TranslationServiceImpl extends GenericService implements TranslationService {

	@Autowired
	TranslationRepository translationRepository;

	@Autowired
	public CultureService cultureService;

	@Autowired
	public ResourceService resourceService;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TranslationEntity create(TranslationEntity translation) throws MultilanguageException {
		entityNotDefined(translation, GenericService.TranslationName);
		TranslationEntity read = read(translation);
		createUniqueViolation(read, GenericService.TranslationName);
		return translationRepository.saveAndFlush(translation);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TranslationEntity read(TranslationEntity translation) throws MultilanguageException {
		TranslationEntity find = null;
		if (translation != null) {
			String id = translation.getId();
			if (id != null) {
				find = translationRepository.findOne(id);
			}
			if (find == null) {
				CultureEntity culture = cultureService.read(translation.getCulture());
				ResourceEntity resource = resourceService.read(translation.getResource());
				if (culture != null && resource != null) {
					find = translationRepository.find(culture, resource);
				}
			}
		}
		refresh(find);
		return find;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TranslationEntity update(TranslationEntity translation) throws MultilanguageException {
		TranslationEntity read = read(translation);
		updateObsoleteTimestamp(translation, read, GenericService.TranslationName);
		copyProperties(translation, read);
		return translationRepository.saveAndFlush(read);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(TranslationEntity translation) throws MultilanguageException {
		TranslationEntity read = read(translation);
		deleteObsoleteTimestamp(translation, read, GenericService.TranslationName);
		translationRepository.delete(translation);
	}

	@Override
	public GenericOutput<TranslationEntity> select(TranslationPredicate predicate) {
		GenericOutput<TranslationEntity> genericOutput = new GenericOutput<TranslationEntity>();
		genericOutput.setGenericEntities(translationRepository.findAll());
		return genericOutput;
	}

}
