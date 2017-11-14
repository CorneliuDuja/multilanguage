package md.zamolxis.multilanguage.model;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "ml_resource", uniqueConstraints = @UniqueConstraint(columnNames = { "tenant", "code",
		"category" }, name = "uk_resource_tenant_code_category"))
public class ResourceModel extends GenericModel {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenant", columnDefinition = UUID, nullable = false, insertable = true, updatable = false, foreignKey = @ForeignKey(name = "fk_resource_tenant"))
	private TenantModel tenant;

	@Column(name = "code", columnDefinition = CODE, nullable = false, insertable = true, updatable = false)
	private String code;

	@Column(name = "category", columnDefinition = CODE, nullable = true, insertable = true, updatable = false)
	private String category;

	@Column(name = "created", nullable = false, insertable = true, updatable = false)
	private Timestamp created;

	@Column(name = "used", nullable = false, insertable = true, updatable = true)
	private Timestamp used;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUsed() {
		return used;
	}

	public void setUsed(Timestamp used) {
		this.used = used;
	}

}