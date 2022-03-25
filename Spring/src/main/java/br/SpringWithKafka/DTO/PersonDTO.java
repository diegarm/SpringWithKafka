package br.SpringWithKafka.DTO;

import lombok.Data;

@Data
public class PersonDTO implements CommonDTO{
	
	private String firstName;
	
	private String lastName;
	
	private String fiscalNumber;

	@Override
	public String getType() {
		return "PersonDTO";
	}
}