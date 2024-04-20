/**
 * 
 */
package org.drdeesw.commons.services;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.drdeesw.commons.dto.pojos.UniquePojo;
import org.drdeesw.commons.dto.queries.JpqlQuery;
import org.drdeesw.commons.dto.queries.QueryResults;
import org.springframework.util.MultiValueMap;


/**
 * @author gary_kephart
 *
 */
public interface ComposerCrudService<P extends UniquePojo<ID>, ID extends Serializable>
    extends CrudService<P,ID>
{
  /**
   * @param obj
   * @return
   */
  P create(
    P obj);


  /**
   * @param obj
   * @return
   */
  void delete(
    P obj);


  /**
   * @param id
   * @return
   */
  Optional<P> findById(
    ID id);


  /**
   * @param parameterMap
   * @return
   * @throws Exception
   */
  QueryResults<P> findByQuery(
    MultiValueMap<String, String> parameterMap) throws Exception;


  /**
   * @param query
   * @return
   * @throws Exception 
   */
  <Q extends JpqlQuery<P>> QueryResults<P> findByQuery(
    Q query) throws Exception;


  /**
   * @param id
   * @return
   */
  P get(
    ID id);


  /**
   * @param ids
   * @return
   * @throws Exception 
   */
  QueryResults<P> get(
    Set<ID> ids) throws Exception;


  /**
   * @param obj
   * @return
   */
  P save(
    P obj);


  /**
   * @param pojos
   * @return
   */
  List<P> saveAll(
    Collection<P> pojos);


  /**
   * @param pojos
   * @return
   */
  List<P> saveAllAndFlush(
    Collection<P> pojos);


  /**
   * @param obj
   * @return
   */
  P saveAndFlush(
    P obj);


  /**
   * @param obj
   * @return
   */
  P update(
    P obj);

}
