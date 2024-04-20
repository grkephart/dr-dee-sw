/**
 * 
 */
package org.drdeesw.commons.services.impl;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.drdeesw.commons.dto.entities.UniqueEntity;
import org.drdeesw.commons.dto.pojos.UniquePojo;
import org.drdeesw.commons.dto.queries.JpqlQuery;
import org.drdeesw.commons.dto.queries.QueryResults;
import org.drdeesw.commons.services.ComposerCrudService;
import org.springframework.util.MultiValueMap;


/**
 *
 * @author gary_kephart
 *
 * @param <P> the POJO class
 * @param <E> the entity class
 * @param <ID> the ID class
 */
public abstract class AbstractComposerJpaCrudServiceImpl<P extends UniquePojo<ID>, E extends UniqueEntity<ID>, ID extends Serializable>
    extends AbstractJpaCrudServiceImpl<P, E, ID> implements ComposerCrudService<P, ID>
{

  /**
   * @param clazz
   */
  protected AbstractComposerJpaCrudServiceImpl(Class<E> clazz)
  {
    super(clazz);
  }


  /**
   * Sometimes it's more efficient to compose the entire collection at once.
   * May save on calls to other services.
   * 
   * @param values
   * @return
   * @throws Exception 
   */
  protected abstract Collection<P> compose(
    Collection<P> values) throws Exception;


  /**
   * 
   * @param queryResults
   * @return
   */
  protected abstract P compose(
    P pojo);


  /**
   * @param queryResults
   * @return
   * @throws Exception 
   */
  protected QueryResults<P> compose(
    QueryResults<P> queryResults) throws Exception
  {
    compose(queryResults.getRecords());

    return queryResults;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#convertEntityToPojo(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  protected P convertEntityToPojo(
    E entity)
  {
    // TODO Auto-generated method stub
    return null;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#convertPojoToEntity(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  protected E convertPojoToEntity(
    P pojo)
  {
    // TODO Auto-generated method stub
    return null;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#create(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P create(
    P obj)
  {
    // TODO Auto-generated method stub
    return super.create(obj);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#delete(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public void delete(
    P obj)
  {
    // TODO Auto-generated method stub
    super.delete(obj);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#findById(java.io.Serializable)
   */
  @Override
  public Optional<P> findById(
    ID id)
  {
    Optional<P> optPojo = super.findById(id);

    if (optPojo.isPresent())
      optPojo = Optional.of(compose(optPojo.get()));

    return optPojo;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#findByQuery(org.springframework.util.MultiValueMap)
   */
  @Override
  public QueryResults<P> findByQuery(
    MultiValueMap<String, String> parameterMap) throws Exception
  {
    final QueryResults<P> queryResults = super.findByQuery(parameterMap);

    compose(queryResults);

    return queryResults;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#findByQuery(org.drdeesw.commons.dto.queries.JpqlQuery)
   */
  @Override
  public <Q extends JpqlQuery<P>> QueryResults<P> findByQuery(
    Q query) throws Exception
  {
    final QueryResults<P> queryResults = super.findByQuery(query);

    compose(queryResults);

    return queryResults;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#get(java.io.Serializable)
   */
  @Override
  public P get(
    ID id)
  {
    P pojo = super.get(id);

    return compose(pojo);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#get(java.util.Set)
   */
  @Override
  public QueryResults<P> get(
    Set<ID> ids) throws Exception
  {
    final QueryResults<P> queryResults = super.get(ids);

    compose(queryResults);

    return queryResults;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#getMap(java.util.Set)
   */
  @Override
  public Map<ID, P> getMap(
    Set<ID> ids) throws Exception
  {
    final Map<ID, P> map = super.getMap(ids);

    compose(map.values());

    return map;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#save(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P save(
    P obj)
  {
    // TODO Auto-generated method stub
    return super.save(obj);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#saveAll(java.util.Collection)
   */
  @Override
  public List<P> saveAll(
    Collection<P> pojos)
  {
    // TODO Auto-generated method stub
    return super.saveAll(pojos);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#saveAllAndFlush(java.util.Collection)
   */
  @Override
  public List<P> saveAllAndFlush(
    Collection<P> pojos)
  {
    // TODO Auto-generated method stub
    return super.saveAllAndFlush(pojos);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#saveAndFlush(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P saveAndFlush(
    P obj)
  {
    // TODO Auto-generated method stub
    return super.saveAndFlush(obj);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#update(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P update(
    P obj)
  {
    // TODO Auto-generated method stub
    return super.update(obj);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.impl.AbstractJpaCrudServiceImpl#updateEntity(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  protected P updateEntity(
    E entity)
  {
    // TODO Auto-generated method stub
    return super.updateEntity(entity);
  }
}
