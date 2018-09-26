package cn.hpp.springboot.config;

import javax.sql.DataSource;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
* @ClassName: BeetlSqlConfig  
* @Description: springboot没有提供自动装配 需自己注解bean
* @author 韩鹏鹏  
* @date 2018年5月7日  
*
 */
@Configuration
public class BeetlSqlConfig {

	//扫描包
	@Bean
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
		BeetlSqlScannerConfigurer configurer = new BeetlSqlScannerConfigurer();
		configurer.setBasePackage("cn.hpp.springboot.persistence");
		configurer.setDaoSuffix("Mapper");
		configurer.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return configurer;
	}

	@Bean(name = "sqlManagerFactoryBean")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean(
			DataSource datasource) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setInterceptors(new Interceptor[] { new DebugInterceptor() });
		factory.setNc(new UnderlinedNameConversion());// 开启驼峰
		factory.setSqlLoader(new ClasspathLoader("/sql"));// sql文件路径
		return factory;
	}

}
