package com.acasa.acasaApp.notification;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.acasa.acasaApp.appuser.AppUser;
import com.acasa.acasaApp.appuser.AppUserService;

@Service
public class NotificationService {

	private final NotificationRepo notifRepo;
	private final AppUserService appUserService;

	@Autowired
	public NotificationService(NotificationRepo notifRepo, AppUserService appUserService) {
		super();
		this.notifRepo = notifRepo;
		this.appUserService = appUserService;
	}

	
	public List<Notification> getAllNotification(){
		return notifRepo.findAll();
	}
	
	
	public Notification getNotificationById(Long notifId) throws NotFoundException {
		return notifRepo.findById(notifId).orElseThrow(NotFoundException:: new);
		
	}
	
	public List<Notification> getNotificationByUserId(Long userId) {
		return this.getAllNotification()
				.stream()
				.filter(notif-> notif.getAppuser().getUserId().equals(userId))
				.collect(Collectors.toList());
	}
	
	public Notification createNotification(Long userId, String notifDescription) throws NotFoundException {
		Notification notification = new Notification();
		AppUser user = appUserService.getUserById(userId);
		notification.setAppuser(user);
		notification.setDescription(notifDescription);
		notification.setIsRead(false);
		notification.setCreatedAt(LocalDate.now());
		return notifRepo.save(notification);
	}
	
	public Notification setReadTrue(Long notifId) throws NotFoundException {
		Notification notifInDb = this.getNotificationById(notifId);
		notifInDb.setIsRead(true);
		return notifRepo.save(notifInDb);
		
	}
	
}
