package md.zamolxis.multilanguage.entity.predicate;

public abstract class GenericPredicate {

	private boolean isExcluded;

	public boolean isExcluded() {
		return isExcluded;
	}

	public void setExcluded(boolean isExcluded) {
		this.isExcluded = isExcluded;
	}

}