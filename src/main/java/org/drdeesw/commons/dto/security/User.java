/**
 * 
 */
package org.drdeesw.commons.dto.security;


import org.drdeesw.commons.dto.base.LongUniqueObject;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
public interface User extends LongUniqueObject
{

  @Override
  public Long getId();


  /**
   * @return the name
   */
  public String getName();


  /**
   * @return the roleNames
   */
  public String getRoleNames();


  /**
   * The email address is the username
   * @return
   */
  public String getUsername();



  /**
   * @return the enabled
   */
  public boolean isEnabled();


  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(
    boolean enabled);


  /**
   * @param name the name to set
   */
  public void setName(
    String name);


  /**
   * @param roleNames the roleNames to set
   */
  public void setRoleNames(
    String roleNames);


  public void setUsername(
    String email);


}
