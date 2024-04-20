/**
 * 
 */
package org.drdeesw.commons.services.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.drdeesw.commons.dto.base.UniqueObject;
import org.drdeesw.commons.dto.queries.JpqlQuery;
import org.drdeesw.commons.dto.queries.QueryResults;
import org.drdeesw.commons.dto.queries.datatables.DataTablesJpqlQuery;
import org.drdeesw.commons.repositories.QueryRepository;
import org.drdeesw.commons.services.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.MultiValueMap;


/**
 *
 * @author gary_kephart
 *
 * @param <P> the POJO class
 * @param <E> the entity class
 * @param <ID> the ID class
 */
public abstract class AbstractJpaCrudServiceImpl<P extends UniqueObject<ID>, E extends UniqueObject<ID>, ID extends Serializable>
    implements CrudService<P, ID>
{
  private Class<E>               entityClass;
  private QueryRepository<E, ID> queryRepository;
  private JpaRepository<E, ID>   repository;

  /**
   * @param clazz
   */
  protected AbstractJpaCrudServiceImpl(Class<E> clazz)
  {
    this.entityClass = clazz;
  }


  /**
   * @param entities
   * @return
   */
  private List<P> convertEntityToPojo(
    Collection<E> entities)
  {
    List<P> pojos = new ArrayList<P>(entities.size());

    entities.forEach(entity -> pojos.add(convertEntityToPojo(entity)));

    return pojos;
  }


  /**
   * @param entity
   * @return
   */
  protected abstract P convertEntityToPojo(
    E entity);


  /**
   * @param entityQueryResults
   * @return
   */
  protected QueryResults<P> convertEntityToPojo(
    QueryResults<E> entityQueryResults)
  {
    QueryResults<P> queryResults = new QueryResults<P>(entityQueryResults.getDraw(),
        entityQueryResults.getSize());

    entityQueryResults.forEach(entity -> queryResults.add(convertEntityToPojo(entity)));

    return queryResults;
  }


  /**
   * @param entities
   * @return
   */
  private List<E> convertPojoToEntity(
    Collection<P> pojos)
  {
    List<E> entities = new ArrayList<E>(pojos.size());

    for (P pojo : pojos)
    {
      entities.add(convertPojoToEntity(pojo));
    }

    return entities;
  }


  /**
   * @param pojo
   * @return
   */
  protected abstract E convertPojoToEntity(
    P pojo);


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#create(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P create(
    P obj)
  {
    E entity = convertPojoToEntity(obj);

    return convertEntityToPojo(this.repository.save(entity));
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#delete(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public void delete(
    P obj)
  {
    E entity = convertPojoToEntity(obj);

    this.repository.delete(entity);
  }


  /**
   * @param <Q>
   * @param query
   * @return
   * @throws Exception
   */
  private <Q extends JpqlQuery<E>> QueryResults<P> findByEntityQuery(
    Q query) throws Exception
  {
    QueryResults<E> queryResults = this.queryRepository.findByQuery(query);

    return convertEntityToPojo(queryResults);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#findById(java.io.Serializable)
   */
  @Override
  public Optional<P> findById(
    ID id)
  {
    Optional<E> optEntity = this.repository.findById(id);

    return optEntity.isPresent() ? Optional.of(convertEntityToPojo(optEntity.get()))
                                 : Optional.empty();
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#findByQuery(org.springframework.util.MultiValueMap)
   */
  @Override
  public QueryResults<P> findByQuery(
    MultiValueMap<String, String> parameterMap) throws Exception
  {
    JpqlQuery<E> query = getQuery(parameterMap);

    return findByEntityQuery(query);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#findByQuery(org.drdeesw.commons.dto.queries.JpqlQuery)
   */
  public <Q extends JpqlQuery<P>> QueryResults<P> findByQuery(
    Q query) throws Exception
  {
    JpqlQuery<E> entityQuery = new JpqlQuery<E>(this.entityClass, query);

    return findByEntityQuery(entityQuery);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#get(java.io.Serializable)
   */
  @Override
  public P get(
    ID id)
  {
    E entity = this.repository.getReferenceById(id);

    return convertEntityToPojo(entity);
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#get(java.util.Set)
   */
  @Override
  public QueryResults<P> get(
    Set<ID> ids) throws Exception
  {
    JpqlQuery<E> query = new JpqlQuery<E>(this.entityClass)//
        .in("id", ids);

    return findByEntityQuery(query);
  }


  /**
   * @param ids
   * @return
   * @throws Exception
   */
  @Override
  public Map<ID, P> getMap(
    Set<ID> ids) throws Exception
  {
    QueryResults<P> queryResults = get(ids);
    Map<ID, P> map = new HashMap<>();

    queryResults.forEach(result -> map.put(result.getId(), result));
    
    return map;
  }


  /**
   * @param parameterMap
   * @return
   * @throws Exception
   */
  private JpqlQuery<E> getQuery(
    MultiValueMap<String, String> parameterMap) throws Exception
  {
    return new DataTablesJpqlQuery<E>(this.entityClass, parameterMap);
  }


  /**
   * @param <R>
   * @param repository
   */
  protected <R extends JpaRepository<E, ID> & QueryRepository<E, ID>> void init(
    R repository)
  {
    this.queryRepository = repository;
    this.repository = repository;
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#create(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P save(
    P obj)
  {
    E entity = convertPojoToEntity(obj);

    return convertEntityToPojo(this.repository.save(entity));
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#saveAll(java.util.Collection)
   */
  @Override
  public List<P> saveAll(
    Collection<P> pojos)
  {
    List<E> entities = convertPojoToEntity(pojos);

    return convertEntityToPojo(this.repository.saveAll(entities));
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#saveAllAndFlush(java.util.Collection)
   */
  @Override
  public List<P> saveAllAndFlush(
    Collection<P> pojos)
  {
    List<E> entities = convertPojoToEntity(pojos);

    return convertEntityToPojo(this.repository.saveAllAndFlush(entities));
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#create(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P saveAndFlush(
    P obj)
  {
    E entity = convertPojoToEntity(obj);

    return convertEntityToPojo(this.repository.saveAndFlush(entity));
  }


  /* (non-Javadoc)
   * @see org.drdeesw.commons.services.CrudService#update(org.drdeesw.commons.dto.base.UniqueObject)
   */
  @Override
  public P update(
    P obj)
  {
    E entity = convertPojoToEntity(obj);

    return convertEntityToPojo(this.repository.save(entity));
  }


  /**
   * @param entity
   * @return
   */
  protected P updateEntity(
    E entity)
  {
    return convertEntityToPojo(this.repository.save(entity));
  }

}
