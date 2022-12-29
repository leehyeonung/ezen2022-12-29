package domain;

public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String reg_Date;
	private int read_count;
	private String image_file;
	
	public BoardVO() {}

	
	
	public BoardVO(int bno) {
		this.bno = bno;
	}


	//insert(title,writer,content)
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	//list(bno,title,writer,reg_date,read_count)
	public BoardVO(int bno, String title, String writer, String reg_Date, int read_count) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.reg_Date = reg_Date;
		this.read_count = read_count;
	}
	
	//detail(all)
	public BoardVO(int bno, String title, String writer, String content,
			String reg_Date, int read_count,String image_file) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.reg_Date = reg_Date;
		this.read_count = read_count;
		this.image_file=image_file;
	}
	
	//update(bno,title,content)
	public BoardVO(int bno, String title, String content) {	
		this.bno = bno;
		this.title = title;
		this.content = content;
	}

	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(String reg_Date) {
		this.reg_Date = reg_Date;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}



	public String getImage_file() {
		return image_file;
	}



	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}
	
	
	
}
