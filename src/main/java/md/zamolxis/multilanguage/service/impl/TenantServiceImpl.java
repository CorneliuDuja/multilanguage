package md.zamolxis.multilanguage.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.predicate.TenantPredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
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
	public TenantEntity create(TenantEntity tenant) throws MultilanguageException {
		entityNotDefined(tenant, GenericService.TenantName);
		TenantEntity read = read(tenant);
		createUniqueViolation(read, GenericService.TenantName);
		return tenantRepository.saveAndFlush(tenant);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public TenantEntity read(TenantEntity tenant) throws MultilanguageException {
		TenantEntity find = null;
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
	public TenantEntity update(TenantEntity tenant) throws MultilanguageException {
		TenantEntity read = read(tenant);
		updateObsoleteTimestamp(tenant, read, GenericService.TenantName);
		copyProperties(tenant, read);
		return tenantRepository.saveAndFlush(read);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(TenantEntity tenant) throws MultilanguageException {
		TenantEntity read = read(tenant);
		deleteObsoleteTimestamp(tenant, read, GenericService.TenantName);
		tenantRepository.delete(tenant);
	}

	@Override
	public GenericOutput<TenantEntity> select(TenantPredicate predicate) {
		GenericOutput<TenantEntity> genericOutput = new GenericOutput<TenantEntity>();
		genericOutput.setGenericEntities(tenantRepository.findAll());
		return genericOutput;
	}

}
