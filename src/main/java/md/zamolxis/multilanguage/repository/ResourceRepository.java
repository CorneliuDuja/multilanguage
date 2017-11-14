package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.model.ResourceModel;
import md.zamolxis.multilanguage.model.TenantModel;

public interface ResourceRepository extends JpaRepository<ResourceModel, String> {

	@Query("select x from resource x where x.tenant = :tenant and x.code = :code and x.category = :category")
	ResourceModel find(@Param("tenant") TenantModel tenant, @Param("code") String code, @Param("category") String category);

}