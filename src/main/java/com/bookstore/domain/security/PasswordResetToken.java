package com.bookstore.domain.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bookstore.domain.User;

@Entity
public class PasswordResetToken {
	private static int EXPIRATION=60*24;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	private String token;
	
	@OneToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	private Date expiryDate;
	
	public PasswordResetToken(){}
	
	public PasswordResetToken(final String token, final User user){
		super();
		this.token=token;
		this.user=user;
		this.expiryDate=calculateExpiryDate(EXPIRATION);
	}
	
	private Date calculateExpiryDate(final int expiryTimeInMinutes ){
		final Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE,expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	public void updateToken(final String token){
		this.token=token;
		this.expiryDate=calculateExpiryDate(EXPIRATION);
	}

	public static int getEXPIRATION() {
		return EXPIRATION;
	}

	public static void setEXPIRATION(int eXPIRATION) {
		EXPIRATION = eXPIRATION;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "PasswordResetToken [Id=" + Id + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate
				+ "]";
	}
	
	
}
