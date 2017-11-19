package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.entity.TranslationEntity;
import md.zamolxis.multilanguage.entity.predicate.TranslationPredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;

public interface TranslationService {

	TranslationEntity create(TranslationEntity translation) throws MultilanguageException;

	TranslationEntity read(TranslationEntity translation) throws MultilanguageException;

	TranslationEntity update(TranslationEntity translation) throws MultilanguageException;

	void delete(TranslationEntity translation) throws MultilanguageException;

	GenericOutput<TranslationEntity> select(TranslationPredicate predicate) throws MultilanguageException;

}
