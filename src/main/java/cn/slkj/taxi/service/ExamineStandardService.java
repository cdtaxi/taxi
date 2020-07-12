package cn.slkj.taxi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.slkj.taxi.entity.ExamineStandard;
import cn.slkj.taxi.mapper.ExamineStandardMapper;
import cn.slkj.taxi.util.PageData;

/*
 * 
 */
@Service
public class ExamineStandardService {
	@Autowired
	private ExamineStandardMapper mapper;

	public List<ExamineStandard> getStandardList(PageData pd) {
		return mapper.getStandardList(pd);
	}
	
	public ExamineStandard getStandardById(String id) {
		return mapper.getStandardById(id);
	}

	public int delete(String id) {
		return mapper.delete(id);
	}

	public int edit(PageData pd) {
		return mapper.edit(pd);
	}

	public int save(PageData pd) {
		return mapper.save(pd);
	}

}
