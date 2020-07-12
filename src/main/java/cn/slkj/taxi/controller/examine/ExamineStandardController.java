package cn.slkj.taxi.controller.examine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.Examine;
import cn.slkj.taxi.entity.ExamineStandard;
import cn.slkj.taxi.service.ExamineStandardService;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.Tree;

@Controller
@RequestMapping("/examineStandard")
public class ExamineStandardController extends BaseController {
	
	@Autowired
	private ExamineStandardService service;

	@RequestMapping("/listPage")
	public String listPage() {
		return "examine/examine_standard_list";
	}
	
	@RequestMapping({ "/goAdd" })
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("examine/examine_standard_edit");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/goEdit" })
	public ModelAndView goEdit() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			ExamineStandard es = service.getStandardById(pd.getString("id"));
			mv.setViewName("examine/examine_standard_edit");
			mv.addObject("pd", es);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/listTree")
	public List<ExamineStandard> listTree() {
		PageData pd = getPageData();
		List<ExamineStandard> list = service.getStandardList(pd);
		return makeTree(list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCombotree")
	public List<Tree> getCombotree() {
		PageData pd = getPageData();
		List<ExamineStandard> list = makeTree(service.getStandardList(pd));
		return toTree(list);
	}

	

	@ResponseBody
	@RequestMapping(value = { "/save" }, method = { RequestMethod.POST })
	public boolean add() throws Exception {
		PageData pd = new PageData();
		try {
			pd = getPageData();
			String id = pd.getString("id");
			int rti = 0;
			if (Tools.notEmpty(id)) {
				rti = service.edit(pd);
			} else {
				rti = service.save(pd);
			}
			return rti > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}

	@ResponseBody
	@RequestMapping({ "/delete" })
	public boolean delete(@RequestParam String id) throws Exception {
		try {
			int i = service.delete(id);
			return i > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}

	/**
	 * 组装树形列表 treegrid
	 * 
	 * @param list
	 * @return
	 */
	private List<ExamineStandard> makeTree(List<ExamineStandard> list) {
		List<ExamineStandard> parent = new ArrayList<ExamineStandard>();
		for (ExamineStandard e : list) {
			if (e.getStype().equals("0")) {
				e.setChildren(new ArrayList<ExamineStandard>(0));
				parent.add(e);
			}
		}
		list.removeAll(parent);
		makeChildren(parent, list);
		return parent;
	}

	private void makeChildren(List<ExamineStandard> parent, List<ExamineStandard> children) {
		if (children.isEmpty()) {
			return;
		}
		List<ExamineStandard> tmp = new ArrayList<ExamineStandard>();
		for (ExamineStandard c1 : parent) {
			for (ExamineStandard c2 : children) {
				c2.setChildren(new ArrayList<ExamineStandard>(0));
				if(!c2.getOrdinal().equals(c1.getOrdinal())){
				if (c1.getOrdinal().equals(c2.getOrdinal().substring(0, 1))) {
					c1.getChildren().add(c2);					
					tmp.add(c2);
				}
				}
			}
		}
		children.removeAll(tmp);
		makeChildren(tmp, children);
	}

	private List<Tree> toTree(List<ExamineStandard> list) {
		List<Tree> trees = new ArrayList<Tree>();
		for (ExamineStandard e : list) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("score", e.getScoring());
			Tree t = new Tree();
			t.setId(e.getOrdinal());
			t.setText(e.getStandard());
			t.setAttributes(hashMap);
			t.setChildren(toTree(e.getChildren()));
			trees.add(t);
		}
		return trees;
	}

	
}
