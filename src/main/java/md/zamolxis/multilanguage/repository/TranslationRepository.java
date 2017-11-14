package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.TranslationEntity;

public interface TranslationRepository extends JpaRepository<TranslationEntity, String> {

	@Query("select x from translation x where x.culture = :culture and x.resource = :resource")
	TranslationEntity find(@Param("culture") CultureEntity culture, @Param("resource") ResourceEntity resource);

}