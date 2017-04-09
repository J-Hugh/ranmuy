package com.neo.admin.common.value;

import java.util.List;
import java.util.Map;

/**
 * BootstrapTree js插件数据格式
 * @author Neo
 *
 */
public class BootstrapTree {

	//ID
	private String resourcesId;
	/**
	 * 节点文本值
	 */
	private String text;
	
	/**
	 * 路径
	 */
	private String url;
	/**
	 * 节点初始状态
	 * state.checked
	 * state.disabled
	 * state.expanded
	 * state.selected
	 */
	private Map<String, Object> state;
	/**
	 * 节点标签
	 */
	private List<String> tags;
	/**
	 * 子节点
	 */
	private List<BootstrapTree> nodes;
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getResourcesId() {
		return resourcesId;
	}
	public void setResourcesId(String resourcesId) {
		this.resourcesId = resourcesId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Map<String, Object> getState() {
		return state;
	}
	public void setState(Map<String, Object> state) {
		this.state = state;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<BootstrapTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<BootstrapTree> nodes) {
		this.nodes = nodes;
	}
	
	
}
