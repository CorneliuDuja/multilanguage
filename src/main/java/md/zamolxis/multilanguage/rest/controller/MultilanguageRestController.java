package md.zamolxis.multilanguage.rest.controller;

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
@RequestMapping("/Multilanguage")
public class MultilanguageRestController {

	@Autowired
	public MultilanguageService multilanguageService;

	@RequestMapping(value = "/translate", method = RequestMethod.GET)
	public ResponseEntity<?> translate(@RequestParam String tenantCode, @RequestParam String cultureCode,
			@RequestParam String resourceCode, @RequestParam String resourceCategory) throws MultilanguageException {
		return new ResponseEntity<>(
				multilanguageService.translate(tenantCode, cultureCode, resourceCode, resourceCategory), HttpStatus.OK);
	}

}
