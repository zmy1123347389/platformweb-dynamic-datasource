package com.behere.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Created by PrimaryKey on 17/2/4.
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
@Configuration
public class DruidDBConfig {
	private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);

	@Bean
	public ServletRegistrationBean druidServlet() {
		// 新建servlet
		ServletRegistrationBean reg = new ServletRegistrationBean();
		// Druid内置提供了一个StatViewServlet用于展示Druid的统计信息
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("allow", ""); // 白名单
		return reg;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		// 排除过滤的路径
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		// druid 0.2.7版本开始支持profile，配置profileEnable能够监控单个url调用的sql列表。
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		// principalCookieName配置。如果你的user信息保存在cookie中，你可以配置principalCookieName，使得druid知道当前的user是谁
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		// principalSessionName配置。你可以配置principalSessionName，使得druid能够知道当前的session的用户是谁
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		// 拦截所有请求
		filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
		return filterRegistrationBean;
	}
}
