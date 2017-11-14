package md.zamolxis.multilanguage.service;

import java.util.Collection;

public class GenericOutput<T> {

	private Collection<T> genericModels;

	public Collection<T> getGenericModels() {
		return genericModels;
	}

	public void setGenericModels(Collection<T> genericModels) {
		this.genericModels = genericModels;
	}

}
