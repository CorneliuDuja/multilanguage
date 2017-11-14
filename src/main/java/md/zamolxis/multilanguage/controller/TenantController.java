package md.zamolxis.multilanguage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import md.zamolxis.multilanguage.entity.TenantEntity;
import md.zamolxis.multilanguage.entity.predicate.TenantPredicate;
import md.zamolxis.multilanguage.exception.ServiceException;
import md.zamolxis.multilanguage.service.GenericOutput;
import md.zamolxis.multilanguage.service.TenantService;

@RestController
@RequestMapping("/Tenant")
public class TenantController {

	@Autowired
	public TenantService tenantService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody TenantEntity tenant) throws ServiceException {
		return new ResponseEntity<>(tenantService.create(tenant), HttpStatus.OK);
	}

	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ResponseEntity<?> read(@RequestBody TenantEntity tenant) throws ServiceException {
		return new ResponseEntity<>(tenantService.read(tenant), HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody TenantEntity tenant) throws ServiceException {
		return new ResponseEntity<>(tenantService.update(tenant), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestBody TenantEntity tenant) throws ServiceException {
		tenantService.delete(tenant);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ResponseEntity<GenericOutput<TenantEntity>> select(@RequestBody TenantPredicate predicate)
			throws ServiceException {
		return new ResponseEntity<>(tenantService.select(predicate), HttpStatus.OK);
	}

}
