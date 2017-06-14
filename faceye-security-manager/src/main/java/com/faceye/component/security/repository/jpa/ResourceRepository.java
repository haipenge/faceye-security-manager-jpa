package com.faceye.component.security.repository.jpa;

import com.faceye.component.security.entity.Resource;
import com.faceye.component.security.repository.jpa.custom.ResourceRepositoryCustom;
import com.faceye.feature.repository.jpa.BaseRepository;
/**
 * Resource 实体DAO
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月20日
 */
public interface ResourceRepository extends BaseRepository<Resource,Long>,ResourceRepositoryCustom {
	
	/**
	 * 根据URL地址取得Resource 对像
	 * @todo
	 * @param url
	 * @return
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年7月1日
	 */
	public Resource getResourceByUrl(String url);
	
}/**@generate-repository-source@**/
