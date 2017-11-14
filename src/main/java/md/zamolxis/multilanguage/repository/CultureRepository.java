package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.model.CultureModel;
import md.zamolxis.multilanguage.model.TenantModel;

public interface CultureRepository extends JpaRepository<CultureModel, String> {

	@Query("select x from culture x where x.tenant = :tenant and x.code = :code")
	CultureModel find(@Param("tenant") TenantModel tenant, @Param("code") String code);

}