/**
 * 
 */
package org.drdeesw.commons.dto.queries;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author gkephart
 *
 */
public class Query<T>
{
  public static final String   DEFAULT_ALIAS            = "x";
  public static final boolean  DEFAULT_CASE_INSENSITIVE = true;

  private String               alias;
  private boolean              caseInsensitive;
  private Class<T>             clazz;
  private List<Condition>      conditions;
  private ArrayList<Condition> mandatoryConditions;
  private Match                match;
  private Integer              maxResults;
  private List<Ordering>       orderings;
  private Boolean              performCount;
  private Integer              start;

  /**
   * @param clazz
   */
  public Query(Class<T> clazz)
  {
    this(clazz, Match.MATCH_ALL);
  }


  /**
   * @param clazz
   * @param match
   */
  public Query(Class<T> clazz, Match match)
  {
    this.alias = DEFAULT_ALIAS;
    this.clazz = clazz;
    this.conditions = new ArrayList<Condition>();
    this.mandatoryConditions = new ArrayList<Condition>();
    this.match = match;
    this.orderings = new ArrayList<>();
  }


  /**
   * Converts a POJO query (PQ) of class P to an entity query of type T.
   * 
   * @param <PQ>
   * @param entityClass
   * @param that
   */
  public <PQ extends JpqlQuery<?>> Query(Class<T> entityClass, PQ that)
  {
    this.alias = that.getAlias();
    this.clazz = entityClass;
    this.conditions = new ArrayList<Condition>(that.getConditions());
    this.mandatoryConditions = new ArrayList<Condition>(that.getMandatoryConditions());
    this.match = that.getMatch();
    this.orderings = new ArrayList<>(that.getOrderings());
  }


  /**
   * @param <Q>
   * @param condition
   * @return
   */
  public <Q extends Query<T>> Q add(
    Condition condition)
  {
    String fieldName = condition.getFieldName();

    if (fieldName != null)
    {
      condition.setFieldName(this.alias + "." + fieldName);
    }

    this.conditions.add(condition);

    return cast();
  }


  /**
   * @param propertyName
   * @param value
   * @return
   * @throws ApplicationException
   */
  public <Q extends Query<T>> Q addMandatoryCondition(
    String propertyName,
    Object value) throws Exception
  {
    if (propertyName != null && value != null)
    {
      Operator operator = Operator.byLhs(propertyName);

      // Check for LHS bracket operator

      if (operator == null)
        operator = this.caseInsensitive ? Operator.IEQUALS : Operator.EQUALS;
      else
        propertyName = propertyName.substring(0, propertyName.indexOf("[")); // remove operator from propertyName

      boolean added = addMandatoryCondition(propertyName, operator, value, false);

      if (!added)
        throw new Exception("mandatory condition not added: " + propertyName + " = " + value);
    }

    return cast();
  }


  /**
   * @param propertyName
   * @param operator
   * @param value
   * @return
   * @throws Exception 
   */
  public <Q extends Query<T>> Q addMandatoryCondition(
    String propertyName,
    Operator operator,
    Object value) throws Exception
  {
    boolean added = addMandatoryCondition(propertyName, operator, value, false);

    if (!added)
      throw new Exception("mandatory condition not added: " + propertyName + " = " + value);

    return cast();
  }


  /**
   * @param propertyName
   * @param operator
   * @param value
   * @param isRef
   * @return
   */
  private boolean addMandatoryCondition(
    String propertyName,
    Operator operator,
    Object value,
    boolean isRef)
  {
    return this.mandatoryConditions.add(new Condition(propertyName, operator, value, isRef));
  }


  /**
   * @param name
   * @param ascending
   */
  public <Q extends Query<T>> Q addOrdering(
    String name,
    boolean ascending)
  {
    this.orderings.add(new Ordering(name, ascending));

    return cast();
  }


  public <Q extends Query<T>> Q and(
    Condition... conditions)
  {
    add(Condition.and(conditions));

    return cast();
  }


  /**
   * @param <Q>
   * @return
   */
  @SuppressWarnings("unchecked")
  protected <Q extends Query<T>> Q cast()
  {
    return (Q)this;
  }


  /**
   * @return
   */
  public boolean conditionsPresent()
  {
    return !this.conditions.isEmpty();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q equals(
    String fieldName,
    Object value)
  {
    add(Condition.equals(fieldName, value));

    return cast();
  }

  /**
   * @param propertyName
   * @param value
   * @param isRef true if the value is actually a reference to another attribute (e.g. x.a = y.b) versus a literal value
   * @return
   */
  public <Q extends Query<T>> Q equals(
    String propertyName,
    Object value,
    boolean isRef)
  {
    if (propertyName != null && value != null)
      add(Condition.equals(propertyName, value, isRef));

    return cast();
  }


  /**
   * @param subquery
   * @return
   */
  public <Q extends Query<T>> Q exists(
    Query<?> subquery)
  {
    if (subquery != null)
      add(Condition.exists(subquery));

    return cast();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q ge(
    String fieldName,
    Object value)
  {
    add(Condition.ge(fieldName, value));

    return cast();
  }


  /**
   * @return the alias
   */
  public String getAlias()
  {
    return this.alias;
  }


  /**
   * @return the clazz's simple name
   */
  public String getClassSimpleName()
  {
    return this.clazz.getSimpleName();
  }


  /**
   * @return the conditions
   */
  public List<Condition> getConditions()
  {
    return this.conditions;
  }


  /**
   * @return the mandatoryConditions
   */
  public ArrayList<Condition> getMandatoryConditions()
  {
    return mandatoryConditions;
  }


  /**
   * @return the match
   */
  public Match getMatch()
  {
    return match;
  }


  /**
   * @return the maxResults
   */
  public Integer getMaxResults()
  {
    return maxResults;
  }


  /**
   * @param defaultValue
   * @return
   */
  public int getMaxResults(
    int defaultValue)
  {
    return this.maxResults == null ? defaultValue : this.maxResults;
  }


  /**
   * @return the orderings
   */
  public List<Ordering> getOrderings()
  {
    return orderings;
  }


  /**
   * @return the start
   */
  public Integer getStart()
  {
    return start;
  }


  /**
   * @param defValue
   * @return
   */
  public int getStart(
    int defValue)
  {
    return this.start == null ? defValue : this.start;
  }

  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q gt(
    String fieldName,
    Object value)
  {
    add(Condition.gt(fieldName, value));

    return cast();
  }

  /**
   * @return
   */
  public boolean hasMandatoryConditions()
  {
    return !this.mandatoryConditions.isEmpty();
  }



  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q iequals(
    String fieldName,
    Object value)
  {
    add(Condition.iequals(fieldName, value));

    return cast();
  }

  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q ilike(
    String fieldName,
    String value)
  {
    add(Condition.ilike(fieldName, value));

    return cast();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q in(
    String fieldName,
    Collection<?> value)
  {
    add(Condition.in(fieldName, value));

    return cast();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q in(
    String fieldName,
    Object... values)
  {
    add(Condition.in(fieldName, Arrays.asList(values)));

    return cast();
  }


  /**
   * @return the caseInsensitive
   */
  public boolean isCaseInsensitive()
  {
    return caseInsensitive;
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q isNotNull(
    String fieldName)
  {
    add(Condition.isNotNull(fieldName));

    return cast();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q isNull(
    String fieldName)
  {
    add(Condition.isNull(fieldName));

    return cast();
  }


  /**
   * @return the performCount
   */
  public boolean isPerformCount()
  {
    return Boolean.TRUE.equals(this.performCount);
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q like(
    String fieldName,
    String value)
  {
    add(Condition.like(fieldName, value));

    return cast();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q lt(
    String fieldName,
    Object value)
  {
    add(Condition.lt(fieldName, value));

    return cast();
  }


  /**
   * @param string
   * @param string2
   * @return
   */
  public <Q extends Query<T>> Q notEquals(
    String fieldName,
    Object value)
  {
    add(Condition.notEquals(fieldName, value));

    return cast();
  }


  /**
   * @param subquery
   * @return
   */
  public <Q extends Query<T>> Q notExists(
    Query<?> subquery)
  {
    if (subquery != null)
      add(Condition.notExists(subquery));

    return cast();
  }


  public <Q extends Query<T>> Q or(
    Condition... conditions)
  {
    add(Condition.or(conditions));

    return cast();
  }


  /**
   * @param alias the alias to set
   */
  public <Q extends Query<T>> Q setAlias(
    String alias)
  {
    this.alias = alias;

    return cast();
  }


  /**
   * @param caseInsensitive the caseInsensitive to set
   */
  public void setCaseInsensitive(
    boolean caseInsensitive)
  {
    this.caseInsensitive = caseInsensitive;
  }


  /**
   * @param match the match to set
   */
  public void setMatch(
    Match match)
  {
    this.match = match;
  }


  /**
   * @param length
   */
  public <Q extends Query<T>> Q setMaxResults(
    Integer maxResults)
  {
    this.maxResults = maxResults;

    return cast();
  }


  /**
   * @param performCount
   */
  public <Q extends Query<T>> Q setPerformCount(
    Boolean performCount)
  {
    this.performCount = performCount;

    return cast();
  }


  /**
   * @param start
   */
  public <Q extends Query<T>> Q setStart(
    Integer start)
  {
    this.start = start;

    return cast();
  }


  /**
   * @param <Q>
   * @return
   */
  public <Q extends Query<T>> Q uniqueResult()
  {
    setPerformCount(false);
    setMaxResults(1);

    return cast();
  }
}
