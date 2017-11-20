package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.entity.TenantEntity;

public interface TenantRepository extends JpaRepository<TenantEntity, String> {

	@Query("select x from tenant x where x.code = :code")
	TenantEntity read(@Param("code") String code);

}