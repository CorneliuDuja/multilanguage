package md.zamolxis.multilanguage.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.TranslationEntity;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.repository.CultureRepository;
import md.zamolxis.multilanguage.repository.ResourceRepository;
import md.zamolxis.multilanguage.repository.TenantRepository;
import md.zamolxis.multilanguage.repository.TranslationRepository;
import md.zamolxis.multilanguage.service.GenericService;
import md.zamolxis.multilanguage.service.MultilanguageService;
import md.zamolxis.multilanguage.service.SettingsService;

@Service
public class MultilanguageServiceImpl extends GenericService implements MultilanguageService {

	@Autowired
	public TenantRepository tenantRepository;

	@Autowired
	public CultureRepository cultureRepository;

	@Autowired
	public ResourceRepository resourceRepository;

	@Autowired
	public TranslationRepository translationRepository;

	@Autowired
	public SettingsService settingsService;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public String translate(String tenantCode, String cultureCode, String resourceCode, String resourceCategory)
			throws MultilanguageException {
		String sense = resourceCode;
		TenantEntity tenant = new TenantEntity();
		tenant.setCode(tenantCode);
		tenant.setName(tenantCode);
		TenantEntity tenantRead = tenantRepository.read(tenantCode);
		if (tenantRead == null) {
			tenantRead = tenantRepository.saveAndFlush(tenant);
		}
		tenant = tenantRead;
		CultureEntity culture = new CultureEntity();
		culture.setTenant(tenant);
		culture.setCode(cultureCode);
		culture.setName(cultureCode);
		CultureEntity cultureRead = cultureRepository.read(tenant, cultureCode);
		if (cultureRead == null) {
			cultureRead = cultureRepository.saveAndFlush(culture);
		}
		culture = cultureRead;
		Timestamp timestamp = getCurrentTimestamp();
		ResourceEntity resource = new ResourceEntity();
		resource.setTenant(tenantRead);
		resource.setCode(resourceCode);
		resource.setCategory(resourceCategory);
		resource.setCreated(timestamp);
		resource.setUsed(timestamp);
		ResourceEntity resourceRead = resourceRepository.read(tenant, resourceCode, resourceCategory);
		if (resourceRead == null) {
			resourceRead = resourceRepository.saveAndFlush(resource);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(timestamp);
			calendar.add(Calendar.DATE, -settingsService.getLatencyDays());
			if (resourceRead.getUsed().getTime() < calendar.getTimeInMillis()) {
				resourceRead.setUsed(timestamp);
				resourceRead = resourceRepository.saveAndFlush(resourceRead);
			}
		}
		resource = resourceRead;
		TranslationEntity translation = new TranslationEntity();
		translation.setCulture(culture);
		translation.setResource(resource);
		TranslationEntity translationRead = translationRepository.read(culture, resource);
		if (translationRead != null) {
			sense = translationRead.getSense();
		}
		return sense;
	}

}
