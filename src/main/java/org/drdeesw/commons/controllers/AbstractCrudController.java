/**
 * 
 */
package org.drdeesw.commons.controllers;


import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drdeesw.commons.dto.pojos.UniquePojo;
import org.drdeesw.commons.dto.queries.JpqlQuery;
import org.drdeesw.commons.dto.queries.QueryResults;
import org.drdeesw.commons.services.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;


/**
 * @author gary_kephart
 *
 */
public abstract class AbstractCrudController<P extends UniquePojo<ID>, ID extends Serializable>
    extends AbstractController
{
  private static final Log   log = LogFactory.getLog(AbstractCrudController.class);
  private CrudService<P, ID> crudService;

  /**
   * 
   */
  protected AbstractCrudController()
  {
  }


  /**
   * @param obj
   * @return
   */
  protected ResponseEntity<P> create(
    P obj)
  {
    return ResponseEntity.ok(this.crudService.create(obj));
  }


  /**
   * @param id
   * @return
   */
  protected ResponseEntity<P> delete(
    P obj)
  {
    this.crudService.delete(obj);

    return ResponseEntity.ok(obj);
  }


  /**
   * @param parameterMap
   * @return
   */
  protected QueryResults<P> findByQuery(
    MultiValueMap<String, String> parameterMap)
  {
    try
    {
      return this.crudService.findByQuery(parameterMap);
    }
    catch (Exception e)
    {
      log.error("[findByQuery]", e);
      throw e;
    }
  }


  /**
   * @param <Q>
   * @param query
   * @return
   */
  protected <Q extends JpqlQuery<P>> QueryResults<P> findByQuery(
    Q query)
  {
    return this.crudService.findByQuery(query);
  }


  /**
   * @param id
   * @return
   */
  protected ResponseEntity<Optional<P>> findById(
    ID id)
  {
    return ResponseEntity.ok(this.crudService.findById(id));
  }


  /**
   * @param id
   * @return
   */
  protected ResponseEntity<P> get(
    ID id)
  {
    return ResponseEntity.ok(this.crudService.get(id));
  }


  /**
   * @param crudService
   */
  protected void init(
    CrudService<P, ID> crudService)
  {
    this.crudService = crudService;
  }


  /**
   * @param id
   * @return
   */
  protected ResponseEntity<P> update(
    P obj)
  {
    return ResponseEntity.ok(this.crudService.update(obj));
  }
}
