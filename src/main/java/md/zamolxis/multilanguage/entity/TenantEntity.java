package md.zamolxis.multilanguage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "tenant")
@Table(name = "ml_tenant", uniqueConstraints = @UniqueConstraint(columnNames = { "code" }, name = "ml_uk_tenant_code"))
public class TenantEntity extends GenericEntity {

	@Column(name = "code", columnDefinition = CODE, nullable = false, insertable = true, updatable = false)
	private String code;

	@Column(name = "name", columnDefinition = TEXT, nullable = false, insertable = true, updatable = true)
	private String name;

	@Column(name = "description", columnDefinition = TEXT, nullable = true, insertable = true, updatable = true)
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}