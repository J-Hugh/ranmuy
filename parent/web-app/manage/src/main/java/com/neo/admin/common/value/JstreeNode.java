package com.neo.admin.common.value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jstree 插件数据实体
 * @author Neo
 *
 */
public class JstreeNode {

	private String id;
	
	/**
	 * 节点名称
	 */
	private String text;
	
	/**
	 * 节点图标 （glyphicon）
	 */
	private String icon;
	
	/**
	 * 节点声明 
	 * opened
	 * selected
	 */
	private Map<String, Object> state; 
	
	/**
	 * 子节点集合
	 */
	private List<JstreeNode> children;


	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Map<String, Object> getState() {
		if(state == null){
			state=new HashMap<String, Object>() ;
		}
		return state;
	}

	public void setState(Map<String, Object> state) {
		this.state = state;
	}

	public List<JstreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<JstreeNode> children) {
		this.children = children;
	}
	
}
