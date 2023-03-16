package com.fullstackduck.boxes.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.enums.StatusLicenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;

//Classe auxiliar de configuração para o perfil de testes
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private LicencaRepository licencaRepository;

	@Override
	public void run(String... args) throws Exception {
		Licenca l1 = new Licenca(null, StatusLicenca.ATIVA, null, TipoLicenca.AUNAL, 98.90);
		
		licencaRepository.saveAll(Arrays.asList(l1));
	}
}