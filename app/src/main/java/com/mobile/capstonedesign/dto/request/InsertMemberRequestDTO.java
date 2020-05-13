package com.mobile.capstonedesign.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InsertMemberRequestDTO {
	private String email;
	private String password;
	private String name;
	private String phoneNumber;
}
