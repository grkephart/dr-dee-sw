package org.drdeesw.commons.dto.security;


import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.drdeesw.commons.dto.entities.AbstractNamedUniqueEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@MappedSuperclass
@Access(AccessType.FIELD)
@AttributeOverride(name = "name", column = @Column(name = "group_name"))
public class Role<UR extends UserRole<?,?>> extends AbstractNamedUniqueEntity<Long>
{
  private static final long serialVersionUID = 1L;
  @OneToMany(mappedBy = "role", fetch=FetchType.LAZY)
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
