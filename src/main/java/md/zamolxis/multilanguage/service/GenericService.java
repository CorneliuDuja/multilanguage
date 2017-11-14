package md.zamolxis.multilanguage.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import md.zamolxis.multilanguage.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import md.zamolxis.multilanguage.exception.ExceptionData.ExceptionDataType;
import md.zamolxis.multilanguage.exception.ServiceException;

public abstract class GenericService {

	@Autowired
	protected SettingsService settingsService;

	@Autowired
	protected EntityManager entityManager;

	public static final String TenantName = TenantModel.class.getSimpleName();
	public static final String CultureName = CultureModel.class.getSimpleName();
	public static final String ResourceName = ResourceModel.class.getSimpleName();
	public static final String TranslationName = TranslationModel.class.getSimpleName();

	public void entityNotDefined(GenericModel generic, String name) throws ServiceException {
		if (generic != null)
			return;
		throw new ServiceException(ExceptionDataType.ENTITY_NOT_DEFINED, new Object[] { name });
	}

	public void createUniqueViolation(GenericModel generic, String name) throws ServiceException {
		if (generic == null)
			return;
		throw new ServiceException(ExceptionDataType.CREATE_UNIQUE_VIOLATION, new Object[] { name });
	}

	public void readNotFound(GenericModel generic, String name) throws ServiceException {
		if (generic != null)
			return;
		throw new ServiceException(ExceptionDataType.READ_NOT_FOUND, new Object[] { name });
	}

	public void updateObsoleteTimestamp(GenericModel current, GenericModel previous, String name)
			throws ServiceException {
		entityNotDefined(current, name);
		readNotFound(previous, name);
		if (!equals(previous.getVersion(), current.getVersion())) {
			throw new ServiceException(ExceptionDataType.UPDATE_OBSOLETE_TIMESTAMP, new Object[] { name });
		}
	}

	public void deleteObsoleteTimestamp(GenericModel current, GenericModel previous, String name)
			throws ServiceException {
		entityNotDefined(current, name);
		readNotFound(previous, name);
		if (!equals(previous.getVersion(), current.getVersion())) {
			throw new ServiceException(ExceptionDataType.DELETE_OBSOLETE_TIMESTAMP, new Object[] { name });
		}
	}

	public boolean equals(Timestamp previous, Timestamp current) {
		boolean equals = false;
		if (previous != null && current != null) {
			equals = Math.abs(previous.getTime() - current.getTime()) <= settingsService.getTimestampDiff();
		}
		return equals;
	}

	public Timestamp getMinTimestamp() {
		return new Timestamp(settingsService.getTimestampMin());
	}

	public Timestamp getCurrentTimestamp(Timestamp timestamp) {
		if (timestamp == null) {
			timestamp = new Timestamp(System.currentTimeMillis());
		}
		return timestamp;
	}

	public Timestamp getMaxTimestamp() {
		return new Timestamp(settingsService.getTimestampMax());
	}

	public void copyProperties(Object source, Object target, String... ignoreProperties) {
		List<String> properties = new ArrayList<String>();
		properties.add("id");
		properties.add("version");
		if (ignoreProperties != null) {
			properties.addAll(Arrays.asList(ignoreProperties));
		}
		BeanUtils.copyProperties(source, target, properties.toArray(new String[properties.size()]));
	}

	public void refresh(Object object) {
		if (object != null) {
			entityManager.refresh(object);
		}
	}

}
