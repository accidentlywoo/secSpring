package com.my.vo;
import java.util.Properties;

public class PropertiesVO {
	private Properties env;
	// Map�� ���� �ڷ� ���� ����<"String","String">
	public PropertiesVO(Properties env) {
		this.env = env;
	}
	public Properties getEnv() {
		return env;
	}
	public void setEnv(Properties env) {
		this.env = env;
	}
	@Override
	public String toString() {
		return "PropertiesVO [env=" + env + "]";
	}
}
