package md.zamolxis.multilanguage.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "resource")
@Table(name = "ml_resource", uniqueConstraints = @UniqueConstraint(columnNames = { "tenant", "code",
		"category" }, name = "ml_uk_resource_tenant_code_category"))
public class ResourceEntity extends GenericEntity {

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenant", columnDefinition = UUID, nullable = false, insertable = true, updatable = false, foreignKey = @ForeignKey(name = "ml_fk_resource_tenant"))
	private TenantEntity tenant;

	@NotNull
	@Size(min = MIN_VARCHAR, max = MAX_VARCHAR)
	@Column(name = "code", columnDefinition = CODE, nullable = false, insertable = true, updatable = false)
	private String code;

	@Column(name = "category", columnDefinition = CODE, nullable = true, insertable = true, updatable = false)
	private String category;

	@Column(name = "created", nullable = false, insertable = true, updatable = false)
	private Timestamp created;

	@Column(name = "used", nullable = false, insertable = true, updatable = true)
	private Timestamp used;

	public TenantEntity getTenant() {
		return tenant;
	}

	public void setTenant(TenantEntity tenant) {
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