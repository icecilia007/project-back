package com.brzas.project.alternative.service;

import com.brzas.project.alternative.models.Alternative;
import com.brzas.project.alternative.repository.AlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlternativeServiceImpl implements AlternativeService {
    private final AlternativeRepository alternativeRepository;

    @Autowired
    public AlternativeServiceImpl(AlternativeRepository alternativeRepository) {
        this.alternativeRepository = alternativeRepository;
    }

    @Override
    public List<Alternative> getAllAlternatives() {
        return alternativeRepository.findAll();
    }

    @Override
    public Alternative getAlternativeById(long id) {
        Optional<Alternative> alternativeOptional = alternativeRepository.findById(id);
        return alternativeOptional.orElse(null);
    }

    @Override
    public Alternative createAlternative(Alternative alternative) {
        validateEntry(alternative);

        return alternativeRepository.save(alternative);
    }

    private static void validateEntry(Alternative alternative) {
        if (alternative.getDescription().isEmpty() || alternative.getOption() == 0 || alternative.getOption()==' ') {
            throw new IllegalArgumentException("The request is not valid. Description and option are required.");
        }
    }

    @Override
    public Alternative updateAlternative(Alternative alternative) {
        validateEntry(alternative);
        return alternativeRepository.save(alternative);
    }
    @Override
    public Alternative deleteAlternative(long id) {
        Optional<Alternative> optionalAlternative = alternativeRepository.findById(id);

        if (optionalAlternative.isPresent()) {
            Alternative alternative = optionalAlternative.get();
            alternativeRepository.deleteById(id);
            return alternative;
        } else {
            throw new IllegalArgumentException("Alternative not found");
        }
    }
}
