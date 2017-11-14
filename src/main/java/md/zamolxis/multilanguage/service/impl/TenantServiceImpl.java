package md.zamolxis.multilanguage.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import md.zamolxis.multilanguage.exception.ServiceException;
import md.zamolxis.multilanguage.model.TenantModel;
import md.zamolxis.multilanguage.model.predicate.TenantPredicate;
import md.zamolxis.multilanguage.repository.TenantRepository;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.GenericService;
import md.zamolxis.multilanguage.service.TenantService;

@Service
public class TenantServiceImpl extends GenericService implements TenantService {

	@Autowired
	TenantRepository tenantRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TenantModel create(TenantModel tenant) throws ServiceException {
		entityNotDefined(tenant, GenericService.TenantName);
		TenantModel tenantRead = read(tenant);
		createUniqueViolation(tenantRead, GenericService.TenantName);
		return tenantRepository.saveAndFlush(tenant);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TenantModel read(TenantModel tenant) throws ServiceException {
		TenantModel find = null;
		if (tenant != null) {
			String id = tenant.getId();
			if (id != null) {
				find = tenantRepository.findOne(id);
			}
			if (find == null) {
				String code = tenant.getCode();
				if (code != null) {
					find = tenantRepository.find(code);
				}
			}
		}
		refresh(find);
		return find;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TenantModel update(TenantModel tenant) throws ServiceException {
		TenantModel tenantRead = read(tenant);
		updateObsoleteTimestamp(tenant, tenantRead, GenericService.TenantName);
		copyProperties(tenant, tenantRead);
		return tenantRepository.saveAndFlush(tenantRead);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(TenantModel tenant) throws ServiceException {
		TenantModel tenantRead = read(tenant);
		deleteObsoleteTimestamp(tenant, tenantRead, GenericService.TenantName);
		tenantRepository.delete(tenant);
	}

	@Override
	public GenericOutput<TenantModel> select(TenantPredicate predicate) {
		GenericOutput<TenantModel> genericOutput = new GenericOutput<TenantModel>();
		genericOutput.setGenericModels(tenantRepository.findAll());
		return genericOutput;
	}

}
