package com.faceye.component.security.repository.jpa;

import com.faceye.component.security.entity.Role;
import com.faceye.component.security.repository.jpa.custom.RoleRepositoryCustom;
import com.faceye.feature.repository.jpa.BaseRepository;
/**
 * Role 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface RoleRepository extends BaseRepository<Role,Long>,RoleRepositoryCustom {
	
	public Role getRoleByName(String name);
}/**@generate-repository-source@**/
