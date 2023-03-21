package com.acasa.acasaApp.address;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	private final AddressRepo addressRepo;

	@Autowired
	public AddressService(AddressRepo addressRepo) {
		super();
		this.addressRepo = addressRepo;
	}
	
	
	public List<Address> getAllAddress(){
		return addressRepo.findAll();
	}
	
	public Address getAddressById(Long id) throws NotFoundException {
		return addressRepo.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public Address addAddress(Address address) {
		address.setCreatedAt(LocalDate.now());
		return addressRepo.save(address);
	}

	public Address editAddress(Long id, Address editedAddress) throws NotFoundException {
		Address addressInDb = addressRepo.findById(id).orElseThrow(NotFoundException::new);
		addressInDb.setHouseNo(editedAddress.getHouseNo());
		addressInDb.setStreetAddress(editedAddress.getStreetAddress());
		addressInDb.setBarangay(editedAddress.getBarangay());
		addressInDb.setCity(editedAddress.getCity());
		addressInDb.setRegion(editedAddress.getRegion());
		addressInDb.setModifiedAt(LocalDate.now());
		return addressRepo.save(addressInDb);
	}
	
	public Address setDefaultAddress(Long addressId, Long userId) {
		List<Address> userAddresses = addressRepo.findAll();
		 Address defaultAddress = new Address();
		 
		 
				userAddresses.stream()
						.filter(address->address.getAppUser().getUserId().equals(userId))
						.collect(Collectors.toList());
//						.forEach(address-> {
//							if(address.getAddressId() == addressId) {
//								address.setIsDefault(true);
//								addressRepo.save(address);
//								
//							}else {
//								address.setIsDefault(false);
//								addressRepo.save(address);
//							}
//						});
		
		for(Address address : userAddresses) {
			if(address.getAddressId() == addressId) {
				address.setIsDefault(true);
				addressRepo.save(address);
				defaultAddress = address;
			}else {
				address.setIsDefault(false);
				addressRepo.save(address);
			}
		}
		
		return defaultAddress;
	}
	
}
