package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.predicate.CulturePredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;

public interface CultureService {

	CultureEntity create(CultureEntity culture) throws MultilanguageException;

	CultureEntity read(CultureEntity culture) throws MultilanguageException;

	CultureEntity update(CultureEntity culture) throws MultilanguageException;

	void delete(CultureEntity culture) throws MultilanguageException;

	GenericOutput<CultureEntity> select(CulturePredicate predicate) throws MultilanguageException;

}
