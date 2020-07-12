package cn.slkj.taxi.util;

import java.util.Map;

import org.codehaus.jackson.map.util.JSONPObject;

public class AppUtil {
	protected static Logger logger = Logger.getLogger(AppUtil.class);

	public static Object returnObject(PageData pd, Map map) {
		if (pd.containsKey("callback")) {
			String callback = pd.get("callback").toString();
			return new JSONPObject(callback, map);
		}
		return map;
	}
}
