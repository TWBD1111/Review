package com.ispan.eeit69.service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.repository.LabelRepository;
import com.ispan.eeit69.repository.LabelRepositoryCustom;
import com.ispan.eeit69.service.LabelService;

@Service
@Transactional
public class LabelServiceimpl implements LabelService {
	
	LabelRepository labelRepository;
	LabelRepositoryCustom labelRepositoryCustom;
	
	public LabelServiceimpl(LabelRepository labelRepository, LabelRepositoryCustom labelRepositoryCustom) {
		super();
		this.labelRepository = labelRepository;
		this.labelRepositoryCustom = labelRepositoryCustom;
	}
	

	@Override
	public void resetLabelTable() {
		throw new RuntimeException("本系統未提供此功能");			
	}

	@Override
	public void save(Label label) {
		labelRepository.save(label);
	}

	@Override
	public boolean existsByLabelId(Label label) {
		if (labelRepositoryCustom.isPersist(label)) {
			labelRepositoryCustom.detach(label);
		}
		Label emp = findByLabelId(label.getLabelId());
		return emp!= null;
	}

	@Override
	public boolean isPersist(Label label) {
		boolean ans = labelRepository.existsById(label.getId());
		return ans;
	}

	@Override
	public void detach(Label label) {
		labelRepositoryCustom.detach(label);		
	}

	@Override
	public void update(Label label) {
		labelRepository.save(label);
		
	}

	@Override
	public void deleteById(Integer id) {
		labelRepository.deleteById(id);	
	}

	@Override
	public List<Label> findAll() {
		return labelRepository.findAll();
	}

	@Override
	public Optional<Label> findById(Integer id) {
		return labelRepository.findById(id);
	}

	@Override
	public Label findByLabelId(String labelId) {
		Label emp = labelRepository.findByLabelId(labelId);
		return emp;
	}
	
	@Override
	public BigDecimal getLabelPriceById(Integer id) {
        Label label = labelRepository.findById(id).orElse(null);
        if (label != null) {
            return label.getLabelPrice();
        } else {
            return null;
        }
    }
	
}
