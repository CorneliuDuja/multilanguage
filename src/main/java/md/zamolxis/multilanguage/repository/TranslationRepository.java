package md.zamolxis.multilanguage.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import md.zamolxis.multilanguage.model.CultureModel;
import md.zamolxis.multilanguage.model.ResourceModel;
import md.zamolxis.multilanguage.model.TranslationModel;

public interface TranslationRepository extends JpaRepository<TranslationModel, String> {

	@Query("select x from translation x where x.culture = :culture and x.resource = :resource")
	TranslationModel find(@Param("culture") CultureModel culture, @Param("resource") ResourceModel resource);

}