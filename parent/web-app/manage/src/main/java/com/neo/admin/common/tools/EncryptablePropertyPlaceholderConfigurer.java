package com.neo.admin.common.tools;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			String username = props.getProperty("jdbc.local.username");
			if (username != null) {
				props.setProperty("jdbc.local.username", DESUtils.decrypt(username, "t$o&9x2p", "salt#&@!"));
			}
			String password = props.getProperty("jdbc.local.password");
			if (password != null) {
				props.setProperty("jdbc.local.password", DESUtils.decrypt(password, "t$o&9x2p", "salt#&@!"));
			}
			String url = props.getProperty("jdbc.local.url"); 
			if (url != null) {
				System.out.println(DESUtils.decrypt(url,"t$o&9x2p", "salt#&@!"));
				props.setProperty("jdbc.local.url", DESUtils.decrypt(url,"t$o&9x2p", "salt#&@!"));
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}
