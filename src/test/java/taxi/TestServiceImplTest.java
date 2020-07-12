package taxi;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.slkj.taxi.entity.Menu;
import cn.slkj.taxi.service.MenuService;
import cn.slkj.taxi.util.PageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TestServiceImplTest {
	@Autowired
	MenuService service;

	@Test
	public void test() {
		PageData pd = new PageData();
		List<Menu> list = service.listAllMenu(pd );
		for (Menu menu : list) {
			
			
			System.out.println(menu.toString());
		}
	}
}
