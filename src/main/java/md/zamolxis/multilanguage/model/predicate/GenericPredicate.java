package md.zamolxis.multilanguage.model.predicate;

import javax.persistence.*;

@MappedSuperclass
public abstract class GenericPredicate {

	private boolean isExcluded;

	public boolean isExcluded() {
		return isExcluded;
	}

	public void setExcluded(boolean isExcluded) {
		this.isExcluded = isExcluded;
	}

}