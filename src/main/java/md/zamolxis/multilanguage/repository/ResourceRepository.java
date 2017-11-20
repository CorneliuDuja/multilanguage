package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.TenantEntity;

public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {

	@Query("select x from resource x where x.tenant = :tenant and x.code = :code and x.category = :category")
	ResourceEntity read(@Param("tenant") TenantEntity tenant, @Param("code") String code,
			@Param("category") String category);

}