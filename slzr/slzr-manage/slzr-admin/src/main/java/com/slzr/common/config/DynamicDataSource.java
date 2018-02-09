package com.slzr.common.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<Object, Object> _targetDataSources;

    /**
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     * @describe 数据源为空或者为0时，自动切换至默认数据源，即在配置文件中定义的dataSource数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DbContextHolder.getDBType();
        if (dataSourceName == null) {
            dataSourceName = Constants.DEFAULT_DATA_SOURCE_NAME;
        } else {
            this.selectDataSource(dataSourceName);
        }
        log.debug("--------> use datasource " + dataSourceName);
        return dataSourceName;
    }

    /**
     * 到数据库中查找名称为dataSourceName的数据源
     *
     * @author Geloin
     * @date Jan 20, 2014 12:15:41 PM
     * @param dataSourceName
     */
    private void selectDataSource(String dataSourceName) {
        Object sid = DbContextHolder.getDBType();
        if (StringUtils.isEmpty(dataSourceName)
                || dataSourceName.trim().equals("dataSource")) {
            DbContextHolder.setDBType("dataSource");
            return;
        }
        Object obj = this._targetDataSources.get(dataSourceName);
        if (obj != null && sid.equals(dataSourceName)) {
            return;
        } else {
            DataSource dataSource = this.getDataSource(dataSourceName);
            if (null != dataSource) {
                this.setDataSource(dataSourceName, dataSource);
            }
        }
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this._targetDataSources = targetDataSources;
        super.setTargetDataSources(this._targetDataSources);
        afterPropertiesSet();
    }

    private void addTargetDataSource(String key, DataSource dataSource) {
        this._targetDataSources.put(key, dataSource);
        this.setTargetDataSources(this._targetDataSources);
    }

    private DataSource createDataSource(String driverClassName, String url,
                                        String username, String password) {
    	DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 到数据库中查询名称为dataSourceName的数据源
     *
     * @author Geloin
     * @date Jan 20, 2014 12:18:12 PM
     * @param dataSourceName
     * @return
     */
    private DataSource getDataSource(String dataSourceName) {
        this.selectDataSource(Constants.DEFAULT_DATA_SOURCE_NAME);
        this.determineCurrentLookupKey();
        Connection conn = null;
        try {
            conn = this.getConnection();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT C_NAME,C_TYPE,C_URL,C_USER_NAME,");
            builder.append("C_PASSWORD,C_JNDI_NAME,C_DRIVER_CLASS_NAME ");
            builder.append("FROM IA_DATA_SOURCE WHERE c_name = ?");

            PreparedStatement ps = conn.prepareStatement(builder.toString());
            ps.setString(1, dataSourceName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Integer type = rs.getInt("C_TYPE");
                if (StringUtils.isNotEmpty(String.valueOf(type))) {
                    // DB
                    String url = rs.getString("C_URL");
                    String userName = rs.getString("C_USER_NAME");
                    String password = rs.getString("C_PASSWORD");
                    String driverClassName = rs
                            .getString("C_DRIVER_CLASS_NAME");
                    DataSource dataSource = this.createDataSource(
                            driverClassName, url, userName, password);
                    return dataSource;
                } else {
                    // JNDI
                    String jndiName = rs.getString("C_JNDI_NAME");

                    JndiDataSourceLookup jndiLookUp = new JndiDataSourceLookup();
                    DataSource dataSource = jndiLookUp.getDataSource(jndiName);
                    return dataSource;
                }

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            log.error(String.valueOf(e));
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error(String.valueOf(e));
            }
        }
        return null;
    }

    /**
     * 将已存在的数据源存储到内存中
     *
     * @author Geloin
     * @date Jan 20, 2014 12:24:13 PM
     * @param dataSourceName
     * @param dataSource
     */
    private void setDataSource(String dataSourceName, DataSource dataSource) {
        this.addTargetDataSource(dataSourceName, dataSource);
        DbContextHolder.setDBType(dataSourceName);
    }

}