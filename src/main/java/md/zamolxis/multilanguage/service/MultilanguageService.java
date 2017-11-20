package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.exception.MultilanguageException;

public interface MultilanguageService {

	String translate(String tenantCode, String cultureCode, String resourceCode, String resourceCategory)
			throws MultilanguageException;

}
