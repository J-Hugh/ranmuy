package com.neo.espionage.modular.wechatRecord.domain;

import java.sql.Timestamp;

public class WechatRecord {
    
    /**
      * 
      **/
	private Long id;
    /**
      * 
      **/
	private String fromName;
    /**
      * 
      **/
	private String toName;
    /**
      * 
      **/
	private String content;
    /**
      * 
      **/
	private java.sql.Timestamp recordDate;
    /**
      * 
      **/
	private java.sql.Timestamp createDate;


	public WechatRecord() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Timestamp recordDate) {
		this.recordDate = recordDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\":")
				.append(id);
		sb.append(",\"fromName\":\"")
				.append(fromName).append('\"');
		sb.append(",\"toName\":\"")
				.append(toName).append('\"');
		sb.append(",\"content\":\"")
				.append(content).append('\"');
		sb.append(",\"recordDate\":\"")
				.append(recordDate).append('\"');
		sb.append(",\"createDate\":\"")
				.append(createDate).append('\"');
		sb.append('}');
		return sb.toString();
	}
}