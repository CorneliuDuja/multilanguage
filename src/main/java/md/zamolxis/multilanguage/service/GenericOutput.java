package md.zamolxis.multilanguage.service;

import java.util.Collection;

public class GenericOutput<T> {

	private Collection<T> genericEntities;

	public Collection<T> getGenericEntities() {
		return genericEntities;
	}

	public void setGenericEntities(Collection<T> genericEntities) {
		this.genericEntities = genericEntities;
	}

}
