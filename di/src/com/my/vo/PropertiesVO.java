package com.my.vo;
import java.util.Properties;

public class PropertiesVO {
	private Properties env;
	// Map의 하위 자료 구조 오직<"String","String">
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
