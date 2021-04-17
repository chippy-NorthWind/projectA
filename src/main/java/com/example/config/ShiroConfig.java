package com.example.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author 橙鼠鼠
 */
@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean(name = "sManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("sManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        /*anon:无需认证就可以访问
         * authc:认证了才可以访问
         * user:记住我后才可以访问
         * perms:需要资源权限
         * role:需要角色权限
         * */

        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("/static/**", "anon");
        params.put("/user/login", "anon");
        params.put("/", "authc");
        params.put("/toIndex", "authc");


        params.put("/user/toAdd", "perms[admin]");
        params.put("/user/add", "perms[admin]");
        params.put("/user/toUpdate/**", "perms[admin]");
        params.put("/user/update/**", "perms[admin]");
        params.put("/user/toList", "perms[admin]");
        params.put("/user/delete/**", "perms[admin]");

        bean.setFilterChainDefinitionMap(params);

        bean.setLoginUrl("/user/tologin");
        System.out.println("完成权限录入");
        return bean;
    }
}
