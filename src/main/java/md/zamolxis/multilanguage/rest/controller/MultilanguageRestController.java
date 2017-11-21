package md.zamolxis.multilanguage.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import md.zamolxis.multilanguage.exception.MultilanguageException;
import md.zamolxis.multilanguage.service.MultilanguageService;

@RestController
@RequestMapping("/multilanguage")
public class MultilanguageRestController {

	@Autowired
	public MultilanguageService multilanguageService;

	@RequestMapping(value = "/translate", method = RequestMethod.GET)
	public ResponseEntity<?> translate(@RequestParam String tenant, @RequestParam String culture,
			@RequestParam String resource, @RequestParam Optional<String> category) throws MultilanguageException {
		return new ResponseEntity<>(
				multilanguageService.translate(tenant, culture, resource, category.isPresent() ? category.get() : null),
				HttpStatus.OK);
	}

}
