/**
 * 
 */
package org.drdeesw.commons.dto.queries.datatables;


import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author gary_kephart
 *
 */
public class JQueryDataTableParamModel implements Serializable
{
  public static final String  ASC              = "asc";
  public static final String  DESC             = "desc";
  private static final long   serialVersionUID = -1330932243154216268L;
  private Map<String, Object> ajaxData;
  private String[]            columnData;
  private String[]            columnNames;
  private boolean[]           columnOrderables;
  private boolean[]           columnSearchable;
  private boolean[]           columnSearchRegex;
  private String[]            columnSearchValues;
  private Integer             draw;
  private Integer             length;
  private int[]               orderColumns;
  private boolean[]           orderDirs;
  private boolean             performCount;
  private Boolean             searchCaseInsensitive;
  private Boolean             searchRegex;
  private String              searchValue;
  private Integer             start;


  /**
   * The default constructor.
   */
  public JQueryDataTableParamModel()
  {
    this.ajaxData = new HashMap<String, Object>();
    this.performCount = true;
  }


  /**
   * The copy constructor.
   * 
   * @param that
   */
  public JQueryDataTableParamModel(JQueryDataTableParamModel that)
  {
    this.columnData = that.columnData;
    this.columnNames = that.columnNames;
    this.columnOrderables = that.columnOrderables;
    this.columnSearchable = that.columnSearchable;
    this.columnSearchRegex = that.columnSearchRegex;
    this.columnSearchValues = that.columnSearchValues;
    this.draw = that.draw;
    this.length = that.length;
    this.orderColumns = that.orderColumns;
    this.orderDirs = that.orderDirs;
    this.searchRegex = that.searchRegex;
    this.searchValue = that.searchValue;
    this.start = that.start;
    this.performCount = that.performCount;

    this.ajaxData = new HashMap<String, Object>();
  }


  /**
   * @return the ajaxData
   */
  public Map<String, Object> getAjaxData()
  {
    return ajaxData;
  }


  /**
   * Column's data source, as defined by columns.data option.
   * 
   * @return the columnData
   */
  public String[] getColumnData()
  {
    return columnData;
  }


  /**
   * Column names, as defined by columns.name option.
   * 
   * @return the columnNames
   */
  public String[] getColumnNames()
  {
    return columnNames;
  }


  /**
   * Flag to indicate if this column is orderable (true) or not (false).
   * 
   * @return the columnOrderables
   */
  public boolean[] getColumnOrderables()
  {
    return columnOrderables;
  }


  /**
   * Flag to indicate if this column is searchable (true) or not (false).
   * 
   * @return the columnSearchable
   */
  public boolean[] getColumnSearchable()
  {
    return columnSearchable;
  }


  /**
   * Flag to indicate if the search term for this column should be treated as 
   * regular expression (true) or not (false). As with global search, normally
   * server-side processing scripts will not perform regular expression 
   * searching for performance reasons on large data sets, but it is 
   * technically possible and at the discretion of your script.
   * 
   * @return the columnSearchRegex
   */
  public boolean[] getColumnSearchRegex()
  {
    return columnSearchRegex;
  }


  /**
   * Comma-separated search values to apply to this specific column.
   * 
   * @return the columnSearchValues
   */
  public String[] getColumnSearchValues()
  {
    return columnSearchValues;
  }


  /**
   * Draw counter. A draw is a page of data. This is used by DataTables to 
   * ensure that the Ajax returns from server-side processing requests are 
   * drawn in sequence by DataTables (Ajax requests are asynchronous and 
   * thus can return out of sequence). This is used as part of the draw 
   * return parameter.
   * 
   * @return the draw
   */
  public Integer getDraw()
  {
    return draw;
  }


  /**
   * Number of records that the table can display in the current draw. It is 
   * expected that the number of records returned will be equal to this number, 
   * unless the server has fewer records to return. Note that this can be -1 
   * to indicate that all records should be returned (although that negates 
   * any benefits of server-side processing!)
   * 
   * @return the length
   */
  public Integer getLength()
  {
    return length;
  }


  /**
   * Columns to which ordering should be applied. This is an index reference 
   * to the columns array of information that is also submitted to the server.
   * 
   * @return the orderColumns
   */
  public int[] getOrderColumns()
  {
    return orderColumns;
  }


  /**
   * Ordering direction for this column. It will be asc or desc to indicate 
   * ascending ordering or descending ordering, respectively.
   * 
   * @return the orderDirs
   */
  public boolean[] getOrderDirs()
  {
    return orderDirs;
  }


  /**
   * @return the searchCaseInsensitive
   */
  public Boolean getSearchCaseInsensitive()
  {
    return searchCaseInsensitive;
  }


  /**
   * true if the global filter should be treated as a regular expression for 
   * advanced searching, false otherwise. Note that normally server-side 
   * processing scripts will not perform regular expression searching for
   * performance reasons on large data sets, but it is technically possible
   * and at the discretion of your script.
   * 
   * @return the searchRegex
   */
  public Boolean getSearchRegex()
  {
    return searchRegex;
  }


  /**
   * Global search value. To be applied to all columns which have searchable 
   * as true.
   * 
   * @return the searchValue
   */
  public String getSearchValue()
  {
    return searchValue;
  }


  /**
   * @param data
   * @return
   */
  public String getSearchValue(
    String data)
  {
    String value = null;

    for (int x = 0; x < this.columnData.length; x++)
    {
      if (data.equals(this.columnData[x]))
      {
        if (this.columnSearchable[x])
        {
          if (this.searchValue != null && this.searchValue.length() > 0)
            value = this.searchValue;
          else if (this.columnSearchValues[x] != null && this.columnSearchValues[x].length() > 0)
            value = this.columnSearchValues[x];
        }
      }
    }

    return value;
  }


  /**
   * Paging first record indicator. This is the start point in the current 
   * data set (0 index based - i.e. 0 is the first record).
   * 
   * @return the start
   */
  public Integer getStart()
  {
    return start;
  }


  /**
   * @return the performCount
   */
  public boolean isPerformCount()
  {
    return performCount;
  }


  public void setAjaxData(
    String key,
    Object value)
  {
    this.ajaxData.put(key, value);
  }


  /**
   * @param columnData the columnData to set
   */
  public void setColumnData(
    String[] columnData)
  {
    this.columnData = columnData;
  }


  public void setColumnNames(
    String[] columnNames)
  {
    this.columnNames = columnNames;
  }


  /**
   * @param columnOrderables the columnOrderables to set
   */
  public void setColumnOrderables(
    boolean[] columnOrderables)
  {
    this.columnOrderables = columnOrderables;
  }


  /**
   * @param columnSearchable the columnSearchable to set
   */
  public void setColumnSearchable(
    boolean[] columnSearchable)
  {
    this.columnSearchable = columnSearchable;
  }


  /**
   * @param columnSearchRegex the columnSearchRegex to set
   */
  public void setColumnSearchRegex(
    boolean[] columnSearchRegex)
  {
    this.columnSearchRegex = columnSearchRegex;
  }


  /**
   * @param columnSearchValues the columnSearchValues to set
   */
  public void setColumnSearchValues(
    String[] columnSearchValues)
  {
    this.columnSearchValues = columnSearchValues;
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
   * @param length the length to set
   */
  public void setLength(
    Integer length)
  {
    this.length = length;
  }


  /**
   * @param orderColumns the orderColumns to set
   */
  public void setOrderColumns(
    int[] orderColumns)
  {
    this.orderColumns = orderColumns;
  }


  /**
   * @param orderDirs the orderDirs to set
   */
  public void setOrderDirs(
    boolean[] orderDirs)
  {
    this.orderDirs = orderDirs;
  }


  /**
   * @param performCount the performCount to set
   */
  public void setPerformCount(
    boolean performCount)
  {
    this.performCount = performCount;
  }


  /**
   * @param searchCaseInsensitive the searchCaseInsensitive to set
   */
  public void setSearchCaseInsensitive(
    Boolean searchCaseInsensitive)
  {
    this.searchCaseInsensitive = searchCaseInsensitive;
  }


  /**
   * @param searchRegex the searchRegex to set
   */
  public void setSearchRegex(
    Boolean searchRegex)
  {
    this.searchRegex = searchRegex;
  }


  /**
   * @param searchValue the searchValue to set
   */
  public void setSearchValue(
    String searchValue)
  {
    this.searchValue = searchValue;
  }


  /**
   * @param start the start to set
   */
  public void setStart(
    Integer start)
  {
    this.start = start;
  }


  @Override
  public String toString()
  {
    return "{draw: " + this.draw //
           + ", start: " + this.start //
           + ", length: " + this.length //
           + ", search[value]: " + this.searchValue //
           + ", search[regex]: " + this.searchRegex //
           + ", columnData: " + Arrays.toString(this.columnData) //
           + ", columnNames: " + Arrays.toString(this.columnNames) //
           + ", columnSearchable: " + Arrays.toString(this.columnSearchable) //
           + ", columnOrderables: " + Arrays.toString(this.columnOrderables) //
           + ", columnSearchValues: " + Arrays.toString(this.columnSearchValues) //
           + ", columnSearchRegex: " + Arrays.toString(this.columnSearchRegex) //
           + ", orders: " + Arrays.toString(this.orderColumns)//
           + ", orderDirs: " + Arrays.toString(this.orderDirs)//
           + "}";
  }
}
