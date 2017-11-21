package md.zamolxis.multilanguage.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import md.zamolxis.multilanguage.entity.CultureEntity;
import md.zamolxis.multilanguage.entity.predicate.CulturePredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.service.CultureService;
import md.zamolxis.multilanguage.service.GenericOutput;

@RestController
@RequestMapping("/culture")
public class CultureRestController {

	@Autowired
	public CultureService cultureService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@Validated @RequestBody CultureEntity culture) throws MultilanguageException {
		return new ResponseEntity<>(cultureService.create(culture), HttpStatus.OK);
	}

	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ResponseEntity<?> read(@Validated @RequestBody CultureEntity culture) throws MultilanguageException {
		return new ResponseEntity<>(cultureService.read(culture), HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@Validated @RequestBody CultureEntity culture) throws MultilanguageException {
		return new ResponseEntity<>(cultureService.update(culture), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@Validated @RequestBody CultureEntity culture) throws MultilanguageException {
		cultureService.delete(culture);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ResponseEntity<GenericOutput<CultureEntity>> select(@Validated @RequestBody CulturePredicate predicate)
			throws MultilanguageException {
		return new ResponseEntity<>(cultureService.select(predicate), HttpStatus.OK);
	}

}
