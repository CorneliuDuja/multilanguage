package md.zamolxis.multilanguage.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import md.zamolxis.multilanguage.entity.ResourceEntity;
import md.zamolxis.multilanguage.entity.predicate.ResourcePredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.ResourceService;

@RestController
@RequestMapping("/Resource")
public class ResourceRestController {

	@Autowired
	public ResourceService resourceService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@Validated @RequestBody ResourceEntity resource) throws MultilanguageException {
		return new ResponseEntity<>(resourceService.create(resource), HttpStatus.OK);
	}

	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ResponseEntity<?> read(@Validated @RequestBody ResourceEntity resource) throws MultilanguageException {
		return new ResponseEntity<>(resourceService.read(resource), HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@Validated @RequestBody ResourceEntity resource) throws MultilanguageException {
		return new ResponseEntity<>(resourceService.update(resource), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@Validated @RequestBody ResourceEntity resource) throws MultilanguageException {
		resourceService.delete(resource);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ResponseEntity<GenericOutput<ResourceEntity>> select(@Validated @RequestBody ResourcePredicate predicate)
			throws MultilanguageException {
		return new ResponseEntity<>(resourceService.select(predicate), HttpStatus.OK);
	}

}
