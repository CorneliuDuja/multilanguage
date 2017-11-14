package md.zamolxis.multilanguage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "translation")
@Table(name = "ml_translation", uniqueConstraints = @UniqueConstraint(columnNames = { "culture",
		"resource" }, name = "ml_uk_translation_culture_resource"))
public class TranslationEntity extends GenericEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "culture", columnDefinition = UUID, nullable = false, insertable = true, updatable = false, foreignKey = @ForeignKey(name = "ml_fk_translation_culture"))
	private CultureEntity culture;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resource", columnDefinition = UUID, nullable = false, insertable = true, updatable = false, foreignKey = @ForeignKey(name = "ml_fk_translation_resource"))
	private ResourceEntity resource;

	@Column(name = "sense", columnDefinition = CODE, nullable = false, insertable = true, updatable = true)
	private String sense;

	public CultureEntity getCulture() {
		return culture;
	}

	public void setCulture(CultureEntity culture) {
		this.culture = culture;
	}

	public ResourceEntity getResource() {
		return resource;
	}

	public void setResource(ResourceEntity resource) {
		this.resource = resource;
	}

	public String getSense() {
		return sense;
	}

	public void setSense(String sense) {
		this.sense = sense;
	}

}