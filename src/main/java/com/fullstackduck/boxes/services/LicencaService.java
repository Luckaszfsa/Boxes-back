package com.fullstackduck.boxes.services;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstackduck.boxes.entities.Licenca;
import com.fullstackduck.boxes.entities.enums.TipoLicenca;
import com.fullstackduck.boxes.repositories.LicencaRepository;
import com.fullstackduck.boxes.repositories.UsuarioRepository;
import com.fullstackduck.boxes.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service // Component registration
public class LicencaService {

	@Autowired
    private LicencaRepository licencaRepository;
    
    public List<Licenca> findAll() {
        return licencaRepository.findAll();
    }

    public Licenca findById(Long id) {
        System.out.println("ID recebido: " + id);
        try {
            Optional<Licenca> obj = licencaRepository.findById(id);
            return obj.orElseThrow(() -> new ResourceNotFoundException("Licença não encontrada com o id: " + id));
        } catch (NoSuchElementException ex) {
            throw new ResourceNotFoundException("Licença não encontrada com o id: " + id);
        }
    }
    
    public Licenca inserirLicenca(Licenca licenca) {
		return licencaRepository.save(licenca);
	}
    
    public Licenca atualizarStatusLicenca(Long id, Licenca obj) {
    	try {
			Licenca entity = licencaRepository.getReferenceById(id);
			atualizarStatusLicenca(entity, obj);
			return licencaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

    /*@Transactional
    public Instant dataValidade(Long id) {
    	try {
    		Licenca entity = licencaRepository.getReferenceById(id);
	        TipoLicenca tipoLicenca = entity.getTipoLicenca();
	        Instant dataValidade = Instant.now();
	        if (entity != null) {
	        	if (tipoLicenca == TipoLicenca.GRATUITA) {
	        		dataValidade = dataValidade.plus(Duration.ofDays(30));
	        	} else if (tipoLicenca == TipoLicenca.MENSAL) {
	                dataValidade = dataValidade.plus(Duration.ofDays(30));
	            } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
	                dataValidade = dataValidade.plus(Duration.ofDays(180));
	            } else if (tipoLicenca == TipoLicenca.ANUAL) {
	                dataValidade = dataValidade.plus(Duration.ofDays(365));
	            }
	        	entity.setDataValidade(dataValidade);
	        }
	        return entity.getDataValidade();
        } catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    }

    @Transactional
    public Integer diasLicenca(Long id) {
    	Integer diasLicenca;
    	try {
    		Licenca entity = licencaRepository.getReferenceById(id);
    		long dias = ChronoUnit.DAYS.between(entity.getDataAquisicao(), entity.getDataValidade());
    		diasLicenca = (int) dias;
    		entity.setDiasLicenca(diasLicenca);
    	} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    	return diasLicenca;
    }*/
    
    public Licenca renovarLicenca(Long id, Licenca obj) {
    	try {
			Licenca entity = licencaRepository.getReferenceById(id);
			renovarLicenca(entity, obj);
			return licencaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
    }

	
	//métodos auxiliares
    private void atualizarStatusLicenca(Licenca entity, Licenca obj) {
		entity.setStatusLicenca(obj.getStatusLicenca());
	}
    
	private void renovarLicenca(Licenca entity, Licenca obj) {
        TipoLicenca tipoLicenca = entity.getTipoLicenca();
        Instant novaDataValidadeLicenca = entity.getDataValidade();
        Integer novoDiasLicenca = entity.getDiasLicenca();
        if (tipoLicenca == TipoLicenca.MENSAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(30));
            novoDiasLicenca += 30; 
        } else if (tipoLicenca == TipoLicenca.SEMESTRAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(180));
            novoDiasLicenca += 180;
        } else if (tipoLicenca == TipoLicenca.ANUAL) {
            novaDataValidadeLicenca = novaDataValidadeLicenca.plus(Duration.ofDays(365));
            novoDiasLicenca += 365;
        }
        entity.setDataValidade(novaDataValidadeLicenca);
        entity.setDiasLicenca(novoDiasLicenca);
    }
}
