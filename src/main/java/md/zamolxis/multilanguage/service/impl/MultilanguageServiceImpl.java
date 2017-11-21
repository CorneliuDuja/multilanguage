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
		String translate = resourceCode;
		Timestamp timestamp = getCurrentTimestamp();
		TenantEntity tenant = tenantRepository.read(tenantCode);
		if (tenant == null) {
			tenant = new TenantEntity();
			tenant.setCode(tenantCode);
			tenant.setName(tenantCode);
			tenant = tenantRepository.saveAndFlush(tenant);
		}
		CultureEntity culture = cultureRepository.read(tenant, cultureCode);
		if (culture == null) {
			culture = new CultureEntity();
			culture.setTenant(tenant);
			culture.setCode(cultureCode);
			culture.setName(cultureCode);
			culture = cultureRepository.saveAndFlush(culture);
		}
		ResourceEntity resource = resourceRepository.read(tenant, resourceCode, resourceCategory);
		if (resource == null) {
			resource = new ResourceEntity();
			resource.setTenant(tenant);
			resource.setCode(resourceCode);
			resource.setCategory(resourceCategory);
			resource.setCreated(timestamp);
			resource.setUsed(timestamp);
			resource = resourceRepository.saveAndFlush(resource);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(timestamp);
			calendar.add(Calendar.DATE, -settingsService.getLatencyDays());
			if (resource.getUsed().getTime() < calendar.getTimeInMillis()) {
				resource.setUsed(timestamp);
				resource = resourceRepository.saveAndFlush(resource);
			}
		}
		TranslationEntity translation = translationRepository.read(culture, resource);
		if (translation != null) {
			translate = translation.getSense();
		}
		return translate;
	}

}
