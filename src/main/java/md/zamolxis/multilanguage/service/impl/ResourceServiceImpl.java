package md.zamolxis.multilanguage.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.predicate.ResourcePredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.repository.ResourceRepository;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.GenericService;
import md.zamolxis.multilanguage.service.ResourceService;
import md.zamolxis.multilanguage.service.TenantService;

@Service
public class ResourceServiceImpl extends GenericService implements ResourceService {

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	public TenantService tenantService;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResourceEntity create(ResourceEntity resource) throws MultilanguageException {
		entityNotDefined(resource, GenericService.ResourceName);
		ResourceEntity read = read(resource);
		createUniqueViolation(read, GenericService.ResourceName);
		return resourceRepository.saveAndFlush(resource);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResourceEntity read(ResourceEntity resource) throws MultilanguageException {
		ResourceEntity read = null;
		if (resource != null) {
			String id = resource.getId();
			if (id != null) {
				read = resourceRepository.findOne(id);
			}
			if (read == null) {
				TenantEntity tenant = tenantService.read(resource.getTenant());
				String code = resource.getCode();
				if (tenant != null && code != null) {
					read = resourceRepository.read(tenant, code, resource.getCategory());
				}
			}
		}
		refresh(read);
		return read;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResourceEntity update(ResourceEntity resource) throws MultilanguageException {
		ResourceEntity read = read(resource);
		updateObsoleteTimestamp(resource, read, GenericService.ResourceName);
		copyProperties(resource, read);
		return resourceRepository.saveAndFlush(read);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(ResourceEntity resource) throws MultilanguageException {
		ResourceEntity read = read(resource);
		deleteObsoleteTimestamp(resource, read, GenericService.ResourceName);
		resourceRepository.delete(resource);
	}

	@Override
	public GenericOutput<ResourceEntity> select(ResourcePredicate predicate) {
		GenericOutput<ResourceEntity> genericOutput = new GenericOutput<ResourceEntity>();
		genericOutput.setGenericEntities(resourceRepository.findAll());
		return genericOutput;
	}

}
