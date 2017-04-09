package com.neo.admin.common.tools;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

/**
 * 数据库配置加密
 * @author neoThe
 *
 */
public class PropertiesEncryptFactoryBean implements FactoryBean<Properties>  {

	private Properties properties;
	
	@Override
	public Properties getObject() throws Exception {
		return getProperties();
	}

	@Override
	public Class<?> getObjectType() {
		 return java.util.Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {

		this.properties = properties;  
        String originalUsername = properties.getProperty("user");  
        String originalPassword = properties.getProperty("password");
        
        boolean needDecrypt =  Boolean.parseBoolean(properties.getProperty("needDecrypt", "true"));
        if(needDecrypt){
        	if (originalUsername != null){
                String newUsername = deEncryptString(originalUsername);  
                properties.put("user", newUsername);  
            }
            if (originalPassword != null){
                String newPassword = deEncryptString(originalPassword);  
                properties.put("password", newPassword);  
            }
        }
	}
	
	//简单加密  
    private String deEncryptString(String originalString){
    	try {
			return DESUtils.decrypt(originalString, "t$o&9x2p", "salt#&@!");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    }
    public static void main(String[] args) throws Exception {
		String user = "e6wifilbs";
    	String password = "NjZmMmQzMDQ2YmRiZjdh";
    	
    	System.out.println(DESUtils.encrypt(user, "t$o&9x2p", "salt#&@!"));
    	System.out.println(DESUtils.encrypt(password, "t$o&9x2p", "salt#&@!"));
    	
	}
}
