/**
 * 
 */
package org.drdeesw.commons.dto.security;


import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.drdeesw.commons.dto.base.AbstractLongUniqueObject;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */@AttributeOverride(name = "id", column = @Column(name="user_id"))

@MappedSuperclass
@Access(AccessType.FIELD)
public class User<UR extends UserRole<?,?>> extends AbstractLongUniqueObject
{
  private static final long serialVersionUID = 1L;
  @Column(name = "enabled")
  private boolean           enabled;
  @Column(name = "name")
  private String            name;
  @Formula("(SELECT GROUP_CONCAT(gm.group_name) FROM group_members_v gm WHERE gm.user_id = user_id)")
  private String            roleNames;
  @Column(name = "username")
  private String            username;
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private Set<UR>     userRoles;

  /**
   * Hibernate
   */
  public User()
  {
  }


  public User(Long id)
  {
    super(id);
  }


  /**
   * For testing.
   * 
   * @param name
   * @param email
   */
  public User(String name, String username)
  {
    this.username = username;
    this.name     = name;
  }


  /**
   * For when logging in reveals a new user.
   * 
   * @param name
   * @param username
   * @param enabled
   */
  public User(String name, String username, boolean enabled)
  {
    this.enabled  = enabled;
    this.username = username;
    this.name     = name;
  }


  @Override
  public Long getId()
  {
    return super.getId();
  }


  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }


  /**
   * @return the roleNames
   */
  public String getRoleNames()
  {
    return roleNames;
  }


  /**
   * The email address is the username
   * 
   * @return
   */
  public String getUsername()
  {
    return username;
  }


  /**
   * @return the userRoles
   */
  @JsonIgnore
  public Set<UR> getUserRoles()
  {
    return userRoles;
  }


  /**
   * @return the enabled
   */
  public boolean isEnabled()
  {
    return enabled;
  }


  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled)
  {
    this.enabled = enabled;
  }



  /**
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }


  /**
   * @param roleNames the roleNames to set
   */
  public void setRoleNames(String roleNames)
  {
    this.roleNames = roleNames;
  }


  public void setUsername(String email)
  {
    this.username = email;
  }


  /**
   * @param userRoles the userRoles to set
   */
  public void setUserRoles(Set<UR> userRoles)
  {
    this.userRoles = userRoles;
  }
}
