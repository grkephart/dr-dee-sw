package org.drdeesw.commons.dto.security;


import org.drdeesw.commons.dto.pojos.AbstractNamedUniquePojo;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@SuppressWarnings("serial")
public class RolePojo extends AbstractNamedUniquePojo<Long> implements Role
{

  /* (non-Javadoc)
   * @see org.drdeesw.reactrax.dto.general.AbstractRenameableObject#getName()
   */
  @Override
  public String getName()
  {
    return super.getName();
  }

}
