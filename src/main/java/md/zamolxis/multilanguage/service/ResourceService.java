package md.zamolxis.multilanguage.service;

import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.predicate.ResourcePredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;

public interface ResourceService {

	ResourceEntity create(ResourceEntity resource) throws MultilanguageException;

	ResourceEntity read(ResourceEntity resource) throws MultilanguageException;

	ResourceEntity update(ResourceEntity resource) throws MultilanguageException;

	void delete(ResourceEntity resource) throws MultilanguageException;

	GenericOutput<ResourceEntity> select(ResourcePredicate predicate) throws MultilanguageException;

}
