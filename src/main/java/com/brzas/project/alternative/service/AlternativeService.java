package com.brzas.project.alternative.service;

import com.brzas.project.alternative.models.Alternative;
import java.util.List;

public interface AlternativeService {
    Alternative getAlternativeById(long id);
    List<Alternative> getAllAlternatives();
    Alternative createAlternative(Alternative alternative);
    Alternative updateAlternative(Alternative alternative);
    Alternative deleteAlternative(long id);

}
