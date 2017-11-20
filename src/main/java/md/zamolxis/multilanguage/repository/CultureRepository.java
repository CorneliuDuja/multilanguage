package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.TenantEntity;

public interface CultureRepository extends JpaRepository<CultureEntity, String> {

	@Query("select x from culture x where x.tenant = :tenant and x.code = :code")
	CultureEntity read(@Param("tenant") TenantEntity tenant, @Param("code") String code);

}