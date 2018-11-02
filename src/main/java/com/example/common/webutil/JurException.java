package com.example.common.webutil;

/**
 * 权限异常
 */
public class JurException extends RuntimeException {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 6806129545290130142L;
	
	private int type;
	

	/**
	 * @return 获得异常类型
	 */
	public int getType() {
		return type;
	}

	public JurException(int type) {
        super("当前账号无此权限：" + type);
        this.type = type;
    }

	public JurException(int type, String s) {
		super(s);
		this.type = type;
	}

}
