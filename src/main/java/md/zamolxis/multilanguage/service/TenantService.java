package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.predicate.TenantPredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;

public interface TenantService {

	TenantEntity create(TenantEntity tenant) throws MultilanguageException;

	TenantEntity read(TenantEntity tenant) throws MultilanguageException;

	TenantEntity update(TenantEntity tenant) throws MultilanguageException;

	void delete(TenantEntity tenant) throws MultilanguageException;

	GenericOutput<TenantEntity> select(TenantPredicate predicate) throws MultilanguageException;

}
