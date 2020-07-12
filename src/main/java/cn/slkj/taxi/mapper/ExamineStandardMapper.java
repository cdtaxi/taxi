package cn.slkj.taxi.mapper;

import java.util.List;
import java.util.Map;

import cn.slkj.taxi.entity.ExamineStandard;
import cn.slkj.taxi.util.PageData;

public interface ExamineStandardMapper {

	public List<ExamineStandard> getStandardList(PageData pd);
	
	public ExamineStandard getStandardById(String id);

	public int save(PageData pd);

	public int edit(PageData pd);

	public int delete(String id);

}
