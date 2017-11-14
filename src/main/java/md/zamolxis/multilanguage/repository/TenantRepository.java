package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.model.TenantModel;

public interface TenantRepository extends JpaRepository<TenantModel, String> {

	@Query("select x from tenant x where x.code = :code")
	TenantModel find(@Param("code") String code);

}