package com.acasa.acasaApp.address;

import java.rmi.ServerException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {
private final AddressService addressService;


	


	@Autowired
	public AddressController(AddressService addressService) {
	super();
	this.addressService = addressService;
}


	@GetMapping
	public ResponseEntity<List<Address>> getAddress(){
		return ResponseEntity.ok().body(addressService.getAllAddress());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable Long id) throws NotFoundException{
		return ResponseEntity.ok().body(addressService.getAddressById(id));
	}
	
	@PostMapping
	public ResponseEntity addAddress(@RequestBody Address address) throws ServerException{
		Address returnedAddress = addressService.addAddress(address);
		if(returnedAddress == null) {
			throw new ServerException("Address was not recorded");
		}else {
			return ResponseEntity.ok(HttpStatus.CREATED);
		}
	}
	
	
	@PutMapping(path = "/{id}")
	public ResponseEntity editAddress(@PathVariable Long id, @RequestBody Address address) throws ServerException, NotFoundException {
		Address returnedAddress = addressService.editAddress(id, address);
		if(
				!returnedAddress.getHouseNo().equals(address.getHouseNo()) 
				|| !returnedAddress.getStreetAddress().equals(address.getStreetAddress())
				|| !returnedAddress.getBarangay().equals(address.getBarangay())
				|| !returnedAddress.getCity().equals(address.getCity())
				|| !returnedAddress.getRegion().equals(address.getRegion())
				
				) {
			throw new ServerException("Address was not edited");
		}else {
			return ResponseEntity.ok("Address is successfully edited");
		}
		
	}
	
	@PutMapping(path = "set-address-default/{addressId}/{userId}")
	public ResponseEntity setDefaultAddress(@PathVariable Long addressId, @PathVariable Long userId) throws ServerException {
		Address returnedAddress = addressService.setDefaultAddress(addressId, userId);
		if(returnedAddress == null) throw new ServerException(null);
		return ResponseEntity.ok("Successfully set Default Address");
	}
	
}
