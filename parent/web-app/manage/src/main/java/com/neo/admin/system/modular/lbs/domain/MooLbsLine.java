package com.neo.admin.system.modular.lbs.domain;

import java.util.List;

public class MooLbsLine{
    
    /**
      * 
      **/
	private Long id;
    /**
      * 
      **/
	private Long line_id;
    /**
      * 线路名称
      **/
	private String name;
    /**
      * 线路名称
      **/
	private String line_name;
    /**
      * 首发站名称
      **/
	private String front_name;
    /**
      * 起点拼音
      **/
	private String front_spell;
    /**
      * 终点站名称
      **/
	private String terminal_name;
    /**
      * 终点站拼音名
      **/
	private String terminal_spell;
    /**
      * 公交车公司名称
      **/
	private String company;
    /**
      * 线路长度
      **/
	private String length;
    /**
      * 用文字来描述无特定发车规则（即发车无规律）的发车信息
      **/
	private String time_desc;
    /**
      * 早班车时间
      **/
	private String start_time;
    /**
      * 末班车时间，20:00
      **/
	private String end_time;
    /**
      * 描述性信息
      **/
	private String description;
    /**
      * 总票价
      **/
	private String total_price;
    /**
      * 是否环线，0-否，1-是
      **/
	private String loop;
    /**
      * 是否自动（无人）售票，0-人工售票，1-无人售票
      **/
	private String auto;
    /**
      * 是否可以使用电子售票（公交卡），0-不可以使用，1-可以使用
      **/
	private String ic_card;
    /**
      * 是否双层, 0-否, 1-是
      **/
	private String double_deck;
    /**
      * 公共交通线是否路经高速道路，0-不经过，1-经过
      **/
	private String express_way;
    /**
      * 线路状态, 0-停运，1-正常, 包含在bus标识内
      **/
	private String status;
    /**
      * 是否有空调，0-非空调车，1-空调车
      **/
	private String air;
    /**
      * 是否可用月票. 0-不可用;1-可用
      **/
	private String commutation_ticket;

	
	private List<MooLbsStation> stationdes;
	
	
	
	
	public MooLbsLine(String line_name) {
		super();
		this.line_name = line_name;
	}


	public MooLbsLine() {
		super();
	}

	
	public List<MooLbsStation> getStationdes() {
		return stationdes;
	}


	public void setStationdes(List<MooLbsStation> stationdes) {
		this.stationdes = stationdes;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
	public String getTime_desc() {
		return time_desc;
	}


	public void setTime_desc(String time_desc) {
		this.time_desc = time_desc;
	}


	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLoop() {
		return this.loop;
	}

	public void setLoop(String loop) {
		this.loop = loop;
	}
	public String getAuto() {
		return this.auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getAir() {
		return this.air;
	}

	public void setAir(String air) {
		this.air = air;
	}


	public Long getLine_id() {
		return line_id;
	}


	public void setLine_id(Long line_id) {
		this.line_id = line_id;
	}


	public String getLine_name() {
		return line_name;
	}


	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}


	public String getFront_name() {
		return front_name;
	}


	public void setFront_name(String front_name) {
		this.front_name = front_name;
	}


	public String getFront_spell() {
		return front_spell;
	}


	public void setFront_spell(String front_spell) {
		this.front_spell = front_spell;
	}


	public String getTerminal_name() {
		return terminal_name;
	}


	public void setTerminal_name(String terminal_name) {
		this.terminal_name = terminal_name;
	}


	public String getTerminal_spell() {
		return terminal_spell;
	}


	public void setTerminal_spell(String terminal_spell) {
		this.terminal_spell = terminal_spell;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


	public String getEnd_time() {
		return end_time;
	}


	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}


	public String getTotal_price() {
		return total_price;
	}


	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}


	public String getIc_card() {
		return ic_card;
	}


	public void setIc_card(String ic_card) {
		this.ic_card = ic_card;
	}


	public String getDouble_deck() {
		return double_deck;
	}


	public void setDouble_deck(String double_deck) {
		this.double_deck = double_deck;
	}


	public String getExpress_way() {
		return express_way;
	}


	public void setExpress_way(String express_way) {
		this.express_way = express_way;
	}


	public String getCommutation_ticket() {
		return commutation_ticket;
	}


	public void setCommutation_ticket(String commutation_ticket) {
		this.commutation_ticket = commutation_ticket;
	}

}