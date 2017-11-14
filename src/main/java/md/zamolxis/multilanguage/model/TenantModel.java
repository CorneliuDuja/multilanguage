package md.zamolxis.multilanguage.model;

import javax.persistence.*;

@Entity
@Table(name = "ml_tenant", uniqueConstraints = @UniqueConstraint(columnNames = { "code" }, name = "uk_tenant_code"))
public class TenantModel extends GenericModel {

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