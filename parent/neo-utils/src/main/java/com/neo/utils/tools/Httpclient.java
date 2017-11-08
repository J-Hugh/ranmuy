package com.neo.utils.tools;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Httpclient {

	public static final RequestConfig config = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(60000).build();
	public static final String CHARSET = "UTF-8";

	/**
	 * 模拟GET请求
	 * 
	 * @param url 请求地址
	 * @param net 如果不需要使用代理 该参数设置为null
	 * @param headers 如果不需要设置请求头 该参数设置为null
	 * @param params 如果请求没有参数 该参数设置为null
	 * @return
	 */
	public static String doGet(CloseableHttpClient httpClient,String url,
			Map<String, String> headers, Map<String, String> params) {
		return doGet(httpClient,url, headers, params, CHARSET);
	}
	/**
	 * 模拟POST请求
	 * @param url 请求地址
	 * @param net 如果不需要使用代理 该参数设置为null
	 * @param headers 如果不需要设置请求头 该参数设置为null
	 * @param params 如果请求没有参数 该参数设置为null
	 * @return
	 */
	public static String doPost(CloseableHttpClient httpClient,String url,
			Map<String, String> headers, Map<String, String> params,String body) {
		return doPost(httpClient,url, headers, params,body, CHARSET);
	}
	
	
	public static Header[] doGetHeader(CloseableHttpClient httpClient,String url, 
			Map<String, String> headers, Map<String, String> params) {
		return doGetHeader(httpClient,url, headers, params, CHARSET);
	}
	private static String doGet(CloseableHttpClient httpClient,String url,  Map<String, String> headers, Map<String, String> params, String charset) {
		

		try {

			// 设置参数
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(),value));
					}
				}
				url += "?"+ EntityUtils.toString(new UrlEncodedFormEntity(pairs,charset));
			}

			HttpGet httpGet = new HttpGet(url);
			// 设置请求头
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			
			
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Header[] doGetHeader(CloseableHttpClient httpClient,String url, Map<String, String> headers, Map<String, String> params, String charset) {
		
		try {

			// 设置参数
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(),value));
					}
				}
				url += "?"+ EntityUtils.toString(new UrlEncodedFormEntity(pairs,charset));
			}

			HttpGet httpGet = new HttpGet(url);
			// 设置请求头
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			response.close();
			//Header[]  h = response.getHeaders("Set-Cookie");
			return response.getAllHeaders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String doPost(CloseableHttpClient httpClient ,String url, Map<String, String> headers, Map<String, String> params,String body,String charset) {

		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(),value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			
			 HttpEntity entity1 = new StringEntity(body);
			 httpPost.setEntity(entity1);
			
			// 设置请求头
			if (headers != null && !headers.isEmpty()) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :"
						+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
