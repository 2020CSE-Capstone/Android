package com.mobile.capstonedesign.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMemberRequestDTO {
	private String email;
	private String password;
	private String name;
	private String phoneNumber;
}