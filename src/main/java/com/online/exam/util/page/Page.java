package com.online.exam.util.page;


import java.util.ArrayList;
import java.util.List;


/**
 * @author cen
 * 分页对象
 * @since 0.1.0
 */
public class Page<T> {

	protected Integer pageNo = 1;//当前页数
	protected Integer pageSize = -1;//每页记录数
	protected Boolean autoCount = Boolean.TRUE;//最后一页
	protected List<String> orderList = new ArrayList<String>();
	protected List<String> ascOrderList = new ArrayList<String>();
	protected List<String> descOrderList = new ArrayList<String>();

	protected List<T> result = new ArrayList<T>();//返回的值
	protected Long totalCount = -1L;//总记录数

	protected Integer display; // 显示的页数
	protected List<Integer> pageNoList; // 要显示的页码List
	
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Page<T> setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Page<T> setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public Boolean getAutoCount() {
		return autoCount;
	}

	public Page<T> setAutoCount(Boolean autoCount) {
		this.autoCount = autoCount;
		return this;
	}

	public List<T> getResult() {
		return result;
	}

	public Page<T> setResult(List<T> result) {
		this.result = result;
		return this;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public Page<T> setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public Long getTotalPages() {
		if (totalCount < 0) {
			return -1L;
		}

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页
	 * @return
	 */
	public Boolean getHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 获取下一页的页码
	 * @return
	 */
	public Integer getNextPage() {
		if (getHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否具有上一页
	 * @return
	 */
	public Boolean getHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 获取上一页页码
	 * @return
	 */
	public Integer getPrePage() {
		if (getHasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}
	
	//
	public List<String> getOrderList() {
		return orderList;
	}
	
	public List<String> getAscOrderList() {
		return ascOrderList;
	}
	
	public List<String> getDescOrderList() {
		return descOrderList;
	}
	
	public Page<T> asc(String column) {
		orderList.add(column + " ASC");
		ascOrderList.add(column);
		return this;
	}
	
	public Page<T> desc(String column) {
		orderList.add(column + " DESC");
		descOrderList.add(column);
		return this;
	}

	public Integer getDisplay() {
		return null != display ? display : 11;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public List<Integer> getPageNoList() {
		if (null == this.pageNoList) {
			pageNoList = new ArrayList<Integer>();
			Long totalPage=this.getTotalPages();
			int display=this.getDisplay();
			if ( totalPage<display ) {
				// 如果最大页数小于要显示的页数，则显示全部页数
				int i = 1;
				while (i <= this.getTotalPages()) {
					pageNoList.add(i++);
				}
			} else {
				if (this.getPageNo() <= this.getDisplay() / 2) {
					int i = 1;
					while (i <= this.getDisplay()) {
						pageNoList.add(i++);
					}
				} else {
					int i = this.getPageNo()  + this.getDisplay() / 2 + 1;
					if (i <= this.getTotalPages()) {
						i = i - this.getDisplay();
					} else {
						i = this.getTotalPages().intValue() - this.getDisplay() + 1;
					}
					int j = 0;
					while (j < this.getDisplay()) {
						pageNoList.add(i + (j++));
					}
				}
			}
		}

		return pageNoList;
	}

	public void setPageNoList(List<Integer> pageNoList) {
		this.pageNoList = pageNoList;
	}

}
