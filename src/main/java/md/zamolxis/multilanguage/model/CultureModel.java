package md.zamolxis.multilanguage.model;

import javax.persistence.*;

@Entity
@Table(name = "ml_culture", uniqueConstraints = @UniqueConstraint(columnNames = { "tenant",
		"code" }, name = "uk_culture_tenant_code"))
public class CultureModel extends GenericModel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenant", columnDefinition = UUID, nullable = false, insertable = true, updatable = false, foreignKey = @ForeignKey(name = "fk_culture_tenant"))
	private TenantModel tenant;

	@Column(name = "code", columnDefinition = CODE, nullable = false, insertable = true, updatable = false)
	private String code;

	@Column(name = "name", columnDefinition = TEXT, nullable = false, insertable = true, updatable = true)
	private String name;

	@Column(name = "description", columnDefinition = TEXT, nullable = true, insertable = true, updatable = true)
	private String description;

	public TenantModel getTenant() {
		return tenant;
	}

	public void setTenant(TenantModel tenant) {
		this.tenant = tenant;
	}

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