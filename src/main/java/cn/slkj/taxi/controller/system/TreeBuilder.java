package cn.slkj.taxi.controller.system;

import java.util.ArrayList;
import java.util.List;

import cn.slkj.taxi.entity.Menus;
import net.sf.json.JSONArray;

public class TreeBuilder {
	List<Menus> nodes = new ArrayList<>();

	public List<Menus> buildTree(List<Menus> nodes) {

		TreeBuilder treeBuilder = new TreeBuilder(nodes);

		return treeBuilder.buildJSONTree();
	}

	public TreeBuilder() {
	}

	public TreeBuilder(List<Menus> nodes) {
		super();
		this.nodes = nodes;
	}

	// 构建JSON树形结构
	public List<Menus> buildJSONTree() {
		List<Menus> nodeTree = buildTree();
		// JSONArray jsonArray = JSONArray.fromObject(nodeTree);
		// System.out.println("--------json-------------------");
		// System.out.println(jsonArray.toString());
		// return jsonArray.toString();
		return nodeTree;
	}

	// 构建树形结构
	public List<Menus> buildTree() {
		List<Menus> treeNodes = new ArrayList<>();
		List<Menus> rootNodes = getRootNodes();
		for (Menus rootNode : rootNodes) {
			buildChildNodes(rootNode);
			treeNodes.add(rootNode);
		}
		return treeNodes;
	}

	// 递归子节点
	public void buildChildNodes(Menus node) {
		List<Menus> children = getChildNodes(node);
		if (!children.isEmpty()) {
			for (Menus child : children) {
				buildChildNodes(child);
			}
			node.setMenus(children);
		}
	}

	// 获取父节点下所有的子节点
	public List<Menus> getChildNodes(Menus pnode) {
		List<Menus> childNodes = new ArrayList<>();
		for (Menus n : nodes) {
			if (pnode.getMenuid().equals(n.getParentMenu())) {
				childNodes.add(n);
			}
		}
		return childNodes;
	}

	// 判断是否为根节点
	public boolean rootNode(Menus node) {
		boolean isRootNode = true;
		for (Menus n : nodes) {
			if (node.getParentMenu().equals(n.getMenuid())) {
				isRootNode = false;
				break;
			}
		}
		return isRootNode;
	}

	// 获取集合中所有的根节点
	public List<Menus> getRootNodes() {
		List<Menus> rootNodes = new ArrayList<>();
		for (Menus n : nodes) {
			if (rootNode(n)) {
				rootNodes.add(n);
			}
		}
		return rootNodes;
	}

}
