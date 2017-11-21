package md.zamolxis.multilanguage.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import md.zamolxis.multilanguage.entity.TranslationEntity;
import md.zamolxis.multilanguage.entity.predicate.TranslationPredicate;
import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.TranslationService;

@RestController
@RequestMapping("/translation")
public class TranslationRestController {

	@Autowired
	public TranslationService translationService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@Validated @RequestBody TranslationEntity translation)
			throws MultilanguageException {
		return new ResponseEntity<>(translationService.create(translation), HttpStatus.OK);
	}

	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ResponseEntity<?> read(@Validated @RequestBody TranslationEntity translation) throws MultilanguageException {
		return new ResponseEntity<>(translationService.read(translation), HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@Validated @RequestBody TranslationEntity translation)
			throws MultilanguageException {
		return new ResponseEntity<>(translationService.update(translation), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@Validated @RequestBody TranslationEntity translation)
			throws MultilanguageException {
		translationService.delete(translation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ResponseEntity<GenericOutput<TranslationEntity>> select(
			@Validated @RequestBody TranslationPredicate predicate) throws MultilanguageException {
		return new ResponseEntity<>(translationService.select(predicate), HttpStatus.OK);
	}

}
