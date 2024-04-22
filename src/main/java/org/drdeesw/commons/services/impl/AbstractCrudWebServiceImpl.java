/**
 * 
 */
package org.drdeesw.commons.services.impl;

import java.io.Serializable;

import org.drdeesw.commons.dto.pojos.UniquePojo;
import org.drdeesw.commons.services.CrudService;

/**
 * 
 */
public abstract class AbstractCrudWebServiceImpl<T extends UniquePojo<ID>, ID extends Serializable> extends AbstractService implements CrudService<T, ID>
{
  protected AbstractCrudWebServiceImpl()
  {
    
  }
}
