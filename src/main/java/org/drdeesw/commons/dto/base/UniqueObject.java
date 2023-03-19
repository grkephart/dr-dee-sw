/**
 * 
 */
package org.drdeesw.commons.dto.base;

import java.io.Serializable;

/**
 * A data transfer object that is unique due to its unique ID.
 * 
 * @author gary_kephart
 *
 */
public interface UniqueObject<ID extends Serializable> extends DataTransferObject
{
  ID getId();
  
  void setId(ID id);
}
