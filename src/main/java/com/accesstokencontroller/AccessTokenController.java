package com.accesstokencontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.AccessTokenServiceImplementation.AccessTokenServiceImplementation;
import com.accesstokenservicelayer.AccessTokenServiceLayer;
import com.dto.AccessTokenPojo;

@RestController
public class AccessTokenController {
	private final AccessTokenServiceLayer accessTokenServiceLayer;
	
	@Autowired
	public AccessTokenController(AccessTokenServiceLayer accessTokenServiceLayer)
	{
		this.accessTokenServiceLayer=accessTokenServiceLayer;
	}
	
	@Autowired
	private AccessTokenServiceImplementation accessTokenServiceImplementation;
	
	AccessTokenPojo accessTokenPojo;
	
	 @GetMapping("/accesstoken/{clientId}")
	 @ResponseBody
	public String findByclientId(@PathVariable String clientId,String secretCode) throws Exception{
		 String v= accessTokenServiceImplementation.findByclientId(clientId);
		 Date now = new Date();
	     if(v==null)
	     {
			getData( clientId,secretCode);
			return v;	    	 
	     }
	     else {
	    	 return v;
	     }	      
	}
	  
	
	   
	@GetMapping("/app")
	public String getData(@RequestParam String clientId,@RequestParam String secretCode) throws Exception{ 
		String accesToken=accessTokenServiceLayer.consumeAPI(clientId,secretCode);
		AccessTokenPojo data=new AccessTokenPojo();
		data.setClientId(clientId);
		data.setSecretCode(secretCode);
		data.setAccess_token(accesToken);
		accessTokenServiceImplementation.saveAccessToken(data);
		return null;
	}
	
	
	@PostMapping("save")
	public AccessTokenPojo saveAccessToken(AccessTokenPojo accesTokenPojo)
	{
		
		System.out.println(accesTokenPojo.getAccess_token()+ " "+accesTokenPojo.getClientId());
		return null;
		
	}

	
}
