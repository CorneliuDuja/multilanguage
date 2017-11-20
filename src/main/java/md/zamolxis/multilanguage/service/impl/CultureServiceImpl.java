package md.zamolxis.multilanguage.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.predicate.CulturePredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.repository.CultureRepository;
import md.zamolxis.multilanguage.service.CultureService;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.GenericService;
import md.zamolxis.multilanguage.service.TenantService;

@Service
public class CultureServiceImpl extends GenericService implements CultureService {

	@Autowired
	CultureRepository cultureRepository;

	@Autowired
	public TenantService tenantService;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public CultureEntity create(CultureEntity culture) throws MultilanguageException {
		entityNotDefined(culture, GenericService.CultureName);
		CultureEntity read = read(culture);
		createUniqueViolation(read, GenericService.CultureName);
		return cultureRepository.saveAndFlush(culture);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public CultureEntity read(CultureEntity culture) throws MultilanguageException {
		CultureEntity read = null;
		if (culture != null) {
			String id = culture.getId();
			if (id != null) {
				read = cultureRepository.findOne(id);
			}
			if (read == null) {
				TenantEntity tenant = tenantService.read(culture.getTenant());
				String code = culture.getCode();
				if (tenant != null && code != null) {
					read = cultureRepository.read(tenant, code);
				}
			}
		}
		refresh(read);
		return read;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public CultureEntity update(CultureEntity culture) throws MultilanguageException {
		CultureEntity read = read(culture);
		updateObsoleteTimestamp(culture, read, GenericService.CultureName);
		copyProperties(culture, read);
		return cultureRepository.saveAndFlush(read);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(CultureEntity culture) throws MultilanguageException {
		CultureEntity read = read(culture);
		deleteObsoleteTimestamp(culture, read, GenericService.CultureName);
		cultureRepository.delete(culture);
	}

	@Override
	public GenericOutput<CultureEntity> select(CulturePredicate predicate) {
		GenericOutput<CultureEntity> genericOutput = new GenericOutput<CultureEntity>();
		genericOutput.setGenericEntities(cultureRepository.findAll());
		return genericOutput;
	}

}
