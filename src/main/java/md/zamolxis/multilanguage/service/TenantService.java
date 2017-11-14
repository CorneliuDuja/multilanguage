package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.predicate.TenantPredicate;
import md.zamolxis.multilanguage.exception.ServiceException;

public interface TenantService {

	TenantEntity create(TenantEntity tenant) throws ServiceException;

	TenantEntity read(TenantEntity tenant) throws ServiceException;

	TenantEntity update(TenantEntity tenant) throws ServiceException;

	void delete(TenantEntity tenant) throws ServiceException;

	GenericOutput<TenantEntity> select(TenantPredicate predicate) throws ServiceException;

}
