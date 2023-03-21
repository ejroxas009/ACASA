package com.acasa.acasaApp.appuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
	
	private final AppUserRepo appUserRepo;
	
	@Autowired
	public AppUserService(AppUserRepo appUserRepo) {
		super();
		this.appUserRepo = appUserRepo;
	}
	
	
	public List<AppUser> getAllAppUser(){
		return appUserRepo.findAll();
	}
	
	public AppUser getUserById(Long userId) throws NotFoundException {
		return appUserRepo.findById(userId).orElseThrow(NotFoundException :: new);
	}
	
}
