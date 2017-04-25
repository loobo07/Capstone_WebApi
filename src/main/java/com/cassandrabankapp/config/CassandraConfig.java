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
		cluster.setContactPoints("34.223.208.50");
		cluster.setContactPoints("52.90.138.94");
		cluster.setContactPoints("35.165.48.22");
		cluster.setContactPoints("52.207.107.111");
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
