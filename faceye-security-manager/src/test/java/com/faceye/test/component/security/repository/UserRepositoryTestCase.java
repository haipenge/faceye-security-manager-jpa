package com.faceye.test.component.security.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.junit.Assert;

import com.faceye.component.security.entity.Role;
import com.faceye.component.security.entity.User;
import com.faceye.component.security.repository.jpa.RoleRepository;
import com.faceye.component.security.repository.jpa.UserRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;

/**
 * User DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class UserRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private UserRepository userRepository = null;
	
	@Autowired
	private RoleRepository roleRepository=null;
	@Autowired
	private DataSource dataSource=null;

	@Before
	public void before() throws Exception {
		// this.userRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		
		for (int i = 0; i < 100; i++) {
			Role role=this.roleRepository.getOne(11L);
			User entity = new User();
			entity.setUsername("test-"+i);
			Set<Role> roles=new HashSet<Role>(0);
			roles.add(role);
			entity.setRoles(roles);
			this.userRepository.save(entity);
		}
		Iterable<User> entities = this.userRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		User entity = new User();
		this.userRepository.save(entity);
		this.userRepository.deleteById(entity.getId());
		Iterable<User> entities = this.userRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		User entity = new User();
		this.userRepository.save(entity);
		User user = this.userRepository.getOne(entity.getId());
		Assert.assertTrue(user != null);
	}
	
	@Test
	public void testGetPage() throws Exception {
		Page page=this.userRepository.getPage(null, 1, 25);
		Assert.assertTrue(page != null && page.getTotalElements() == 201 && page.getNumberOfElements() == 25);

	}
	
	
	@Test
	public void testGetPageBySQL() throws Exception{
		StringBuilder sql = new StringBuilder("select user.id as userId,role.id as roleId,user.name as username from security_user user,security_role role,security_user_role user_role where 1=1 and user.id=user_role.user_id and role.id =user_role.role_id order by user.id desc");
		Map params=new HashMap();
		params.put("LIKE|user.name", "-11");
		Page page=this.userRepository.getPage(params,sql.toString(), "Auth", 1, 100);
		Assert.assertTrue(page!=null &&page.getSize()==100);
		
	}
	
	@Test
	public void showDatabases() throws Exception {
		String sql = "show databases;";
		if (dataSource != null) {
			Connection conn = this.dataSource.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				String db = rs.getString(1);
				logger.debug(">>FaceYe db is:" + db);
				Assert.assertTrue(StringUtils.isNotEmpty(db));

			} else {
				Assert.assertTrue(1 > 2);
			}
			rs.close();
			stmt.close();
			conn.close();
		} else {
			Assert.assertTrue(1 > 2);
		}

	}

}
