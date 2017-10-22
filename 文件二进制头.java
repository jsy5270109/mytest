/**
	 * 文件的上传，需要在CustomerAction类中定义成员的属性，命名是有规则的！！
	 * private File upload;		// 表示要上传的文件
	 * private String uploadFileName;	表示是上传文件的名称（没有中文乱码）
	 * private String uploadContentType;	表示上传文件的MIME类型
	 * 提供set方法，拦截器就注入值了
	 */
	
	// 要上传的文件
	private File upload;
	// 文件的名称
	private String uploadFileName;
	// 文件的MIME的类型
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	/**
	 * 保存客户的方法
	 * @return
	 * @throws IOException 
	 * @throws FileUploadException 
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	public String save() throws IOException, FileUploadException{
		
		
		//HttpServletRequest request = ServletActionContext.getRequest();
		//  DiskFileItemFactory factory = new DiskFileItemFactory();
		// ServletFileUpload upload2 = new ServletFileUpload(factory);
		//List<FileItem> list = upload2.parseRequest(request);
		File f=upload;
		FileInputStream stream = new FileInputStream(upload);
		String typeByStream = getTypeByStream(stream);
		
	}
	 public static String bytesToHexString(byte[] src){     
	        StringBuilder strBuilder = new StringBuilder();     
	        if (src == null || src.length <= 0) {     
	            return null;     
	        }     
	        for (int i = 0; i < src.length; i++) {     
	            int v = src[i] & 0xFF;     
	            String strhs = Integer.toHexString(v);     
	            if (strhs.length() < 2) {     
	            	strBuilder.append(0);     
	            }     
	            strBuilder.append(strhs);     
	        }     
	        return strBuilder.toString();     
	    }
	 
	 /**
	  * 根据文件流读取图片文件真实类型
	  * @param is
	  * @return
	  */
	public static String getTypeByStream(FileInputStream is) {
		byte[] b = new byte[4];
		try {
			is.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String type = bytesToHexString(b).toUpperCase();
		if (type.contains("FFD8FF")) {
			return "jpg";
		} else if (type.contains("89504E47")) {
			return "png";
		} else if (type.contains("47494638")) {
			return "gif";
		} else if (type.contains("49492A00")) {
			return "tif";
		} else if (type.contains("424D")) {
			return "bmp";
		}
		return type;
	}
	
	/*public static void main(String args[]) {
//	     String src = "D:/workspace//8129.jpg";
//	     String src = "D:/workspace//temp/1.gif";
	     String src = "D:/003-004-join.jpg";
	     FileInputStream is = null;
		try {
			is = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
//	        byte[] b = new byte[4];  
//	        is.read(b, 0, b.length);  
//	        System.out.println(bytesToHexString(b));
	       
	        String type = getTypeByStream(is);
	        System.out.println(type);
	}*/