/**
 * 
 */
package org.drdeesw.commons.dto.security;

import org.drdeesw.commons.dto.base.LongUniqueObject;

/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gkephart
 *
 */
public interface UserRole extends LongUniqueObject
{


  /**
   * @return the role ID
   */
  public Long getRoleId();


  /**
   * @return the user ID
   */
  public Long getUserId();


  /**
   * @return the username
   */
  public String getUsername();


  /**
   * @param roleId the role ID to set
   */
  public void setRoleId(
    Long role);


  /**
   * @param userId the user ID to set
   */
  public void setUserId(
    Long userId);


  /**
   * @param username the username to set
   */
  public void setUsername(
    String username);

}
