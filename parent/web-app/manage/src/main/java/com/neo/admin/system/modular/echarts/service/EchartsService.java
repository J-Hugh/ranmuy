package com.neo.admin.system.modular.echarts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neo.admin.common.value.ConstantMapper;
import com.neo.admin.system.modular.echarts.domain.Serie;

/**
 * Echarts相关服务
 * 
 * @author luoyulin
 *
 */
@Service
public class EchartsService {

	/**
	 * 将一组数据转换成符合echarts格式的数据
	 * 
	 * @param name
	 * @param data
	 * @return
	 */
	public Serie format(String name, Integer[] data) {
		Serie serie = new Serie(name, data);
		return serie;
	}
	/**
	 * 将一组数据转换成符合echarts格式的数据
	 * 
	 * @param name
	 * @param data
	 * @return
	 */
	public List<Serie> format(Map<String, Integer[]> data) {
		List<Serie> series = new ArrayList<>();
		for (Map.Entry<String, Integer[]> entry : data.entrySet()) {
			String name = entry.getKey();
			Integer[] value = entry.getValue();
			Serie serie = format(ConstantMapper.getCity(name), value);
			series.add(serie);
		}
		return series;
	}

}
