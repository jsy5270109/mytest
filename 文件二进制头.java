/**
	 * �ļ����ϴ�����Ҫ��CustomerAction���ж����Ա�����ԣ��������й���ģ���
	 * private File upload;		// ��ʾҪ�ϴ����ļ�
	 * private String uploadFileName;	��ʾ���ϴ��ļ������ƣ�û���������룩
	 * private String uploadContentType;	��ʾ�ϴ��ļ���MIME����
	 * �ṩset��������������ע��ֵ��
	 */
	
	// Ҫ�ϴ����ļ�
	private File upload;
	// �ļ�������
	private String uploadFileName;
	// �ļ���MIME������
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
	 * ����ͻ��ķ���
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
	  * �����ļ�����ȡͼƬ�ļ���ʵ����
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