package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.cases.Anjian;
import cn.slkj.taxi.entity.cases.Anyou;
import cn.slkj.taxi.entity.cases.Zfry;
import cn.slkj.taxi.mapper.CaseMapper;
import cn.slkj.taxi.util.PageData;

@Service
@Transactional
public class CaseService {
	@Autowired
	private CaseMapper mapper;

	public PageData listJbxx(PageData pd) {
		return mapper.listJbxx(pd);
	}

	public List<PageData> caseList(PageData pd) {
		return mapper.caseList(pd);
	}

	public List<Zfry> zfryList(HashMap<String, Object> hashMap, PageBounds pageBounds) {
 		return mapper.zfryList(hashMap, pageBounds);
	}

	public List<Anjian> caseListPage(HashMap<String, Object> hashMap, PageBounds pageBounds) {
		
 		return mapper.caseListPage(hashMap, pageBounds);
	}

	public List<Anyou> anyouList(HashMap<String, Object> hashMap, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return mapper.anyouList(hashMap, pageBounds);
	}

}
