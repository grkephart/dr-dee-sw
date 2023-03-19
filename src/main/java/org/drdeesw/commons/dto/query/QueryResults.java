/**
 * 
 */
package org.drdeesw.commons.dto.query;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.drdeesw.commons.dto.query.datatables.DataTablesJpqlQuery;


/**
 * @author gkephart
 *
 */
public class QueryResults<T> implements Iterable<T>
{
  private Integer  draw;
  private Query<T> query;
  private List<T>  records;
  private int      totalRecords;

  /**
   * @param values
   */
  public QueryResults(Integer draw, T[] values)
  {
    this.draw = draw;
    this.records = (List<T>)Arrays.asList(values);
    this.totalRecords = values.length;
  }


  /**
   * @param values
   */
  public QueryResults(Integer draw, T[] values, int totalRecords)
  {
    this.draw = draw;
    this.records = (List<T>)Arrays.asList(values);
    this.totalRecords = totalRecords;
  }


  /**
   * @param resultList
   * @param totalRecords
   */
  public QueryResults(List<T> resultList, int totalRecords, DataTablesJpqlQuery<T> query)
  {
    this.records = resultList;
    this.totalRecords = totalRecords;
    this.query = query;
    this.draw = query == null ? -1 : query.getDraw();
  }


  /**
   * @param resultList
   * @param totalRecords
   */
  public QueryResults(List<T> resultList, int size, Integer draw)
  {
    this.records = resultList;
    this.totalRecords = size;
    this.draw = draw;
  }


  /**
   * @param resultList
   * @param totalRecords
   */
  public QueryResults(List<T> resultList, int totalRecords, Query<T> query)
  {
    this.records = resultList;
    this.totalRecords = totalRecords;
    this.query = query;

    if (this.query instanceof DataTablesJpqlQuery)
    {
      this.draw = ((DataTablesJpqlQuery<T>)query).getDraw();
    }
  }


  /**
   * @param values
   */
  public QueryResults(T[] values)
  {
    this.records = (List<T>)Arrays.asList(values);
    this.totalRecords = values.length;
  }


  /**
   * @param index
   * @return
   */
  public T get(
    int index)
  {
    return this.records.get(index);
  }


  /**
   * @return the draw
   */
  public Integer getDraw()
  {
    return draw;
  }


  /**
   * @return the query
   */
  public Query<T> getQuery()
  {
    return query;
  }


  /**
   * @return the records
   */
  public List<T> getRecords()
  {
    return records;
  }


  /**
   * For DataTables JQuery plugin.
   * 
   * @return
   */
  public int getRecordsTotal()
  {
    return this.totalRecords;
  }


  public int getSize()
  {
    return this.records == null ? 0 : this.records.size();
  }


  /**
   * @return the totalRecords
   */
  public int getTotalRecords()
  {
    return totalRecords;
  }


  /**
   * @return
   */
  public boolean isEmpty()
  {
    return this.records == null || this.records.isEmpty();
  }


  /* (non-Javadoc)
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator()
  {
    return this.records == null ? null : this.records.iterator();
  }


  /**
   * @param draw the draw to set
   */
  public void setDraw(
    Integer draw)
  {
    this.draw = draw;
  }


  /**
   * @param query the query to set
   */
  public void setQuery(
    Query<T> query)
  {
    this.query = query;
  }


  /**
   * @param records the records to set
   */
  public void setRecords(
    List<T> records)
  {
    this.records = records;
  }


  /**
   * @param totalRecords the totalRecords to set
   */
  public void setTotalRecords(
    int totalRecords)
  {
    this.totalRecords = totalRecords;
  }


  /**
   * @return
   */
  public T uniqueResult()
  {
    if (this.records != null && this.records.size() == 1)
      return this.records.get(0);
    else
      return null;
  }
}
