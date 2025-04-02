package com.RetailerReward.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name cannot be empty")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	private String email;
}
