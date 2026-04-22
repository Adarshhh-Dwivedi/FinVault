package com.example.SpringBank.api.v1.request.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank; // Better for Strings
import jakarta.validation.constraints.Pattern;  // For SSN and Phone validation
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Modernized for SpringBank.
 * Ensuring data integrity for PII (Personally Identifiable Information).
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCustomerRequest {

    @NotBlank(message = "Name is required and cannot be empty")
    private String name;

    @NotBlank(message = "SSN is required")
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{4}$", message = "SSN must follow the format XXX-XX-XXXX")
    private String ssn;

    private String address1;
    private String address2;
    private String city;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Contact number must be a valid phone number")
    private String contactNumber;
}