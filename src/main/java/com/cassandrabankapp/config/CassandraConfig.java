package com.cassandrabankapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@Configuration
@EnableCassandraRepositories(basePackages={"com.cassandrabankapp"})
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints("52.24.133.0");
		cluster.setContactPoints("54.148.178.63");
		cluster.setContactPoints("54.89.250.10");
		cluster.setContactPoints("54.237.246.209");
		cluster.setPort(9042);
		return cluster;
	}

	@Bean
	public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
		return new BasicCassandraMappingContext();
	}

	@Override
	protected String getKeyspaceName() {
		return "cassandrabankapp";
	}

}
