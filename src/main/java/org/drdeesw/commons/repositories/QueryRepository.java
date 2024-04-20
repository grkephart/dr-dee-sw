/**
 * 
 */
package org.drdeesw.commons.repositories;


import java.io.Serializable;

import org.drdeesw.commons.dto.base.UniqueObject;
import org.drdeesw.commons.dto.queries.JpqlQuery;
import org.drdeesw.commons.dto.queries.QueryResults;


/**
 * @author gary_kephart
 *
 */
public interface QueryRepository<T extends UniqueObject<ID>, ID extends Serializable>
{
  /**
   * Can't name this method find... because Spring will have a fit.
   * 
   * @param query
   * @return
   * @throws Exception
   */
  <Q extends JpqlQuery<T>> QueryResults<T> advancedSearch(Q query) throws Exception;
}
