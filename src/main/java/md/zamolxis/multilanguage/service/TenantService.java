package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.exception.ServiceException;
import md.zamolxis.multilanguage.model.TenantModel;
import md.zamolxis.multilanguage.model.predicate.TenantPredicate;

import java.util.Collection;

public interface TenantService {

	TenantModel create(TenantModel tenant) throws ServiceException;

	TenantModel read(TenantModel tenant) throws ServiceException;

	TenantModel update(TenantModel tenant) throws ServiceException;

	void delete(TenantModel tenant) throws ServiceException;

	Collection<TenantModel> select(TenantPredicate predicate) throws ServiceException;

}
