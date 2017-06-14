package com.faceye.test.component.security.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext.xml"})
public class DataSourceTestCase  extends AbstractTransactionalJUnit4SpringContextTests{
	private DataSource dataSource=null;
	@Test
	public void showDatabases() throws Exception {
		String sql = "show databases;";
		if (dataSource != null) {
			Connection conn = this.dataSource.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String db = rs.getString(1);
				logger.debug(">>FaceYe db is:" + db);
				Assert.isTrue(StringUtils.isNotEmpty(db));

			}
			rs.close();
			stmt.close();
			conn.close();
		} else {
			Assert.isTrue(dataSource!=null);
		}

	}
}
