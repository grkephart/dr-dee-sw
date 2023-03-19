package org.drdeesw.commons.dto.security;


import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.drdeesw.commons.dto.base.AbstractNamedObject;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@MappedSuperclass
@AttributeOverride(name = "name", column = @Column(name="group_name"))
@Access(AccessType.FIELD)
public class Role<UR extends UserRole<?,?>> extends AbstractNamedObject<Long>
{
  private static final long serialVersionUID = 1L;
  @OneToMany(mappedBy = "role")
  private Set<UR>     userRoles;

  /* (non-Javadoc)
   * @see org.drdeesw.reactrax.dto.general.AbstractRenameableObject#getName()
   */
  @Override
  public String getName()
  {
    return super.getName();
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
   * @param userRoles the userRoles to set
   */
  public void setUserRoles(
    Set<UR> userRoles)
  {
    this.userRoles = userRoles;
  }
}
