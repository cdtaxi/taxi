package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.cases.Anjian;
import cn.slkj.taxi.entity.cases.Anyou;
import cn.slkj.taxi.entity.cases.Zfry;
import cn.slkj.taxi.util.PageData;

public interface CaseMapper {

	// 基本信息查询
	PageData listJbxx(PageData pd);

	// 案件查询
	List<PageData> caseList(PageData pd);

	List<Anjian> caseListPage(HashMap<String, Object> hashMap, PageBounds pageBounds);

	// 执法人员
	List<Zfry> zfryList(HashMap<String, Object> hashMap, PageBounds pageBounds);

	// 案由
	List<Anyou> anyouList(HashMap<String, Object> hashMap, PageBounds pageBounds);

}
