package com.amdocs.media.assignement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.amdocs.media.assignement.dao.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaProducer {

	@Autowired
	KafkaTemplate<Integer, String> kt;
	
	@Autowired
	ObjectMapper obj;
	
	public ResponseEntity<?> sendProfile(Profile p,int type)
	{
		try {
			
			String value=obj.writeValueAsString(p);
			
		ListenableFuture<SendResult<Integer,String>> lf=	kt.sendDefault(type,value);
		
		lf.addCallback(new ListenableFutureCallback<SendResult<Integer,String>>(){

			@Override
			public void onSuccess(SendResult<Integer, String> result) {

				System.out.println(value);
			}

			@Override
			public void onFailure(Throwable ex) {
				
				try {
					throw new Exception("Please try again");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		});
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}

