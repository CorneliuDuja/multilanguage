package md.zamolxis.multilanguage.entity;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class GenericEntity {

	public static final String UUID = "VARCHAR(36)";
	public static final String CODE = "VARCHAR(200)";
	public static final String TEXT = "TEXT";
	public static final CharSequence DELIMITER = ", ";

	public static final int MIN_VARCHAR = 1;
	public static final int MAX_VARCHAR = 200;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = UUID, nullable = false, unique = true, insertable = true, updatable = false)
	private String id;

	@Column(name = "version", nullable = false, insertable = true, updatable = true)
	private Timestamp version;

	@PrePersist
	protected void onCreate() {
		Calendar calendar = Calendar.getInstance();
		version = new Timestamp(calendar.getTimeInMillis());
	}

	@PreUpdate
	protected void onUpdate() {
		Calendar calendar = Calendar.getInstance();
		version = new Timestamp(calendar.getTimeInMillis());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getVersion() {
		return version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object object) {
		boolean equals = false;
		if (id != null && object != null && this.getClass().equals(object.getClass())) {
			GenericEntity generic = (GenericEntity) object;
			if (generic != null && id.equals(generic.getId())) {
				equals = true;
			}
		}
		return equals;
	}

}