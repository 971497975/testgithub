package tellhow.cavate.utils;

public class Page {
	private int pageRecord;// 每页记录条数
	private int currentPage;// 当前第几页
	private int totalRecord;// 总记录数
	private int startIndex;// 当前页起始记录索引
	private int totalPage;// 总页数
	private boolean hasPrePage;// 是否有上页
	private boolean hasNextPage;// 是否有下页

	public Page() {
	};

	// 使用构造方法替代了getter和setter方法
	public Page(int pageRecord, int currentPage, int totalRecord, int startIndex, int totalPage, boolean hasPrePage,boolean hasNextPage) {
		this.pageRecord = pageRecord;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.startIndex = startIndex;
		this.totalPage = totalPage;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}

	public int getPageRecord() {
		return pageRecord;
	}

	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
}
